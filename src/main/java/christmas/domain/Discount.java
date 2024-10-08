package christmas.domain;

public class Discount {
    private final int christmasDiscount;
    private final int weekdayDiscount;
    private final int weekendDiscount;
    private final int specialDiscount;
    private final int giftDiscount;
    private final int totalDiscount;

    private Discount(int christmasDiscount, int weekdayDiscount, int weekendDiscount, int specialDiscount, int giftDiscount, int totalDiscount) {
        this.christmasDiscount = christmasDiscount;
        this.weekdayDiscount = weekdayDiscount;
        this.weekendDiscount = weekendDiscount;
        this.specialDiscount = specialDiscount;
        this.giftDiscount = giftDiscount;
        this.totalDiscount = totalDiscount;
    }

    public int getChristmasDiscount() {
        return christmasDiscount;
    }

    public int getWeekdayDiscount() {
        return weekdayDiscount;
    }

    public int getWeekendDiscount() {
        return weekendDiscount;
    }

    public int getSpecialDiscount() {
        return specialDiscount;
    }

    public int getGiftDiscount() {
        return giftDiscount;
    }

    public int getTotalDiscount() {
        return totalDiscount;
    }

    public static Discount createDiscount( int christmasDiscount, int weekdayDiscount, int weekendDiscount, int specialDiscount, int giftDiscount, int totalDiscount ) {
        return new Discount(christmasDiscount, weekdayDiscount, weekendDiscount, specialDiscount, giftDiscount, totalDiscount );
    }

}
