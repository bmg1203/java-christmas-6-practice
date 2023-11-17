package christmas.systems.eventSystem;

import static christmas.enums.benefit.DiscountBenefit.BASIC_BENEFIT;
import static christmas.enums.benefit.DiscountBenefit.INCREASE_BENEFIT;
import static christmas.enums.benefit.DiscountBenefit.WEEK_BENEFIT;
import static christmas.enums.events.decemberevent.GiftEvents.GIFT_EVENT;
import static christmas.enums.events.decemberevent.LinearDiscountEvents.CHRISTMAS_D_DAY_DISCOUNT;
import static christmas.enums.events.decemberevent.SpecialDiscount.SPECIAL_DISCOUNT;
import static christmas.enums.events.decemberevent.WeekDiscountEvents.WEEKDAY_DISCOUNT;
import static christmas.enums.events.decemberevent.WeekDiscountEvents.WEEKEND_DISCOUNT;
import static christmas.enums.menu.BeverageMenu.CHAMPAGNE;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.enums.menu.DessertMenu;
import christmas.enums.menu.MainMenu;
import christmas.event.EventBenefit;
import christmas.order.Order;
import christmas.order.Orders;
import java.time.LocalDate;
import java.time.Month;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EventSystemTest {
    private final static Order orderTwoDessert = new Order(DessertMenu.CHOCOLATE_CAKE, 2);
    private final static Order oderTwoSteak = new Order(MainMenu.T_BONE_STEAK, 2);
    private final static Orders ordersWithMain = new Orders(Set.of(oderTwoSteak));
    private final static Orders ordersOver120_000 = new Orders(Set.of(orderTwoDessert, oderTwoSteak));
    private final static LocalDate reservationDate = LocalDate.of(2023, Month.DECEMBER, 3);
    private final static Integer CHRIST_MAS_EVENT_AFTER_TWO_DAYS_BENEFIT = (BASIC_BENEFIT.getAmount() + (
            INCREASE_BENEFIT.getAmount() * 2));
    private final static Integer WEEK_BENEFIT_CONTAIN_TWO_MAIN = (WEEK_BENEFIT.getAmount() * 2);


    public EventSystem eventSystem() {
        EventInitializer eventInitializer = new EventInitializer();
        eventInitializer.increaseEverydayDiscountEventsAdd(CHRISTMAS_D_DAY_DISCOUNT.getInstance());
        eventInitializer.specialDiscountEventAdd(SPECIAL_DISCOUNT.getInstance());
        eventInitializer.amountToGiftEventsAdd(GIFT_EVENT.getInstance());
        eventInitializer.weekDiscountEventAdd(WEEKDAY_DISCOUNT.getInstance());
        eventInitializer.weekDiscountEventAdd(WEEKEND_DISCOUNT.getInstance());

        return new EventSystem(eventInitializer);
    }

    @DisplayName("특별할인, 주중할인(2), 증정이벤트, 크리스마스할인 당첨시")
    @Test
    void whenSpecialChristmasWeekdayGiftEvent() {
        //given
        final EventSystem eventSystem = eventSystem();
        final Integer totalBenefitAmount =
                BASIC_BENEFIT.getAmount() + CHRIST_MAS_EVENT_AFTER_TWO_DAYS_BENEFIT + WEEK_BENEFIT_CONTAIN_TWO_MAIN
                        + CHAMPAGNE.getAmount();

        //when
        EventBenefit eventBenefit = eventSystem.activateEvent(reservationDate, ordersOver120_000);
        Integer totalBenefit = eventBenefit.showTotalDiscount();

        //then
        assertThat(totalBenefit).isEqualTo(totalBenefitAmount);

    }

    @DisplayName("특별할인, 주중할인(2), 증정이벤트, 크리스마스할인 당첨시")
    @Test
    void whenSpecialChristmasWeekdayEvent() {
        //given
        final EventSystem eventSystem = eventSystem();
        final Integer totalBenefitAmount =
                BASIC_BENEFIT.getAmount() + CHRIST_MAS_EVENT_AFTER_TWO_DAYS_BENEFIT + WEEK_BENEFIT_CONTAIN_TWO_MAIN;
        //when
        EventBenefit eventBenefit = eventSystem.activateEvent(reservationDate, ordersWithMain);
        Integer totalBenefit = eventBenefit.showTotalDiscount();

        //then
        assertThat(totalBenefit).isEqualTo(totalBenefitAmount);

    }
}