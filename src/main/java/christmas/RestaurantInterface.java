package christmas;

import static christmas.enums.events.NoEvent.NO_EVENT;
import static christmas.enums.menu.NoMenu.NO_MENU;

import camp.nextstep.edu.missionutils.Console;
import christmas.enums.events.NoEvent;
import christmas.enums.menu.MenuItem;
import christmas.enums.menu.NoMenu;
import christmas.event.OneEventResult;
import christmas.order.OrderSystem;
import christmas.order.Orders;
import christmas.order.Receipt;
import christmas.utils.StringToDateParser;
import christmas.utils.StringToOrdersParser;
import christmas.views.Messages;
import christmas.views.OutputView;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class RestaurantInterface {
    private final static Integer YEAR = 2023;
    private final static Integer MONTH = Month.DECEMBER.getValue();
    private final static String RESTAURANT_NAME = "우테코 식당";
    private final OrderSystem orderSystem;

    public RestaurantInterface(OrderSystem orderSystem) {
        this.orderSystem = orderSystem;
    }

    public void process(){
        OutputView.printOut(Messages.announceHello(RESTAURANT_NAME, MONTH));
        OutputView.printOut(Messages.askDate(MONTH));
        String input = this.readLine();

        LocalDate reservationDate = StringToDateParser.makeReservation(YEAR, MONTH, input);
        OutputView.printOut(Messages.askMenuAndQuantity());

        input = this.readLine();
        Orders orders = StringToOrdersParser.parseInputToOrderSet(input);
        OutputView.printOut(Messages.announceEventBenefit(RESTAURANT_NAME,reservationDate));
        OutputView.printOut("");
        OutputView.printOut(Messages.announceOrders());
        OutputView.printOut(Messages.repeatAllOrders(orders));


        OutputView.printOut(Messages.announceBeforeDiscount());
        Receipt receipt = orderSystem.calculateOrderResult(reservationDate, orders);
        Integer totalPriceBeforeDiscount = receipt.totalPriceBeforeDiscount();
        OutputView.printOut(Messages.showAmount(totalPriceBeforeDiscount));
        OutputView.printOut("");

        MenuItem gift = receipt.gift();
        OutputView.printOut(Messages.announceGift());
        String giftResult = NO_MENU.getName();
        if(!gift.equals(NO_MENU)){
            giftResult = Messages.gift(gift, 1);
        }
        OutputView.printOut(giftResult);
        OutputView.printOut("");

        OutputView.printOut(Messages.announceEventBenefits());
        List<OneEventResult> oneEventResults = receipt.oneEventResults();
        String oneEventResult = NO_EVENT.getName();
        if (!oneEventResults.isEmpty()){
            oneEventResult = Messages.perEventBenefit(oneEventResults);
        }
        OutputView.printOut(oneEventResult);
        OutputView.printOut("");

        OutputView.printOut(Messages.announceTotalDiscountBenefit());
        OutputView.printOut(Messages.showAmount(-receipt.discountBenefit()));
        OutputView.printOut("");

        OutputView.printOut(Messages.AfterDiscountAmount());
        OutputView.printOut(Messages.showAmount(receipt.totalPriceBeforeDiscount() - receipt.discountBenefit()));
        OutputView.printOut("");

        OutputView.printOut(Messages.announceEventBadge(reservationDate.getMonthValue()));
        OutputView.printOut(receipt.badge().getName());

        this.close();
    }

    private String readLine(){
       return Console.readLine();
    }

    private void close(){
        Console.close();
    }
}
