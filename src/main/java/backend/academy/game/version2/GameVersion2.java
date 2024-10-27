package backend.academy.game.version2;

import backend.academy.game.LogicGame;
import backend.academy.maze.algorithms.solvers.SearcherPathBasedOnDijkstra;
import backend.academy.maze.enums.AnswerType;
import backend.academy.maze.enums.BoundType;
import backend.academy.maze.models.Coordinate;
import backend.academy.maze.models.Edge;
import backend.academy.maze.models.Maze;
import backend.academy.maze.services.Constant;
import backend.academy.maze.services.WorkWithPath;
import backend.academy.maze.services.validators.CoordinateValidator;
import java.util.List;
import java.util.Map;

public class GameVersion2 implements LogicGame {

    @Override
    public void startGame() {

        SearcherPathBasedOnDijkstra searcherPathBasedOnDijkstra;

        while (true) {
            CONSOLE_INPUT_USER.printlnMessage("Хэй хэй хэй ! Новая концепция игры. Готовы продолжить ?");
            CONSOLE_INPUT_USER.printlnMessage(Constant.YESNO);

            String ready = INPUT_COMMAND_USER.getCommandStartGame();
            if (AnswerType.YES.answer().equals(ready)) {

                CONSOLE_INPUT_USER.printlnMessage(Constant.INPUTSIZEMAZE + " (число >4)");
                CONSOLE_INPUT_USER.printMessage(Constant.INPUTHEIGHT);
                int height = INPUT_COMMAND_USER.getNumberUserSizeMaze();
                CONSOLE_INPUT_USER.printMessage(Constant.INPUTWIDTH);
                int width = INPUT_COMMAND_USER.getNumberUserSizeMaze();

                Maze maze = MAZE_GENERATOR_BASED_ON_KRUSKAL.generateMaze(height, width);
                List<Edge> mst = MAZE_GENERATOR_BASED_ON_KRUSKAL.mst();
                MAZE_RENDER.printMaze(maze);

                // Выбор, какую границу будем удалять
                GameOutput.choiceBoundText(CONSOLE_INPUT_USER);
                BoundType boundType = MAZE_TYPE_PROVIDER.getBoundType();


                // подготовка поля с ловушками и подарками
                MAZE_RENDER.printMazeWithWeight(mst, maze);
                // подготовка входов для игры
                List<Coordinate> passages = maze.createManyEntrance(boundType);

                MAZE_RENDER.printMaze(maze);

                CONSOLE_INPUT_USER.printlnMessage("Выберите вход:");
                Coordinate startEntrance =
                    FACTORY_COORDINATE.getUserCoordinateEntrance(passages, boundType);

                CONSOLE_INPUT_USER.printlnMessage("Введите координаты, куда хотите прийти");
                Coordinate endPoint = FACTORY_COORDINATE.getUserPoint(maze);

                // корректность координат
                Coordinate startPoint = CoordinateValidator.makeCorrectCoordinateForEntrance(boundType, startEntrance);

                // Инициализация алгоритма Дейкстры
                searcherPathBasedOnDijkstra = new SearcherPathBasedOnDijkstra(mst, maze);

                // Путь пользователя
                List<Coordinate> pathUser = searcherPathBasedOnDijkstra.findPath(startPoint, endPoint);

                // дистанция пути пользователя
                int distanceUser = searcherPathBasedOnDijkstra.distanceSum();

                // вывод конечного лабиринта вместе с путем пользователя
                MAZE_RENDER.printPath(pathUser, startPoint, endPoint, maze);

                // удаление стартовой точки пользователя из проходов(дверок)
                passages.remove(startEntrance);

                // метод для сравнения путей пользователя и других возможных вариантах
                Map<Coordinate, Integer> possiblePath =
                    WorkWithPath.getBestPath(passages, distanceUser, searcherPathBasedOnDijkstra, startPoint, endPoint);
                Coordinate bestPoint = possiblePath.entrySet().iterator().next().getKey();

                // подсчет результат
                ScoreManager.calculatingBestPath(possiblePath, bestPoint, distanceUser, CONSOLE_INPUT_USER);

            } else {
                break;
            }
        }

    }
}
