package backend.academy.maze;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public final class Maze {
    private final int height;
    private final int width;
    private final Cell[][] grid;


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
