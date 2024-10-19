package backend.academy.maze;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashSet;

public class GeneratorKruskal implements Generator {
    public final SecureRandom random = new SecureRandom();

    @Override
    public Maze generateMaze(int height, int width) {
        Maze maze = new Maze(width, height);
        HashSet<Edge> edges = new HashSet<>();
        UnionFindImpl unionFind = new UnionFindImpl(height * width);
        // Создаем и заполняем ребра
        EdgeHandler.initSetEdges(maze.grid, edges);
        for (Edge edge : edges) {
            System.out.println(edge);
        }
        // Сортируем ребра
        ArrayList<Edge> sortedEdges = EdgeHandler.sortEdges(edges);

        unionCell(sortedEdges, width, unionFind, maze);
        return maze;
    }

    public static void unionCell(ArrayList<Edge> edges, int width, UnionFindImpl unionFind, Maze maze) {
        for (Edge edge : edges) {
            int indexCellRow = edge.firstNode().toIndex(width);
            int indexCellCol = edge.secondNode().toIndex(width);
            if (!unionFind.find(indexCellRow, indexCellCol)) {
                unionFind.union(indexCellRow, indexCellCol);
                GeneratorPrim.addPassageBetween(edge.firstNode(), edge.secondNode(), maze.grid);
                maze.grid[edge.firstNode().row()][edge.firstNode().column()].type = Type.PASSAGE;
                maze.grid[edge.secondNode().row()][edge.secondNode().column()].type = Type.PASSAGE;
            }
            System.out.println("------------------------");
            maze.printMaze();
        }
    }

    public void addWall(Maze maze) {
        for (int i = 0; i < 11; i++) {
            int row = random.nextInt(1, maze.height() -1) ;
            int column = random.nextInt(1, maze.width() -1) ;
            System.out.println(row);
            System.out.println(column);
            maze.grid[row][column].type = Type.WALL;
        }

    }

}
