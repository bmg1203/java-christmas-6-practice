package christmas.controller;

import christmas.domain.Gift;
import christmas.domain.Orders;
import christmas.service.GiftService;

import static christmas.view.output.OutputMessage.*;
import static christmas.view.output.OutputView.*;
import static java.lang.String.format;

public class GiftController {
    public static Gift getGift(Orders orders) {
        Gift gift = GiftService.makeGift(orders);

        printStaticMessage(GIFT_MENU_TITLE);

        if (isNone(gift)) {
            printStaticMessage(NONE_MESSAGE);
            printNewLine();
            return gift;
        }

        printDynamicMessage(format(QUANTITY_MESSAGE.getMessage(), gift.getMenu().getName(), gift.getQuantity()));
        printNewLine();
        return gift;
    }

    private static boolean isNone(Gift gift) {
        return gift.getQuantity() == 0;
    }

}
