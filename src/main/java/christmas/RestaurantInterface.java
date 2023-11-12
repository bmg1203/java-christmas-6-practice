package christmas;

import static christmas.enums.events.NoEvent.NO_EVENT;
import static christmas.enums.menu.NoMenu.NO_MENU;

import christmas.enums.menu.MenuItem;
import christmas.event.OneEventResult;
import christmas.order.OrderSystem;
import christmas.order.Orders;
import christmas.order.Receipt;
import christmas.utils.StringToDateParser;
import christmas.utils.StringToOrdersParser;
import christmas.views.InputView;
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
        printAskDate();
        String input = InputView.readLine();
        LocalDate reservationDate = StringToDateParser.makeReservation(YEAR, MONTH, input);

        printAskMenuAndQuantity();
        input = InputView.readLine();
        Orders orders = StringToOrdersParser.parseInputToOrderSet(input);
        Receipt receipt = orderSystem.calculateOrderResult(reservationDate, orders);

        printResult(reservationDate, orders, receipt);
        InputView.close();
    }

    private static void printResult(LocalDate reservationDate, Orders orders, Receipt receipt) {
        printBenefit(reservationDate);
        printOrders(orders);
        printAmountBeforeDiscount(receipt);
        printGiftBenefit(receipt);
        printEventBenefits(receipt);
        printDiscountBenefit(receipt);
        printAfterDiscount(receipt);
        printBadge(reservationDate, receipt);
    }

    private static void printAskDate() {
        OutputView.printOut(Messages.announceHello(RESTAURANT_NAME, MONTH));
        OutputView.printOut(Messages.askDate(MONTH));
    }

    private static void printAskMenuAndQuantity() {
        OutputView.printOut(Messages.askMenuAndQuantity());
    }

    private static void printBenefit(LocalDate reservationDate) {
        OutputView.printOut(Messages.announceEventBenefit(RESTAURANT_NAME, reservationDate));
        OutputView.printOut("");
    }

    private static void printOrders(Orders orders) {
        OutputView.printOut(Messages.announceOrders());
        OutputView.printOut(Messages.repeatAllOrders(orders));
    }

    private static void printAmountBeforeDiscount(Receipt receipt) {
        OutputView.printOut(Messages.announceBeforeDiscount());
        Integer totalPriceBeforeDiscount = receipt.totalPriceBeforeDiscount();
        OutputView.printOut(Messages.showAmount(totalPriceBeforeDiscount));
        OutputView.printOut("");
    }

    private static void printGiftBenefit(Receipt receipt) {
        MenuItem gift = receipt.gift();
        OutputView.printOut(Messages.announceGift());
        String giftResult = NO_MENU.getName();
        if(!gift.equals(NO_MENU)){
            giftResult = Messages.gift(gift, 1);
        }
        OutputView.printOut(giftResult);
        OutputView.printOut("");
    }

    private static void printEventBenefits(Receipt receipt) {
        OutputView.printOut(Messages.announceEventBenefits());
        List<OneEventResult> oneEventResults = receipt.oneEventResults();
        String oneEventResult = NO_EVENT.getName();
        if (!oneEventResults.isEmpty()){
            oneEventResult = Messages.perEventBenefit(oneEventResults);
        }
        OutputView.printOut(oneEventResult);
        OutputView.printOut("");
    }

    private static void printDiscountBenefit(Receipt receipt) {
        OutputView.printOut(Messages.announceTotalDiscountBenefit());
        OutputView.printOut(Messages.showAmount(-receipt.discountBenefit()));
        OutputView.printOut("");
    }

    private static void printAfterDiscount(Receipt receipt) {
        OutputView.printOut(Messages.AfterDiscountAmount());
        OutputView.printOut(Messages.showAmount(receipt.totalPriceBeforeDiscount() - receipt.discountBenefit()));
        OutputView.printOut("");
    }

    private static void printBadge(LocalDate reservationDate, Receipt receipt) {
        OutputView.printOut(Messages.announceEventBadge(reservationDate.getMonthValue()));
        OutputView.printOut(receipt.badge().getName());
    }
}
