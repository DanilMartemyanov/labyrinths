package maze;

import backend.academy.maze.Coordinate;
import backend.academy.maze.Edge;
import backend.academy.maze.GeneratorKruskal;
import backend.academy.maze.Maze;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashSet;

public class GeneratorKruskalTest {
    private Maze maze;
    private GeneratorKruskal generatorKruskal;
    private HashSet<Edge> edges;

    @BeforeEach
    void setUp() {
        maze = new Maze(11, 11);
        generatorKruskal = new GeneratorKruskal();
        edges = new HashSet<>();
    }

    @Test
    void initEdges(){
        generatorKruskal.initSetEdges(maze.grid, edges);
//        generatorKruskal.addEdge(new Coordinate(1,1), maze.grid, edges);
        System.out.println(edges.size());
        System.out.println(new Edge(new Coordinate(0, 0), new Coordinate(0, 1)));
    }
}
