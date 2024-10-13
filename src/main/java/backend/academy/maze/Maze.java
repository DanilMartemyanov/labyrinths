package backend.academy.maze;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public final class Maze {
    private final int height;
    private final int width;
    public Cell[][] grid;

    public Maze(int height, int width) {
        this.height = height;
        this.width = width;
        this.grid = new Cell[height][width];
        initializeGrid();
    }

    public void initializeGrid() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                grid[i][j] = new Cell(i, j, Type.WALL);
            }
        }
    }

    public void printMaze() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(grid[i][j].type == Type.WALL ? "\u26D4" : "\u2705");
            }
            System.out.println();
        }
    }

}
