package backend.academy.maze;

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
    private final GeneratorPrim generatorPrim = new GeneratorPrim();
    private final GeneratorKruskal generatorKruskal = new GeneratorKruskal();
    private DepthFirstSearch depthFirstSearch;
    private BreadthFirstSearch breadthFirstSearch;
    private ArrayList<Coordinate> path;
    private Dijkstra dijkstra;

    public UserInterface() {
    }

    public void gameVersion1() {
        while (true) {

            printStream.println("Хэй хэй хэй ! Готовы сгенерировать лабиринт ?");
            printStream.println(Constant.YESNO);

            String ready = InputUser.startGame(bufferedReader, printStream);

            if (Constant.YES.equals(ready)) {

                printStream.println(Constant.INPUTSIZEMAZE);
                printStream.print(Constant.INPUTHEIGHT);
                int height = InputUser.getNumberUserSizeMaze(bufferedReader, printStream);
                printStream.print(Constant.INPUTWIDTH);
                int width = InputUser.getNumberUserSizeMaze(bufferedReader, printStream);

                printStream.println("Выберите алгоритм для генерации лабиринта:");
                printStream.println("[1] - алгоритм Прима");
                printStream.println("[2] - алгоритм Крускала");
                printStream.println(Constant.CHOSE);

                AlgorithmType algorithmType = InputUser.getAlgorithmGenerateMazeType(bufferedReader, printStream);

                printStream.println("Выберите алгоритм для поиска пути: ");
                printStream.println("[1] - алгоритм поиска в глубину (DFS)");
                printStream.println("[2] - алгоритм поиска в ширину (BFS)");
                printStream.println(Constant.CHOSE);

                FindPathType findPathType = InputUser.getFindPathMazeType(bufferedReader, printStream);

                Maze maze;

                printStream.println("Ваш лабиринт: ");
                switch (algorithmType) {
                    case PRIM -> maze = generatorPrim.generateMaze(height, width);
                    case KRUSKAL -> maze = generatorKruskal.generateMaze(height, width);
                    default -> throw new IllegalStateException("Unexpected value: " + algorithmType);
                }
                PrintMaze.printMaze(maze);

                String answer = Constant.YES;
                while (Constant.YES.equals(answer)) {
                    Maze mazeAttempt = new Maze(maze);
                    printStream.println("Укажите конечную точку, где изображено: " + Constant.PASSAGE);
                    printStream.println("Начальная точка \"A\":  ");
                    Coordinate startPoint = InputUser.getUserCoordinate(bufferedReader, printStream);
                    printStream.println("Конечная точка \"B\":  ");
                    Coordinate endPoint = InputUser.getUserCoordinate(bufferedReader, printStream);

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
                    answer = InputUser.startGame(bufferedReader, printStream);
                    if (Constant.NO.equals(answer)) {
                        break;
                    }
                }
            } else {
                break;
            }
        }
    }

    public void gameVersion2() {
        while (true) {
            printStream.println("Хэй хэй хэй ! Новая концепция игры. Готовы продолжить ?");
            printStream.println(Constant.YESNO);

            String ready = InputUser.startGame(bufferedReader, printStream);
            if (Constant.YES.equals(ready)) {

                printStream.println(Constant.INPUTSIZEMAZE);
                printStream.print(Constant.INPUTHEIGHT);
                int height = InputUser.getNumberUserSizeMaze(bufferedReader, printStream);
                printStream.print(Constant.INPUTWIDTH);
                int width = InputUser.getNumberUserSizeMaze(bufferedReader, printStream);

                Maze maze = generatorKruskal.generateMaze(height, width);
                ArrayList<Edge> mst = generatorKruskal.mst();
                PrintMaze.printMaze(maze);

                printStream.println("Укажите границу");
                printStream.println("[1] - верхняя");
                printStream.println("[2] - нижняя");
                printStream.println("[3] - правая");
                printStream.println("[4] - левая");

                BoundType boundType = InputUser.getBoundType(bufferedReader, printStream);

                // подготовка поля с ловушками и подарками
                PrintMaze.changeMazeWithWeight(mst, maze);
                // подготовка входов для игры
                List<Coordinate> passages = PrintMaze.createManyEntrance(maze, boundType);

                PrintMaze.printMaze(maze);

                printStream.println("Выберите вход:");
                Coordinate startEntrance = Checker.checkEntrance(bufferedReader, printStream, passages, boundType);

                printStream.println("Введите координаты, куда хотите прийти");
                Coordinate endPoint = Checker.checkEdnPoint(bufferedReader, printStream, maze);

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

        String choseUser = InputUser.getNumberAlgorithm(bufferedReader, printStream);

        if ("1".equals(choseUser)) {
            gameVersion1();
        } else {
            gameVersion2();
        }
    }
}
