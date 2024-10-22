package backend.academy;

import backend.academy.maze.BoundType;
import backend.academy.maze.Coordinate;

public class Checker {
    public static Coordinate checkEntrance(BoundType boundType, Coordinate coordinate){
        switch (boundType){
            case UP -> {
                return new Coordinate(coordinate.row() + 1, coordinate.column());
            }
            case DOWN -> {
                return new Coordinate(coordinate.row() - 1, coordinate.column());
            }
            case RIGHT -> {
                return new Coordinate(coordinate.row(), coordinate.column() - 1);
            }
            case LEFT -> {
                return new Coordinate(coordinate.row(), coordinate.column() + 1);
            }
            default ->
            {
                return null;
            }
        }
    }
}
