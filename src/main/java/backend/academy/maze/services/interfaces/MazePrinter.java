package backend.academy.maze.services.interfaces;

import backend.academy.maze.models.Coordinate;
import backend.academy.maze.models.Edge;
import backend.academy.maze.models.Maze;
import java.util.List;

public interface MazePrinter {
     void printMaze(Maze maze);

     void printPath(List<Coordinate> path, Coordinate start, Coordinate end, Maze maze);

     void printMazeWithWeight(List<Edge> edges, Maze maze);

}
