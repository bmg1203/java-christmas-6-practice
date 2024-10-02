package christmas.domain;

public class Order {
    private final String dishName;
    private final int quantity;

    public Order(String dishName, int quantity) {
        this.dishName = dishName;
        this.quantity = quantity;
    }

    public static Order createOrder(String dishName, int quantity) {
        return new Order(dishName, quantity);
    }
}
