package christmas.domain;

public class Day {
    private final int visitDay;

    private Day(int visitDay) {
        this.visitDay = visitDay;
    }

    public static Day createDay(int visitDay) {
        return new Day(visitDay);
    }
}
