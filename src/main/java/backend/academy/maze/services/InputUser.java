package backend.academy.maze.services;

import backend.academy.maze.enums.AlgorithmType;
import backend.academy.maze.enums.BoundType;
import backend.academy.maze.enums.FindPathType;
import backend.academy.maze.models.Coordinate;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class InputUser {
    static final Pattern PATTERNSTARTGAME = Pattern.compile("^[ynYN]$");
    static final Pattern PATTERNNUMBERALGORITHM = Pattern.compile("^[12]$");
    static final Pattern PATTERNNUMBERBOUND = Pattern.compile("^[1234]$");
    static final Pattern PATTERNNUMBERUSER = Pattern.compile("^[0-9]\\d*$");
    static final Pattern PATTERNNUMBERSIZEMAZE = Pattern.compile("^[4-9]\\d*$|^[1-9]\\d{1,}$$");

    private InputUser() {
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
            case Constant.NUMBER_1 -> boundType = BoundType.UP;
            case Constant.NUMBER_2 -> boundType = BoundType.DOWN;
            case Constant.NUMBER_3 -> boundType = BoundType.RIGHT;
            case Constant.NUMBER_4 -> boundType = BoundType.LEFT;
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
}
