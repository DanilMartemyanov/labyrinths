package backend.academy.maze;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;

public class UserInterface {
    private final PrintStream printStream = new PrintStream(System.out);
    private final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private final GeneratorPrim generatorPrim = new GeneratorPrim();
    private final GeneratorKruskal generatorKruskal = new GeneratorKruskal();
    private  DepthFirstSearch depthFirstSearch;
    private ArrayList<Coordinate> path;

    public UserInterface() {
    }

    public void startGame() {
        while (true) {

            printStream.println("Хэй хэй хэй ! Готовы сгенерировать лабиринт ?");
            printStream.println("Введите y/n");

            String ready = InputUser.startGame(bufferedReader, printStream);

            if (ready.equals(Constant.YES)) {

                printStream.println("Укажите размер лабиринта");
                printStream.print("Введите длину: ");
                int height = InputUser.getNumberUser(bufferedReader, printStream);
                printStream.print("Введите ширину: ");
                int width = InputUser.getNumberUser(bufferedReader, printStream);

                printStream.println("Выберите алгоритм для генерации лабиринта:");
                printStream.println("[1] - алгоритм Прима");
                printStream.println("[2] - алгоритм Крускала");
                printStream.println("Ваш выбор: ");

                AlgorithmType algorithmType = InputUser.algorithmGenerateMazeType(bufferedReader, printStream);

                printStream.println("Выберите алгоритм для поиска пути: ");
                printStream.println("[1] - алгоритм поиска в глубину");
                printStream.println("Ваш выбор: ");

                FindPathType findPathType = InputUser.findPathMazeType(bufferedReader, printStream);

                Maze maze;

                printStream.println("Ваш лабиринт: ");
                switch (algorithmType) {
                    case PRIM -> maze = generatorPrim.generateMaze(height, width);
                    case KRUSKAL -> maze = generatorKruskal.generateMaze(height, width);
                    default -> maze = null;

                }
                depthFirstSearch = new DepthFirstSearch(maze);

                printStream.println("Укажите точки, где изображено: " + Constant.PASSAGE);
                printStream.println("Начальная точка \"A\":  ");
                Coordinate startPoint = InputUser.getUserCoordinate(bufferedReader, printStream);
                printStream.println("Конечная точка \"B\":  ");
                Coordinate endPoint = InputUser.getUserCoordinate(bufferedReader, printStream);

                switch (findPathType) {
                    case DFS -> path = depthFirstSearch.findPath(startPoint, endPoint);
                    default -> path = null;
                }

                if (path != null) {
                    depthFirstSearch.printPath(path, startPoint, endPoint);
                }else {
                    printStream.println("Упс :( похоже путь не найден, попробуйте указать другие точки");
                }

            } else {
                break;
            }
        }
    }
}
