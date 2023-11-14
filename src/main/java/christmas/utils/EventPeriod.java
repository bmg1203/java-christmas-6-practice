package christmas.utils;

import static christmas.enums.events.decemberevent.DecemberEventPeriod.START_OF_THE_MONTH;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;

public record EventPeriod(LocalDate startDate, LocalDate endDate) {
    //한달의 기한을 잡는다.
    public static EventPeriod createMonthPeriod(int year, int month) {
        LocalDate firstDayOfMonth = LocalDate.of(year, Month.of(month), START_OF_THE_MONTH.getValue());
        LocalDate lastDayOfMonth = firstDayOfMonth.with(TemporalAdjusters.lastDayOfMonth());
        return new EventPeriod(firstDayOfMonth, lastDayOfMonth);
    }

    //특정 기한을 잡는다
    public static EventPeriod createTypicalPeriod(int year, int month, int startDate, int endDate) {
        LocalDate firstDayOfMonth = LocalDate.of(year, Month.of(month), startDate);
        LocalDate lastDayOfMonth = LocalDate.of(year, Month.of(month), endDate);
        return new EventPeriod(firstDayOfMonth, lastDayOfMonth);
    }

    //특정일부터 N주까지의 기한을 잡는다
    public static EventPeriod createTypicalWeekPeriod(int year, int month, int startDate, int weekCount) {
        final int perWeek = DayOfWeek.values().length;
        int NWeekAfterDay = perWeek * weekCount;

        LocalDate firstDayOfMonth = LocalDate.of(year, Month.of(month), startDate);
        LocalDate lastDayOfMonth = LocalDate.of(year, Month.of(month), NWeekAfterDay);
        return new EventPeriod(firstDayOfMonth, lastDayOfMonth);
    }
}
