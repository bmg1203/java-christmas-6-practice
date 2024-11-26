package christmas.constants;

public enum CautionMessage {

    EVENT_MIN_PRICE_CAUTION("총주문 금액 10,000원 이상부터 이벤트가 적용됩니다."),
    DRINK_ONLY_CAUTION("음료만 주문 시, 주문할 수 없습니다."),
    MENU_COUNT_MAX_CAUTION("메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다.\n"
            + "(e.g. 시저샐러드-1, 티본스테이크-1, 크리스마스파스타-1, 제로콜라-3, 아이스크림-1의 총개수는 7개)");

    private final String caution;

    CautionMessage(String caution) {
        this.caution = caution;
    }

    public String getCaution() {
        return caution + "\n";
    }
}
