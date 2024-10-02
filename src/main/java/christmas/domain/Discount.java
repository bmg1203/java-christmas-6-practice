package christmas.domain;

public class Discount {
    private int christmasDiscount;
    private int weekdayDiscount;
    private int weekendDiscount;
    private int specialDiscount;

    private Discount(int christmasDiscount, int weekdayDiscount, int weekendDiscount, int specialDiscount ) {
        this.christmasDiscount = christmasDiscount;
        this.weekdayDiscount = weekdayDiscount;
        this.weekendDiscount = weekendDiscount;
        this.specialDiscount = specialDiscount;
    }

    public static Discount createDiscount( int christmasDiscount, int weekdayDiscount, int weekendDiscount, int specialDiscount) {
        return new Discount(christmasDiscount, weekdayDiscount, weekendDiscount, specialDiscount);
    }

}
