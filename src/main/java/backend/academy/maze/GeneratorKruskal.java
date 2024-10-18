package backend.academy.maze;

import java.util.ArrayList;
import java.util.HashSet;

public class GeneratorKruskal implements Generator {

    @Override
    public Maze generateMaze(int height, int width) {
        Maze maze = new Maze(width, height);
        HashSet<Edge> edges = new HashSet<>();
        UnionFindImpl unionFind = new UnionFindImpl(height*width);
        // Создаем и заполняем ребра
        EdgeHandler.initSetEdges(maze.grid, edges);
        for (Edge edge: edges){
            System.out.println(edge);
        }
        // Сортируем ребра
        ArrayList<Edge> sortedEdges = EdgeHandler.sortEdges(edges);


        unionCell(sortedEdges, width, unionFind, maze);
        maze.printMaze();
        return maze ;
    }

    public static  void unionCell(ArrayList<Edge> edges, int width, UnionFindImpl unionFind, Maze maze) {
        for (Edge edge : edges){
            int indexCellRow = edge.firstNode().toIndex(width);
            int indexCellCol = edge.secondNode().toIndex(width);
            if(!unionFind.find(indexCellRow, indexCellCol)){
                unionFind.union(indexCellRow, indexCellCol);
                maze.grid[edge.firstNode().row()][edge.firstNode().column()].type = Type.PASSAGE;
                maze.grid[edge.secondNode().row()][edge.secondNode().column()].type = Type.PASSAGE;
            }
            System.out.println("------------------------");
            maze.printMaze();
        }
    }

}
