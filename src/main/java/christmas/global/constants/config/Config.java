package christmas.global.constants.config;

import christmas.global.constants.menu.Menu;

import static christmas.global.constants.menu.Menu.Champagne;

public class Config {
    // System Configuration
    public static final String MENU_DELIMITER = ",";
    public static final String QUANTITY_SEPARATOR = "-";

    // Event Configuration
    public static final int STARTING_AMOUNT = 1000;
    public static final int WEEKDAY_DESSERT_DISCOUNT = 2023;
    public static final int WEEKEND_MAIN_MENU_DISCOUNT = 2023;
    public static final int SPECIAL_EVENT_DISCOUNT = 1000;
    public static final int GIFT_THRESHOLD = 120000;
    public static final int GIFT_QUANTITY = 1;
    public static final Menu GIFT_ITEM = Champagne;
}
