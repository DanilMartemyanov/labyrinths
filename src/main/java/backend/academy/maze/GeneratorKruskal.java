package backend.academy.maze;

import java.util.ArrayList;
import java.util.HashSet;

public class GeneratorKruskal implements Generator {
    private final ArrayList<Coordinate> directions = Coordinate.generateDirections(Constant.STEP_1);


    @Override
    public Maze generateMaze(int height, int width) {
        return null;
    }

    public void addEdge(Coordinate from, Cell[][] grid, HashSet<Edge> edges ) {
        for (Coordinate direction : directions) {
            int newRow = from.row() + direction.row();
            int newColumn = from.column() + direction.column();
            Edge edge = new Edge(from, new Coordinate(newRow, newColumn));
            if (GeneratorPrim.checkBounds(new Coordinate(newRow, newColumn), grid) && !edges.contains(edge)) {
                edges.add(edge);
            }
        }
    }

    public void initSetEdges(Cell[][] grid, HashSet<Edge> edges) {
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                Coordinate coordinate = new Coordinate(i, j);
                addEdge(coordinate, grid, edges);
            }
        }
    }
}
