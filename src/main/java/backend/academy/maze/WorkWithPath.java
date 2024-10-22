package backend.academy.maze;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorkWithPath {

    public static Map<Coordinate, Integer> getBestPath(
        List<Coordinate> passages,
        int distanceUser,
        Dijkstra dijkstra,
        Coordinate end,
        Coordinate start
    ) {
        int bestDistance = distanceUser;
        Coordinate bestPoint = start;
        for (Coordinate coordinate : passages) {
            System.out.println("bestPath");
            System.out.println(coordinate);
            dijkstra.findPath(coordinate, end);
            System.out.println(dijkstra.distanceSum);
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
