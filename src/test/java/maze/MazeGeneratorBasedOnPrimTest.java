package maze;

import backend.academy.maze.models.Cell;
import backend.academy.maze.models.Coordinate;
import backend.academy.maze.algorithms.generators.MazeGeneratorBasedOnPrim;
import backend.academy.maze.models.Maze;
import backend.academy.maze.enums.CellType;
import backend.academy.maze.services.validators.CoordinateValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MazeGeneratorBasedOnPrimTest {
    private Maze maze;
    private List<Coordinate> directionsPrim;
    private Set<Cell> visited;
    private Set<Cell> neighbors;

    @BeforeEach
    void setUp() {
        maze = new Maze(10, 10);
        directionsPrim = Coordinate.generateDirections(2);
        visited = new HashSet<>();
        neighbors = new HashSet<>();
    }

    @Test
    void checkBoundsRow() {
        boolean check = CoordinateValidator.checkBounds(new Coordinate(-1, 3), maze);
        Assertions.assertFalse(check);
    }

    @Test
    void checkBoundsColumn() {
        boolean check = CoordinateValidator.checkBounds(new Coordinate(5, -1), maze);
        Assertions.assertFalse(check);
    }

    @Test
    void checkBounds() {
        boolean check = CoordinateValidator.checkBounds(new Coordinate(9, 9), maze);
        Assertions.assertFalse(check);
    }

    @Test
    void addNeighbourPrim() {
        MazeGeneratorBasedOnPrim.addNeighbor(new Cell(5, 5, CellType.PASSAGE), directionsPrim, neighbors, maze, visited);
        MazeGeneratorBasedOnPrim.addNeighbor(new Cell(3, 5, CellType.PASSAGE), directionsPrim, neighbors, maze, visited);
    }

    @Test
    void addNeighbourDFS() {
        MazeGeneratorBasedOnPrim.addNeighbor(new Cell(5, 5, CellType.PASSAGE), directionsPrim, neighbors, maze, visited);
    }

}
