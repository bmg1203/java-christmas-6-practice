package christmas.global.constants;

public enum EventType {
    CHRISTMAS("Christmas"),
    WEEKDAY("Weekday"),
    WEEKEND("Weekend"),
    SPECIAL("Special");

    EventType(String type) {
        this.type = type;
    }

    private final String type;
}
