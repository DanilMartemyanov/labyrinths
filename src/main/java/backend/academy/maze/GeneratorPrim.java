package backend.academy.maze;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashSet;

public class GeneratorPrim implements Generator {
    public Maze generateMaze(int height, int width) {
        SecureRandom random = new SecureRandom();
        // создание сетки
        Cell[][] grid = new Cell[height][width];

        // создание лабиринта
        Maze maze = new Maze(height, width, grid);
        // заполнение сетки
        maze.initializeGrid();

        // создание направления
        ArrayList<Coordinate> directions = Coordinate.generateDirections(2);
        // Начальная точка
        int randomRow = random.nextInt(height / 2) * 2 + 1;
        int randomColumn = random.nextInt(width / 2) * 2 + 1;
        Cell startPoint = new Cell(randomRow, randomColumn, Type.PASSAGE);
        grid[randomRow][randomColumn] = startPoint;

        // Множество соседей
        HashSet<Cell> neighbours = new HashSet<>();
        neighbours.add(startPoint);

        // Список посещенных клеток
        HashSet<Cell> visited = new HashSet<>();
        visited.add(startPoint);

        while (!neighbours.isEmpty()) {
            int indexNeighbour = random.nextInt(neighbours.size());
            Cell randomNeighbor = neighbours.stream().toList().get(indexNeighbour);

            // Удаляем из множества соседей
            neighbours.remove(randomNeighbor);

            // Добавляем проход между ячейками
            addPassageBetween(startPoint, randomNeighbor, grid);

            // Добавляем новых соседей, если они не посещены
            addNeighbour(randomNeighbor, directions, neighbours, grid, visited);

            // Отмечаем как посещённого
            visited.add(randomNeighbor);

            // Устанавливаем новую стартовую точку
            startPoint = randomNeighbor;
            maze.printMaze();
            System.out.println();
            System.out.println("-------------------------");
        }
        maze.printMaze();
        return maze;
    }


    public static void addNeighbour(
        Cell cell,
        ArrayList<Coordinate> directions,
        HashSet<Cell> neighbours,
        Cell[][] grid,
        HashSet<Cell> visited
    ) {
        for (Coordinate coordinate : directions) {
            int row = cell.row() + coordinate.row();
            int column = cell.column() + coordinate.column();

            // Проверяем границы
            if (checkBounds(new Coordinate(row, column), grid)) {
                continue;
            }

            // Проверяем, если клетка уже посещена или является проходом
            if (grid[row][column].type == Type.PASSAGE || visited.contains(grid[row][column])) {
                continue;
            }

            // Добавляем в соседи, если клетка не посещена
            neighbours.add(grid[row][column]);
        }

    }

    public static boolean checkBounds(Coordinate cell, Cell[][] grid) {
        return cell.row() >= grid.length || cell.row() < 0 || cell.column() >= grid[0].length || cell.column() < 0;
    }

    public static void addPassageBetween(Cell cell, Cell neighbor, Cell[][] grid) {
        int passageRow = (cell.row() + neighbor.row()) / 2;
        int passageColumn = (cell.column() + neighbor.column()) / 2;
        grid[passageRow][passageColumn] = new Cell(passageRow, passageColumn, Type.PASSAGE);
    }
}

