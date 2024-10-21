package backend.academy.maze;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Main {
    public static void main(String[] args) {
        UserInterface userInterface = new UserInterface();
        userInterface.startGame();
        // TODO: сделать разные типы поверхности, сделать несколько проходов
    }
}
