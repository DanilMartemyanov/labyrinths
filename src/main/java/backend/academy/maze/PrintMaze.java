package backend.academy.maze;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class PrintMaze {
    private static final PrintStream PRINT_STREAM = new PrintStream(System.out, true, StandardCharsets.UTF_8);

    @SuppressFBWarnings("UCPM_USE_CHARACTER_PARAMETERIZED_METHOD")
    public static void printMaze(Maze maze) {
        Maze copyMaze = new Maze(maze);
        for (int i = 0; i < copyMaze.height(); i++) {
            for (int j = 0; j < copyMaze.width(); j++) {
                switch (copyMaze.grid[i][j].type) {
                    case PASSAGE:
                        PRINT_STREAM.print(Constant.PASSAGE);
                        break;
                    case WALL:
                        PRINT_STREAM.print(Constant.WALL);
                        break;
                    case A:
                        PRINT_STREAM.print(Constant.PERSON);
                        break;
                    case B:
                        PRINT_STREAM.print(Constant.CAKE);
                        break;
                    case GLASS:
                        PRINT_STREAM.print(Constant.GLASS);
                        break;
                    case BOMB:
                        PRINT_STREAM.print(Constant.BOMB);
                        break;
                    case GIFT:
                        PRINT_STREAM.print(Constant.GIFT);
                        break;
                    case ENTRANCE:
                        PRINT_STREAM.print(Constant.ENTRANCE);
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
                    maze.grid[row][column].type = CellType.GIFT;
                    break;
                case 0:
                    maze.grid[row][column].type = CellType.BOMB;
                    break;
                default:
                    maze.grid[row][column].type = CellType.PASSAGE;
            }
        }
    }

    // Метод для вывода пути на сетку
    public static void printPath(ArrayList<Coordinate> path, Coordinate start, Coordinate end, Maze maze) {
        Maze copyMaze = new Maze(maze);
        copyMaze.grid[start.row()][start.column()].type = CellType.A;
        copyMaze.grid[end.row()][end.column()].type = CellType.B;

        path.remove(start);
        path.remove(end);

        for (Coordinate coordinate : path) {
            copyMaze.grid[coordinate.row()][coordinate.column()].type = CellType.GLASS;
        }
        printMaze(copyMaze);
    }

    public static List<Coordinate> createManyPassage(Maze maze, BoundType boundType) {
        SecureRandom random = new SecureRandom();
        List<Coordinate> line = getLine(maze, boundType);
        List<Coordinate> passages = new ArrayList<>();
        int count = 0;
        while (count < 3) {
            Coordinate coordinate = line.get(random.nextInt(line.size()));
            System.out.println(coordinate);
            if (maze.grid[coordinate.row()][coordinate.column()].type == CellType.PASSAGE) {
                switch (boundType) {
                    case BoundType.UP:
                        maze.grid[coordinate.row() - 1][coordinate.column()].type = CellType.ENTRANCE;
                        passages.add(coordinate);
                        count++;
                        break;
                    case BoundType.DOWN:
                        maze.grid[coordinate.row() + 1][coordinate.column()].type = CellType.ENTRANCE;
                        passages.add(coordinate);
                        count++;
                        break;
                    case BoundType.RIGHT:
                        maze.grid[coordinate.row()][coordinate.column() + 1].type = CellType.ENTRANCE;
                        passages.add(coordinate);
                        count++;
                        break;
                    case BoundType.LEFT:
                        maze.grid[coordinate.row()][coordinate.column() - 1].type = CellType.ENTRANCE;
                        passages.add(coordinate);
                        count++;
                        break;

                }
            }
        }
        return passages;
    }

    public static List<Coordinate> getLine(Maze maze, BoundType boundType) {
        List<Coordinate> line = new ArrayList<>();
        int start = 1;
        int endWidth = maze.width() -2;
        int endHeight = maze.height() -2;
        switch (boundType) {
            case BoundType.UP:
                return getHorizontalLine(start, endWidth, 1, line);
            case BoundType.DOWN:
                return getHorizontalLine(start, endWidth, endHeight, line);

            case BoundType.RIGHT:
                return getVerticalLine(start, endWidth, endHeight, line);
            case BoundType.LEFT:
                return getVerticalLine(start, endHeight, 1, line);
            default:
                return null;
        }
    }

    public static List<Coordinate> getHorizontalLine(int start, int end, int row,List<Coordinate> line) {
        for(int i = start; i < end; i++) {
            line.add(new Coordinate(row, i));
        }
        return line;
    }

    public static List<Coordinate> getVerticalLine(int start, int end, int column,List<Coordinate> line) {
        for(int i = start; i < end; i++) {
            line.add(new Coordinate(i, column));
        }
        return line;
    }
}
