package christmas.domain;

public class VisitDay {
    private final int visitDay;

    private VisitDay(int visitDay) {
        this.visitDay = visitDay;
    }

    public static VisitDay createVisitDay(int visitDay) {
        return new VisitDay(visitDay);
    }

    public int getVisitDay() {
        return visitDay;
    }
}
