package backend.academy.maze;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorkWithPath {

    public static Map<Coordinate, Integer> getBestPath(
        List<Coordinate> passages,
        int distanceUser,
        Dijkstra dijkstra,
        Coordinate start,
        Coordinate end,
        BoundType boundType
    ) {
        int bestDistance = distanceUser;
        Coordinate bestPoint = start;
        for (Coordinate coordinate : passages) {
            dijkstra.findPath(coordinate, end);
            if (dijkstra.distanceSum < bestDistance) {
                bestDistance = dijkstra.distanceSum;
                bestPoint = coordinate;
            }
        }
        HashMap<Coordinate, Integer> path = new HashMap<>();
        path.put(bestPoint, bestDistance);
        return path;
    }

}
