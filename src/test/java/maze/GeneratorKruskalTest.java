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
    void addEdge(){
        Coordinate from = new Coordinate(1, 1);
        generatorKruskal.addEdge(from, edges, maze.grid );
        for (Edge edge : edges) {
            System.out.println(edge.toString());
        }
        maze.printMaze();
    }
}
