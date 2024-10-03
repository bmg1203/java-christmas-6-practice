package christmas.global.handler;

import christmas.global.exception.InputException;

import java.util.function.Supplier;

import static christmas.view.output.OutputView.printErrorMessage;

public class ExceptionHandler {
    public static <T> T execute(Supplier<T> supplier) {
        while(true) {
            try {
                return supplier.get();
            } catch (InputException e) {
                printErrorMessage(e.getMessage());
            }
        }
    }

}
