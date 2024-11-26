package christmas.utils;

import christmas.constants.ErrorMessage;
import java.text.DecimalFormat;

public class Parser {

    public static int stringToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.VISIT_DAY_ERROR.getMessage());
        }
    }

    public static String intToMoneyFormat(int price) {
        DecimalFormat df = new DecimalFormat("###,###");
        return df.format(price);
    }
}
