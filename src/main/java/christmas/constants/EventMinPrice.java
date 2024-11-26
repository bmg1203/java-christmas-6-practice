package christmas.constants;

public enum EventMinPrice {

    EVENT_MIN_PRICE(10000);

    private final int price;

    EventMinPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
