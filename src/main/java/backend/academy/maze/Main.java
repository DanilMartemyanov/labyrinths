package backend.academy.maze;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GeneratorPrim generator = new GeneratorPrim();
        Maze maze = generator.generateMaze(11, 11);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        DepthFirstSearch depthFirstSearch = new DepthFirstSearch(maze.grid);
        try {
            System.out.println("Введите координаты начальной точки");
            int rowA = Integer.parseInt(br.readLine());
            int colA = Integer.parseInt(br.readLine());
            System.out.println("Введите координаты конечной точки");
            int rowB = Integer.parseInt(br.readLine());
            int colB = Integer.parseInt(br.readLine());

            Coordinate coordinateA = new Coordinate(rowA, colA);
            Coordinate coordinateB = new Coordinate(rowB, colB);
            System.out.println(depthFirstSearch.findPath(coordinateA, coordinateB));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
