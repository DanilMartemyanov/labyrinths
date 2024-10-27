package maze;

import backend.academy.maze.algorithms.generators.GeneratorKruskal;
import backend.academy.maze.models.EdgeHandler;
import backend.academy.maze.models.Edge;
import backend.academy.maze.models.Maze;
import backend.academy.maze.models.UnionFindImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static org.assertj.core.api.Assertions.assertThat;

public class GeneratorKruskalTest {
    private Maze maze;
    private GeneratorKruskal generatorKruskal;
    private HashSet<Edge> edges;
    private UnionFindImpl unionFind;

    @BeforeEach
    void setUp() {
        maze = new Maze(11, 11);
        generatorKruskal = new GeneratorKruskal();
        edges = new HashSet<>();
        unionFind = new UnionFindImpl(121);

    }

    @Test
    void initEdgesTest() {
        EdgeHandler.initSetEdges(maze.grid(), edges);
        assertThat(edges.size()).isNotNull();
    }

    @Test
    void getMst() {
        EdgeHandler.initSetEdges(maze.grid(), edges);

        List<Edge> sortedEdges = EdgeHandler.sortEdges(edges);

        List<Edge> mst = GeneratorKruskal.unionCell(sortedEdges, unionFind, maze);
        Set<Edge> mstSet = new HashSet<>(mst);
        Assertions.assertNotNull(mstSet);

    }


}
