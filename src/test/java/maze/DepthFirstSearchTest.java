package maze;

import backend.academy.maze.DepthFirstSearch;
import backend.academy.maze.Maze;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DepthFirstSearchTest {
    private Maze maze;
    private DepthFirstSearch depthFirstSearch;

    @BeforeEach
    void setUp() {
        maze = new Maze(11, 11);
        depthFirstSearch = new DepthFirstSearch(maze);
    }

    @Test
    void findPathTest(){

    }
}
