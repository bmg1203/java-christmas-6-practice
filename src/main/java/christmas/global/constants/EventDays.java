package christmas.global.constants;

import java.util.Arrays;
import java.util.List;

import static christmas.global.constants.EventType.*;

public enum EventDays {
    CHRISTMAS_D_DAY(CHRISTMAS, Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25)),
    WEEKDAYS(WEEKDAY, Arrays.asList(3,4,5,6,7,10,11,12,13,14,17,18,19,20,21,24,25,26,27,28,31)),
    WEEKENDS(WEEKEND, Arrays.asList(1,2,8,9,15,16,22,23,29,30)),
    SPECIAL_DAYS(SPECIAL, Arrays.asList(3,10,17,24,25,31));

    private final EventType type;
    private final List<Integer> day;

    public List<Integer> getDay() {
        return day;
    }

    public EventType getType() {
        return type;
    }

    EventDays(EventType type, List<Integer> day) {
        this.type = type;
        this.day = day;
    }
}
