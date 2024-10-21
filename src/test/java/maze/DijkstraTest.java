package maze;

import backend.academy.maze.Coordinate;
import backend.academy.maze.Dijkstra;
import backend.academy.maze.Edge;
import backend.academy.maze.EdgeHandler;
import backend.academy.maze.GeneratorKruskal;
import backend.academy.maze.Maze;
import backend.academy.maze.UnionFindImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashSet;

public class DijkstraTest {
    private GeneratorKruskal generatorKruskal;
    private Maze maze;
    private HashSet<Edge> edges;
    private UnionFindImpl unionFind;
    private Dijkstra dijkstra;

    @BeforeEach
    public void setUp() {
        generatorKruskal = new GeneratorKruskal();
        maze = new Maze(11, 11);
        edges = new HashSet<>();
        unionFind = new UnionFindImpl(121);

    }

    @Test
    void findPathDijkstra() {

        EdgeHandler.initSetEdges(maze.grid, edges);

        // Сортируем ребра
        ArrayList<Edge> sortedEdges = EdgeHandler.sortEdges(edges);

        ArrayList<Edge> mst = GeneratorKruskal.unionCell(sortedEdges, unionFind, maze);
        System.out.println(mst.size());

        dijkstra = new Dijkstra(mst);
        ArrayList<Coordinate> path = dijkstra.findPath(new Coordinate(1, 1), new Coordinate(9, 9));
        System.out.println(dijkstra.distanceSum);
    }
}
