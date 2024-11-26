package christmas.domain;

import christmas.constants.GiftEvent;

public class SalePrice {

    private static final int CHRISTMAS_SALE_PRICE_STANDARD = 1000;
    private static final int CHRISTMAS_SALE_PRICE_INCREASE = 100;
    private static final String WEEKDAY_SALE_TYPE = "디저트";
    private static final String WEEKEND_SALE_TYPE = "메인";
    private static final int SALE_PRICE = 2023;
    private static final int SPECIAL_SALE_PRICE = 1000;
    private final int christmasSalePrice;
    private final int weekDaySalePrice;
    private final int weekendSalePrice;
    private final int specialSalePrice;
    private final int giftEventPrice;

    public SalePrice(Menus menus, Event event, Visit visit, Orders orders) {
        this.christmasSalePrice = calculateChristmasSalePrice(event, visit);
        this.weekDaySalePrice = calculateWeekDaySalePrice(event, menus, orders);
        this.weekendSalePrice = calculateWeekendSalePrice(event, menus, orders);
        this.specialSalePrice = calculateSpecialSalePrice(event);
        this.giftEventPrice = calculateGiftEventPrice(event, menus);
    }

    public int getChristmasSalePrice() {
        return christmasSalePrice;
    }

    public int getWeekDaySalePrice() {
        return weekDaySalePrice;
    }

    public int getWeekendSalePrice() {
        return weekendSalePrice;
    }

    public int getSpecialSalePrice() {
        return specialSalePrice;
    }

    public int getGiftEventPrice() {
        return giftEventPrice;
    }

    private int calculateChristmasSalePrice(Event event, Visit visit) {
        if (event.isChristmasDaySale()) {
            return CHRISTMAS_SALE_PRICE_STANDARD + CHRISTMAS_SALE_PRICE_INCREASE * (visit.getDay() - 1);
        }
        return 0;
    }

    private int calculateWeekDaySalePrice(Event event, Menus menus, Orders orders) {
        if (event.isWeekDaySale()) {
            int count = getDessertCount(menus, orders);
            return count * SALE_PRICE;
        }
        return 0;
    }

    private static int getDessertCount(Menus menus, Orders orders) {
        int count = 0;
        for (Order order : orders.getOrders()) {
            Menu menu = menus.getMenus().get(order.getName());
            if (menu.getType().equals(WEEKDAY_SALE_TYPE)) {
                count += order.getCount();
            }
        }
        return count;
    }

    private int calculateWeekendSalePrice(Event event, Menus menus, Orders orders) {
        if (event.isWeekendSale()) {
            int count = getMainCount(menus, orders);
            return count * SALE_PRICE;
        }
        return 0;
    }

    private static int getMainCount(Menus menus, Orders orders) {
        int count = 0;
        for (Order order : orders.getOrders()) {
            Menu menu = menus.getMenus().get(order.getName());
            if (menu.getType().equals(WEEKEND_SALE_TYPE)) {
                count += order.getCount();
            }
        }
        return count;
    }

    private int calculateSpecialSalePrice(Event event) {
        if (event.isSpecialSale()) {
            return SPECIAL_SALE_PRICE;
        }
        return 0;
    }

    private int calculateGiftEventPrice(Event event, Menus menus) {
        if (event.isGiftEvent()) {
            Menu Champagne = menus.getMenus().get(GiftEvent.GIFT_MENU_NAME.getName());
            return Champagne.getPrice();
        }
        return 0;
    }

    public int calculateTotalSalePrice() {
        int sum = 0;
        sum += christmasSalePrice;
        sum += weekDaySalePrice;
        sum += weekendSalePrice;
        sum += specialSalePrice;
        sum += giftEventPrice;
        return sum;
    }
}
