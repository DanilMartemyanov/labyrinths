package maze;

import backend.academy.maze.models.Coordinate;
import org.junit.jupiter.api.Test;

public class CoordinateTest {
    @Test
    void toIndex() {
        Coordinate coordinate = new Coordinate(1, 2);
        System.out.println(coordinate.toIndex(10));
    }

    @Test
    void directionsDiagonalTest() {
        Coordinate coordinate = new Coordinate(1, 1);
        for (Coordinate coordinate1 : Coordinate.directionsDiagonal(1)) {
            System.out.println(
                new Coordinate(coordinate.row() + coordinate1.row(),
                    coordinate.column() + coordinate1.column()));
        }
    }
}
