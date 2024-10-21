package backend.academy.maze;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class DepthFirstSearch extends Solver implements PathFinding {

    public DepthFirstSearch(Maze maze) {
        super(maze);
    }

    @Override
    public ArrayList<Coordinate> findPath(Coordinate start, Coordinate end) {
        Deque<Coordinate> dequeArray = new ArrayDeque<>();

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
                if (!super.visited().contains(neighbor)) {
                    super.visited().add(neighbor);  // Отмечаем соседа как посещённого
                    super.parentMap().put(neighbor, current);  // Запоминаем родителя для восстановления пути
                    dequeArray.push(neighbor);  // Добавляем соседа в стек
                }
            }
        }
        return null;  // Путь не найден
    }
}
