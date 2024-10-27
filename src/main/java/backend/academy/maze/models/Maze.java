package backend.academy.maze.models;

import backend.academy.maze.enums.BoundType;
import backend.academy.maze.enums.CellType;
import backend.academy.maze.services.Constant;
import backend.academy.maze.services.PrintMaze;
import lombok.Getter;
import lombok.Setter;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public final class Maze {
    private static final PrintStream PRINT_STREAM = new PrintStream(System.out, true, StandardCharsets.UTF_8);
    private final int height;
    private final int width;
    private Cell[][] grid;

    public Maze(int height, int width) {
        this.height = height;
        this.width = width;
        this.grid = new Cell[height][width];
        initializeGrid();
    }

    public Maze(Maze originalMaze) {
        this.height = originalMaze.height;
        this.width = originalMaze.width;
        this.grid = new Cell[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                this.grid[i][j] = new Cell(originalMaze.grid[i][j]);
            }
        }
    }

    public void initializeGrid() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                grid[i][j] = new Cell(i, j, CellType.WALL);
            }
        }
    }

    public void setPassage(int row, int column) {
        this.grid[row][column].type(CellType.PASSAGE);
    }

    public List<Coordinate> createManyEntrance(BoundType boundType) {
        SecureRandom random = new SecureRandom();
        List<Coordinate> line = PrintMaze.getLine(this, boundType);
        List<Coordinate> passages = new ArrayList<>();
        List<Coordinate> passagesForPrint = new ArrayList<>();
        int count = 0;

        while (count < Constant.NUMBER_3) {
            Coordinate coordinate = line.get(random.nextInt(line.size()));
            Coordinate newEntrance = null;
            if (this.grid()[coordinate.row()][coordinate.column()].type() == CellType.PASSAGE) {
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
                    this.grid()[newEntrance.row()][newEntrance.column()].type(CellType.ENTRANCE);
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

}
