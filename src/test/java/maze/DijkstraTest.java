package maze;

import backend.academy.maze.algorithms.generators.GeneratorKruskal;
import backend.academy.maze.algorithms.solvers.Dijkstra;
import backend.academy.maze.models.Coordinate;
import backend.academy.maze.models.Maze;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DijkstraTest {
    private GeneratorKruskal generatorKruskal;
    private Maze maze;
    private Dijkstra dijkstra;

    @BeforeEach
    public void setUp() {
        generatorKruskal = new GeneratorKruskal();
        maze = generatorKruskal.generateMaze(11, 11);
        dijkstra = new Dijkstra(generatorKruskal.mst(), maze);
    }

    @Test
    void findPathDijkstra() {
        dijkstra.findPath(new Coordinate(1, 1), new Coordinate(9, 9));
        assertThat(dijkstra.distanceSum())
            .isNotZero()
            .isNotNull();
    }
}
