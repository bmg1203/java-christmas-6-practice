package christmas.event.evnets.specialdiscount;

import static christmas.enums.benefit.DiscountBenefit.NO_BENEFIT;

import christmas.event.OneEventResult;
import christmas.utils.EventPeriod;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class SpecialDayDiscount implements SpecialDayDiscountEvent {
    private final static Integer CHRISTMAS_DAY = 25;
    private final String eventName;
    private final Integer discountAmount;
    private final EventPeriod eventPeriod;

    public SpecialDayDiscount(String eventName, EventPeriod eventPeriod, Integer discountAmount) {
        this.eventName = eventName;
        this.eventPeriod = eventPeriod;
        this.discountAmount = discountAmount;
    }

    private Integer calculateDiscount(LocalDate reservationDate) {
        DayOfWeek dayOfWeek = reservationDate.getDayOfWeek();
        if (dayOfWeek == DayOfWeek.SUNDAY || reservationDate.getDayOfMonth() == CHRISTMAS_DAY) {
            return discountAmount;
        }
        return NO_BENEFIT.getAmount();
    }

    @Override
    public Boolean isEventActivate(LocalDate reservationDate) {
        return !(reservationDate.isBefore(eventPeriod.startDate()) || reservationDate.isAfter(eventPeriod.endDate()));
    }

    @Override
    public OneEventResult execute(LocalDate reservationDate) {
        if (isEventActivate(reservationDate)) {
            Integer discountBenefit = calculateDiscount(reservationDate);
            return new OneEventResult(eventName, discountBenefit);
        }
        return OneEventResult.NO_EVENT_RESULT();
    }
}
