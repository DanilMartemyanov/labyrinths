package maze;

import backend.academy.maze.Cell;
import backend.academy.maze.Coordinate;
import backend.academy.maze.GeneratorPrim;
import backend.academy.maze.Maze;
import backend.academy.maze.Type;
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
        maze = new Maze(11,11);
        directionsPrim = Coordinate.generateDirections(2);
        visited = new HashSet<>();
        neighbors = new HashSet<>();
    }
    @Test
    void checkBoundsRow(){
        boolean check = GeneratorPrim.checkBounds(new Coordinate(-1,3), maze.grid);
        Assertions.assertFalse(check);
    }
    @Test
    void checkBoundsColumn(){
        boolean check = GeneratorPrim.checkBounds(new Coordinate(5,-1), maze.grid);
        Assertions.assertFalse(check);
    }

    @Test
    void checkBounds(){
        boolean check = GeneratorPrim.checkBounds(new Coordinate(0,2), maze.grid);
        Assertions.assertFalse(check);
    }

    @Test
    void addNeighbourPrim(){
        GeneratorPrim.addNeighbor(new Cell(5,5, Type.PASSAGE), directionsPrim, neighbors, maze.grid, visited);
        GeneratorPrim.addNeighbor(new Cell(3,5, Type.PASSAGE), directionsPrim, neighbors, maze.grid, visited);
    }

    @Test
    void addNeighbourDFS(){
        GeneratorPrim.addNeighbor(new Cell(5,5, Type.PASSAGE), directionsPrim, neighbors, maze.grid, visited);
    }

}
