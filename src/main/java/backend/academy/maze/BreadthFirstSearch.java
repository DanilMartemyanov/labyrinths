package backend.academy.maze;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class BreadthFirstSearch implements PathFinding {
    private Maze maze;
    private HashSet<Coordinate> visited;
    private ArrayList<Coordinate> path;
    private Map<Coordinate, Coordinate> parentMap;
    private Queue<Coordinate> queue; // для обработки клеток на текущем уровне

    public BreadthFirstSearch(Maze maze) {
        this.maze = maze;
        this.visited = new HashSet<>();
        this.path = new ArrayList<>();
        this.parentMap = new HashMap<>();
        this.queue = new LinkedList<>();
    }

    @Override
    public ArrayList<Coordinate> findPath(Coordinate start, Coordinate end) {
        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Coordinate current = queue.poll();
            if (current.equals(end)) {
                PathFinding.reconstructPath(start, end, parentMap, path);
                return path;
            }
            // Получаем соседей текущей клетки
            for (Coordinate neighbor : PathFinding.getNeighbors(current, maze)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);  // Отмечаем соседа как посещённого
                    parentMap.put(neighbor, current);  // Запоминаем родителя для восстановления пути
                    queue.add(neighbor);  // Добавляем соседа в стек
                }
            }
        }
        return null;
    }
}
