package christmas.domain;

import christmas.constants.SavedSpecialSaleDay;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Event {

    private static final int CHRISTMAS_DAY_SALE_MAX_DAY = 25;
    private static final int YEAR = 2023;
    private static final int MONTH = 12;
    private static final int FRIDAY = 5;
    private static final int SATURDAY = 6;
    private static final int GIFT_PRICE = 120000;
    private final boolean christmasDaySale;
    private final boolean weekDaySale;
    private final boolean weekendSale;
    private final boolean specialSale;
    private final boolean giftEvent;
    private final List<Integer> specialSaleDay;

    public Event(Visit visit, TotalPrice totalPrice) {
        this.christmasDaySale = checkChristmasDaySale(visit);
        this.weekendSale = checkWeekendSale(visit);
        this.weekDaySale = checkWeekDaySale();
        this.specialSaleDay = getSpecialSaleDay();
        this.specialSale = checkSpecialSale(visit);
        this.giftEvent = checkGiftEvent(totalPrice);
    }

    public boolean isChristmasDaySale() {
        return christmasDaySale;
    }

    public boolean isWeekDaySale() {
        return weekDaySale;
    }

    public boolean isWeekendSale() {
        return weekendSale;
    }

    public boolean isSpecialSale() {
        return specialSale;
    }

    public boolean isGiftEvent() {
        return giftEvent;
    }

    private boolean checkChristmasDaySale(Visit visit) {
        if (visit.getDay() <= CHRISTMAS_DAY_SALE_MAX_DAY) {
            return true;
        }
        return false;
    }

    private boolean checkWeekendSale(Visit visit) {
        LocalDate date = LocalDate.of(YEAR, MONTH, visit.getDay());
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        int dayOfWeekNumber = dayOfWeek.getValue();

        if (dayOfWeekNumber == FRIDAY || dayOfWeekNumber == SATURDAY) {
            return true;
        }
        return false;
    }

    private boolean checkWeekDaySale() {
        if (weekDaySale) {
            return false;
        }
        return true;
    }

    private List<Integer> getSpecialSaleDay() {
        List<Integer> saveDays = new ArrayList<>();
        for (SavedSpecialSaleDay savedSpecialSaleDay : SavedSpecialSaleDay.values()) {
            saveDays.add(savedSpecialSaleDay.getDay());
        }
        return saveDays;
    }

    private boolean checkSpecialSale(Visit visit) {
        if (specialSaleDay.contains(visit.getDay())) {
            return true;
        }
        return false;
    }

    private boolean checkGiftEvent(TotalPrice totalPrice) {
        if (totalPrice.getTotalPrice() >= GIFT_PRICE) {
            return true;
        }
        return false;
    }
}
