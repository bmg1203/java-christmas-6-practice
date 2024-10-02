package christmas.domain;

import java.util.List;

public class Orders {
    private List<Order> orderList;

    private Orders(List<Order> orderList) {
        this.orderList = orderList;
    }

    public static Orders createOrders(List<Order> orderList) {
        return new Orders(orderList);
    }

    public List<Order> getOrderList() {
        return orderList;
    }
}
