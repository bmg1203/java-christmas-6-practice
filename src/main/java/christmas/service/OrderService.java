package christmas.service;

import christmas.domain.Order;
import christmas.domain.Orders;
import christmas.global.constants.Menu;

import java.util.Arrays;
import java.util.List;

import static christmas.global.constants.Config.*;

public class OrderService {
    public static Orders makeOrders(String input) {
        List<Order> orderList = makeOrderList(input);

        return Orders.createOrders(orderList);
    }

    private static List<Order> makeOrderList(String input) {
        List<String> parsedString = parsing(input);

        return parsedString.stream()
                .map(OrderService::split)
                .map(parts -> Order.createOrder(Menu.getMenuByName(parts[0]), Integer.parseInt(parts[1])))
                .toList();
    }

    private static List<String> parsing(String input) {
        return Arrays.stream(input.split(MENU_DELIMITER))
                .toList();
    }

    private static String[] split(String input) {
        return input.split(QUANTITY_SEPARATOR);
    }
}
