package backend.academy.maze;

import backend.academy.Checker;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorkWithPath {

    public static Map<Coordinate, Integer> getBestPath(
        List<Coordinate> passages,
        int distanceUser,
        Dijkstra dijkstra,
        Coordinate end,
        Coordinate start,
        BoundType boundType
    ) {
        int bestDistance = distanceUser;
        Coordinate bestPoint = start;
        for (Coordinate coordinate : passages) {
            Coordinate correctCoordinate = Checker.checkEntrance(boundType, coordinate);
            dijkstra.findPath(correctCoordinate, end);
            if (dijkstra.distanceSum < bestDistance) {
                bestDistance = dijkstra.distanceSum;
                bestPoint = coordinate;
            }
        }
        HashMap<Coordinate, Integer> path = new HashMap<>();
        path.put(bestPoint, distanceUser);
        return path;
    }
}
