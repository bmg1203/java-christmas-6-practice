package christmas.controller;

import christmas.domain.Order;
import christmas.domain.Orders;
import christmas.domain.VisitDay;
import christmas.global.handler.ExceptionHandler;
import christmas.global.util.MoneyFormating;
import christmas.service.OrderService;
import christmas.view.input.InputView;

import java.util.List;

import static christmas.view.output.OutputMessage.*;
import static christmas.view.output.OutputView.*;
import static java.lang.String.format;

public class OrderController {
    public static Orders printOrders(VisitDay visitDay) {
        Orders orders = makeOrders();

        List<String> orderMenu = OrderService.makeOrderFormat(orders.getOrderList());
        String preDiscountTotal = MoneyFormating.convertMoneyFormat(orders.getPreDiscountTotal());

        printDynamicMessage(format(EVENT_PREVIEW_MESSAGE.getMessage(), visitDay.getVisitDay()));
        printNewLine();

        printStaticMessage(ORDER_MENU_TITLE);
        printStringList(orderMenu);
        printNewLine();

        printStaticMessage(PRE_DISCOUNT_TOTAL_TITLE);
        printDynamicMessage(format(ORDER_PRICE_MESSAGE.getMessage(), preDiscountTotal));
        printNewLine();

        return orders;
    }

    private static Orders makeOrders() {
        return ExceptionHandler.execute(OrderController::requestOrders);
    }

    private static Orders requestOrders() {
        printStaticMessage(ASK_MENU_AND_COUNT_MESSAGE);

        String input = InputView.input();

        return OrderService.getOrders(input);
    }
}
