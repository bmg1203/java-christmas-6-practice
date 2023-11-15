package christmas.model;

import java.util.AbstractMap.SimpleEntry;
import java.util.List;
import java.util.Objects;

public class Menu {

    private final Category category;

    private final String menuName;

    private final int price;

    public static int checkBill (List<SimpleEntry<Menu, Integer>> orderedMenu) {
        return orderedMenu.stream()
                .mapToInt(el -> el.getKey().price * el.getValue())
                .sum();
    }

    public Menu(Category category, String menuName, int price) {
        this.category = category;
        this.menuName = menuName;
        this.price = price;
    }

    public boolean checkMenu (String menuName) {
        return menuName.equals(this.menuName);
    }


    public boolean checkCategory (String category) {
        return this.category.isSame(category);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || menuName.getClass() != o.getClass()) return false;
        String menu = (String) o;
        return this.menuName.equals(menu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(menuName);
    }

    @Override
    public String toString() {
        return menuName;
    }
}

// TODO : 메뉴 이름 입력받아 Contains 메서드로 포함여부 확인하기 위해, equals 메서드 오버 로딩