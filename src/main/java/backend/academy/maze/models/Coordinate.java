package backend.academy.maze.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public record Coordinate(int row, int column) implements Comparable<Coordinate> {
    public static List<Coordinate> generateDirections(int stepSize) {
        List<Coordinate> directions = new ArrayList<>();
        directions.add(new Coordinate(-stepSize, 0));
        directions.add(new Coordinate(stepSize, 0));
        directions.add(new Coordinate(0, -stepSize));
        directions.add(new Coordinate(0, stepSize));

        return directions;
    }

    // перевод индекса из двумерного массива в одномерный
    public int toIndex(int width) {
        return row * width + column;
    }

    @Override
    public int compareTo(Coordinate other) {
        if (this.row != other.row) {
            return Integer.compare(this.row, other.row);
        } else {
            return Integer.compare(this.column, other.column);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Coordinate that = (Coordinate) obj;
        return row == that.row && column == that.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }

}
