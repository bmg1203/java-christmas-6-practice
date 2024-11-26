package christmas.constants;

public enum GiftEvent {

    GIFT_MENU_NAME ("샴페인");

    private final String name;

    GiftEvent(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
