package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.constants.ErrorMessage;
import christmas.constants.InputPrompts;
import christmas.domain.Menus;
import christmas.domain.Order;
import christmas.domain.Orders;
import christmas.domain.Visit;
import christmas.utils.Parser;
import christmas.utils.Split;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputView {

    private static final String INPUT_PATTERN = "[가-힣]+-[0-9]+";

    public Visit visitInput() {
        while (true) {
            try {
                System.out.println(InputPrompts.WELCOME_AND_RESERVATION_INPUT.getPrompt());
                int day = Parser.stringToInt(Console.readLine());
                return new Visit(day);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public Orders ordersInput(Menus menus) {
        while (true) {
            try {
                System.out.println(InputPrompts.ORDER_MENU_INPUT.getPrompt());
                List<String> inputs = Split.commaSplit(Console.readLine());
                validateInputForm(inputs);
                return getOrders(inputs, menus);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void validateInputForm(List<String> inputs) {
        Pattern pattern = Pattern.compile(INPUT_PATTERN);
        for (String input : inputs) {
            Matcher matcher = pattern.matcher(input);
            if (!matcher.find()) {
                throw new IllegalArgumentException(ErrorMessage.MENU_INPUT_ERROR.getMessage());
            }
        }
    }

    private Orders getOrders(List<String> inputs, Menus menus) {
        List<Order> orders = new ArrayList<>();
        for (String input : inputs) {
            List<String> splitInput = Split.hyphenSplit(input);
            String name = splitInput.get(0);
            int count = Parser.stringToInt(splitInput.get(1));
            orders.add(new Order(name, count, menus));
        }
        return new Orders(orders);
    }
}
