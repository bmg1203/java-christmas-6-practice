package christmas.enums.menu;

public enum DessertMenu implements MenuItem {
    CHOCOLATE_CAKE("초코케이크", 15_000), ICE_CREAM("아이스크림", 5_000);
    private final String name;
    private final Integer price;

    DessertMenu(String name, Integer price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String getName() {
        return name;
    }

    public Integer getAmount() {
        return price;
    }
}
