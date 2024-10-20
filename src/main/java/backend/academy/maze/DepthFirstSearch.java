package backend.academy.maze;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;


public class DepthFirstSearch implements PathFinding {
    private HashSet<Coordinate> visited;  // Используем Coordinate вместо Cell для отслеживания посещённых клеток
    private ArrayList<Coordinate> path;
    private Maze maze;
    private Map<Coordinate, Coordinate> parentMap; // родители клеток пути

    public DepthFirstSearch(Maze maze) {
        this.maze = maze;
        this.visited = new HashSet<>();
        this.path = new ArrayList<>();
        this.parentMap = new HashMap<>();
    }

    @Override
    public ArrayList<Coordinate> findPath(Coordinate start, Coordinate end) {
        Deque<Coordinate> dequeArray = new ArrayDeque<>();


        dequeArray.push(start);
        visited.add(start);

        while (!dequeArray.isEmpty()) {
            Coordinate current = dequeArray.pop();

            // Добавляем клетку в путь, если она еще не была посещена
            if (current.equals(end)) {
                // Восстанавливаем путь от конечной точки до начальной
                PathFinding.reconstructPath(start, end, parentMap, path);
                return path;
            }

            // Получаем соседей текущей клетки
            for (Coordinate neighbor : PathFinding.getNeighbors(current, maze)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);  // Отмечаем соседа как посещённого
                    parentMap.put(neighbor, current);  // Запоминаем родителя для восстановления пути
                    dequeArray.push(neighbor);  // Добавляем соседа в стек
                }
            }
        }
        return null;  // Путь не найден
    }
}
