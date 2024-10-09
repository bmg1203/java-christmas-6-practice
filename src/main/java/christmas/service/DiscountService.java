package christmas.service;

import christmas.domain.*;
import christmas.global.constants.menu.Menu;
import christmas.global.util.MoneyFormating;

import java.util.ArrayList;
import java.util.List;

import static christmas.global.constants.config.Config.*;
import static christmas.global.constants.event.EventDays.*;
import static christmas.global.constants.event.EventType.*;
import static christmas.global.constants.menu.MenuType.DESSERT;
import static christmas.global.constants.menu.MenuType.MAIN;
import static christmas.view.output.OutputMessage.EVENT_LIST_MESSAGE;
import static java.lang.String.format;

public class DiscountService {
    public static Discount getDiscount(VisitDay visitDay, Orders orders, Gift gift) {
        if (!isBenefitAvailable(orders)) return Discount.createDiscount(0,0,0,0,0,0);

        int day = visitDay.getVisitDay();
        List<Order> orderList = orders.getOrderList();

        int christmasDiscount = getChristmasDiscount(day);
        int weekdayDiscount = getWeekdayDiscount(day, orderList);
        int weekendDiscount = getWeekendDiscount(day, orderList);
        int specialDiscount = getSpecialDiscount(day);
        int giftDiscount = getGiftDiscount(gift);
        int totalDiscount = christmasDiscount + weekdayDiscount + weekendDiscount + specialDiscount + giftDiscount;

        return Discount.createDiscount(christmasDiscount, weekdayDiscount, weekendDiscount, specialDiscount, giftDiscount, totalDiscount);
    }

    public static List<String> makeEventList(Discount discount, VisitDay visitDay, Gift gift) {
        int day = visitDay.getVisitDay();
        List<String> eventList = new ArrayList<>();

        if (isChristmas(day) && discount.getChristmasDiscount() > 0) eventList.add(format(EVENT_LIST_MESSAGE.getMessage(), CHRISTMAS.getType(), MoneyFormating.convertMoneyFormat(discount.getChristmasDiscount())));
        if (isWeekday(day) && discount.getWeekdayDiscount() > 0) eventList.add(format(EVENT_LIST_MESSAGE.getMessage(), WEEKDAY.getType(), MoneyFormating.convertMoneyFormat(discount.getWeekdayDiscount())));
        if (isWeekend(day) && discount.getWeekendDiscount() > 0) eventList.add(format(EVENT_LIST_MESSAGE.getMessage(), WEEKDAY.getType(), MoneyFormating.convertMoneyFormat(discount.getWeekendDiscount())));
        if (isSpecialEvent(day)) eventList.add(format(EVENT_LIST_MESSAGE.getMessage(), SPECIAL.getType(), MoneyFormating.convertMoneyFormat(discount.getSpecialDiscount())));
        if (isGiftEvent(gift)) eventList.add(format(EVENT_LIST_MESSAGE.getMessage(), GIFT.getType(), MoneyFormating.convertMoneyFormat(discount.getGiftDiscount())));

        return eventList;
    }

    private static boolean isBenefitAvailable(Orders orders) {
        return orders.getPreDiscountTotal() >= 10000;
    }

    private static int getMainCount(List<Order> orderList) {
        return orderList.stream()
                .mapToInt(order -> {
                    Menu menu = order.getMenu();
                    int quantity = order.getQuantity();

                    if (menu.getType() == DESSERT) return quantity;
                    return 0;
                })
                .sum();
    }

    private static int getDessertCount(List<Order> orderList) {
        return orderList.stream()
                .mapToInt(order -> {
                    Menu menu = order.getMenu();
                    int quantity = order.getQuantity();

                    if (menu.getType() == MAIN) return quantity;
                    return 0;
                })
                .sum();

    }

    private static int getChristmasDiscount(int day) {
        if(isChristmas(day)) return STARTING_AMOUNT + (day - 1) * 100;
        return 0;
    }

    private static int getWeekdayDiscount(int day, List<Order> orderList) {
        if(isWeekday(day)) {
            return getDessertCount(orderList) * WEEKDAY_DESSERT_DISCOUNT;
        }
        return 0;
    }

    private static int getWeekendDiscount(int day, List<Order> orderList) {
        if(isWeekend(day)) {
            return getMainCount(orderList) * WEEKEND_MAIN_MENU_DISCOUNT;
        }
        return 0;
    }

    private static int getSpecialDiscount(int day) {
        if(isSpecialEvent(day)) return SPECIAL_EVENT_DISCOUNT;
        return 0;
    }

    private static int getGiftDiscount(Gift gift) {
        Menu menu = gift.getMenu();
        int price = menu.getPrice();
        int quantity = gift.getQuantity();

        return price * quantity;
    }

    private static boolean isChristmas(int day) {
        List<Integer> eventDays = CHRISTMAS_D_DAY.getDay();

        return eventDays.contains(day);
    }

    private static boolean isWeekday(int day) {
        List<Integer> eventDays = WEEKDAYS.getDay();

        return eventDays.contains(day);
    }

    private static boolean isWeekend(int day) {
        List<Integer> eventDays = WEEKENDS.getDay();

        return eventDays.contains(day);
    }

    private static boolean isSpecialEvent(int day) {
        List<Integer> eventDays = SPECIAL_DAYS.getDay();

        return eventDays.contains(day);
    }

    private static boolean isGiftEvent(Gift gift) {
        return gift.getQuantity() != 0;
    }
}
