package christmas.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;

public record EventPeriod(LocalDate startDate, LocalDate endDate) {
    private final static Integer FIRST_DAY_OF_MONTH = 1;

    //한달의 기한을 잡는다.
    public static EventPeriod createMonthPeriod(int year, int month) {
        LocalDate firstDayOfMonth = LocalDate.of(year, Month.of(month), FIRST_DAY_OF_MONTH);
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
