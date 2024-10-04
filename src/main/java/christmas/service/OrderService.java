package christmas.service;

import christmas.domain.Order;
import christmas.domain.Orders;
import christmas.global.constants.menu.Menu;
import christmas.global.constants.menu.MenuType;
import christmas.global.exception.InputException;

import java.util.Arrays;
import java.util.List;

import static christmas.global.constants.config.Config.*;
import static christmas.global.exception.ErrorMessage.INVALID_MENU_ERROR;
import static christmas.global.exception.ErrorMessage.INVALID_ORDER_ERROR;
import static christmas.view.output.OutputMessage.QUANTITY_MESSAGE;
import static java.lang.String.format;

public class OrderService {
    public static List<String> makeOrderFormat(List<Order> orderList) {
        return orderList.stream()
                .map(order -> {
                    Menu menu = order.getMenu();
                    String dishName = menu.getName();
                    int quantity = order.getQuantity();

                    return format(QUANTITY_MESSAGE.getMessage(), dishName, quantity);
                })
                .toList();
    }

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

    private static List<Order> makeOrderList(String input) {
        if(!isValidFormat(input)) throw new InputException(INVALID_MENU_ERROR);

        List<String> parsedString = parsing(input);

        List<Order> orderList = parsedString.stream()
                .map(OrderService::split)
                .map(parts -> {
                    String DishName = parts[0];
                    int quantity = Integer.parseInt(parts[1]);

                    if(!isValidDishName(DishName)) throw new InputException(INVALID_MENU_ERROR);

                    return Order.createOrder(Menu.getMenuByName(DishName), quantity);
                })
                .toList();

        if(isOnlyDrinks(orderList)) throw new InputException(INVALID_ORDER_ERROR);

        return orderList;
    }

    private static boolean isOnlyDrinks(List<Order> orderList) {
        return orderList.stream()
                .map(Order::getMenu)
                .map(Menu::getType)
                .allMatch(menuType -> menuType == MenuType.DRINKS);
    }

    private static boolean isValidDishName(String dishName) {
        return Menu.getMenuByName(dishName) != null;
    }

    private static List<String> parsing(String input) {
        return Arrays.stream(input.split(MENU_DELIMITER))
                .toList();
    }

    private static String[] split(String input) {
        return input.split(QUANTITY_SEPARATOR);
    }

    private static boolean isValidFormat(String input) {
        return input.matches("([가-힣]+-[1-9]\\d*)(,[가-힣]+-[1-9]\\d*)*");
    }
}
