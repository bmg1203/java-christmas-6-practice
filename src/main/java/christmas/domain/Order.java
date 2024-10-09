package christmas.domain;

import christmas.global.constants.menu.Menu;

public class Order {
    private final Menu menu;
    private final int quantity;

    public Order(Menu menu, int quantity) {
        this.menu = menu;
        this.quantity = quantity;
    }

    public static Order createOrder(Menu menu, int quantity) {
        return new Order(menu, quantity);
    }

    public int getQuantity() {
        return quantity;
    }

    public Menu getMenu() {
        return menu;
    }
}
