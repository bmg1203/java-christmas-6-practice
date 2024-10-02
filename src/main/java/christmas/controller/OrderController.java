package christmas.controller;

import christmas.domain.Orders;
import christmas.service.OrderService;
import christmas.view.input.InputView;

import static christmas.view.output.OutputMessage.ASK_MENU_AND_COUNT_MESSAGE;
import static christmas.view.output.OutputView.printStaticMessage;

public class OrderController {
    public static Orders getOrders() {
        printStaticMessage(ASK_MENU_AND_COUNT_MESSAGE);

        String input = InputView.input();

        return OrderService.makeOrders(input);
    }
}
