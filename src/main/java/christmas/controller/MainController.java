package christmas.controller;

import christmas.domain.Discount;
import christmas.domain.VisitDay;
import christmas.domain.Orders;
import christmas.service.DiscountService;

import static christmas.view.output.OutputMessage.INFORMATION_MESSAGE;
import static christmas.view.output.OutputView.printStaticMessage;

public class MainController {
    public static void start() {
        printStaticMessage(INFORMATION_MESSAGE);

        VisitDay visitDay = VisitDayController.getVisitDay();
        Orders orders = OrderController.makeOrders(visitDay);

    }
}
