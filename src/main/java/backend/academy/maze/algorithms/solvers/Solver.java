package backend.academy.maze.algorithms.solvers;

import backend.academy.maze.models.Coordinate;
import backend.academy.maze.models.Maze;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Solver {
    private Set<Coordinate> visited;  // Используем Coordinate вместо Cell для отслеживания посещённых клеток
    private ArrayList<Coordinate> path;
    private Maze maze;
    private Map<Coordinate, Coordinate> parentMap;

    public Solver(Maze maze) {
        this.maze = maze;
        this.visited = new HashSet<>();
        this.path = new ArrayList<>();
        this.parentMap = new HashMap<>();
    }

    public void clearCollections() {
        visited.clear();
        path.clear();
        parentMap.clear();
    }
}
