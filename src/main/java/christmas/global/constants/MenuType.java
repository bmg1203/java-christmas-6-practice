package christmas.global.constants;

public enum MenuType {
    APPETIZER("Appetizer"),
    MAIN("Main"),
    DESSERT("Dessert"),
    DRINKS("Drinks");

    MenuType(String type) {
        this.type = type;
    }

    private final String type;
}
