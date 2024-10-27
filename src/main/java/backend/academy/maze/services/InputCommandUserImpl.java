package backend.academy.maze.services;

import backend.academy.maze.services.interfaces.ConsoleInputUser;
import backend.academy.maze.services.interfaces.InputCommandUser;
import backend.academy.maze.services.validators.InputUserValidator;



public final class InputCommandUserImpl implements InputCommandUser {
    private ConsoleInputUser consoleInputUser;

    public InputCommandUserImpl(ConsoleInputUser consoleInputUser) {
        this.consoleInputUser = consoleInputUser;
    }

    public String getCommandStartGame() {
        String ready;
        do {
            ready = consoleInputUser.getInput();
            boolean check = InputUserValidator.isValidStartCommand(ready);
            if (!check) {
                consoleInputUser.printlnMessage(Constant.INCORRECTINPUT);
            }
        } while (!InputUserValidator.isValidStartCommand(ready));
        return ready.toLowerCase();
    }

    public String getNumberAlgorithm() {

        String answerAlgorithm;
        do {
            answerAlgorithm = consoleInputUser.getInput();
            boolean check = InputUserValidator.isValidNumberAlgorithm(answerAlgorithm);
            if (!check) {
                consoleInputUser.printlnMessage(Constant.INCORRECTINPUT);
            }
        } while (!InputUserValidator.isValidNumberAlgorithm(answerAlgorithm));
        return answerAlgorithm;

    }

    public int getNumberUser() {
        String numberUser;
        do {
            numberUser = consoleInputUser.getInput();
            boolean check = InputUserValidator.isValidNumberUser(numberUser);
            if (!check) {
                consoleInputUser.printlnMessage(Constant.INCORRECTINPUT);
            }
        } while (!InputUserValidator.isValidNumberUser(numberUser));
        return Integer.parseInt(numberUser);
    }

    public int getNumberUserBound() {

        String numberUserBound;
        do {
            numberUserBound = consoleInputUser.getInput();
            boolean check = InputUserValidator.isValidNumberBound(numberUserBound);
            if (!check) {
                consoleInputUser.printlnMessage(Constant.INCORRECTINPUT);
            }
        } while (!InputUserValidator.isValidNumberBound(numberUserBound));
        return Integer.parseInt(numberUserBound);

    }

    public int getNumberUserSizeMaze() {

        String numberUserSizeMaze;
        do {
            numberUserSizeMaze = consoleInputUser.getInput();
            boolean check = InputUserValidator.isValidNumberSizeMaze(numberUserSizeMaze);
            if (!check) {
                consoleInputUser.printlnMessage(Constant.INCORRECTINPUT);
            }
        } while (!InputUserValidator.isValidNumberSizeMaze(numberUserSizeMaze));
        return Integer.parseInt(numberUserSizeMaze);

    }

}
