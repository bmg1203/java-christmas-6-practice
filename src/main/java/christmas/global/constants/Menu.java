package christmas.global.constants;

public enum Menu {
    // Appetizer
    Mushroom_Soup("Appetizer", "양송이수프", 6000),
    Tapas("Appetizer", "타파스", 5500),
    Caesar_Salad("Appetizer", "시저샐러드", 8000),

    // Main
    T_bone_steak("Main", "티본스테이크", 55000),
    Barbecue_ribs("Main", "바비큐립", 54000),
    Seafood_pasta("Main", "해산물파스타", 35000),
    Christmas_pasta("Main", "크리스마스파스타", 25000),

    // Dessert
    Chocolate_cake("Dessert", "초코케이크", 15000),
    Ice_cream("Dessert", "아이스크림", 5000),

    // Drinks
    Zero_cola("Drinks", "제로콜라", 3000),
    Red_wine("Drinks", "레드와인", 60000),
    Champagne("Drinks", "샴페인", 25000);

    private final String type;
    private final String name;
    private final int price;

    Menu(String type, String name, int price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public static Menu getMenuByName(String name) {
        for (Menu menu : Menu.values()) {
            if (menu.name.equals(name)) return menu;
        }
        return null;
    }

}
