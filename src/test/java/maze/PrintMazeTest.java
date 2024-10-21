package maze;

import backend.academy.maze.BoundType;
import backend.academy.maze.Maze;
import backend.academy.maze.PrintMaze;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PrintMazeTest {
    private PrintMaze printMaze;

    @BeforeEach
    public void setUp() {
        printMaze = new PrintMaze();
    }
    @Test
    void getLineTest(){
        Maze maze = new Maze(11, 11);
        System.out.println(printMaze.getLine(maze, BoundType.UP));
        System.out.println(printMaze.getLine(maze, BoundType.DOWN));
        System.out.println(printMaze.getLine(maze, BoundType.RIGHT));
        System.out.println(printMaze.getLine(maze, BoundType.LEFT));
    }
}
