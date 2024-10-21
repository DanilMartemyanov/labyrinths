package backend.academy.maze;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashSet;

public class GeneratorKruskal implements Generator {
    public ArrayList<Edge> mst;
    private static final SecureRandom RANDOM = new SecureRandom();

    @Override
    public Maze generateMaze(int height, int width) {
        int heightMaze = Generator.getNumberOdd(height);
        int widthMaze = Generator.getNumberOdd(width);
        Maze maze = new Maze(heightMaze, widthMaze);
        PrintMaze printMaze = new PrintMaze(maze);
        HashSet<Edge> edges = new HashSet<>();
        UnionFindImpl unionFind = new UnionFindImpl(heightMaze * widthMaze);
        // Создаем и заполняем ребра
        EdgeHandler.initSetEdges(maze.grid, edges);

        // Сортируем ребра
        ArrayList<Edge> sortedEdges = EdgeHandler.sortEdges(edges);

        // строим лабиринт
        mst = unionCell(sortedEdges, unionFind, maze);

        printMaze.printMaze();

        return maze;
    }

    // Объединяем множества точек
    public static ArrayList<Edge> unionCell(ArrayList<Edge> edges, UnionFindImpl unionFind, Maze maze) {
        ArrayList<Edge> mst = new ArrayList<>();

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
                edge.weight(RANDOM.nextInt(0, Constant.BOUND_4));
                mst.add(edge);
                unionFind.union(indexCellRow, indexCellCol);

                Generator.addPassageBetween(edge.firstNode(), edge.secondNode(), maze.grid);

                maze.grid[from.row()][from.column()].type = CellType.PASSAGE;
                maze.grid[to.row()][to.column()].type = CellType.PASSAGE;
            }
        }
        return mst;
    }
}
