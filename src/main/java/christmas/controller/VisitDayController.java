package christmas.controller;

import christmas.domain.VisitDay;
import christmas.view.input.InputView;

import static christmas.view.output.OutputMessage.ASK_VISIT_DAY_MESSAGE;
import static christmas.view.output.OutputView.printStaticMessage;

public class VisitDayController {
    public static VisitDay getVisitDay() {
        printStaticMessage(ASK_VISIT_DAY_MESSAGE);

        return VisitDay.createVisitDay(Integer.parseInt(InputView.input()));
    }

}