package maze;

import backend.academy.maze.algorithms.solvers.DepthFirstSearch;
import backend.academy.maze.models.Maze;
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
