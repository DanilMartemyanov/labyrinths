package backend.academy.maze.models;



import backend.academy.maze.services.Constant;
import backend.academy.maze.services.validators.CoordinateValidator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public final class EdgeHandler {
    /**
     * EdgeHandler - класс для работы с рёбрами
     */
    private static final List<Coordinate> DIRECTIONS = Coordinate.generateDirections(Constant.STEP_2);

    private EdgeHandler() {
    }

    /**
     * Проход по направлениям клетки
     * Создание ребра, при условии, что оно находится в зоне сетки
     */

    public static void addEdge(Coordinate from, Maze maze, Set<Edge> edges) {
        for (Coordinate direction : DIRECTIONS) {
            if (!CoordinateValidator.checkBounds(from, maze)) {
                continue;
            }
            int newRow = from.row() + direction.row();
            int newColumn = from.column() + direction.column();
            Edge edge = new Edge(from, new Coordinate(newRow, newColumn));
            if (CoordinateValidator.checkBounds(new Coordinate(newRow, newColumn), maze)) {
                edges.add(edge);
            }
        }
    }

    // Инициализация ребер (создание ребер на сетке)
    public static void initSetEdges(Maze maze, Set<Edge> edges) {
        int rows = maze.grid().length;
        int columns = maze.grid()[0].length;
        for (int i = 1; i < rows; i = i + 2) {
            for (int j = 1; j < columns; j = j + 2) {
                Coordinate coordinate = new Coordinate(i, j);
                addEdge(coordinate, maze, edges);
            }
        }
    }

    // Сортировка ребер
    public static List<Edge> sortEdges(Set<Edge> edges) {
        List<Edge> sortedEdges = new ArrayList<>(edges);
        Collections.sort(sortedEdges);
        return sortedEdges;
    }

}
