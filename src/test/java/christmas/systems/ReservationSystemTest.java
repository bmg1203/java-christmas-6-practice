package christmas.systems;

import static christmas.enums.badge.benefit.BenefitBadge.NONE;
import static christmas.enums.badge.benefit.BenefitBadge.SANTA;
import static christmas.enums.events.decemberevent.GiftEvents.GIFT_EVENT;
import static christmas.enums.events.decemberevent.LinearDiscountEvents.CHRISTMAS_D_DAY_DISCOUNT;
import static christmas.enums.events.decemberevent.SpecialDiscount.SPECIAL_DISCOUNT;
import static christmas.enums.events.decemberevent.WeekDiscountEvents.WEEKDAY_DISCOUNT;
import static christmas.enums.events.decemberevent.WeekDiscountEvents.WEEKEND_DISCOUNT;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.enums.benefit.DiscountBenefit;
import christmas.enums.menu.DessertMenu;
import christmas.enums.menu.MainMenu;
import christmas.event.evnets.linearincreasediscount.LinearIncreaseDiscountEvent;
import christmas.order.Order;
import christmas.order.Orders;
import christmas.order.Receipt;
import christmas.systems.eventSystem.EventInitializer;
import christmas.systems.eventSystem.EventSystem;
import christmas.systems.ordersystem.OrderSystem;
import christmas.systems.reservationsystem.ReservationSystem;
import java.time.LocalDate;
import java.time.Month;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ReservationSystemTest {
    private final static Order orderTwoDessert = new Order(DessertMenu.CHOCOLATE_CAKE, 2);
    private final static Order orderOneIceCream = new Order(DessertMenu.ICE_CREAM, 1);
    private final static Order oderTwoSteak = new Order(MainMenu.T_BONE_STEAK, 2);
    private final static Orders dessertAndSteakOrders = new Orders(Set.of(orderTwoDessert, oderTwoSteak));
    private final static Orders iceCreamOrders = new Orders(Set.of(orderOneIceCream));
    private final static LocalDate reservationDate = LocalDate.of(2023, Month.DECEMBER, 3);
    private final static String RESTAURANT_NAME = "우테코 식당";


    ReservationSystem setALLEvent() {
        EventInitializer eventInitializer = new EventInitializer();
        eventInitializer.increaseEverydayDiscountEventsAdd(CHRISTMAS_D_DAY_DISCOUNT.getInstance());
        eventInitializer.specialDiscountEventAdd(SPECIAL_DISCOUNT.getInstance());
        eventInitializer.amountToGiftEventsAdd(GIFT_EVENT.getInstance());
        eventInitializer.weekDiscountEventAdd(WEEKDAY_DISCOUNT.getInstance());
        eventInitializer.weekDiscountEventAdd(WEEKEND_DISCOUNT.getInstance());

        EventSystem eventSystem = new EventSystem(eventInitializer);
        OrderSystem orderSystem = new OrderSystem(eventSystem);
        return new ReservationSystem(orderSystem);
    }

    ReservationSystem setOneEvent() {
        LinearIncreaseDiscountEvent linearDiscount = CHRISTMAS_D_DAY_DISCOUNT.getInstance();

        EventInitializer eventInitializer = new EventInitializer();
        eventInitializer.increaseEverydayDiscountEventsAdd(linearDiscount);

        EventSystem eventSystem = new EventSystem(eventInitializer);
        OrderSystem orderSystem = new OrderSystem(eventSystem);
        return new ReservationSystem(orderSystem);
    }

    @DisplayName("이벤트가 5개일 때의 혜택 검증")
    @Test
    void allEventBenefit() {
        //given
        final Integer expectedAmount = 31246;
        ReservationSystem reservationSystem = setALLEvent();

        //when
        Receipt receipt = reservationSystem.calculateOrderResult(reservationDate, dessertAndSteakOrders);

        //then
        assertThat(receipt.badge()).isEqualTo(SANTA);
        assertThat(receipt.discountBenefit()).isEqualTo(expectedAmount);
    }

    @DisplayName("이벤트가 5개일 때의 메시지 검증")
    @Test
    void allEventMessage() {
        //given
        ReservationSystem reservationSystem = setALLEvent();

        //when
        Receipt receipt = reservationSystem.calculateOrderResult(reservationDate, dessertAndSteakOrders);
        String result = ReservationSystem.printResult(RESTAURANT_NAME, reservationDate, dessertAndSteakOrders, receipt);

        //then
        assertThat(result).contains("<주문 메뉴>", "초코케이크 2개", "티본스테이크 2개");
        assertThat(result).contains("<할인 후 예상 결제 금액>", "108,754원");
    }

    @DisplayName("이벤트가 1개일 때의 혜택 검증")
    @Test
    void oneEventBenefit() {
        //given
        final Integer expectedAmount = 1200;
        ReservationSystem reservationSystem = setOneEvent();

        //when
        Receipt receipt = reservationSystem.calculateOrderResult(reservationDate, dessertAndSteakOrders);

        //then
        assertThat(receipt.badge()).isEqualTo(NONE);
        assertThat(receipt.discountBenefit()).isEqualTo(expectedAmount);
    }

    @DisplayName("이벤트가 1개일 때의 메시지 검증")
    @Test
    void oneEventMessage() {
        //given
        ReservationSystem reservationSystem = setOneEvent();

        //when
        Receipt receipt = reservationSystem.calculateOrderResult(reservationDate, dessertAndSteakOrders);
        String result = ReservationSystem.printResult(RESTAURANT_NAME, reservationDate, dessertAndSteakOrders, receipt);

        //then
        assertThat(result).contains("<혜택 내역>", "크리스마스 디데이 할인 -1,200원");
        assertThat(result).contains("<총혜택 금액>", "-1,200원");
        assertThat(result).contains("<할인 후 예상 결제 금액>", "138,800원");
        assertThat(result).contains("<12월 이벤트 배지>", "없음");

    }

    @DisplayName("총 금액이 10000원 이하 일 시 혜택없음 검증")
    @Test
    void allEventBenefitUnder10_000() {
        //given
        ReservationSystem reservationSystem = setALLEvent();

        //when
        Receipt receipt = reservationSystem.calculateOrderResult(reservationDate, iceCreamOrders);
        String result = ReservationSystem.printResult(RESTAURANT_NAME, reservationDate, iceCreamOrders, receipt);
        System.out.println(result);

        //then
        assertThat(receipt.badge()).isEqualTo(NONE);
        assertThat(receipt.discountBenefit()).isEqualTo(DiscountBenefit.NO_BENEFIT.getAmount());
    }

    @DisplayName("총 금액이 10000원 이하 일 시 메시지 검증")
    @Test
    void allEventMessageUnder10_000() {
        //given
        ReservationSystem reservationSystem = setALLEvent();

        //when
        Receipt receipt = reservationSystem.calculateOrderResult(reservationDate, iceCreamOrders);
        String result = ReservationSystem.printResult(RESTAURANT_NAME, reservationDate, iceCreamOrders, receipt);

        //then
        assertThat(result).contains("<혜택 내역>", "없음");
        assertThat(result).contains("<총혜택 금액>", "0원");
        assertThat(result).contains("<할인 후 예상 결제 금액>", "5,000원");
        assertThat(result).contains("<12월 이벤트 배지>", "없음");
    }

}