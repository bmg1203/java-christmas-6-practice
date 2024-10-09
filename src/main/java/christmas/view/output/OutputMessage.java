package christmas.view.output;

public enum OutputMessage {
    // Static Message
    INFORMATION_MESSAGE("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."),
    ASK_VISIT_DAY_MESSAGE("12월 중 식당 에상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)"),
    ASK_MENU_AND_COUNT_MESSAGE("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)"),
    ORDER_MENU_TITLE("<주문 메뉴>"),
    PRE_DISCOUNT_TOTAL_TITLE("<할인 전 총주문 금액>"),
    GIFT_MENU_TITLE("<증정 메뉴>"),
    EVENT_LIST_TITLE("<혜택 내역>"),
    TOTAL_PRICE_TITLE("<총혜택 금액>"),
    FINAL_PAYMENT_TITLE("<할인 후 예상 결제 금액>"),
    BADGE_TITLE("<12월 이벤트 배지>"),
    NONE_MESSAGE("없음"),

    // Dynamic Message
    EVENT_PREVIEW_MESSAGE("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"),
    QUANTITY_MESSAGE("%s %d개"),
    ORDER_PRICE_MESSAGE("%s원"),
    DISCOUNT_PRICE_MESSAGE("-%s원"),
    EVENT_LIST_MESSAGE("%s: -%s원"),
    BADGE_MESSAGE("%s");

    public String getMessage() {
        return message;
    }

    private final String message;

    OutputMessage(String message) {
        this.message = message;
    }
}
