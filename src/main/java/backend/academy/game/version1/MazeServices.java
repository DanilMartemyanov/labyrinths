package backend.academy.game.version1;

import backend.academy.maze.algorithms.generators.MazeGeneratorBasedOnKruskal;
import backend.academy.maze.algorithms.generators.MazeGeneratorBasedOnPrim;
import backend.academy.maze.algorithms.solvers.SearcherPathBasedOnBFS;
import backend.academy.maze.algorithms.solvers.SearcherPathBasedOnDFS;
import backend.academy.maze.enums.AlgorithmType;
import backend.academy.maze.enums.FindPathType;
import backend.academy.maze.models.Coordinate;
import backend.academy.maze.models.Maze;
import java.util.ArrayList;
import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MazeServices {
    public static Maze generateMaze(int height, int width, AlgorithmType algorithmType) {
        MazeGeneratorBasedOnPrim mazeGeneratorBasedOnPrim = new MazeGeneratorBasedOnPrim();
        MazeGeneratorBasedOnKruskal mazeGeneratorBasedOnKruskal = new MazeGeneratorBasedOnKruskal();
        Maze maze;
        switch (algorithmType) {
            case PRIM -> maze = mazeGeneratorBasedOnPrim.generateMaze(height, width);
            case KRUSKAL -> maze = mazeGeneratorBasedOnKruskal.generateMaze(height, width);
            default -> throw new IllegalStateException("Unexpected value: " + algorithmType);
        }
        return maze;
    }

    public static List<Coordinate> findPath(
        FindPathType findPathType,
        Maze mazeAttempt,
        Coordinate startPoint,
        Coordinate endPoint
    ) {
        SearcherPathBasedOnBFS searcherPathBasedOnBFS;
        SearcherPathBasedOnDFS searcherPathBasedOnDFS;
        List<Coordinate> path = new ArrayList<>();
        switch (findPathType) {
            case DFS -> {
                searcherPathBasedOnDFS = new SearcherPathBasedOnDFS(mazeAttempt);
                path = searcherPathBasedOnDFS.findPath(startPoint, endPoint);
            }

            case BFS -> {
                searcherPathBasedOnBFS = new SearcherPathBasedOnBFS(mazeAttempt);
                path = searcherPathBasedOnBFS.findPath(startPoint, endPoint);
            }
            default -> path = null;
        }
        return path;
    }
}
