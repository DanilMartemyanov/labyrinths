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
    private final PrintMaze printMaze = new PrintMaze();
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
                    default -> maze = null;
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
                ArrayList<Edge> mst = generatorKruskal.mst;
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
                List<Coordinate> passages = PrintMaze.createManyPassage(maze, boundType);

                PrintMaze.printMaze(maze);
                //  TODO: сделать чек на вход, сделать

                printStream.println("Выберите вход:");
                boolean flag = true;
                Coordinate startEntrance = null;
                while (flag){
                     startEntrance = InputUser.getUserCoordinate(bufferedReader, printStream);
                    flag = Checker.checkOnEntrance(passages, startEntrance);
                    if (!flag) {
                        printStream.println("Укажите координаты дверки: " + Constant.ENTRANCE);
                    }
                }

                printStream.println("Введите координаты, куда хотите прийти");
                Coordinate endPoint = InputUser.getUserCoordinate(bufferedReader, printStream);
                // корректность координат
                Coordinate startPoint = Checker.checkEntrance(boundType, startEntrance);
                dijkstra = new Dijkstra(mst, maze);
                ArrayList<Coordinate> pathUser = dijkstra.findPath(startPoint, endPoint);
                int distanceUser = dijkstra.distanceSum;
                PrintMaze.printPath(pathUser, startPoint, endPoint, maze);
                passages.remove(startEntrance);

                Map<Coordinate, Integer> possiblePath =
                    WorkWithPath.getBestPath(passages, distanceUser, dijkstra, startPoint, endPoint, boundType);
                Coordinate bestPoint = possiblePath.keySet().iterator().next();

                if (bestPoint != startPoint) {
                    printStream.println("Был маршрут оптимальнее");
                    printStream.println("Вход: " + bestPoint + " со стоимостью пути: " + possiblePath.get(bestPoint));
                } else {
                    printStream.println("Ееееее, ваш маршрут самый крутой!");
                }

            } else {
                break;
            }
        }
    }
}
