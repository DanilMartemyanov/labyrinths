package backend.academy.maze;

import org.checkerframework.checker.units.qual.C;
import java.util.ArrayList;

public record Coordinate(int row, int column) {
    public static ArrayList<Coordinate> generateDirections(int stepSize) {
        ArrayList<Coordinate> directions = new ArrayList<>();
        directions.add(new Coordinate(-stepSize, 0));
        directions.add(new Coordinate(stepSize, 0));
        directions.add(new Coordinate(0, -stepSize));
        directions.add(new Coordinate(0, stepSize));

        return directions;
    }

    public int toIndex(int width){
        return row * width + column;
    }

    public static ArrayList<Coordinate> directionsDiagonal(int stepSize){
        ArrayList<Coordinate> directions = generateDirections(stepSize);
        directions.add(new Coordinate(stepSize, -stepSize));
        directions.add(new Coordinate(stepSize, stepSize));
        directions.add(new Coordinate(-stepSize, stepSize));
        directions.add(new Coordinate(-stepSize, -stepSize));
        return directions;
    }
}
