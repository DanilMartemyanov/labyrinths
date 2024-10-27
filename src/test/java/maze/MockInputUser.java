package maze;

import backend.academy.maze.services.interfaces.ConsoleInputUser;
import java.util.Queue;

public class MockInputUser implements ConsoleInputUser {
    private Queue<String> input;
    private StringBuilder output;

    public MockInputUser(Queue<String> input) {
        this.input = input;
        this.output = new StringBuilder();
    }

    @Override
    public String getInput() {
        return input.poll();
    }

    @Override
    public void printMessage(String message) {
        output.append(message).append("\n");
    }

    @Override
    public void printlnMessage(String message) {
        output.append(message).append("\n");
    }
}
