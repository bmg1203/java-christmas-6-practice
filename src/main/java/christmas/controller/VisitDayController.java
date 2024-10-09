package christmas.controller;

import christmas.domain.VisitDay;
import christmas.global.exception.InputException;
import christmas.global.handler.ExceptionHandler;
import christmas.view.input.InputView;

import static christmas.global.exception.ErrorMessage.INVALID_DAY_ERROR;
import static christmas.view.output.OutputMessage.ASK_VISIT_DAY_MESSAGE;
import static christmas.view.output.OutputView.printStaticMessage;

public class VisitDayController {

    public static VisitDay requestVisitDay() {
        return ExceptionHandler.execute(VisitDayController::getVisitDay);
    }

    private static VisitDay getVisitDay() {
        printStaticMessage(ASK_VISIT_DAY_MESSAGE);

        String input = InputView.input();

        if(!isDigit(input)) throw new InputException(INVALID_DAY_ERROR);

        int day = Integer.parseInt(input);

        if(!isValidDay(day)) throw new InputException(INVALID_DAY_ERROR);

        return VisitDay.createVisitDay(day);
    }

    private static boolean isDigit(String input) {
        return input.matches("[0-9]+");
    }

    private static boolean isValidDay(int day) {
        return day >= 1 && day <= 31;
    }

}
