package backend.academy.maze;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class BreadthFirstSearch extends Solver implements PathFinding {

    private Queue<Coordinate> queue; // для обработки клеток на текущем уровне

    public BreadthFirstSearch(Maze maze) {
        super(maze);
    }

    @Override
    public ArrayList<Coordinate> findPath(Coordinate start, Coordinate end) {
        queue.add(start);
        super.visited().add(start);

        while (!queue.isEmpty()) {
            Coordinate current = queue.poll();
            if (current.equals(end)) {
                PathFinding.reconstructPath(start, end, super.parentMap(), super.path());
                return super.path();
            }
            // Получаем соседей текущей клетки
            for (Coordinate neighbor : PathFinding.getNeighbors(current, super.maze())) {
                if (!super.visited().contains(neighbor)) {
                    super.visited().add(neighbor);  // Отмечаем соседа как посещённого
                    super.parentMap().put(neighbor, current);  // Запоминаем родителя для восстановления пути
                    queue.add(neighbor);  // Добавляем соседа в стек
                }
            }
        }
        return null;
    }
}
