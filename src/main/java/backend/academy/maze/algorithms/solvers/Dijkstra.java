package backend.academy.maze.algorithms.solvers;

import backend.academy.maze.models.Coordinate;
import backend.academy.maze.models.Edge;
import backend.academy.maze.models.Maze;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dijkstra extends Solver implements PathFinding {
    private List<Edge> mst;
    private Queue<Coordinate> pqueue;
    private Set<Coordinate> nodes;
    public Map<Coordinate, Integer> distance;
    private int distanceSum;
    private Maze maze;

    public Dijkstra(ArrayList<Edge> mst, Maze maze) {
        super(maze);
        this.mst = mst;
        pqueue = new PriorityQueue<>();
        nodes = new HashSet<>();
        distance = new HashMap<>();

    }

    @Override
    public ArrayList<Coordinate> findPath(Coordinate start, Coordinate end) {
        distanceSum = 0;
        super.clearCollections();
        pqueue.clear();
        distance.clear();

        for (Edge edge : mst) {
            nodes.add(edge.firstNode());
            nodes.add(edge.secondNode());
        }

        for (Coordinate node : nodes) {
            distance.put(node, Integer.MAX_VALUE);
        }

        distance.put(start, 0);

        pqueue.add(start);

        while (!pqueue.isEmpty()) {
            Coordinate current = pqueue.poll();

            if (current.equals(end)) {
                break;
            }

            for (Edge edge : mst) {
                Coordinate neighbour = null;
                if (edge.firstNode().equals(current)) {
                    neighbour = edge.secondNode();
                } else if (edge.secondNode().equals(current)) {
                    neighbour = edge.firstNode();
                }
                if (neighbour != null) {
                    int dist = distance.get(current) + edge.weight();
                    if (dist < distance.get(neighbour)) {
                        distance.put(neighbour, dist);
                        pqueue.add(neighbour);
                        super.parentMap().put(neighbour, current);
                    }
                }
            }
        }
        if (!distance.get(end).equals(Integer.MAX_VALUE)) {
            ArrayList<Coordinate> pathWithIntermediates = new ArrayList<>();
            PathFinding.reconstructPath(start, end, super.parentMap(), super.path());

            // Добавляем промежуточные точки между ребрами
            for (int i = 0; i < super.path().size() - 1; i++) {
                Coordinate current = super.path().get(i);
                Coordinate next = super.path().get(i + 1);

                pathWithIntermediates.add(current);

                Coordinate intermediate = getIntermediatePoint(current, next);
                if (intermediate != null) {
                    pathWithIntermediates.add(intermediate);
                }
            }

            pathWithIntermediates.add(end);

            distanceSum += distance.get(end);
            return pathWithIntermediates;
        }

        return super.path();
    }

    private Coordinate getIntermediatePoint(Coordinate first, Coordinate second) {
        int row = first.row() + (second.row() - first.row()) / 2;
        int column = first.column() + (second.column() - first.column()) / 2;
        return new Coordinate(row, column);
    }
}
