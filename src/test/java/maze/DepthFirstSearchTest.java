package maze;

import backend.academy.maze.algorithms.generators.GeneratorKruskal;
import backend.academy.maze.algorithms.solvers.DepthFirstSearch;
import backend.academy.maze.models.Coordinate;
import backend.academy.maze.models.Maze;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

public class DepthFirstSearchTest {
    private GeneratorKruskal generatorKruskal;
    private DepthFirstSearch depthFirstSearch;

    @BeforeEach
    void setUp() {
        Maze maze = generatorKruskal.generateMaze(11, 11);
        depthFirstSearch = new DepthFirstSearch(maze);
    }

    @Test
    void findPathTest() {
        List<Coordinate> path =
            depthFirstSearch.findPath(new Coordinate(1, 1), new Coordinate(9, 9));
        Assertions.assertNotNull(path);
    }
}
