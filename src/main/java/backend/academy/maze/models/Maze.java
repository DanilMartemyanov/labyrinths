package backend.academy.maze.models;

import backend.academy.maze.enums.CellType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class Maze {
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

}
