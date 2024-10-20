package backend.academy.maze;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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
        Stack<Coordinate> stack = new Stack<>();
        Map<Coordinate, Coordinate> parentMap = new HashMap<>();  // Родители клеток

        stack.push(start);
        visited.add(start);

        while (!stack.isEmpty()) {
            Coordinate current = stack.pop();

            // Добавляем клетку в путь, если она еще не была посещена
            if (current.equals(end)) {
                // Восстанавливаем путь от конечной точки до начальной
                reconstructPath(start, end, parentMap);
                return path;
            }

            // Получаем соседей текущей клетки
            for (Coordinate neighbor : getNeighbors(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);  // Отмечаем соседа как посещённого
                    parentMap.put(neighbor, current);  // Запоминаем родителя для восстановления пути
                    stack.push(neighbor);  // Добавляем соседа в стек
                }
            }
        }
        return null;  // Путь не найден
    }

    // Восстановление пути по родителю, который записывается в Map
    private void reconstructPath(Coordinate start, Coordinate end, Map<Coordinate, Coordinate> parentMap) {
        Coordinate current = end;
        while (!current.equals(start)) {
            path.add(current);
            current = parentMap.get(current);
        }
        path.add(start);
        Collections.reverse(path);
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
    public void printPath(ArrayList<Coordinate> path, Coordinate start, Coordinate end) {

        maze.grid[start.row()][start.column()].type = Type.A;
        maze.grid[end.row()][end.column()].type = Type.B;

        path.remove(start);
        path.remove(end);

        for (Coordinate coordinate : path) {
            maze.grid[coordinate.row()][coordinate.column()].type = Type.GLASS;
        }
    }
}
