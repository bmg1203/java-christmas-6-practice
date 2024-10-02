package christmas.controller;

import christmas.domain.VisitDay;
import christmas.domain.Orders;

import static christmas.view.output.OutputMessage.INFORMATION_MESSAGE;
import static christmas.view.output.OutputView.printStaticMessage;

public class MainController {
    public static void start() {
        printStaticMessage(INFORMATION_MESSAGE);

        VisitDay day = DayController.getVisitDay();
        Orders orders = OrderController.getOrders();
    }
}
