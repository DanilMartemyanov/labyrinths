package backend.academy.maze;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

public class DepthFirstSearch implements PathFinding {
    private HashSet<Coordinate> visited;  // Используем Coordinate вместо Cell для отслеживания посещённых клеток
    private ArrayList<Coordinate> path;
    private Maze maze;

    public DepthFirstSearch(Maze maze) {
        this.maze = maze;
        this.visited = new HashSet<>();
        this.path = new ArrayList<>();
    }

    @Override
    public ArrayList<Coordinate> findPath(Coordinate start, Coordinate end) {
        // Используем явный стек вместо рекурсии
        Stack<Coordinate> stack = new Stack<>();
        stack.push(start);

        while (!stack.isEmpty()) {
            Coordinate current = stack.pop();

            if (visited.contains(current) || !Generator.checkBounds(current, maze.grid)
                || maze.grid[current.row()][current.column()].type == Type.WALL) {
                continue;
            }

            path.add(current);

            // Если мы достигли конечной точки
            if (current.equals(end)) {
                return path;
            }

            visited.add(current);

            // Добавляем соседей
            for (Coordinate neighbor : getNeighbors(current)) {
                if (!visited.contains(neighbor)) {
                    stack.push(neighbor);
                }
            }
        }
        return null;
    }

    // Метод для получения соседей текущей клетки
    private List<Coordinate> getNeighbors(Coordinate current) {
        ArrayList<Coordinate> directions = Coordinate.generateDirections(Constant.STEP_1);
        List<Coordinate> neighbors = new ArrayList<>();

        for (Coordinate direction : directions) {
            int newRow = current.row() + direction.row();
            int newCol = current.column() + direction.column();

            if (Generator.checkBounds(new Coordinate(newRow, newCol), maze.grid)
                && maze.grid[newRow][newCol].type == Type.PASSAGE) {
                neighbors.add(new Coordinate(newRow, newCol));
            }
        }
        return neighbors;
    }

    // Метод для вывода пути на сетку
    public void printPath(ArrayList<Coordinate> path) {
        for (Coordinate coordinate : path) {
            maze.grid[coordinate.row()][coordinate.column()].type = Type.CAKE;
        }
    }
}
