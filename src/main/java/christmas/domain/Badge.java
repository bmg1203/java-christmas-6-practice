package christmas.domain;

public class Badge {

    private static final String STAR_BADGE = "별";
    private static final int STAR_PRICE = 5000;
    private static final String TREE_BADGE = "트리";
    private static final int TREE_PRICE = 10000;
    private static final String SANTA_BADGE = "산타";
    private static final int SANTA_PRICE = 20000;
    private static final String NO_BADGE = "없음";
    private final String badge;

    public Badge(SalePrice salePrice) {
        this.badge = assignBadge(salePrice);
    }

    public String getBadge() {
        return badge;
    }

    private String assignBadge(SalePrice salePrice) {
        int totalSalePrice = salePrice.calculateTotalSalePrice();

        if (totalSalePrice >= SANTA_PRICE) return SANTA_BADGE;
        if (totalSalePrice >= TREE_PRICE) return TREE_BADGE;
        if (totalSalePrice >= STAR_PRICE) return STAR_BADGE;

        return NO_BADGE;
    }
}
