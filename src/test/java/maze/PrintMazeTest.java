package maze;

import backend.academy.maze.enums.BoundType;
import backend.academy.maze.models.Coordinate;
import backend.academy.maze.algorithms.solvers.DepthFirstSearch;
import backend.academy.maze.algorithms.generators.GeneratorKruskal;
import backend.academy.maze.models.Maze;
import backend.academy.maze.services.PrintMaze;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class PrintMazeTest {
    private PrintMaze printMaze;

    @BeforeEach
    public void setUp() {
        printMaze = new PrintMaze();
    }

    @Test
    void getLineTest() {
        Maze maze = new Maze(11, 11);
        System.out.println(printMaze.getLine(maze, BoundType.UP));
        System.out.println(printMaze.getLine(maze, BoundType.DOWN));
        System.out.println(printMaze.getLine(maze, BoundType.RIGHT));
        System.out.println(printMaze.getLine(maze, BoundType.LEFT));
    }

    @Test
    void createManyPassageTest() {
        Maze maze;
        GeneratorKruskal generatorKruskal = new GeneratorKruskal();
        maze = generatorKruskal.generateMaze(11, 11);
        System.out.println();
        PrintMaze.createManyEntrance(maze, BoundType.UP);
        PrintMaze.printMaze(maze);
    }

    @Test
    void imageMazeTest() {
        GeneratorKruskal generatorKruskal = new GeneratorKruskal();
        Maze maze = generatorKruskal.generateMaze(11, 11);
        DepthFirstSearch depthFirstSearch = new DepthFirstSearch(maze);
        ArrayList<Coordinate> path = depthFirstSearch.findPath(new Coordinate(1, 1), new Coordinate(9, 9));
        PrintMaze.printMaze(maze);
        PrintMaze.printPath(path, new Coordinate(1, 1), new Coordinate(9, 9), maze);
        PrintMaze.printMaze(maze);
    }
}
