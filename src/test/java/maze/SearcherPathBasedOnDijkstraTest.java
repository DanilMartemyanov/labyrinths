package maze;

import backend.academy.maze.algorithms.generators.MazeGeneratorBasedOnKruskal;
import backend.academy.maze.algorithms.solvers.SearcherPathBasedOnDijkstra;
import backend.academy.maze.models.Coordinate;
import backend.academy.maze.models.Maze;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SearcherPathBasedOnDijkstraTest {
    private MazeGeneratorBasedOnKruskal mazeGeneratorBasedOnKruskal;
    private Maze maze;
    private SearcherPathBasedOnDijkstra searcherPathBasedOnDijkstra;

    @BeforeEach
    public void setUp() {
        mazeGeneratorBasedOnKruskal = new MazeGeneratorBasedOnKruskal();
        maze = mazeGeneratorBasedOnKruskal.generateMaze(11, 11);
        searcherPathBasedOnDijkstra = new SearcherPathBasedOnDijkstra(mazeGeneratorBasedOnKruskal.mst(), maze);
    }

    @Test
    void findPathDijkstra() {
        searcherPathBasedOnDijkstra.findPath(new Coordinate(1, 1), new Coordinate(9, 9));
        assertThat(searcherPathBasedOnDijkstra.distanceSum())
            .isNotZero()
            .isNotNull();
    }
}
