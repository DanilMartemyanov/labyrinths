package backend.academy.game;


import backend.academy.game.version1.GameVersion1;
import backend.academy.game.version2.GameVersion2;
import backend.academy.maze.services.ConsoleInputUserImpl;
import backend.academy.maze.services.Constant;
import backend.academy.maze.services.InputCommandUserImpl;
import backend.academy.maze.services.interfaces.ConsoleInputUser;
import backend.academy.maze.services.interfaces.InputCommandUser;

public class UserInterface {
    private final ConsoleInputUser consoleInputUser = new ConsoleInputUserImpl();
    private final InputCommandUser inputCommandUser = new InputCommandUserImpl(consoleInputUser);
    private final GameVersion1 gameVersion1 = new GameVersion1();
    private final GameVersion2 gameVersion2 = new GameVersion2();

    public void startGame() {
        consoleInputUser.printlnMessage("Выберите версию игры: ");
        consoleInputUser.printlnMessage("[1] - версия 1");
        consoleInputUser.printlnMessage("[2]- версия 2");
        consoleInputUser.printlnMessage(Constant.CHOSE);

        String choseUser = inputCommandUser.getNumberAlgorithm();

        if ("1".equals(choseUser)) {
            gameVersion1.startGame();
        } else {
            gameVersion2.startGame();
        }
    }
}
