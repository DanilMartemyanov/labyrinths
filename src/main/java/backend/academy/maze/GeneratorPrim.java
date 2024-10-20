package backend.academy.maze;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashSet;

public class GeneratorPrim implements Generator {
    private final SecureRandom random = new SecureRandom();


    public Maze generateMaze(int height, int width) {

        int heightMaze = Generator.generateOdd(height);
        int widthMaze = Generator.generateOdd(width);
        Maze maze = new Maze(heightMaze, widthMaze);

        // Направления движения через 2 клетки (чтобы прыгать через стены)
        ArrayList<Coordinate> directions = Coordinate.generateDirections(Constant.STEP_2);

        int randomRow = random.nextInt(heightMaze / 2) * 2 + 1;
        int randomColumn = random.nextInt(widthMaze / 2) * 2 + 1;
        // Начальная точка

        System.out.println(randomRow);

        System.out.println(randomColumn);
        Cell startPoint = new Cell(randomRow, randomColumn, Type.PASSAGE);
        maze.grid[randomRow][randomColumn] = startPoint;

        // Множество соседей
        HashSet<Cell> neighbours = new HashSet<>();
        // Множество посещённых клеток
        HashSet<Cell> visited = new HashSet<>();
        visited.add(startPoint);

        // Добавляем начальных соседей
        addNeighbor(startPoint, directions, neighbours, maze.grid, visited);

        while (!neighbours.isEmpty()) {
            // Берём случайного соседа
            int indexNeighbour = random.nextInt(neighbours.size());
            Cell randomNeighbor = neighbours.stream().toList().get(indexNeighbour);
            System.out.println(randomNeighbor);
            // Находим посещённую клетку, которая находится на 2 клетки дальше от выбранного соседа
            Cell visitedCell = findVisitedNeighbor(randomNeighbor, directions, maze.grid, visited);

            // Если нашли посещённую клетку
            if (visitedCell != null) {
                // Меняем тип случайного соседа на проход
                randomNeighbor.type = Type.PASSAGE;
                visited.add(randomNeighbor); // Отмечаем текущую клетку как посещённую

                // Создаём проход между посещённой клеткой и соседом
                Generator.addPassageBetween(new Coordinate(visitedCell.row, visitedCell.column),
                    new Coordinate(randomNeighbor.row, randomNeighbor.column), maze.grid);

                // Добавляем новых соседей
                addNeighbor(randomNeighbor, directions, neighbours, maze.grid, visited);
            }

            // Удаляем соседа из множества, так как он уже обработан
            neighbours.remove(randomNeighbor);
            maze.printMaze();
            System.out.println("-----------------------");
        }

        // Финальный вывод лабиринта
        maze.printMaze();
        return maze;
    }

    // Находим посещённую клетку, которая находится на 2 клетки дальше от текущей
    public static Cell findVisitedNeighbor(
        Cell cell,
        ArrayList<Coordinate> directions,
        Cell[][] grid,
        HashSet<Cell> visited
    ) {
        for (Coordinate direction : directions) {
            int newRow = cell.row() + direction.row();
            int newCol = cell.column() + direction.column();

            if (checkBounds(new Coordinate(newRow, newCol), grid)) {
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
        ArrayList<Coordinate> directions,
        HashSet<Cell> neighbours,
        Cell[][] grid,
        HashSet<Cell> visited
    ) {
        for (Coordinate direction : directions) {
            int newRow = cell.row() + direction.row();
            int newCol = cell.column() + direction.column();
            System.out.println("Кандидат на соседа");
            // Проверяем, что клетка в пределах лабиринта и непосещена
            if (checkBounds(new Coordinate(newRow, newCol), grid)) {
                Cell neighbor = grid[newRow][newCol];
                System.out.println(neighbor);
                if (!visited.contains(neighbor) && !neighbours.contains(neighbor)) {
                    // Добавляем только непосещённые клетки
                    System.out.println("Добавляем соседа");
                    System.out.println(neighbor);
                    neighbours.add(neighbor);
                }
            }
        }
    }

    // Проверяем границы лабиринта
    public static boolean checkBounds(Coordinate cell, Cell[][] grid) {
        if (cell.row() > 0 && cell.row() < (grid.length - 1) && cell.column() > 0
            && cell.column() < (grid[0].length - 1)) {
            return true;
        }
        return false;
    }


}






