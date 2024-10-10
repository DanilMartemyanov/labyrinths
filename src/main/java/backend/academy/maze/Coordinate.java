package backend.academy.maze;

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
}
