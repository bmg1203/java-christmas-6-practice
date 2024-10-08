package christmas.domain;

import java.util.List;

public class Orders {
    private final List<Order> orderList;
    private final int preDiscountTotal;

    private Orders(List<Order> orderList, int preDiscountTotal) {
        this.orderList = orderList;
        this.preDiscountTotal = preDiscountTotal;
    }

    public static Orders createOrders(List<Order> orderList, int preDiscountTotal) {
        return new Orders(orderList, preDiscountTotal);
    }

    public int getPreDiscountTotal() {
        return preDiscountTotal;
    }

    public List<Order> getOrderList() {
        return orderList;
    }
}
