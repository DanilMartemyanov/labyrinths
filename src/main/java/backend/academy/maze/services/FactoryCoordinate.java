package backend.academy.maze.services;

import backend.academy.maze.enums.BoundType;
import backend.academy.maze.enums.CellType;
import backend.academy.maze.models.Coordinate;
import backend.academy.maze.models.Maze;
import backend.academy.maze.services.interfaces.ConsoleInputUser;
import backend.academy.maze.services.interfaces.InputCommandUser;
import backend.academy.maze.services.validators.CoordinateValidator;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressFBWarnings("PRMC_POSSIBLY_REDUNDANT_METHOD_CALLS")
public class FactoryCoordinate {
    private InputCommandUser inputCommandUser;
    private ConsoleInputUser consoleInputUser;

    public FactoryCoordinate(InputCommandUser inputCommandUser, ConsoleInputUser consoleInputUser) {
        this.inputCommandUser = inputCommandUser;
        this.consoleInputUser = consoleInputUser;
    }

    public Coordinate getUserCoordinateEntrance(List<Coordinate> passages, BoundType boundType) {
        Coordinate coordinate = getUserCoordinate();
        boolean check = CoordinateValidator.checkMatchEntrance(passages, coordinate, boundType);

        while (!check) {
            consoleInputUser.printlnMessage(
                "Укажите правильные координаты дверки: " + CellType.ENTRANCE.value());
            coordinate = getUserCoordinate();
            check = CoordinateValidator.checkMatchEntrance(passages, coordinate, boundType);

        }

        return coordinate;
    }

    public Coordinate getUserPoint(Maze maze) {
        Coordinate coordinate = getUserCoordinate();
        boolean check = CoordinateValidator.checkBounds(coordinate, maze);

        while (!check) {
            consoleInputUser.printlnMessage("Укажите координаты в границах лабиринта ");
            coordinate = getUserCoordinate();
            check = CoordinateValidator.checkBounds(coordinate, maze);
        }

        return coordinate;
    }

    public Coordinate getUserCoordinate() {
        consoleInputUser.printlnMessage(Constant.COORDINATE + "y(строчка):");
        int userInputRow = inputCommandUser.getNumberUser();
        consoleInputUser.printlnMessage(Constant.COORDINATE + "x(колонка):");
        int userInputColumn = inputCommandUser.getNumberUser();
        return new Coordinate(userInputRow, userInputColumn);
    }

    public static List<Coordinate> getLine(Maze maze, BoundType boundType) {
        List<Coordinate> line = new ArrayList<>();
        int start = 1;
        int endWidth = maze.width() - 2;
        int endHeight = maze.height() - 2;
        switch (boundType) {
            case BoundType.UP -> line = getHorizontalLine(start, endWidth, 1, line);
            case BoundType.DOWN -> line = getHorizontalLine(start, endWidth, endHeight, line);
            case BoundType.RIGHT -> line = getVerticalLine(start, endHeight, endWidth, line);
            case BoundType.LEFT -> line = getVerticalLine(start, endHeight, 1, line);
            default -> line = null;
        }
        return line;
    }

    public static List<Coordinate> getHorizontalLine(int start, int end, int row, List<Coordinate> line) {
        for (int i = start; i < end; i++) {
            line.add(new Coordinate(row, i));
        }
        return line;
    }

    public static List<Coordinate> getVerticalLine(int start, int end, int column, List<Coordinate> line) {
        for (int i = start; i < end; i++) {
            line.add(new Coordinate(i, column));
        }
        return line;
    }
}
