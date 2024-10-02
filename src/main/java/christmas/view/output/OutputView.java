package christmas.view.output;

public class OutputView {
    public static void printStaticMessage(OutputMessage message) {
        System.out.println(message.getMessage());
    }

    public static void printDynamicMessage(String message) {
        System.out.println(message);
    }
}
