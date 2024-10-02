package christmas.service;

import christmas.domain.Gift;
import christmas.domain.Orders;
import christmas.global.constants.Menu;

import static christmas.global.constants.Config.*;

public class GiftService {
    public static Gift makeGift(Orders orders) {
        int preDiscountTotal = orders.getPreDiscountTotal();
        Menu gift = Menu.getMenuByName(GIFT_ITEM.getName());

        if (isReceivable(preDiscountTotal)) return Gift.createGift(gift, GIFT_QUANTITY);
        return Gift.createGift(gift, 0);
    }

    private static boolean isReceivable(int preDiscountTotal) {
        return preDiscountTotal >= GIFT_THRESHOLD;
    }
}
