package backend.academy.game.version2;

import backend.academy.maze.services.interfaces.ConsoleInputUser;
import lombok.experimental.UtilityClass;

@UtilityClass
public class GameOutput {
    public static void choiceBoundText(ConsoleInputUser consoleInputUser) {
        consoleInputUser.printlnMessage("Укажите границу");
        consoleInputUser.printlnMessage("[1] - верхняя");
        consoleInputUser.printlnMessage("[2] - нижняя");
        consoleInputUser.printlnMessage("[3] - правая");
        consoleInputUser.printlnMessage("[4] - левая");
    }
}
