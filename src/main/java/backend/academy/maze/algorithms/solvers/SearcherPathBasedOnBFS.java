package backend.academy.maze.algorithms.solvers;

import backend.academy.maze.models.Coordinate;
import backend.academy.maze.models.Maze;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SearcherPathBasedOnBFS extends Solver implements PathFinding {

    private Queue<Coordinate> queue; // для обработки клеток на текущем уровне

    public SearcherPathBasedOnBFS(Maze maze) {
        super(maze);
        this.queue = new LinkedList<>();
    }

    @Override
    public List<Coordinate> findPath(Coordinate start, Coordinate end) {
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
                if (super.visited().add(neighbor)) {
                    super.parentMap().put(neighbor, current);  // Запоминаем родителя для восстановления пути
                    queue.add(neighbor);  // Добавляем соседа в стек
                }
            }
        }
        return null;
    }
}
