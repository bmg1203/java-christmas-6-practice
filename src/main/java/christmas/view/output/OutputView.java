package christmas.view.output;

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
        System.out.println(list);
    }
}
