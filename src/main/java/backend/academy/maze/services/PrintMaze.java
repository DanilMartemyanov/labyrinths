package backend.academy.maze.services;

import backend.academy.maze.enums.BoundType;
import backend.academy.maze.enums.CellType;
import backend.academy.maze.models.Coordinate;
import backend.academy.maze.models.Edge;
import backend.academy.maze.models.Maze;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.experimental.UtilityClass;

@UtilityClass
@Getter
public class PrintMaze {
    private static final PrintStream PRINT_STREAM = new PrintStream(System.out, true, StandardCharsets.UTF_8);


    @SuppressFBWarnings("UCPM_USE_CHARACTER_PARAMETERIZED_METHOD")
    public static void printMaze(Maze maze) {
        Maze copyMaze = new Maze(maze);
        for (int i = 0; i < copyMaze.height(); i++) {
            for (int j = 0; j < copyMaze.width(); j++) {
                switch (copyMaze.grid()[i][j].type()) {
                    case PASSAGE:
                        PRINT_STREAM.print(CellType.PASSAGE);
                        break;
                    case WALL:
                        PRINT_STREAM.print(CellType.WALL);
                        break;
                    case A:
                        PRINT_STREAM.print(CellType.A);
                        break;
                    case B:
                        PRINT_STREAM.print(CellType.B);
                        break;
                    case GLASS:
                        PRINT_STREAM.print(CellType.GLASS);
                        break;
                    case BOMB:
                        PRINT_STREAM.print(CellType.BOMB);
                        break;
                    case GIFT:
                        PRINT_STREAM.print(CellType.GIFT);
                        break;
                    case ENTRANCE:
                        PRINT_STREAM.print(CellType.ENTRANCE);
                        break;
                    default:
                        break;
                }
            }
            PRINT_STREAM.println();
        }
    }

    public static void changeMazeWithWeight(ArrayList<Edge> edges, Maze maze) {
        for (Edge edge : edges) {
            Coordinate from = edge.firstNode();
            Coordinate to = edge.secondNode();
            int row = from.row() + (to.row() - from.row()) / 2;
            int column = from.column() + (to.column() - from.column()) / 2;
            switch (edge.weight() % 2) {
                case 1:
                    maze.grid()[row][column].type(CellType.GIFT);
                    break;
                case 0:
                    maze.grid()[row][column].type(CellType.BOMB);
                    break;
                default:
                    maze.grid()[row][column].type(CellType.PASSAGE);
            }
        }
    }

    // Метод для вывода пути на сетку
    public static void printPath(ArrayList<Coordinate> path, Coordinate start, Coordinate end, Maze maze) {
        Maze copyMaze = new Maze(maze);
        copyMaze.grid()[start.row()][start.column()].type(CellType.A);
        copyMaze.grid()[end.row()][end.column()].type(CellType.B);

        path.remove(start);
        path.remove(end);

        for (Coordinate coordinate : path) {
            copyMaze.grid()[coordinate.row()][coordinate.column()].type(CellType.GLASS);
        }
        printMaze(copyMaze);
    }

    public static List<Coordinate> createManyEntrance(Maze maze, BoundType boundType) {
        SecureRandom random = new SecureRandom();
        List<Coordinate> line = getLine(maze, boundType);
        List<Coordinate> passages = new ArrayList<>();
        List<Coordinate> passagesForPrint = new ArrayList<>();
        int count = 0;

        while (count < Constant.NUMBER_3) {
            Coordinate coordinate = line.get(random.nextInt(line.size()));
            Coordinate newEntrance = null;
            if (maze.grid()[coordinate.row()][coordinate.column()].type() == CellType.PASSAGE) {
                switch (boundType) {
                    case BoundType.UP:
                        newEntrance = new Coordinate(coordinate.row() - 1, coordinate.column());
                        break;
                    case BoundType.DOWN:
                        newEntrance = new Coordinate(coordinate.row() + 1, coordinate.column());
                        break;
                    case BoundType.RIGHT:
                        newEntrance = new Coordinate(coordinate.row(), coordinate.column() + 1);
                        break;
                    case BoundType.LEFT:
                        newEntrance = new Coordinate(coordinate.row(), coordinate.column() - 1);
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + boundType);
                }

                // Устанавливаем тип клетки на ENTRANCE и добавляем в списки
                if (newEntrance != null) {
                    maze.grid()[newEntrance.row()][newEntrance.column()].type(CellType.ENTRANCE);
                    passages.add(coordinate);
                    passagesForPrint.add(newEntrance);
                    count++;
                }
            }
        }
        PRINT_STREAM.println("Координаты дверок");
        PRINT_STREAM.println(passagesForPrint);
        return passages;
    }

    public static List<Coordinate> getLine(Maze maze, BoundType boundType) {
        List<Coordinate> line = new ArrayList<>();
        int start = 1;
        int endWidth = maze.width() - 2;
        int endHeight = maze.height() - 2;
        switch (boundType) {
            case BoundType.UP -> line = getHorizontalLine(start, endWidth, 1, line);
            case BoundType.DOWN -> line = getHorizontalLine(start, endWidth, endHeight, line);
            case BoundType.RIGHT -> line = getVerticalLine(start, endHeight, endWidth, line);
            case BoundType.LEFT -> line = getVerticalLine(start, endHeight, 1, line);
            default -> line = null;
        }
        return line;
    }

    public static List<Coordinate> getHorizontalLine(int start, int end, int row, List<Coordinate> line) {
        for (int i = start; i < end; i++) {
            line.add(new Coordinate(row, i));
        }
        return line;
    }

    public static List<Coordinate> getVerticalLine(int start, int end, int column, List<Coordinate> line) {
        for (int i = start; i < end; i++) {
            line.add(new Coordinate(i, column));
        }
        return line;
    }
}
