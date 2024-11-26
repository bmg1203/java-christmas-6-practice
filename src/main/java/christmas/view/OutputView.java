package christmas.view;

import christmas.constants.GiftEvent;
import christmas.constants.OutputPrompts;
import christmas.domain.Badge;
import christmas.domain.Event;
import christmas.domain.Order;
import christmas.domain.Orders;
import christmas.domain.SalePrice;
import christmas.domain.TotalPrice;
import christmas.domain.Visit;
import christmas.utils.Parser;

public class OutputView {

    private static final String NO_BENEFIT = "없음";

    public void benefitHeaderOutput(Visit visit) {
        System.out.printf(OutputPrompts.RESULT_HEADER_OUTPUT.getPrompt(), visit.getDay());
        System.out.println();
    }

    public void orderMenuOutput(Orders orders) {
        System.out.println(OutputPrompts.ORDER_MENU_HEADER_OUTPUT.getPrompt());
        for (Order order : orders.getOrders()) {
            System.out.printf(OutputPrompts.ORDER_MENU_OUTPUT.getPrompt(), order.getName(), order.getCount());
        }
        System.out.println();
    }

    public void totalPriceOutput(TotalPrice totalPrice) {
        System.out.printf(OutputPrompts.TOTAL_PRICE_OUTPUT.getPrompt(), Parser.intToMoneyFormat(totalPrice.getTotalPrice()));
    }

    public void giftEventOutput(Event event) {
        if (event.isGiftEvent()) {
            System.out.printf(OutputPrompts.GIFT_MENU_OUTPUT.getPrompt(), GiftEvent.GIFT_MENU_NAME.getName());
            System.out.println();
            return;
        }
        System.out.println(NO_BENEFIT);
        System.out.println();
    }

    public void allBenefitOutput(Event event, SalePrice salePrice) {
        System.out.print(OutputPrompts.BENEFITS_OUTPUT.getPrompt());
        if (!event.isChristmasDaySale() && !event.isWeekDaySale() && !event.isWeekendSale()
            && !event.isSpecialSale() && !event.isGiftEvent()) {
            System.out.println(NO_BENEFIT);
            System.out.println();
        }
        eventOutput(event, salePrice);
    }

    private void eventOutput(Event event, SalePrice salePrice) {
        if (event.isChristmasDaySale())
            System.out.printf(OutputPrompts.CHRISTMAS_SALE_PRICE_OUTPUT.getPrompt(), Parser.intToMoneyFormat(salePrice.getChristmasSalePrice()));
        if (event.isWeekDaySale())
            System.out.printf(OutputPrompts.WEEKDAY_SALE_PRICE_OUTPUT.getPrompt(), Parser.intToMoneyFormat(salePrice.getWeekDaySalePrice()));
        if (event.isWeekendSale())
            System.out.printf(OutputPrompts.WEEKEND_SALE_PRICE_OUTPUT.getPrompt(), Parser.intToMoneyFormat(salePrice.getWeekendSalePrice()));
        if (event.isSpecialSale())
            System.out.printf(OutputPrompts.SPECIAL_SALE_PRICE_OUTPUT.getPrompt(), Parser.intToMoneyFormat(salePrice.getSpecialSalePrice()));
        if (event.isGiftEvent())
            System.out.printf(OutputPrompts.GIFT_EVENT_PRICE_OUTPUT.getPrompt(), Parser.intToMoneyFormat(salePrice.getGiftEventPrice()));
    }

    public void totalSalePriceOutput(SalePrice salePrice) {
        System.out.printf(OutputPrompts.TOTAL_BENEFITS_PRICE_OUTPUT.getPrompt(), Parser.intToMoneyFormat(salePrice.calculateTotalSalePrice()));
    }

    public void finalPriceOutput(TotalPrice totalPrice, SalePrice salePrice) {
        System.out.printf(OutputPrompts.FINAL_PAYMENT_PRICE_OUTPUT.getPrompt(), Parser.intToMoneyFormat(totalPrice.finalPrice(salePrice)));
    }

    public void badgeOutput(Badge badge) {
        System.out.printf(OutputPrompts.BADGE_OUTPUT.getPrompt(), badge.getBadge());
    }
}
