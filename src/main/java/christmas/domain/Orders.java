package christmas.domain;

import java.util.List;

public class Orders {
    private List<Order> orderList;
    private String preDiscountTotal;

    private Orders(List<Order> orderList, String preDiscountTotal) {
        this.orderList = orderList;
        this.preDiscountTotal = preDiscountTotal;
    }

    public static Orders createOrders(List<Order> orderList, String preDiscountTotal) {
        return new Orders(orderList, preDiscountTotal);
    }

    public String getPreDiscountTotal() {
        return preDiscountTotal;
    }

    public List<Order> getOrderList() {
        return orderList;
    }
}
