package christmas.controller;

import christmas.domain.Day;
import christmas.view.input.InputView;

import static christmas.view.output.OutputMessage.ASK_VISIT_DAY_MESSAGE;
import static christmas.view.output.OutputView.printStaticMessage;

public class DayController {
    public static Day getVisitDay() {
        printStaticMessage(ASK_VISIT_DAY_MESSAGE);

        return Day.createDay(Integer.parseInt(InputView.input()));
    }

}
