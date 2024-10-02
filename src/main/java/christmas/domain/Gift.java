package christmas.domain;

import christmas.global.constants.menu.Menu;

public class Gift {
    private Menu menu;
    private int quantity;

    private Gift(Menu menu, int quantity) {
        this.menu = menu;
        this.quantity = quantity;
    }

    public Menu getMenu() {
        return menu;
    }

    public int getQuantity() {
        return quantity;
    }

    public static Gift createGift(Menu menu, int quantity) {
        return new Gift(menu, quantity);
    }
}
