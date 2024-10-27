package backend.academy.maze.algorithms.generators;

import backend.academy.maze.enums.CellType;
import backend.academy.maze.models.Cell;
import backend.academy.maze.models.Coordinate;
import backend.academy.maze.models.Maze;
import backend.academy.maze.services.Constant;
import java.security.SecureRandom;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GeneratorPrim implements Generator {
    private final SecureRandom random = new SecureRandom();

    @Override
    public Maze generateMaze(int height, int width) {
        int heightMaze = Generator.getNumberOdd(height);
        int widthMaze = Generator.getNumberOdd(width);
        Maze maze = new Maze(heightMaze, widthMaze);

        // Направления движения через 2 клетки (чтобы прыгать через стены)
        List<Coordinate> directions = Coordinate.generateDirections(Constant.STEP_2);

        // Начальная точка
        int randomRow = random.nextInt(heightMaze / 2) * 2 + 1;
        int randomColumn = random.nextInt(widthMaze / 2) * 2 + 1;
        Cell startPoint = new Cell(randomRow, randomColumn, CellType.PASSAGE);
        maze.grid()[randomRow][randomColumn] = startPoint;

        // Множество соседей
        Set<Cell> neighbours = new HashSet<>();
        // Множество посещённых клеток
        Set<Cell> visited = new HashSet<>();
        visited.add(startPoint);

        // Добавляем начальных соседей
        addNeighbor(startPoint, directions, neighbours, maze.grid(), visited);

        while (!neighbours.isEmpty()) {
            // Берём случайного соседа
            int indexNeighbour = random.nextInt(neighbours.size());
            Cell randomNeighbor = neighbours.stream().toList().get(indexNeighbour);

            // Находим посещённую клетку, которая находится на 2 клетки дальше от выбранного соседа
            Cell visitedCell = findVisitedNeighbor(randomNeighbor, directions, maze.grid(), visited);

            // Если нашли посещённую клетку
            if (visitedCell != null) {
                // Меняем тип случайного соседа на проход
                randomNeighbor.type(CellType.PASSAGE);
                visited.add(randomNeighbor); // Отмечаем текущую клетку как посещённую

                // Создаём проход между посещённой клеткой и соседом
                Generator.addPassageBetween(new Coordinate(visitedCell.row(), visitedCell.column()),
                    new Coordinate(randomNeighbor.row(), randomNeighbor.column()), maze);

                // Добавляем новых соседей
                addNeighbor(randomNeighbor, directions, neighbours, maze.grid(), visited);
            }

            // Удаляем соседа из множества, так как он уже обработан
            neighbours.remove(randomNeighbor);
        }

        return maze;
    }

    // Находим посещённую клетку, которая находится на 2 клетки дальше от текущей
    public static Cell findVisitedNeighbor(
        Cell cell,
        List<Coordinate> directions,
        Cell[][] grid,
        Set<Cell> visited
    ) {
        for (Coordinate direction : directions) {
            int newRow = cell.row() + direction.row();
            int newCol = cell.column() + direction.column();

            if (Generator.checkBounds(new Coordinate(newRow, newCol), grid)) {
                Cell neighbor = grid[newRow][newCol];
                if (visited.contains(neighbor)) {
                    return neighbor;  // Возвращаем первую найденную посещённую клетку
                }
            }
        }
        return null; // Если не найдено, возвращаем null
    }

    // Добавляем соседей с учётом границ и непосещённых клеток
    public static void addNeighbor(
        Cell cell,
        List<Coordinate> directions,
        Set<Cell> neighbours,
        Cell[][] grid,
        Set<Cell> visited
    ) {
        for (Coordinate direction : directions) {
            int newRow = cell.row() + direction.row();
            int newCol = cell.column() + direction.column();

            // Проверяем, что клетка в пределах лабиринта и непосещена
            if (Generator.checkBounds(new Coordinate(newRow, newCol), grid)) {
                Cell neighbor = grid[newRow][newCol];
                if (!visited.contains(neighbor) && !neighbours.contains(neighbor)) {
                    // Добавляем только непосещённые клетки
                    neighbours.add(neighbor);
                }
            }
        }
    }

}






