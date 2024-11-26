package christmas.domain;

import christmas.constants.EventMinPrice;
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
        this.christmasDaySale = checkChristmasDaySale(visit, totalPrice);
        this.weekendSale = checkWeekendSale(visit, totalPrice);
        this.weekDaySale = checkWeekDaySale(totalPrice);
        this.specialSaleDay = getSpecialSaleDay(totalPrice);
        this.specialSale = checkSpecialSale(visit, totalPrice);
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

    private boolean checkChristmasDaySale(Visit visit, TotalPrice totalPrice) {
        if (visit.getDay() <= CHRISTMAS_DAY_SALE_MAX_DAY && totalPrice.getTotalPrice() >= EventMinPrice.EVENT_MIN_PRICE.getPrice()) {
            return true;
        }
        return false;
    }

    private boolean checkWeekendSale(Visit visit, TotalPrice totalPrice) {
        LocalDate date = LocalDate.of(YEAR, MONTH, visit.getDay());
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        int dayOfWeekNumber = dayOfWeek.getValue();

        if ((dayOfWeekNumber == FRIDAY || dayOfWeekNumber == SATURDAY) && totalPrice.getTotalPrice() >= EventMinPrice.EVENT_MIN_PRICE.getPrice()) {
            return true;
        }
        return false;
    }

    private boolean checkWeekDaySale(TotalPrice totalPrice) {
        if (weekDaySale || totalPrice.getTotalPrice() >= EventMinPrice.EVENT_MIN_PRICE.getPrice()) {
            return false;
        }
        return true;
    }

    private List<Integer> getSpecialSaleDay(TotalPrice totalPrice) {
        List<Integer> saveDays = new ArrayList<>();
        for (SavedSpecialSaleDay savedSpecialSaleDay : SavedSpecialSaleDay.values()) {
            saveDays.add(savedSpecialSaleDay.getDay());
        }
        return saveDays;
    }

    private boolean checkSpecialSale(Visit visit, TotalPrice totalPrice) {
        if (specialSaleDay.contains(visit.getDay()) && totalPrice.getTotalPrice() >= EventMinPrice.EVENT_MIN_PRICE.getPrice()) {
            return true;
        }
        return false;
    }

    private boolean checkGiftEvent(TotalPrice totalPrice) {
        if (totalPrice.getTotalPrice() >= GIFT_PRICE && totalPrice.getTotalPrice() >= EventMinPrice.EVENT_MIN_PRICE.getPrice()) {
            return true;
        }
        return false;
    }
}
