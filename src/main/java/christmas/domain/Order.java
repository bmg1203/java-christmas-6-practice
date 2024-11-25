package christmas.domain;

import christmas.constants.ErrorMessage;

public class Order {

    private static final int MIN_COUNT = 1;
    private final String name;
    private final int count;

    public Order(String name, int count, Menus menus) {
        validateCount(count);
        validateName(name, menus);
        this.name = name;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    private void validateCount(int count) {
        if (count < MIN_COUNT) {
            throw new IllegalArgumentException(ErrorMessage.MENU_INPUT_ERROR.getMessage());
        }
    }

    private void validateName(String name, Menus menus) {
        if (menus.getMenus().get(name) == null) {
            throw new IllegalArgumentException(ErrorMessage.MENU_INPUT_ERROR.getMessage());
        }
    }
}
