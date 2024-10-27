package backend.academy.maze.models;

import backend.academy.maze.algorithms.generators.Generator;
import backend.academy.maze.services.Constant;
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

    public static void addEdge(Coordinate from, Cell[][] grid, Set<Edge> edges) {
        for (Coordinate direction : DIRECTIONS) {
            if (!Generator.checkBounds(from, grid)) {
                continue;
            }
            int newRow = from.row() + direction.row();
            int newColumn = from.column() + direction.column();
            Edge edge = new Edge(from, new Coordinate(newRow, newColumn));
            if (Generator.checkBounds(new Coordinate(newRow, newColumn), grid)) {
                edges.add(edge);
            }
        }
    }

    // Инициализация ребер (создание ребер на сетке)
    public static void initSetEdges(Cell[][] grid, Set<Edge> edges) {
        int rows = grid.length;
        int columns = grid[0].length;
        for (int i = 1; i < rows; i = i + 2) {
            for (int j = 1; j < columns; j = j + 2) {
                Coordinate coordinate = new Coordinate(i, j);
                addEdge(coordinate, grid, edges);
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
