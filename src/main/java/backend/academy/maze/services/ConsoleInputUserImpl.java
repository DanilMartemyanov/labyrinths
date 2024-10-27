package backend.academy.maze.services;

import backend.academy.maze.services.interfaces.ConsoleInputUser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class ConsoleInputUserImpl implements ConsoleInputUser {
    private BufferedReader bufferedReader;
    private PrintStream printStream;

    public ConsoleInputUserImpl() {
        this.bufferedReader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
        this.printStream = new PrintStream(System.out, true, StandardCharsets.UTF_8);
    }

    public String getInput() {
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void printMessage(String message) {
        printStream.print(message);
    }

    @Override
    public void printlnMessage(String message) {
        printStream.println(message);
    }
}
