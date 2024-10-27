package backend.academy.maze.services.interfaces;

import backend.academy.maze.enums.AlgorithmType;
import backend.academy.maze.enums.BoundType;
import backend.academy.maze.enums.FindPathType;

public interface MazeTypeProvider {

    AlgorithmType getGeneratorMazeType();

    BoundType getBoundType();

    FindPathType getSearcherType();
}
