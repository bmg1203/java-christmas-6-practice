package christmas.domain;

public class VisitDay {
    private final int visitDay;

    private VisitDay(int visitDay) {
        this.visitDay = visitDay;
    }

    public static VisitDay createDay(int visitDay) {
        return new VisitDay(visitDay);
    }
}
