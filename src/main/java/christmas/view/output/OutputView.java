package christmas.view.output;

import christmas.global.constants.badge.Badge;

import java.util.List;

public class OutputView {
    public static void printStaticMessage(OutputMessage message) {
        System.out.println(message.getMessage());
    }

    public static void printDynamicMessage(String message) {
        System.out.println(message);
    }

    public static void printNewLine() {
        System.out.println();
    }

    public static void printStringList(List<String> list) {
        for (String s : list) {
            System.out.println(s);
        }
    }

    public static void printBadge(Badge badge) {
        System.out.println(badge.getName());
    }

    public static void printErrorMessage(String message) {
        System.out.println(message);
        printNewLine();
    }
}
