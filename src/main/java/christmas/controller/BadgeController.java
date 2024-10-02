package christmas.controller;

import christmas.domain.Discount;
import christmas.global.constants.Badge;
import christmas.service.BadgeService;

import static christmas.view.output.OutputMessage.BADGE_TITLE;
import static christmas.view.output.OutputView.printBadge;
import static christmas.view.output.OutputView.printStaticMessage;

public class BadgeController {
    public static void printBadgeName(Discount discount) {
        Badge badge = BadgeService.getBadge(discount);

        printStaticMessage(BADGE_TITLE);
        printBadge(badge);
    }
}
