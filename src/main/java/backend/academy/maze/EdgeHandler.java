package backend.academy.maze;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class EdgeHandler {
    /**
     * EdgeHandler - класс для работы с рёбрами
     */


    private static final ArrayList<Coordinate> DIRECTIONS = Coordinate.generateDirections(Constant.STEP_2);


    /**
     * Проход по направлениям клетки
     * Создание ребра, при условии, что оно находится в зоне сетки
     */

    public static void addEdge(Coordinate from, Cell[][] grid, HashSet<Edge> edges) {
        for (Coordinate direction : DIRECTIONS) {
            if (!GeneratorPrim.checkBounds(from, grid)) {
                continue;
            }
            int newRow = from.row() + direction.row();
            int newColumn = from.column() + direction.column();
            Edge edge = new Edge(from, new Coordinate(newRow, newColumn));
            if (GeneratorPrim.checkBounds(new Coordinate(newRow, newColumn), grid) && !edges.contains(edge)) {
                edges.add(edge);
            }
        }
    }

    // Инициализация ребер (создание ребер на сетке)
    public static void initSetEdges(Cell[][] grid, HashSet<Edge> edges) {
        for (int i = 1; i < grid.length; i = i + 2) {
            for (int j = 1; j < grid[0].length; j = j + 2) {
                Coordinate coordinate = new Coordinate(i, j);
                addEdge(coordinate, grid, edges);
            }
        }
    }


    // Сортировка ребер
    public static ArrayList<Edge> sortEdges(HashSet<Edge> edges) {
        ArrayList<Edge> sortedEdges = new ArrayList<>(edges);
        Collections.sort(sortedEdges);
        return sortedEdges;
    }

}
