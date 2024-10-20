package backend.academy.maze;

public final class Constant {
    public static final int STEP_1 = 1;
    public static final int STEP_2 = 2;
    public static final int BOUND_3 = 3;
    public static final String YES = "y";
    public static final String NO = "n";
    public static final String INCORRECTINPUT = "Некорректный ввод, введите значение заново";
    public static final String COORDINATE = "Введите координаты по ";
    public static final String WALL = "\u26D4";
    public static final String PASSAGE = "\u2705";
    public static final String CAKE = "\uD83C\uDF82";
    public static final String PERSON = "\uD83C\uDFC3\u200D♂\uFE0F";
    public static final String GLASS = "\uD83D\uDD0E";
    public static final String CHOSE = "Ваш выбор: ";
    public static final String YESNO = "Введите y/n";


    private Constant() {
        throw new IllegalStateException("Utility class");
    }
}
