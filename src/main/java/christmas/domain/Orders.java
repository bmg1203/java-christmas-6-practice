package christmas.domain;

import java.util.List;

public class Orders {
    private List<Order> orders;
    private int appetizerCount;
    private int mainCount;
    private int dessertCount;
    private int drinksCount;

    private Orders(List<Order> orders, int appetizerCount, int mainCount, int dessertCount, int drinksCount) {
        this.orders = orders;
        this.appetizerCount = appetizerCount;
        this.mainCount = mainCount;
        this.dessertCount = dessertCount;
        this.drinksCount = drinksCount;
    }

    public static Orders createOrders(List<Order> orders, int appetizerCount, int mainCount, int dessertCount, int drinksCount) {
        return new Orders(orders, appetizerCount, mainCount, dessertCount, drinksCount);
    }

    public int getDrinksCount() {
        return drinksCount;
    }

    public int getDessertCount() {
        return dessertCount;
    }

    public int getMainCount() {
        return mainCount;
    }

    public int getAppetizerCount() {
        return appetizerCount;
    }

    public List<Order> getOrders() {
        return orders;
    }
}
