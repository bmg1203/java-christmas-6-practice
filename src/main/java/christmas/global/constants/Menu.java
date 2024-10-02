package christmas.global.constants;

import static christmas.global.constants.MenuType.*;

public enum Menu {
    // Appetizer
    Mushroom_Soup(APPETIZER, "양송이수프", 6000),
    Tapas(APPETIZER, "타파스", 5500),
    Caesar_Salad(APPETIZER, "시저샐러드", 8000),

    // Main
    T_bone_steak(MAIN, "티본스테이크", 55000),
    Barbecue_ribs(MAIN, "바비큐립", 54000),
    Seafood_pasta(MAIN, "해산물파스타", 35000),
    Christmas_pasta(MAIN, "크리스마스파스타", 25000),

    // Dessert
    Chocolate_cake(DESSERT, "초코케이크", 15000),
    Ice_cream(DESSERT, "아이스크림", 5000),

    // Drinks
    Zero_cola(DRINKS, "제로콜라", 3000),
    Red_wine(DRINKS, "레드와인", 60000),
    Champagne(DRINKS, "샴페인", 25000);

    private final MenuType type;
    private final String name;
    private final int price;

    Menu(MenuType type, String name, int price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public MenuType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public static Menu getMenuByName(String name) {
        for (Menu menu : Menu.values()) {
            if (menu.name.equals(name)) return menu;
        }
        return null;
    }

}
