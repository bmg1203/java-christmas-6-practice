package christmas.controller;

import christmas.domain.Discount;
import christmas.domain.Gift;
import christmas.domain.VisitDay;
import christmas.domain.Orders;

import static christmas.view.output.OutputMessage.INFORMATION_MESSAGE;
import static christmas.view.output.OutputView.printStaticMessage;

public class MainController {
    public static void start() {
        printStaticMessage(INFORMATION_MESSAGE);

        VisitDay visitDay = VisitDayController.requestVisitDay();
        Orders orders = OrderController.printOrders(visitDay);
        Gift gift = GiftController.getGift(orders);
        Discount discount = DiscountController.printDiscount(visitDay, orders, gift);
        BadgeController.printBadgeName(discount);
    }
}
