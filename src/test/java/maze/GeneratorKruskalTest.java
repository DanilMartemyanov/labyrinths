package maze;

import backend.academy.maze.algorithms.generators.GeneratorKruskal;
import backend.academy.maze.algorithms.solvers.Dijkstra;
import backend.academy.maze.models.EdgeHandler;
import backend.academy.maze.models.Coordinate;
import backend.academy.maze.models.Edge;
import backend.academy.maze.models.Maze;
import backend.academy.maze.models.UnionFindImpl;
import backend.academy.maze.services.PrintMaze;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;

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
    void initEdges() {
        EdgeHandler.initSetEdges(maze.grid, edges);
//        generatorKruskal.addEdge(new Coordinate(1,1), maze.grid, edges);
        System.out.println(edges.size());
        System.out.println(new Edge(new Coordinate(0, 0), new Coordinate(0, 1)));
    }

    @Test
    void sortEdges() {
        edges.add(new Edge(new Coordinate(0, 0), new Coordinate(0, 1)));
        edges.add(new Edge(new Coordinate(1, 0), new Coordinate(1, 1)));
        edges.add(new Edge(new Coordinate(1, 1), new Coordinate(1, 2)));
        edges.add(new Edge(new Coordinate(2, 0), new Coordinate(2, 1)));
        ArrayList<Edge> arrayEdges = EdgeHandler.sortEdges(edges);
        for (Edge edge : arrayEdges) {
            System.out.println(edge);
        }
    }

    @Test
    void unionFind() {
        PrintMaze printMaze = new PrintMaze();
        UnionFindImpl unionFind = new UnionFindImpl(maze.width() * maze.height());
        Coordinate coordinate1 = new Coordinate(1, 1);
        Coordinate coordinate2 = new Coordinate(1, 2);
        Coordinate coordinate3 = new Coordinate(2, 1);
        Coordinate coordinate4 = new Coordinate(2, 2);
        int index1 = coordinate1.toIndex(11);
        System.out.println(index1);
        int index2 = coordinate2.toIndex(11);
        System.out.println(index2);
        int index3 = coordinate3.toIndex(11);
        System.out.println(index3);
        int index4 = coordinate4.toIndex(11);
        System.out.println(index4);
        unionFind.union(index1, index2);
        unionFind.union(index1, index3);
        unionFind.union(index1, index4);
        System.out.println(unionFind.find(index1, index2));
        System.out.println(unionFind.find(index1, index3));
        System.out.println(unionFind.find(index1, index4));
        System.out.println(unionFind.getRoot(index2));
        System.out.println(unionFind.getRoot(index3));
        System.out.println(unionFind.getRoot(index4));
        printMaze.printMaze(maze);
    }

    @Test
    void getMst() {
        // Создаем и заполняем ребра
        EdgeHandler.initSetEdges(maze.grid, edges);

        // Сортируем ребра
        ArrayList<Edge> sortedEdges = EdgeHandler.sortEdges(edges);

        ArrayList<Edge> mst = GeneratorKruskal.unionCell(sortedEdges, unionFind, maze);
        HashSet<Edge> mstSet = new HashSet<>(mst);
        System.out.println(mstSet.size());
        for (Edge edge : mstSet) {
            if (edge.secondNode().equals(new Coordinate(9, 1))) {
                System.out.println(edge);
            }
        }

    }

    @Test
    void imageMaze() {
        Maze maze1 = generatorKruskal.generateMaze(21, 21);
        System.out.println();
        ArrayList<Edge> mst = generatorKruskal.mst();
        for (Edge edge : mst) {
            System.out.println(edge);
        }

        PrintMaze.changeMazeWithWeight(mst, maze1);
        System.out.println();
        PrintMaze.printMaze(maze1);
        Dijkstra dijkstra = new Dijkstra(mst, maze1);
        System.out.println(mst);
        ArrayList<Coordinate> path = dijkstra.findPath(new Coordinate(1, 1), new Coordinate(9, 9));
        PrintMaze.printPath(path, new Coordinate(0, 0), new Coordinate(9, 9), maze1);
    }
}
