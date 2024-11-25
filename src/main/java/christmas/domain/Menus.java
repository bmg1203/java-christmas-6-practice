package christmas.domain;

import christmas.constants.SavedMenus;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class Menus {

    private final Map<String, Menu> menus;

    public Menus() {
        this.menus = saveMenus();
    }

    public Map<String, Menu> getMenus() {
        return Collections.unmodifiableMap(menus);
    }

    private Map<String, Menu> saveMenus() {
        Map<String, Menu> saveMenu = new LinkedHashMap<>();

        for (SavedMenus savedMenus : SavedMenus.values()) {
            saveMenu.put(savedMenus.getName(), new Menu(savedMenus.getName(), savedMenus.getPrice(), savedMenus.getType()));
        }

        return saveMenu;
    }
}