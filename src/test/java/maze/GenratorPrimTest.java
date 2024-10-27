package maze;

import backend.academy.maze.models.Cell;
import backend.academy.maze.models.Coordinate;
import backend.academy.maze.algorithms.generators.Generator;
import backend.academy.maze.algorithms.generators.GeneratorPrim;
import backend.academy.maze.models.Maze;
import backend.academy.maze.enums.CellType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashSet;

public class GenratorPrimTest {
    private Maze maze;
    private ArrayList<Coordinate> directionsPrim;
    private ArrayList<Coordinate> directionsDFS;
    private HashSet<Cell> visited;
    private HashSet<Cell> neighbors;

    @BeforeEach
    void setUp() {
        maze = new Maze(10,10);
        directionsPrim = Coordinate.generateDirections(2);
        visited = new HashSet<>();
        neighbors = new HashSet<>();
    }
    @Test
    void checkBoundsRow(){
        boolean check = Generator.checkBounds(new Coordinate(-1,3), maze.grid());
        Assertions.assertFalse(check);
    }
    @Test
    void checkBoundsColumn(){
        boolean check = Generator.checkBounds(new Coordinate(5,-1), maze.grid());
        Assertions.assertFalse(check);
    }

    @Test
    void checkBounds(){
        boolean check = Generator.checkBounds(new Coordinate(9,9), maze.grid());
        Assertions.assertFalse(check);
    }

    @Test
    void addNeighbourPrim(){
        GeneratorPrim.addNeighbor(new Cell(5,5, CellType.PASSAGE), directionsPrim, neighbors, maze.grid(), visited);
        GeneratorPrim.addNeighbor(new Cell(3,5, CellType.PASSAGE), directionsPrim, neighbors, maze.grid(), visited);
    }

    @Test
    void addNeighbourDFS(){
        GeneratorPrim.addNeighbor(new Cell(5,5, CellType.PASSAGE), directionsPrim, neighbors, maze.grid(), visited);
    }

}
