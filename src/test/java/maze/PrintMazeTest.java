package maze;

import backend.academy.maze.enums.BoundType;
import backend.academy.maze.models.Coordinate;
import backend.academy.maze.algorithms.generators.MazeGeneratorBasedOnKruskal;
import backend.academy.maze.models.Maze;
import backend.academy.maze.services.PrintMaze;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class PrintMazeTest {

    @Test
    void getLineTest() {
        Maze maze = new Maze(11, 11);
        List<Coordinate> coordinates = PrintMaze.getLine(maze, BoundType.UP) ;
        assertThat(coordinates)
            .startsWith(new Coordinate(1, 1))
            .endsWith(new Coordinate(1, 9));
    }

    @Test
    void createManyPassageTest() {
        MazeGeneratorBasedOnKruskal mazeGeneratorBasedOnKruskal = new MazeGeneratorBasedOnKruskal();
        Maze maze = mazeGeneratorBasedOnKruskal.generateMaze(12, 12);
        List<Coordinate> passages = maze.createManyEntrance(BoundType.UP);
        List<Coordinate> line = new ArrayList<>();
        PrintMaze.getHorizontalLine(0, maze.width(),1, line );
        assertThat(line).containsAll(passages);
    }

}
