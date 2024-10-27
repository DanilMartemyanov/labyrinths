package backend.academy.maze.services;

import backend.academy.maze.enums.BoundType;
import backend.academy.maze.models.Coordinate;
import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Checker {
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



}
