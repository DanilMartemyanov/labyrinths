package backend.academy.maze.services;

import backend.academy.maze.algorithms.generators.GeneratorKruskal;
import backend.academy.maze.algorithms.generators.GeneratorPrim;
import backend.academy.maze.algorithms.solvers.BreadthFirstSearch;
import backend.academy.maze.algorithms.solvers.DepthFirstSearch;
import backend.academy.maze.algorithms.solvers.Dijkstra;
import backend.academy.maze.enums.AlgorithmType;
import backend.academy.maze.enums.AnswerType;
import backend.academy.maze.enums.BoundType;
import backend.academy.maze.enums.CellType;
import backend.academy.maze.enums.FindPathType;
import backend.academy.maze.models.Coordinate;
import backend.academy.maze.models.Edge;
import backend.academy.maze.models.Maze;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserInterface {
    private final PrintStream printStream = new PrintStream(System.out, true, StandardCharsets.UTF_8);
    private final BufferedReader bufferedReader =
        new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
    private final GeneratorKruskal generatorKruskal = new GeneratorKruskal();

    public UserInterface() {
    }

    public void gameVersion1() {
        GeneratorPrim generatorPrim = new GeneratorPrim();
        BreadthFirstSearch breadthFirstSearch;
        ArrayList<Coordinate> path;
        DepthFirstSearch depthFirstSearch;

        while (true) {

            printStream.println("Хэй хэй хэй ! Готовы сгенерировать лабиринт ?");
            printStream.println(Constant.YESNO);

            String ready = CheckInputUser.startGame(bufferedReader, printStream);
            if (AnswerType.YES.answer().equals(ready)) {

                printStream.println(Constant.INPUTSIZEMAZE);
                printStream.print(Constant.INPUTHEIGHT);
                int height = CheckInputUser.getNumberUserSizeMaze(bufferedReader, printStream);
                printStream.print(Constant.INPUTWIDTH);
                int width = CheckInputUser.getNumberUserSizeMaze(bufferedReader, printStream);

                printStream.println("Выберите алгоритм для генерации лабиринта:");
                printStream.println("[1] - алгоритм Прима");
                printStream.println("[2] - алгоритм Крускала");
                printStream.println(Constant.CHOSE);

                AlgorithmType algorithmType = CheckInputUser.getAlgorithmGenerateMazeType(bufferedReader, printStream);

                printStream.println("Выберите алгоритм для поиска пути: ");
                printStream.println("[1] - алгоритм поиска в глубину (DFS)");
                printStream.println("[2] - алгоритм поиска в ширину (BFS)");
                printStream.println(Constant.CHOSE);

                FindPathType findPathType = CheckInputUser.getFindPathMazeType(bufferedReader, printStream);

                Maze maze;

                printStream.println("Ваш лабиринт: ");
                switch (algorithmType) {
                    case PRIM -> maze = generatorPrim.generateMaze(height, width);
                    case KRUSKAL -> maze = generatorKruskal.generateMaze(height, width);
                    default -> throw new IllegalStateException("Unexpected value: " + algorithmType);
                }
                PrintMaze.printMaze(maze);

                String answer = AnswerType.YES.answer();
                while (AnswerType.YES.answer().equals(answer)) {
                    Maze mazeAttempt = new Maze(maze);
                    printStream.println("Укажите конечную точку, где изображено: " + CellType.PASSAGE);
                    printStream.println("Начальная точка \"A\":  ");
                    Coordinate startPoint = CheckInputUser.checkEdnPoint(bufferedReader, printStream, maze);
                    printStream.println("Конечная точка \"B\":  ");
                    Coordinate endPoint = CheckInputUser.checkEdnPoint(bufferedReader, printStream, maze);

                    switch (findPathType) {
                        case DFS -> {
                            depthFirstSearch = new DepthFirstSearch(mazeAttempt);
                            path = depthFirstSearch.findPath(startPoint, endPoint);
                        }

                        case BFS -> {
                            breadthFirstSearch = new BreadthFirstSearch(mazeAttempt);
                            path = breadthFirstSearch.findPath(startPoint, endPoint);
                        }
                        default -> path = null;
                    }

                    if (path != null) {
                        PrintMaze.printPath(path, startPoint, endPoint, mazeAttempt);
                    } else {
                        printStream.println("Упс :( похоже путь не найден, попробуйте указать другие точки");
                    }
                    printStream.println("Хотите найти другой путь ?");
                    printStream.println(Constant.YESNO);
                    answer = CheckInputUser.startGame(bufferedReader, printStream);
                    if (AnswerType.NO.answer().equals(answer)) {
                        break;
                    }
                }
            } else {
                break;
            }
        }
    }

    public void gameVersion2() {
        Dijkstra dijkstra;

        while (true) {
            printStream.println("Хэй хэй хэй ! Новая концепция игры. Готовы продолжить ?");
            printStream.println(Constant.YESNO);

            String ready = CheckInputUser.startGame(bufferedReader, printStream);
            if (AnswerType.YES.answer().equals(ready)) {

                printStream.println(Constant.INPUTSIZEMAZE + " (число >4)");
                printStream.print(Constant.INPUTHEIGHT);
                int height = CheckInputUser.getNumberUserSizeMaze(bufferedReader, printStream);
                printStream.print(Constant.INPUTWIDTH);
                int width = CheckInputUser.getNumberUserSizeMaze(bufferedReader, printStream);

                Maze maze = generatorKruskal.generateMaze(height, width);
                ArrayList<Edge> mst = generatorKruskal.mst();
                PrintMaze.printMaze(maze);

                printStream.println("Укажите границу");
                printStream.println("[1] - верхняя");
                printStream.println("[2] - нижняя");
                printStream.println("[3] - правая");
                printStream.println("[4] - левая");

                BoundType boundType = CheckInputUser.getBoundType(bufferedReader, printStream);

                // подготовка поля с ловушками и подарками
                PrintMaze.changeMazeWithWeight(mst, maze);
                // подготовка входов для игры
                List<Coordinate> passages = PrintMaze.createManyEntrance(maze, boundType);

                PrintMaze.printMaze(maze);

                printStream.println("Выберите вход:");
                Coordinate startEntrance =
                    CheckInputUser.checkEntrance(bufferedReader, printStream, passages, boundType);

                printStream.println("Введите координаты, куда хотите прийти");
                Coordinate endPoint = CheckInputUser.checkEdnPoint(bufferedReader, printStream, maze);

                // корректность координат
                Coordinate startPoint = Checker.makeCorrectCoordinateForEntrance(boundType, startEntrance);

                // Инициализация алгоритма Дейкстры
                dijkstra = new Dijkstra(mst, maze);
                // Путь пользователя
                ArrayList<Coordinate> pathUser = dijkstra.findPath(startPoint, endPoint);
                // дистанция пути пользователя
                int distanceUser = dijkstra.distanceSum();

                // вывод конечного лабиринта вместе с путем пользователя
                PrintMaze.printPath(pathUser, startPoint, endPoint, maze);
                // удаление стартовой точки пользователя из проходов(дверок)
                passages.remove(startEntrance);
                // метод для сравнения путей пользователя и других возможных вариантах
                Map<Coordinate, Integer> possiblePath =
                    WorkWithPath.getBestPath(passages, distanceUser, dijkstra, startPoint, endPoint);
                Coordinate bestPoint = possiblePath.entrySet().iterator().next().getKey();

                if (possiblePath.get(bestPoint) < distanceUser) {
                    printStream.println("Был маршрут оптимальнее");
                    printStream.println("Вход: " + bestPoint + " со стоимостью пути: " + possiblePath.get(bestPoint));
                    printStream.println("Ваш маршрут составил: " + distanceUser);
                } else {
                    printStream.println("Ееееее, ваш маршрут самый крутой!");
                }

            } else {
                break;
            }
        }
    }

    public void startGame() {
        printStream.println("Выберите версию игры: ");
        printStream.println("[1] - версия 1");
        printStream.println("[2]- версия 2");
        printStream.println(Constant.CHOSE);

        String choseUser = CheckInputUser.getNumberAlgorithm(bufferedReader, printStream);

        if ("1".equals(choseUser)) {
            gameVersion1();
        } else {
            gameVersion2();
        }
    }
}
