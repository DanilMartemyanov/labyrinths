package backend.academy.maze.services;

import backend.academy.maze.algorithms.generators.Generator;
import backend.academy.maze.enums.AlgorithmType;
import backend.academy.maze.enums.BoundType;
import backend.academy.maze.enums.CellType;
import backend.academy.maze.enums.FindPathType;
import backend.academy.maze.models.Coordinate;
import backend.academy.maze.models.Maze;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class CheckInputUser {
    private static final Pattern PATTERNSTARTGAME = Pattern.compile("^[ynYN]$");
    private static final Pattern PATTERNNUMBERALGORITHM = Pattern.compile("^[12]$");
    private static final Pattern PATTERNNUMBERBOUND = Pattern.compile("^[1234]$");
    private static final Pattern PATTERNNUMBERUSER = Pattern.compile("^[0-9]\\d*$");
    private static final Pattern PATTERNNUMBERSIZEMAZE = Pattern.compile("^[4-9]\\d*$|^[1-9]\\d{1,}$$");
    private static final int NUMBER_1 = 1;
    private static final int NUMBER_2 = 2;
    private static final int NUMBER_4 = 4;

    private CheckInputUser() {
    }

    public static String startGame(BufferedReader bufferedReader, PrintStream printStream) {
        try {
            String ready = bufferedReader.readLine();
            Matcher matcherReady = PATTERNSTARTGAME.matcher(ready);

            while (!matcherReady.find()) {
                printStream.println(Constant.INCORRECTINPUT);
                ready = bufferedReader.readLine();
                matcherReady = PATTERNSTARTGAME.matcher(ready);
            }
            return ready.toLowerCase();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static String getNumberAlgorithm(BufferedReader bufferedReader, PrintStream printStream) {
        try {
            String answerAlgorithm = bufferedReader.readLine();
            Matcher matcherAlgorithm = PATTERNNUMBERALGORITHM.matcher(answerAlgorithm);
            while (!matcherAlgorithm.find()) {
                printStream.println(Constant.INCORRECTINPUT);
                answerAlgorithm = bufferedReader.readLine();
                matcherAlgorithm = PATTERNNUMBERALGORITHM.matcher(answerAlgorithm);
            }
            return answerAlgorithm;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static AlgorithmType getAlgorithmGenerateMazeType(BufferedReader bufferedReader, PrintStream printStream) {
        switch (getNumberAlgorithm(bufferedReader, printStream)) {
            case "1":
                return AlgorithmType.PRIM;
            case "2":
                return AlgorithmType.KRUSKAL;
            default:
                return null;
        }
    }

    public static int getNumberUser(BufferedReader bufferedReader, PrintStream printStream) {
        try {
            String numberUser = bufferedReader.readLine();
            Matcher matcherAlgorithm = PATTERNNUMBERUSER.matcher(numberUser);
            while (!matcherAlgorithm.find()) {
                printStream.println(Constant.INCORRECTINPUT);
                numberUser = bufferedReader.readLine();
                matcherAlgorithm = PATTERNNUMBERUSER.matcher(numberUser);
            }
            return Integer.parseInt(numberUser);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static FindPathType getFindPathMazeType(BufferedReader bufferedReader, PrintStream printStream) {
        switch (getNumberAlgorithm(bufferedReader, printStream)) {
            case "1":
                return FindPathType.DFS;
            case "2":
                return FindPathType.BFS;
            default:
                return null;
        }
    }

    public static Coordinate getUserCoordinate(BufferedReader bufferedReader, PrintStream printStream) {
        printStream.println(Constant.COORDINATE + "y(строчка):");
        int userInputRow = getNumberUser(bufferedReader, printStream);
        printStream.println(Constant.COORDINATE + "x(колонка):");
        int userInputColumn = getNumberUser(bufferedReader, printStream);
        return new Coordinate(userInputRow, userInputColumn);
    }

    public static int getNumberUserBound(BufferedReader bufferedReader, PrintStream printStream) {
        try {
            String numberUser = bufferedReader.readLine();
            Matcher matcher = PATTERNNUMBERBOUND.matcher(numberUser);
            while (!matcher.find()) {
                printStream.println(Constant.INCORRECTINPUT);
                numberUser = bufferedReader.readLine();
                matcher = PATTERNNUMBERBOUND.matcher(numberUser);
            }
            return Integer.parseInt(numberUser);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static BoundType getBoundType(BufferedReader bufferedReader, PrintStream printStream) {
        BoundType boundType;
        switch (getNumberUserBound(bufferedReader, printStream)) {
            case NUMBER_1 -> boundType = BoundType.UP;
            case NUMBER_2 -> boundType = BoundType.DOWN;
            case Constant.NUMBER_3 -> boundType = BoundType.RIGHT;
            case NUMBER_4 -> boundType = BoundType.LEFT;
            default -> throw new RuntimeException("Invalid bound type");

        }
        return boundType;
    }

    public static int getNumberUserSizeMaze(BufferedReader bufferedReader, PrintStream printStream) {
        try {
            String numberUser = bufferedReader.readLine();
            Matcher matcher = PATTERNNUMBERSIZEMAZE.matcher(numberUser);
            while (!matcher.find()) {
                printStream.println(Constant.INCORRECTINPUT);
                numberUser = bufferedReader.readLine();
                matcher = PATTERNNUMBERSIZEMAZE.matcher(numberUser);
            }
            return Integer.parseInt(numberUser);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
            startEntrance = CheckInputUser.getUserCoordinate(bufferedReader, printStream);
            // проверка на совпадения ввода пользователя и реальным проходом
            flag = Checker.checkMatchEntrance(passages, startEntrance, boundType);
            if (!flag) {
                printStream.println("Укажите правильные координаты дверки: " + CellType.ENTRANCE);
            }
        }
        return startEntrance;
    }

    public static Coordinate checkEndPoint(BufferedReader bufferedReader, PrintStream printStream, Maze maze) {
        Coordinate endPoint = null;
        boolean checkEndPoint = false;
        while (!checkEndPoint) {
            endPoint = CheckInputUser.getUserCoordinate(bufferedReader, printStream);
            checkEndPoint = Generator.checkBounds(endPoint, maze.grid());
            if (!checkEndPoint) {
                printStream.println("Укажите точку в границах лабиринта");
            }
        }
        return endPoint;
    }
}
