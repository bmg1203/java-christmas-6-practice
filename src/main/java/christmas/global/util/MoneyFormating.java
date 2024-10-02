package christmas.global.util;

public class MoneyFormating {
    public static String convertMoneyFormat(int price) {
        return String.format("%,d", price);
    }
}
