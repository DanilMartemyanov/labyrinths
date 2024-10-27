package backend.academy.maze.services.validators;

import java.util.regex.Pattern;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class InputUserValidator {
    private static final Pattern PATTERNSTARTGAME = Pattern.compile("^[ynYN]$");
    private static final Pattern PATTERNNUMBERALGORITHM = Pattern.compile("^[12]$");
    private static final Pattern PATTERNNUMBERBOUND = Pattern.compile("^[1234]$");
    private static final Pattern PATTERNNUMBERUSER = Pattern.compile("^[0-9]\\d*$");
    private static final Pattern PATTERNNUMBERSIZEMAZE = Pattern.compile("^[4-9]\\d*$|^[1-9]\\d{1,}$$");

    public static boolean isValidStartCommand(String input) {
        return PATTERNSTARTGAME.matcher(input).matches();
    }

    public static boolean isValidNumberAlgorithm(String input) {
        return PATTERNNUMBERALGORITHM.matcher(input).matches();
    }

    public static boolean isValidNumberBound(String input) {
        return PATTERNNUMBERBOUND.matcher(input).matches();
    }

    public static boolean isValidNumberUser(String input) {
        return PATTERNNUMBERUSER.matcher(input).matches();
    }

    public static boolean isValidNumberSizeMaze(String input) {
        return PATTERNNUMBERSIZEMAZE.matcher(input).matches();
    }

}
