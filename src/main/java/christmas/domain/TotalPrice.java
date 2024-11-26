package christmas.domain;

public class TotalPrice {

    private final int totalPrice;

    public TotalPrice(Menus menus, Orders orders) {
        this.totalPrice = calculateTotalPrice(menus, orders);
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    private int calculateTotalPrice(Menus menus, Orders orders) {
        int sum = 0;
        for (Order order : orders.getOrders()) {
            Menu menu = menus.getMenus().get(order.getName());
            sum += menu.getPrice() * order.getCount();
        }
        return sum;
    }

    public int finalPrice(SalePrice salePrice) {
        return totalPrice - salePrice.calculateTotalSalePrice();
    }
}
