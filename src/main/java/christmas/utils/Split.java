package christmas.utils;

import java.util.List;

public class Split {

    private static final String COMMA = ",";
    private static final String HYPHEN = "-";

    public static List<String> commaSplit(String input) {
        input = input.replaceAll(" ", "");
        return List.of(input.split(COMMA));
    }

    public static List<String> hyphenSplit(String input) {
        return List.of(input.split(HYPHEN));
    }
}
