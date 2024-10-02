package christmas.service;

import christmas.domain.Order;
import christmas.domain.Orders;
import christmas.global.constants.menu.Menu;

import java.util.Arrays;
import java.util.List;

import static christmas.global.constants.config.Config.*;
import static christmas.view.output.OutputMessage.QUANTITY_MESSAGE;
import static java.lang.String.format;

public class OrderService {
    public static Orders getOrders(String input) {
        List<Order> orderList = makeOrderList(input);
        int preDiscountTotal = calculatePreDisCountTotal(orderList);

        return Orders.createOrders(orderList, preDiscountTotal);
    }

    private static int calculatePreDisCountTotal(List<Order> orderList) {
        return orderList.stream()
                .map(order -> {
                    Menu menu = order.getMenu();
                    int price = menu.getPrice();
                    int quantity = order.getQuantity();

                    return price * quantity;
                })
                .reduce(0, Integer::sum);
    }

    public static List<String> makeOrderMenu(List<Order> orderList) {
        return orderList.stream()
                .map(order -> {
                    Menu menu = order.getMenu();
                    String dishName = menu.getName();
                    int quantity = order.getQuantity();

                    return format(QUANTITY_MESSAGE.getMessage(), dishName, quantity);
                })
                .toList();
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
