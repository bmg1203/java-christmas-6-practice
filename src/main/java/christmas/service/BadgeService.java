package christmas.service;

import christmas.domain.Discount;
import christmas.global.constants.Badge;

import static christmas.global.constants.Badge.*;

public class BadgeService {
    public static Badge getBadge(Discount discount) {
        int totalDiscount = discount.getTotalDiscount();

        return whichBadge(totalDiscount);
    }

    private static Badge whichBadge(int price) {
        if (price > SANTA.getPrice()) return SANTA;
        if (price > TREE.getPrice()) return TREE;
        if (price > STAR.getPrice()) return STAR;
        return NONE;
    }
}
