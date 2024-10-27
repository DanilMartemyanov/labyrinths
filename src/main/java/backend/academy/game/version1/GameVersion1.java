package backend.academy.game.version1;

import backend.academy.game.LogicGame;
import backend.academy.maze.enums.AlgorithmType;
import backend.academy.maze.enums.AnswerType;
import backend.academy.maze.enums.CellType;
import backend.academy.maze.enums.FindPathType;
import backend.academy.maze.models.Coordinate;
import backend.academy.maze.models.Maze;
import backend.academy.maze.services.Constant;
import java.util.List;

public class GameVersion1 implements LogicGame {
    @Override
    public void startGame() {
        while (true) {
            List<Coordinate> path;
            CONSOLE_INPUT_USER.printlnMessage("Хэй хэй хэй ! Готовы сгенерировать лабиринт ?");
            CONSOLE_INPUT_USER.printlnMessage(Constant.YESNO);

            String ready = INPUT_COMMAND_USER.getCommandStartGame();
            if (AnswerType.YES.answer().equals(ready)) {
                // ввод размера лабиринта
                CONSOLE_INPUT_USER.printlnMessage(Constant.INPUTSIZEMAZE);
                CONSOLE_INPUT_USER.printMessage(Constant.INPUTHEIGHT);
                int height = INPUT_COMMAND_USER.getNumberUserSizeMaze();
                CONSOLE_INPUT_USER.printMessage(Constant.INPUTWIDTH);
                int width = INPUT_COMMAND_USER.getNumberUserSizeMaze();

                // выбор алгоритма генерации лабиринта
                GameOutput.choicesGenerator(CONSOLE_INPUT_USER);
                AlgorithmType algorithmType = MAZE_TYPE_PROVIDER.getGeneratorMazeType();

                // выбор поисковика
                GameOutput.choicesSearcher(CONSOLE_INPUT_USER);
                FindPathType findPathType = MAZE_TYPE_PROVIDER.getSearcherType();

                Maze maze;

                CONSOLE_INPUT_USER.printlnMessage("Ваш лабиринт: ");
                maze = MazeServices.generateMaze(height, width, algorithmType);
                MAZE_RENDER.printMaze(maze);

                String answer = AnswerType.YES.answer();
                while (AnswerType.YES.answer().equals(answer)) {
                    Maze mazeAttempt = new Maze(maze);
                    CONSOLE_INPUT_USER.printlnMessage(
                        "Укажите конечную точку, где изображено: " + CellType.PASSAGE.value());
                    CONSOLE_INPUT_USER.printlnMessage("Начальная точка \"A\":  ");
                    Coordinate startPoint = FACTORY_COORDINATE.getUserPoint(maze);
                    CONSOLE_INPUT_USER.printlnMessage("Конечная точка \"B\":  ");
                    Coordinate endPoint = FACTORY_COORDINATE.getUserPoint(maze);

                    // Поиск пути
                    path = MazeServices.findPath(findPathType, mazeAttempt, startPoint, endPoint);

                    if (!path.isEmpty()) {
                        MAZE_RENDER.printPath(path, startPoint, endPoint, mazeAttempt);
                    } else {
                        CONSOLE_INPUT_USER.printlnMessage(
                            "Упс :( похоже путь не найден, попробуйте указать другие точки");
                    }
                    CONSOLE_INPUT_USER.printlnMessage("Хотите найти другой путь ?");
                    CONSOLE_INPUT_USER.printlnMessage(Constant.YESNO);
                    answer = INPUT_COMMAND_USER.getCommandStartGame();
                    if (AnswerType.NO.answer().equals(answer)) {
                        break;
                    }
                }
            } else {
                break;
            }
        }
    }
}

