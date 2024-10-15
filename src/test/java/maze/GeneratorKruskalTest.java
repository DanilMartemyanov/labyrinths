package maze;

import backend.academy.maze.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
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
        EdgeHandler.initSetEdges(maze.grid, edges);
//        generatorKruskal.addEdge(new Coordinate(1,1), maze.grid, edges);
        System.out.println(edges.size());
        System.out.println(new Edge(new Coordinate(0, 0), new Coordinate(0, 1)));
    }

    @Test
    void sortEdges(){
        edges.add(new Edge(new Coordinate(0, 0), new Coordinate(0, 1)));
        edges.add(new Edge(new Coordinate(1, 0), new Coordinate(1, 1)));
        edges.add(new Edge(new Coordinate(1, 1), new Coordinate(1, 2)));
        edges.add(new Edge(new Coordinate(2, 0), new Coordinate(2, 1)));
        ArrayList<Edge> arrayEdges = EdgeHandler.sortEdges(edges);
        for (Edge edge : arrayEdges) {
            System.out.println(edge);
        }
    }
}
