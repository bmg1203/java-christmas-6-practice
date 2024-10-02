package christmas.service;

import christmas.domain.Order;
import christmas.domain.Orders;

import java.util.Arrays;
import java.util.List;

public class OrderService {
    public static Orders makeOrders(String input) {
        List<Order> orderList = makeOrderList(input);

        return Orders.createOrders(orderList);
    }

    private static List<Order> makeOrderList(String input) {
        List<String> parsedString = parsing(input);

        return parsedString.stream()
                .map(OrderService::split)
                .map(parts -> Order.createOrder(parts[0], Integer.parseInt(parts[1])))
                .toList();
    }

    private static List<String> parsing(String input) {
        return Arrays.stream(input.split(","))
                .toList();
    }

    private static String[] split(String input) {
        return input.split("-");
    }
}
