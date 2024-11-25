package christmas.constants;

public enum ErrorMessage {

    VISIT_DAY_ERROR("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    //메뉴판에 없는 메뉴, 메뉴 개수 1 미만, 메뉴 형식, 중복메뉴 관련 오류 메시지
    MENU_INPUT_ERROR("유효하지 않은 주문입니다. 다시 입력해 주세요.");

    private final String logLevel = "[ERROR] ";
    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return logLevel + message;
    }
}