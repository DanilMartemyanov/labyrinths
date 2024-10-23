package backend.academy.maze;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Checker {
    public static Coordinate makeCorrectCoordinateForEntrance(BoundType boundType, Coordinate coordinate) {
        Coordinate newCoordinate;
        switch (boundType) {
            case UP -> newCoordinate = new Coordinate(coordinate.row() + 1, coordinate.column());

            case DOWN -> newCoordinate = new Coordinate(coordinate.row() - 1, coordinate.column());

            case RIGHT -> newCoordinate = new Coordinate(coordinate.row(), coordinate.column() - 1);

            case LEFT -> newCoordinate = new Coordinate(coordinate.row(), coordinate.column() + 1);

            default -> newCoordinate = null;

        }
        return newCoordinate;
    }

    public static boolean checkMatchEntrance(List<Coordinate> passages, Coordinate coordinate, BoundType boundType) {
        Coordinate correctForCheck = makeCorrectCoordinateForEntrance(boundType, coordinate);
        for (Coordinate passage : passages) {
            if (correctForCheck.equals(passage)) {
                return true;
            }
        }
        return false;
    }

    public static Coordinate checkEntrance(
        BufferedReader bufferedReader,
        PrintStream printStream,
        List<Coordinate> passages,
        BoundType boundType
    ) {
        boolean flag = false;
        Coordinate startEntrance = null;

        while (!flag) {
            startEntrance = InputUser.getUserCoordinate(bufferedReader, printStream);
            // проверка на совпадения ввода пользователя и реальным проходом
            flag = Checker.checkMatchEntrance(passages, startEntrance, boundType);
            if (!flag) {
                printStream.println("Укажите правильные координаты дверки: " + Constant.ENTRANCE);
            }
        }
        return startEntrance;
    }

    public static Coordinate checkEdnPoint(BufferedReader bufferedReader, PrintStream printStream, Maze maze) {
        Coordinate endPoint = null;
        boolean checkEndPoint = false;
        while (!checkEndPoint) {
            endPoint = InputUser.getUserCoordinate(bufferedReader, printStream);
            checkEndPoint = Generator.checkBounds(endPoint, maze.grid());
        }
        return endPoint;
    }

}
