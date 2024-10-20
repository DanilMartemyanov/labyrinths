package backend.academy.maze;

import java.security.SecureRandom;

public interface Generator {
    Maze generateMaze(int height, int width);

    // Создаёт проход между клетками
    static void addPassageBetween(Coordinate current, Coordinate neighbor, Cell[][] grid) {
        int passageRow = (current.row() + neighbor.row()) / 2;
        int passageColumn = (current.column() + neighbor.column()) / 2;
        grid[passageRow][passageColumn] = new Cell(passageRow, passageColumn, Type.PASSAGE);  // Устанавливаем проход
    }

    static int generateOdd(int parametr) {
        if (parametr % 2 == 0) {
            return parametr + 1;
        }else {
            return parametr;
        }
    }
}
