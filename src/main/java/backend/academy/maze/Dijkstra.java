package backend.academy.maze;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class Dijkstra implements PathFinding {
    private HashMap<Coordinate, Coordinate> parentMap;
    private ArrayList<Edge> mst;
    private HashSet<Coordinate> visited;
    private ArrayList<Coordinate> path;
    private PriorityQueue<Coordinate> pqueue;
    private Set<Coordinate> nodes;
    public Map<Coordinate, Integer> distance;

    public Dijkstra(ArrayList<Edge> mst) {
        this.mst = mst;
        parentMap = new HashMap<>();
        visited = new HashSet<>();
        path = new ArrayList<>();
        pqueue = new PriorityQueue<>();
        nodes = new HashSet<>();
        distance = new HashMap<>();

    }

    @Override
    public ArrayList<Coordinate> findPath(Coordinate start, Coordinate end) {
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
                if(neighbour != null) {
                    int dist = distance.get(current) + edge.weight();
                    if(dist < distance.get(neighbour)) {
                        distance.put(neighbour, dist);
                        pqueue.add(neighbour);
                        parentMap.put(neighbour, current);
                    }
                }
            }
        }
        if(!distance.get(end).equals(Integer.MAX_VALUE)) {
            PathFinding.reconstructPath(start, end, parentMap, path);
        }
        return path;
    }
}
