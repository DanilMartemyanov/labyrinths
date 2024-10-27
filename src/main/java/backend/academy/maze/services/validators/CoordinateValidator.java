package backend.academy.maze.services.validators;

import backend.academy.maze.enums.BoundType;
import backend.academy.maze.models.Coordinate;
import backend.academy.maze.models.Maze;
import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CoordinateValidator {
    public static Coordinate makeCorrectCoordinateForEntrance(BoundType boundType, Coordinate coordinate) {
        Coordinate newCoordinate;
        switch (boundType) {
            case UP -> newCoordinate = new Coordinate(coordinate.row() + 1, coordinate.column());

            case DOWN -> newCoordinate = new Coordinate(coordinate.row() - 1, coordinate.column());

            case RIGHT -> newCoordinate = new Coordinate(coordinate.row(), coordinate.column() - 1);

            case LEFT -> newCoordinate = new Coordinate(coordinate.row(), coordinate.column() + 1);

            default -> newCoordinate = null;

        }
        return newCoordinate;
    }

    public static boolean checkMatchEntrance(List<Coordinate> passages, Coordinate coordinate, BoundType boundType) {
        Coordinate correctForCheck = makeCorrectCoordinateForEntrance(boundType, coordinate);
        for (Coordinate passage : passages) {
            if (correctForCheck.equals(passage)) {
                return true;
            }
        }
        return false;
    }

    // Проверяем границы лабиринта
    public boolean checkBounds(Coordinate cell, Maze maze) {
        if (cell.row() > 0 && cell.row() < (maze.grid().length - 1) && cell.column() > 0
            && cell.column() < (maze.grid()[0].length - 1)) {
            return true;
        }
        return false;
    }

}
