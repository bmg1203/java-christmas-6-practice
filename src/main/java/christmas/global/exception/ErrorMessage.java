package christmas.global.exception;

public enum ErrorMessage {
    // Input day
    INVALID_DAY_ERROR("유효하지 않은 날짜입니다. 다시 입력해 주세요."),

    // Input Menu
    INVALID_MENU_ERROR("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    INVALID_ORDER_ERROR("음료만 주문할 수 없습니다. 다시 입력해주세요");


    public String getMessage() {
        return message;
    }

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }
}
