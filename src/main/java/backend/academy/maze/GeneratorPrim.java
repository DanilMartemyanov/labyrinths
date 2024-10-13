package backend.academy.maze;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashSet;

public class GeneratorPrim implements Generator {

    public Maze generateMaze(int height, int width) {
        SecureRandom random = new SecureRandom();
        Maze maze = new Maze(height, width);

        // Направления движения через 2 клетки (чтобы прыгать через стены)
        ArrayList<Coordinate> directions = Coordinate.generateDirections(2);

        // Начальная точка
        int randomRow = random.nextInt(height / 2) * 2 + 1;
        int randomColumn = random.nextInt(width / 2) * 2 + 1;
        Cell startPoint = new Cell(randomRow, randomColumn, Type.PASSAGE);
        maze.grid[randomRow][randomColumn] = startPoint;

        // Множество соседей
        HashSet<Cell> neighbours = new HashSet<>();
        // Множество посещённых клеток
        HashSet<Cell> visited = new HashSet<>();
        visited.add(startPoint);

        // Вывод начальной точки
        System.out.println("startPoint: " + startPoint.row + ", " + startPoint.column);

        // Добавляем начальных соседей
        addNeighbor(startPoint, directions, neighbours, maze.grid, visited);

        while (!neighbours.isEmpty()) {
            // Берём случайного соседа
            int indexNeighbour = random.nextInt(neighbours.size());
            Cell randomNeighbor = neighbours.stream().toList().get(indexNeighbour);

            // Вывод случайного соседа
            System.out.println("рандомный сосед");
            System.out.println("row: " + randomNeighbor.row + " column: " + randomNeighbor.column);

            // Находим посещённую клетку, которая находится на 2 клетки дальше от выбранного соседа
            Cell visitedCell = findVisitedNeighbor(randomNeighbor, directions, maze.grid, visited);

            // Если нашли посещённую клетку
            if (visitedCell != null) {
                // Меняем тип случайного соседа на проход
                randomNeighbor.type = Type.PASSAGE;
                visited.add(randomNeighbor); // Отмечаем текущую клетку как посещённую

                // Создаём проход между посещённой клеткой и соседом
                addPassageBetween(visitedCell, randomNeighbor, maze.grid);

                // Вывод прохода между посещённой клеткой и соседом
                System.out.println("Создаём проход между клетками");
                System.out.println("visitedCell: " + visitedCell.row + ", " + visitedCell.column);
                System.out.println("randomNeighbor: " + randomNeighbor.row + ", " + randomNeighbor.column);

                // Добавляем новых соседей
                addNeighbor(randomNeighbor, directions, neighbours, maze.grid, visited);
            }

            // Удаляем соседа из множества, так как он уже обработан
            neighbours.remove(randomNeighbor);

            // Вывод текущего состояния лабиринта
            maze.printMaze();
            System.out.println();
            System.out.println("---------------------");
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

            // Проверяем, что клетка в пределах лабиринта и непосещена
            if (checkBounds(new Coordinate(newRow, newCol), grid)) {
                Cell neighbor = grid[newRow][newCol];
                if (!visited.contains(neighbor) && !neighbours.contains(neighbor)) {
                    // Вывод добавленного соседа
                    System.out.println("neighbour: " + neighbor.row + ", " + neighbor.column);
                    neighbours.add(neighbor); // Добавляем только непосещённые клетки
                }
            }
        }
    }

    // Проверяем границы лабиринта (оставлен прежний метод)
    public static boolean checkBounds(Coordinate cell, Cell[][] grid) {
        if (cell.row() >= 0 && cell.row() < grid.length && cell.column() >= 0 && cell.column() < grid[0].length) {
            return true;
        }
        return false;
    }

    // Добавляем проход между клетками (удаляем стену)
    public static void addPassageBetween(Cell current, Cell neighbor, Cell[][] grid) {
        System.out.println("текущая клетка");
        System.out.println("current: " + current.row + ", " + current.column);
        System.out.println("neighbor: " + neighbor.row + ", " + neighbor.column);
        int passageRow = (current.row() + neighbor.row()) / 2;
        int passageColumn = (current.column() + neighbor.column()) / 2;
        System.out.println("Создался проход на позиции");
        System.out.println("row: " + passageRow + " column: " + passageColumn);
        grid[passageRow][passageColumn] = new Cell(passageRow, passageColumn, Type.PASSAGE);  // Устанавливаем проход
    }
}






