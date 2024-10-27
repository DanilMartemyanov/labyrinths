package backend.academy.maze;

import backend.academy.game.UserInterface;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Main {
    public static void main(String[] args) {
        UserInterface userInterface = new UserInterface();
        userInterface.startGame();
    }
}
