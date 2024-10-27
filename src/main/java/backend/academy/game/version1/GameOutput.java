package backend.academy.game.version1;

import backend.academy.maze.services.Constant;
import backend.academy.maze.services.interfaces.ConsoleInputUser;
import lombok.experimental.UtilityClass;

@UtilityClass
public class GameOutput {

    public static void choicesGenerator(ConsoleInputUser consoleInputUser) {
        consoleInputUser.printlnMessage("Выберите алгоритм для генерации лабиринта:");
        consoleInputUser.printlnMessage("[1] - алгоритм Прима");
        consoleInputUser.printlnMessage("[2] - алгоритм Крускала");
        consoleInputUser.printlnMessage(Constant.CHOSE);
    }

    public static void choicesSearcher(ConsoleInputUser consoleInputUser) {
        consoleInputUser.printlnMessage("Выберите алгоритм для поиска пути: ");
        consoleInputUser.printlnMessage("[1] - алгоритм поиска в глубину (DFS)");
        consoleInputUser.printlnMessage("[2] - алгоритм поиска в ширину (BFS)");
        consoleInputUser.printlnMessage(Constant.CHOSE);
    }
}
