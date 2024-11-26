package christmas.constants;

public enum OutputPrompts {

    RESULT_HEADER_OUTPUT("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"),
    ORDER_MENU_HEADER_OUTPUT("<주문 메뉴>"),
    ORDER_MENU_OUTPUT("%s %d개"),
    TOTAL_PRICE_OUTPUT("<할인 전 총주문 금액>\n%s원\n"),
    GIFT_MENU_OUTPUT("<증정 메뉴>\n%s\n"),
    BENEFITS_OUTPUT("<혜택 내역>\n%s\n"),
    CHRISTMAS_SALE_PRICE_OUTPUT("크리스마스 디데이 할인: -%s원"),
    WEEKDAY_SALE_PRICE_OUTPUT("평일 할인: -%s원"),
    WEEKEND_SALE_PRICE_OUTPUT("주말 할인: -%s원"),
    SPECIAL_SALE_PRICE_OUTPUT("특별 할인: -%s원"),
    GIFT_EVENT_PRICE_OUTPUT("증정 이벤트: -%s원"),
    TOTAL_BENEFITS_PRICE_OUTPUT("<총혜택 금액>\n-%s원\n"),
    FINAL_PAYMENT_PRICE_OUTPUT("<할인 후 예상 결제 금액>\n%s원\n"),
    BADGE_OUTPUT("<12월 이벤트 배지>\n%s원");

    private final String prompt;

    OutputPrompts(String prompt) {
        this.prompt = prompt;
    }

    public String getPrompt() {
        return prompt + "\n";
    }
}