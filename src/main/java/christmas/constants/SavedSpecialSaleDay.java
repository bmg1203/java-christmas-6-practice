package christmas.constants;

public enum SavedSpecialSaleDay {

    THREE(3),
    TEN(10),
    SEVENTEEN(17),
    TWENTY_FOUR(24),
    TWENTY_FIVE(25),

    THIRTY_ONE(31);

    private final int day;

    SavedSpecialSaleDay(int day) {
        this.day = day;
    }

    public int getDay() {
        return day;
    }
}
