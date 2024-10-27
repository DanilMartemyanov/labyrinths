package backend.academy.maze.algorithms.generators;


import backend.academy.maze.models.Cell;
import backend.academy.maze.models.Coordinate;
import backend.academy.maze.models.Maze;

public interface Generator {
    Maze generateMaze(int height, int width);

    // Создаёт проход между клетками
    static void addPassageBetween(Coordinate current, Coordinate neighbor, Maze maze) {
        /**
         * Вычисляем координаты точки, между двух других
         * операция позволяет не выходить за пределы int
         */
        int passageRow = current.row() + (neighbor.row() - current.row()) / 2;
        int passageColumn = current.column() + (neighbor.column() - current.column()) / 2;
        maze.setPassage(passageRow, passageColumn);  // Устанавливаем проход
    }

    static int getNumberOdd(int parametr) {
        if (parametr % 2 == 0) {
            return parametr + 1;
        } else {
            return parametr;
        }
    }

    // Проверяем границы лабиринта
    static boolean checkBounds(Coordinate cell, Cell[][] grid) {
        if (cell.row() > 0 && cell.row() < (grid.length - 1) && cell.column() > 0
            && cell.column() < (grid[0].length - 1)) {
            return true;
        }
        return false;
    }
}
