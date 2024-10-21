package backend.academy.maze;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class PrintMaze {
    private final PrintStream printStream = new PrintStream(System.out, true, StandardCharsets.UTF_8);

    private Maze maze;

    public PrintMaze(Maze maze) {
        this.maze = maze;
    }

    @SuppressFBWarnings("UCPM_USE_CHARACTER_PARAMETERIZED_METHOD")
    public void printMaze() {
        for (int i = 0; i < maze.height(); i++) {
            for (int j = 0; j < maze.width(); j++) {
                switch (maze.grid[i][j].type) {
                    case PASSAGE:
                        printStream.print(Constant.PASSAGE);
                        break;
                    case WALL:
                        printStream.print(Constant.WALL);
                        break;
                    case A:
                        printStream.print(Constant.PERSON);
                        break;
                    case B:
                        printStream.print(Constant.CAKE);
                        break;
                    case GLASS:
                        printStream.print(Constant.GLASS);
                        break;
                    case BOMB:
                        printStream.print(Constant.BOMB);
                        break;
                    case GIFT:
                        printStream.print(Constant.GIFT);
                        break;
                    default:
                        break;
                }
            }
            printStream.println();
        }
    }

    public void printMazeWithWeight(ArrayList<Edge> edges) {
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
