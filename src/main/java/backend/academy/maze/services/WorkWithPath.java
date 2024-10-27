package backend.academy.maze.services;

import backend.academy.maze.algorithms.solvers.SearcherPathBasedOnDijkstra;
import backend.academy.maze.models.Coordinate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.experimental.UtilityClass;

@UtilityClass
public class WorkWithPath {

    public static Map<Coordinate, Integer> getBestPath(
        List<Coordinate> passages,
        int distanceUser,
        SearcherPathBasedOnDijkstra searcherPathBasedOnDijkstra,
        Coordinate start,
        Coordinate end
    ) {
        int bestDistance = distanceUser;
        Coordinate bestPoint = start;
        for (Coordinate coordinate : passages) {
            searcherPathBasedOnDijkstra.findPath(coordinate, end);
            if (searcherPathBasedOnDijkstra.distanceSum() < bestDistance) {
                bestDistance = searcherPathBasedOnDijkstra.distanceSum();
                bestPoint = coordinate;
            }
        }
        Map<Coordinate, Integer> path = new HashMap<>();
        path.put(bestPoint, bestDistance);
        return path;
    }
}
