package christmas.controller;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.Badge;
import christmas.domain.Event;
import christmas.domain.Menus;
import christmas.domain.Orders;
import christmas.domain.SalePrice;
import christmas.domain.TotalPrice;
import christmas.domain.Visit;
import christmas.view.InputView;
import christmas.view.OutputView;

public class ChristmasController {

    private Menus menus;
    private Visit visit;
    private Orders orders;
    private TotalPrice totalPrice;
    private Event event;
    private SalePrice salePrice;
    private Badge badge;

    private final InputView inputView;
    private final OutputView outputView;

    public ChristmasController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        setMenus();
        inputs();
        setEvents();
        setBadge();
        outputs();
        Console.close();
    }

    private void setMenus() {
        menus = new Menus();
    }

    private void inputs() {
        visit = inputView.visitInput();
        orders = inputView.ordersInput(menus);
    }

    private void setEvents() {
        totalPrice = new TotalPrice(menus, orders);
        event = new Event(visit, totalPrice);
        salePrice = new SalePrice(menus, event, visit, orders);
    }

    private void setBadge() {
        badge = new Badge(salePrice);
    }

    private void outputs() {
        outputView.benefitHeaderOutput(visit);
        outputView.orderMenuOutput(orders);
        outputView.totalPriceOutput(totalPrice);
        eventOutputs();
        priceOutputs();
        outputView.badgeOutput(badge);
    }

    private void eventOutputs() {
        outputView.giftEventOutput(event);
        outputView.allBenefitOutput(event, salePrice);
    }

    private void priceOutputs() {
        outputView.totalSalePriceOutput(salePrice);
        outputView.finalPriceOutput(totalPrice, salePrice);
    }
}
