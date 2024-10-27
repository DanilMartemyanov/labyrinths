package backend.academy.game;

import backend.academy.maze.algorithms.generators.MazeGeneratorBasedOnKruskal;
import backend.academy.maze.services.ConsoleInputUserImpl;
import backend.academy.maze.services.FactoryCoordinate;
import backend.academy.maze.services.InputCommandUserImpl;
import backend.academy.maze.services.MazePrinterRender;
import backend.academy.maze.services.MazeTypeProviderImpl;
import backend.academy.maze.services.interfaces.ConsoleInputUser;
import backend.academy.maze.services.interfaces.InputCommandUser;
import backend.academy.maze.services.interfaces.MazePrinter;
import backend.academy.maze.services.interfaces.MazeTypeProvider;

public interface LogicGame {
    MazeGeneratorBasedOnKruskal MAZE_GENERATOR_BASED_ON_KRUSKAL = new MazeGeneratorBasedOnKruskal();
    ConsoleInputUser CONSOLE_INPUT_USER = new ConsoleInputUserImpl();
    InputCommandUser INPUT_COMMAND_USER = new InputCommandUserImpl(CONSOLE_INPUT_USER);
    MazeTypeProvider MAZE_TYPE_PROVIDER = new MazeTypeProviderImpl(INPUT_COMMAND_USER);
    FactoryCoordinate FACTORY_COORDINATE = new FactoryCoordinate(INPUT_COMMAND_USER, CONSOLE_INPUT_USER);
    MazePrinter MAZE_RENDER = new MazePrinterRender();

    void startGame();
}
