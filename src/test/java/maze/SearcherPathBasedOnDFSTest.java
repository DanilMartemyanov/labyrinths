package maze;

import backend.academy.maze.algorithms.generators.MazeGeneratorBasedOnKruskal;
import backend.academy.maze.algorithms.solvers.SearcherPathBasedOnDFS;
import backend.academy.maze.models.Coordinate;
import backend.academy.maze.models.Maze;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

public class SearcherPathBasedOnDFSTest {
    private MazeGeneratorBasedOnKruskal mazeGeneratorBasedOnKruskal;
    private SearcherPathBasedOnDFS searcherPathBasedOnDFS;

    @BeforeEach
    void setUp() {
        mazeGeneratorBasedOnKruskal = new MazeGeneratorBasedOnKruskal();
        Maze maze = mazeGeneratorBasedOnKruskal.generateMaze(11, 11);
        searcherPathBasedOnDFS = new SearcherPathBasedOnDFS(maze);
    }

    @Test
    void findPathTest() {
        List<Coordinate> path =
            searcherPathBasedOnDFS.findPath(new Coordinate(1, 1), new Coordinate(9, 9));
        Assertions.assertNotNull(path);
    }
}
