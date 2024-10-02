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

        int appetizerCount = getAppetizerCount(orderList);
        int mainCount = getMainCount(orderList);
        int dessertCount = getDessertCount(orderList);
        int drinksCount = getDrinksCount(orderList);

        return Orders.createOrders(orderList, appetizerCount, mainCount, dessertCount, drinksCount);
    }

    private static int getAppetizerCount(List<Order> orderList) {
        return (int) orderList.stream()
                .map(Order::getMenu)
                .filter(menu -> "Appetizer".equals(menu.getType()))
                .count();

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

    private static int getDrinksCount(List<Order> orderList) {
        return (int) orderList.stream()
                .map(Order::getMenu)
                .filter(menu -> "Drinks".equals(menu.getType()))
                .count();
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
