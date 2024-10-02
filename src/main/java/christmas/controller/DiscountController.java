package christmas.controller;

import christmas.domain.Discount;
import christmas.domain.Gift;
import christmas.domain.Orders;
import christmas.domain.VisitDay;
import christmas.global.util.MoneyFormating;
import christmas.service.DiscountService;

import java.util.List;

import static christmas.view.output.OutputMessage.*;
import static christmas.view.output.OutputView.*;
import static java.lang.String.format;

public class DiscountController {
    public static Discount printDiscount(VisitDay visitDay, Orders orders, Gift gift) {
        Discount discount = DiscountService.getDiscount(visitDay, orders, gift);
        List<String> eventMenu = DiscountService.makeEventList(discount, visitDay, gift);
        int totalDiscount = discount.getTotalDiscount();
        int finalPayment = orders.getPreDiscountTotal() - discount.getTotalDiscount() + discount.getGiftDiscount();

        printEventList(eventMenu);
        printTotalDiscount(totalDiscount);
        printFinalPayment(finalPayment);

        return discount;
    }

    private static void printEventList(List<String> eventMenu) {
        printStaticMessage(EVENT_LIST_TITLE);
        if(eventMenu.isEmpty()) eventMenu.add(NONE_MESSAGE.getMessage());
        printStringList(eventMenu);
        printNewLine();
    }

    private static void printTotalDiscount(int totalDiscount) {
        printStaticMessage(TOTAL_PRICE_TITLE);

        if(totalDiscount == 0) {
            printDynamicMessage(format(ORDER_PRICE_MESSAGE.getMessage(), MoneyFormating.convertMoneyFormat(totalDiscount)));
            printNewLine();
            return;
        }

        printDynamicMessage(format(DISCOUNT_PRICE_MESSAGE.getMessage(), MoneyFormating.convertMoneyFormat(totalDiscount)));
        printNewLine();
    }

    private static void printFinalPayment(int finalPayment) {
        printStaticMessage(FINAL_PAYMENT_TITLE);
        printDynamicMessage(format(ORDER_PRICE_MESSAGE.getMessage(), MoneyFormating.convertMoneyFormat(finalPayment)));
        printNewLine();
    }
}
