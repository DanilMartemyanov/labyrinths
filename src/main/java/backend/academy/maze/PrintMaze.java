package backend.academy.maze;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class PrintMaze {
    private static final PrintStream PRINT_STREAM = new PrintStream(System.out, true, StandardCharsets.UTF_8);

    @SuppressFBWarnings("UCPM_USE_CHARACTER_PARAMETERIZED_METHOD")
    public static void printMaze(Maze maze) {
        for (int i = 0; i < maze.height(); i++) {
            for (int j = 0; j < maze.width(); j++) {
                switch (maze.grid[i][j].type) {
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
                    default:
                        break;
                }
            }
            PRINT_STREAM.println();
        }
    }

    public static void printMazeWithWeight(ArrayList<Edge> edges, Maze maze) {
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
}
