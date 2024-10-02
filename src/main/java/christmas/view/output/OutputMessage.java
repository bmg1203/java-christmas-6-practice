package christmas.view.output;

public enum OutputMessage {
    // Static Message
    INFORMATION_MESSAGE("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."),
    ASK_VISIT_DAY_MESSAGE("12월 중 식당 에상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)"),
    ASK_MENU_AND_COUNT_MESSAGE("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)"),

    // Dynamic Message
    EVENT_PREVIEW_MESSAGE("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");

    public String getMessage() {
        return message;
    }

    private final String message;

    OutputMessage(String message) {
        this.message = message;
    }
}
