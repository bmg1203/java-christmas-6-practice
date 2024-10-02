package christmas.service;

import christmas.domain.Gift;
import christmas.domain.Orders;

import static christmas.global.constants.Config.*;

public class GiftService {
    public static Gift makeGift(Orders orders) {
        int preDiscountTotal = orders.getPreDiscountTotal();

        if (isReceivable(preDiscountTotal)) return Gift.createGift(GIFT_ITEM.getName(), GIFT_QUANTITY);
        return Gift.createGift(GIFT_ITEM.getName(), 0);
    }

    private static boolean isReceivable(int preDiscountTotal) {
        return preDiscountTotal >= GIFT_THRESHOLD;
    }
}
