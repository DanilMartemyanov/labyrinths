package backend.academy.maze.services;

import backend.academy.maze.enums.BoundType;
import backend.academy.maze.enums.CellType;
import backend.academy.maze.models.Coordinate;
import backend.academy.maze.models.Edge;
import backend.academy.maze.models.Maze;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
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
                        PRINT_STREAM.print(CellType.PASSAGE.value());
                        break;
                    case WALL:
                        PRINT_STREAM.print(CellType.WALL.value());
                        break;
                    case A:
                        PRINT_STREAM.print(CellType.A.value());
                        break;
                    case B:
                        PRINT_STREAM.print(CellType.B.value());
                        break;
                    case GLASS:
                        PRINT_STREAM.print(CellType.GLASS.value());
                        break;
                    case BOMB:
                        PRINT_STREAM.print(CellType.BOMB.value());
                        break;
                    case GIFT:
                        PRINT_STREAM.print(CellType.GIFT.value());
                        break;
                    case ENTRANCE:
                        PRINT_STREAM.print(CellType.ENTRANCE.value());
                        break;
                    default:
                        break;
                }
            }
            PRINT_STREAM.println();
        }
    }

    public static void changeMazeWithWeight(List<Edge> edges, Maze maze) {
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
    public static void printPath(List<Coordinate> path, Coordinate start, Coordinate end, Maze maze) {
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

    public static List<Coordinate> getLine(Maze maze, BoundType boundType) {
        List<Coordinate> line = new ArrayList<>();
        int start = 1;
        int endWidth = maze.width() - 1;
        int endHeight = maze.height() - 1;
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
