package christmas.global.exception;

public class InputException extends IllegalArgumentException {
    private static final String PREFIX = "[ERROR]";

    public InputException(ErrorMessage message) {
        super(String.format("%s %s", PREFIX, message.getMessage()));
    }
}
