package backend.academy.maze.algorithms.generators;

import backend.academy.maze.models.Coordinate;
import backend.academy.maze.models.Edge;
import backend.academy.maze.models.EdgeHandler;
import backend.academy.maze.models.Maze;
import backend.academy.maze.models.UnionFind;
import backend.academy.maze.models.UnionFindImpl;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MazeGeneratorBasedOnKruskal implements Generator {
    private List<Edge> mst;
    private static final SecureRandom RANDOM = new SecureRandom();
    private static final int BOUND_4 = 4;

    @Override
    public Maze generateMaze(int height, int width) {
        int heightMaze = Generator.getNumberOdd(height);
        int widthMaze = Generator.getNumberOdd(width);
        Maze maze = new Maze(heightMaze, widthMaze);
        Set<Edge> edges = new HashSet<>();
        UnionFind unionFind = new UnionFindImpl(heightMaze * widthMaze);
        // Создаем и заполняем ребра
        EdgeHandler.initSetEdges(maze, edges);

        // Сортируем ребра
        List<Edge> sortedEdges = EdgeHandler.sortEdges(edges);

        // строим лабиринт
        mst = unionCell(sortedEdges, unionFind, maze);

        return maze;
    }

    // Объединяем множества точек
    public static List<Edge> unionCell(List<Edge> edges, UnionFind unionFind, Maze maze) {
        List<Edge> mst = new ArrayList<>();

        // Кандидаты на ребра в остовное дерево
        for (Edge edge : edges) {
            // Координаты ребер
            Coordinate from = edge.firstNode();
            Coordinate to = edge.secondNode();

            // Перевод координат клетки из двумерного массива в одномерный для структуры данных UnionFind
            int indexCellRow = from.toIndex(maze.width());
            int indexCellCol = to.toIndex(maze.width());

            /**
             * Проверка: состоят ли клетки в одном множестве ?
             * Если нет, объединяем множества и красим клетки в проход и ломаем стену между ними
             */
            if (!unionFind.find(indexCellRow, indexCellCol)) {
                edge.weight(RANDOM.nextInt(0, BOUND_4));
                mst.add(edge);
                unionFind.union(indexCellRow, indexCellCol);

                Generator.addPassageBetween(edge.firstNode(), edge.secondNode(), maze);

                maze.setPassage(from.row(), from.column());
                maze.setPassage(to.row(), to.column());
            }
        }
        return mst;
    }
}
