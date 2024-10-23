package backend.academy.maze;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public interface PathFinding {
    ArrayList<Coordinate> findPath(Coordinate start, Coordinate end);

    // Метод для получения соседей текущей клетки
    static List<Coordinate> getNeighbors(Coordinate current, Maze maze) {
        ArrayList<Coordinate> directions = Coordinate.generateDirections(Constant.STEP_1);
        List<Coordinate> neighbors = new ArrayList<>();

        for (Coordinate direction : directions) {
            int newRow = current.row() + direction.row();
            int newCol = current.column() + direction.column();

            if (Generator.checkBounds(new Coordinate(newRow, newCol), maze.grid)
                && maze.grid[newRow][newCol].type == CellType.PASSAGE) {
                neighbors.add(new Coordinate(newRow, newCol));
            }
        }
        return neighbors;
    }

    // Восстановление пути по родителю, который записывается в Map
    static void reconstructPath(
        Coordinate start,
        Coordinate end,
        Map<Coordinate, Coordinate> parentMap,
        ArrayList<Coordinate> path
    ) {
        Coordinate current = end;
        while (!current.equals(start)) {
            path.add(current);
            current = parentMap.get(current);
        }
        path.add(start);
        Collections.reverse(path);
    }

}
