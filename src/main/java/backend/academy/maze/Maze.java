package backend.academy.maze;

import java.io.PrintStream;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class Maze {
    private final int height;
    private final int width;
    public Cell[][] grid;
    private final PrintStream printStream = new PrintStream(System.out);

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
                switch (grid[i][j].type){
                    case PASSAGE:
                        printStream.print("\u2705");
                        break;
                    case WALL:
                        printStream.print("\u26D4");
                        break;
                    case A:
                        printStream.print("\uD83C\uDFC3\u200D♂\uFE0F"); // бегущий человек
                        break;
                    case B:
                        printStream.print("\uD83C\uDF82"); // бегущий человек
                        break;
                    case GLASS:
                        printStream.print("\uD83D\uDD0E"); // лупа
                        break;
                }
            }
            printStream.println();
        }
    }

}
