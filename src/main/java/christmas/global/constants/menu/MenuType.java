package christmas.global.constants.menu;

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
