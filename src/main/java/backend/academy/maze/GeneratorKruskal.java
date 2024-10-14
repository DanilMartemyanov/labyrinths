package backend.academy.maze;

import java.util.ArrayList;
import java.util.HashSet;

public class GeneratorKruskal implements Generator {
    private ArrayList<Coordinate> directions = Coordinate.generateDirections(Constant.STEP_1);

    @Override
    public Maze generateMaze(int height, int width) {
        return null;
    }

    public void addEdge(Coordinate from, HashSet<Edge> edges, Cell[][] grid) {
        for (Coordinate coordinate : directions) {
            int newRow = from.row() + coordinate.row();
            int newColumn = from.column() + coordinate.column();
            Cell cell = new Cell(newRow, newColumn, Type.PASSAGE);
            if (!edges.contains(cell) && GeneratorPrim.checkBounds(new Coordinate(newRow, newColumn), grid)) {
                edges.add(new Edge(from, new Coordinate(newRow, newColumn)));
            }
        }
    }
}
