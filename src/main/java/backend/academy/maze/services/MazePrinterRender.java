package backend.academy.maze.services;

import backend.academy.maze.enums.CellType;
import backend.academy.maze.models.Coordinate;
import backend.academy.maze.models.Edge;
import backend.academy.maze.models.Maze;
import backend.academy.maze.services.interfaces.MazePrinter;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import lombok.Getter;

@Getter
public class MazePrinterRender implements MazePrinter {
    private final PrintStream printStream = new PrintStream(System.out, true, StandardCharsets.UTF_8);

    @SuppressFBWarnings("UCPM_USE_CHARACTER_PARAMETERIZED_METHOD")
    public void printMaze(Maze maze) {
        Maze copyMaze = new Maze(maze);
        for (int i = 0; i < copyMaze.height(); i++) {
            for (int j = 0; j < copyMaze.width(); j++) {
                switch (copyMaze.grid()[i][j].type()) {
                    case PASSAGE:
                        printStream.print(CellType.PASSAGE.value());
                        break;
                    case WALL:
                        printStream.print(CellType.WALL.value());
                        break;
                    case A:
                        printStream.print(CellType.A.value());
                        break;
                    case B:
                        printStream.print(CellType.B.value());
                        break;
                    case GLASS:
                        printStream.print(CellType.GLASS.value());
                        break;
                    case BOMB:
                        printStream.print(CellType.BOMB.value());
                        break;
                    case GIFT:
                        printStream.print(CellType.GIFT.value());
                        break;
                    case ENTRANCE:
                        printStream.print(CellType.ENTRANCE.value());
                        break;
                    default:
                        break;
                }
            }
            printStream.println();
        }
    }

    public void printMazeWithWeight(List<Edge> edges, Maze maze) {
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
    public void printPath(List<Coordinate> path, Coordinate start, Coordinate end, Maze maze) {
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

}
