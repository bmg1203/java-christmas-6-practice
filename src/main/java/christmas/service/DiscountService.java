package christmas.service;

import christmas.domain.*;

import java.util.List;

import static christmas.global.constants.Config.*;
import static christmas.global.constants.EventDays.*;
import static christmas.global.constants.EventDays.SPECIAL;

public class DiscountService {
    public static Discount getDiscount(VisitDay visitDay, Orders orders) {
        int day = visitDay.getVisitDay();
        List<Order> orderList = orders.getOrderList();

        int christmasDiscount = getChristmasDiscount(day);
        int weekdayDiscount = getWeekdayDiscount(day, orderList);
        int weekendDiscount = getWeekendDiscount(day, orderList);
        int specialDiscount = getSpecialDiscount(day);

        return Discount.createDiscount(christmasDiscount, weekdayDiscount, weekendDiscount, specialDiscount);

    }

    private static int getMainCount(List<Order> orderList) {
        return (int) orderList.stream()
                .map(Order::getMenu)
                .filter(menu -> "Main".equals(menu.getType()))
                .count();
    }

    private static int getDessertCount(List<Order> orderList) {
        return (int) orderList.stream()
                .map(Order::getMenu)
                .filter(menu -> "Dessert".equals(menu.getType()))
                .count();
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
        List<Integer> eventDays = SPECIAL.getDay();

        return eventDays.contains(day);
    }
}
