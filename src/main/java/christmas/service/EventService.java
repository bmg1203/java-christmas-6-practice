package christmas.service;

import christmas.domain.Event;
import christmas.domain.VisitDay;

import java.util.List;

import static christmas.global.constants.EventDays.*;

public class EventService {
    public static Event getEvent(VisitDay visitDay) {
        int day = visitDay.getVisitDay();

        boolean christmasEvent = isChristmas(day);
        boolean weekdayEvent = isWeekday(day);
        boolean weekendEvent = isWeekend(day);
        boolean specialEvent = isSpecialEvent(day);

        return Event.createEvent(christmasEvent, weekdayEvent, weekendEvent, specialEvent);
    }

    private static boolean isChristmas(int day) {
        List<Integer> eventDays = CHRISTMAS_D_DAY.getDay();

        return eventDays.contains(day);
    }

    private static boolean isWeekday(int day) {
        List<Integer> eventDays = WEEKDAYS.getDay();

        return eventDays.contains(day);
    }

    private static boolean isWeekend(int day) {
        List<Integer> eventDays = WEEKENDS.getDay();

        return eventDays.contains(day);
    }

    private static boolean isSpecialEvent(int day) {
        List<Integer> eventDays = SPECIAL.getDay();

        return eventDays.contains(day);
    }
}
