package backend.academy.maze.algorithms.solvers;

import backend.academy.maze.models.Coordinate;
import backend.academy.maze.models.Maze;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class DepthFirstSearch extends Solver implements PathFinding {
    private Deque<Coordinate> dequeArray;

    public DepthFirstSearch(Maze maze) {
        super(maze);
        dequeArray = new ArrayDeque<>();
    }

    @Override
    public ArrayList<Coordinate> findPath(Coordinate start, Coordinate end) {

        super.clearCollections();
        dequeArray.clear();

        dequeArray.push(start);
        super.visited().add(start);

        while (!dequeArray.isEmpty()) {
            Coordinate current = dequeArray.pop();

            // Добавляем клетку в путь, если она еще не была посещена
            if (current.equals(end)) {
                // Восстанавливаем путь от конечной точки до начальной
                PathFinding.reconstructPath(start, end, super.parentMap(), super.path());
                return super.path();
            }

            // Получаем соседей текущей клетки
            for (Coordinate neighbor : PathFinding.getNeighbors(current, super.maze())) {
                if (super.visited().add(neighbor)) {
                    super.parentMap().put(neighbor, current);  // Запоминаем родителя для восстановления пути
                    dequeArray.push(neighbor);  // Добавляем соседа в стек
                }
            }
        }
        return null;  // Путь не найден
    }
}
