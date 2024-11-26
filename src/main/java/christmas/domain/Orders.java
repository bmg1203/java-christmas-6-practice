package christmas.domain;

import christmas.constants.ErrorMessage;
import java.util.ArrayList;
import java.util.List;

public class Orders {

    private static final String DRINK_TYPE = "음료";
    private static final int TYPE_COUNT = 1;
    private static final int MAX_ORDER_COUNT = 20;
    private final List<Order> orders;

    public Orders(List<Order> orders, Menus menus) {
        validateDuplicate(orders);
        validateMaxCount(orders);
        validateType(orders, menus);
        this.orders = orders;
    }

    private void validateDuplicate(List<Order> orders) {
        List<Order> duplicate = new ArrayList<>();
        for (Order order : orders) {
            if (duplicate.contains(order)) {
                throw new IllegalArgumentException(ErrorMessage.MENU_INPUT_ERROR.getMessage());
            }
            duplicate.add(order);
        }
    }

    private void validateMaxCount(List<Order> orders) {
        int sum = 0;
        for (Order order : orders) {
            sum += order.getCount();
        }

        if (sum > MAX_ORDER_COUNT) {
            throw new IllegalArgumentException(ErrorMessage.MENU_INPUT_ERROR.getMessage());
        }
    }

    private void validateType(List<Order> orders, Menus menus) {
        List<String> types = new ArrayList<>();
        for (Order order : orders) {
            Menu menu = menus.getMenus().get(order.getName());
            types.add(menu.getType());
        }
        if (types.contains(DRINK_TYPE) && types.size() == TYPE_COUNT) {
            throw new IllegalArgumentException(ErrorMessage.MENU_INPUT_ERROR.getMessage());
        }
    }
}
