package backend.academy.maze;

import java.util.ArrayList;
import java.util.HashSet;

public class DepthFirstSearch implements PathFinding {
    private HashSet<Cell> visited;
    private ArrayList<Coordinate> path;
    private Cell[][] grid;

    public DepthFirstSearch(Cell[][] grid) {
        this.grid = grid;
        this.visited = new HashSet<>();
        this.path = new ArrayList<>();
    }

    @Override
    public ArrayList<Coordinate> findPath(Coordinate start, Coordinate end) {
        if (dfs(start, end)) {
            return path;
        }
        return null;
    }

    public boolean dfs(Coordinate current, Coordinate end) {
        if (visited.contains(current) || !GeneratorPrim.checkBounds(current, grid)
            || grid[current.row()][current.column()].type == Type.WALL) {
            return false;
        }
        path.add(current);

        if (current.equals(end)) {
            return true;
        }
        visited.add(new Cell(current.row(), current.column(), Type.PASSAGE));

        HashSet<Cell> neighbours = new HashSet<>();
        ArrayList<Coordinate> directions = Coordinate.generateDirections(Constant.STEP_1);

        addNeighborDFS(directions, current, visited, neighbours);
        for (Cell neighbour : neighbours) {
            if (dfs(new Coordinate(neighbour.row, neighbour.column), end)) {
                return true;
            }

        }
        path.remove(current);
        return false;
    }

    private void addNeighborDFS(
        ArrayList<Coordinate> directions,
        Coordinate current,
        HashSet<Cell> visited,
        HashSet<Cell> neigbours
    ) {
        for (Coordinate direction : directions) {
            int rowNeighbor = current.row() + direction.row();
            int columnNeighbor = current.column() + direction.column();

            if (GeneratorPrim.checkBounds(new Coordinate(rowNeighbor, columnNeighbor), grid)
                && grid[rowNeighbor][columnNeighbor].type == Type.PASSAGE) {
                Cell neighbour = new Cell(rowNeighbor, columnNeighbor, Type.PASSAGE);
                if (!visited.contains(neighbour) && !neigbours.contains(neighbour)) {
                    neigbours.add(neighbour);
                }
            }
        }

    }
}
