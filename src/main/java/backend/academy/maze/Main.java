package backend.academy.maze;



import lombok.experimental.UtilityClass;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

@UtilityClass
public class Main {
    public static void main(String[] args) throws IOException {
//        UserInterface userInterface = new UserInterface();
//        userInterface.startGame();
        GeneratorPrim generator = new GeneratorPrim();
        Maze maze = generator.generateMaze(21, 21);
        DepthFirstSearch depthFirstSearch = new DepthFirstSearch(maze);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите координату");
        int x1 = Integer.parseInt(bufferedReader.readLine());
        System.out.println("Введите координату");
        int y1 = Integer.parseInt(bufferedReader.readLine());
        System.out.println("Введите координату");
        int x2 = Integer.parseInt(bufferedReader.readLine());
        System.out.println("Введите координату");
        int y2 = Integer.parseInt(bufferedReader.readLine());
        Coordinate start = new Coordinate(x1, y1);
        Coordinate end = new Coordinate(x2, y2);
        ArrayList<Coordinate> path = depthFirstSearch.findPath(start, end);
        depthFirstSearch.printPath(path,start, end );
        maze.printMaze();

    }
}
