package christmas.view.input.validator;

import christmas.constant.errorMessage.exception.CustomNullPointException;
import christmas.constant.errorMessage.exception.CustomNumberFormatException;
import christmas.constant.errorMessage.input.EventExceptionStatus;

public class DateValidator {

    private static final DateValidator DATE_VALIDATOR = new DateValidator();

    private DateValidator() {
    }

    public static int validateDate(final String date) {
        return DATE_VALIDATOR.validateDateIsNumeric(date);
    }

    private String validateDateIsNull(final String date) {
        try {
            return date.trim();
        } catch (NullPointerException e) {
            throw new CustomNullPointException(EventExceptionStatus.DATE_IS_NOT_CORRECT);
        }
    }

    private int validateDateIsNumeric(final String date) {
        try {
            return Integer.parseInt(validateDateIsNull(date));
        } catch (NumberFormatException e) {
            throw new CustomNumberFormatException(EventExceptionStatus.DATE_IS_NOT_CORRECT);
        }
    }
}