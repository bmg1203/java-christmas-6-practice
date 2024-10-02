package christmas.domain;

public class Event {
    private boolean christmasEvent;
    private boolean weekdayEvent;
    private boolean weekendEvent;
    private boolean specialEvent;

    private Event(boolean christmasEvent, boolean weekdayEvent, boolean weekendEvent, boolean specialEvent) {
        this.christmasEvent = christmasEvent;
        this.weekdayEvent = weekdayEvent;
        this.weekendEvent = weekendEvent;
        this.specialEvent = specialEvent;
    }

    public static Event createEvent(boolean christmasEvent, boolean weekdayEvent, boolean weekendEvent, boolean specialEvent) {
        return new Event(christmasEvent, weekdayEvent, weekendEvent, specialEvent);
    }
}
