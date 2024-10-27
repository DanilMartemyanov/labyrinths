package maze;

import backend.academy.maze.algorithms.generators.GeneratorKruskal;
import backend.academy.maze.algorithms.solvers.Dijkstra;
import backend.academy.maze.models.Coordinate;
import backend.academy.maze.models.Edge;
import backend.academy.maze.models.Maze;
import backend.academy.maze.models.UnionFindImpl;
import backend.academy.maze.services.PrintMaze;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class DijkstraTest {
    private GeneratorKruskal generatorKruskal;
    private Maze maze;
    private HashSet<Edge> edges;
    private UnionFindImpl unionFind;
    private Dijkstra dijkstra;

    @BeforeEach
    public void setUp() {
        generatorKruskal = new GeneratorKruskal();
        edges = new HashSet<>();
        unionFind = new UnionFindImpl(121);

    }

    @Test
    void findPathDijkstra() {

        Maze maze = generatorKruskal.generateMaze(11, 11);
        PrintMaze.printMaze(maze);
        dijkstra = new Dijkstra(generatorKruskal.mst(), maze);
        List<Coordinate> path = dijkstra.findPath(new Coordinate(1, 1), new Coordinate(9, 9));
        System.out.println(dijkstra.distanceSum());
        dijkstra.findPath(new Coordinate(1, 1), new Coordinate(1, 3));
        System.out.println(dijkstra.distanceSum());


    }
}
