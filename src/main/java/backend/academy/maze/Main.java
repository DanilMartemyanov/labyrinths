package backend.academy.maze;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        GeneratorPrim generator = new GeneratorPrim();
        generator.generateMaze(11,11);

    }
}
