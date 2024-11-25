package christmas.utils;

import christmas.constants.ErrorMessage;

public class Parser {

    public static int stringToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.VISIT_DAY_ERROR.getMessage());
        }
    }
}
