package christmas.service;

import christmas.model.Discount;
import christmas.model.Menu;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static christmas.model.DiscountPolicy.*;
import static christmas.model.MenuType.*;

public class OrderService {

    private LocalDate orderDate;
    private Map<Menu, Integer> orderMap;
    private Map<Discount, Integer> discountMap;

    public OrderService(Integer date, Map<Menu, Integer> orderMap) {
        this.orderDate = LocalDate.of(2023, 12, date);
        this.orderMap = orderMap;
        this.discountMap = new HashMap<>();
    }

    public Integer calculateTotalPrice() {
        return orderMap.entrySet().stream()
                .mapToInt(e -> e.getKey().getPrice() * e.getValue())
                .sum();
    }

    public boolean determineGift() {
        return calculateTotalPrice() >= 120000;
    }

    public void calculateDiscount() {
        calculateChristmasDiscount();
        calculateDayDiscount();
        calculateSpecialDiscount();
    }

    public int calculateTotalDiscountPrice() {
        return discountMap.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    public int calculateTotalPayment() {
        return calculateTotalPrice() - calculateTotalDiscountPrice();
    }

    public String calculateBadge() {
        int totalDiscount = calculateTotalDiscountPrice();
        if (totalDiscount >= 20000) {
            return "산타";
        } else if (totalDiscount >= 10000) {
            return "트리";
        } else if (totalDiscount >= 5000) {
            return "별";
        } else {
            return "없음";
        }
    }

    private void calculateChristmasDiscount() {
        if (orderDate.getDayOfMonth() <= 25) {
            int daysIntoDecember = orderDate.getDayOfMonth();
            discountMap.put(Discount.CHRISTMAS_DISCOUNT, CHRISTMAS_DISCOUNT_START.getValue() + (daysIntoDecember - 1) * CHRISTMAS_DISCOUNT_INCREMENT.getValue());

        }
    }

    private void calculateDayDiscount() {
        if (orderDate.getDayOfWeek().getValue() <= 5) { // 일~목
            discountMap.put(Discount.WEEKDAY_DISCOUNT, (int) orderMap.entrySet().stream()
                    .filter(e -> e.getKey().getType() == DESERT)
                    .count() * WEEKDAY_DISCOUNT.getValue());
        } else { // 금~토
            discountMap.put(Discount.WEEKEND_DISCOUNT, (int) orderMap.entrySet().stream()
                    .filter(e -> e.getKey().getType() == MAIN)
                    .count() * WEEKEND_DISCOUNT.getValue());
        }
    }

    private void calculateSpecialDiscount() {
        if(checkStarInEventCalendar()) discountMap.put(Discount.SPECIAL_DISCOUNT, SPECIAL_DISCOUNT.getValue());
    }

    private boolean checkStarInEventCalendar() {
        return orderDate.getDayOfWeek().getValue() == 1;
    }

    public Map<Menu, Integer> getOrderMap() {
        return orderMap;
    }

    public Map<Discount, Integer> getDiscountMap() {
        return discountMap;
    }
}