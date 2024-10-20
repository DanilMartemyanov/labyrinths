package backend.academy.maze;


import java.util.ArrayList;
import java.util.HashSet;

public class GeneratorKruskal implements Generator {

    @Override
    public  Maze generateMaze(int height, int width) {
        int heightMaze = Generator.getNumberOdd(height);
        int widthMaze = Generator.getNumberOdd(width);
        Maze maze = new Maze(heightMaze, widthMaze);
        HashSet<Edge> edges = new HashSet<>();
        UnionFindImpl unionFind = new UnionFindImpl(heightMaze * widthMaze);
        // Создаем и заполняем ребра
        EdgeHandler.initSetEdges(maze.grid, edges);

        // Сортируем ребра
        ArrayList<Edge> sortedEdges = EdgeHandler.sortEdges(edges);

        // строим лабиринт
        unionCell(sortedEdges, unionFind, maze);

        maze.printMaze();

        return maze;
    }

    // Объединяем множества точек
    public static void unionCell(ArrayList<Edge> edges, UnionFindImpl unionFind, Maze maze) {
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

                unionFind.union(indexCellRow, indexCellCol);

                Generator.addPassageBetween(edge.firstNode(), edge.secondNode(), maze.grid);

                maze.grid[from.row()][from.column()].type = Type.PASSAGE;
                maze.grid[to.row()][to.column()].type = Type.PASSAGE;
            }
        }
    }
}
