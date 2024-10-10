package backend.academy.maze;

import java.util.ArrayList;

public interface PathFinding {
    ArrayList<Coordinate> findPath(Coordinate start, Coordinate end);
}
