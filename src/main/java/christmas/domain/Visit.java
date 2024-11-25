package christmas.domain;

import christmas.constants.ErrorMessage;

public class Visit {

    private static final int MIN_DAY = 1;
    private static final int MAX_DAY = 31;
    private final int day;

    public Visit(int day) {
        validateDay(day);
        this.day = day;
    }

    public int getDay() {
        return day;
    }

    private void validateDay(int day) {
        if (day < MIN_DAY || day > MAX_DAY) {
            throw new IllegalArgumentException(ErrorMessage.VISIT_DAY_ERROR.getMessage());
        }
    }
}
