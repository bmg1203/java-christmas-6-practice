package christmas.domain;

import christmas.constants.ErrorMessage;
import java.util.ArrayList;
import java.util.List;

public class Orders {

    private static final int MAX_ORDER_COUNT = 20;
    private final List<Order> orders;

    public Orders(List<Order> orders) {
        validateDuplicate(orders);
        validateMaxCount(orders);
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
}
