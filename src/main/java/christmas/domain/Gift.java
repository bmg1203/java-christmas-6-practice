package christmas.domain;

public class Gift {
    private String name;
    private int quantity;

    private Gift(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public static Gift createGift(String name, int quantity) {
        return new Gift(name, quantity);
    }
}
