package backend.academy.maze.services;

import backend.academy.maze.enums.AlgorithmType;
import backend.academy.maze.enums.BoundType;
import backend.academy.maze.enums.FindPathType;
import backend.academy.maze.services.interfaces.InputCommandUser;
import backend.academy.maze.services.interfaces.MazeTypeProvider;

public class MazeTypeProviderImpl implements MazeTypeProvider {
    private InputCommandUser inputCommandUser;

    public MazeTypeProviderImpl(InputCommandUser inputCommandUser) {
        this.inputCommandUser = inputCommandUser;
    }

    public AlgorithmType getGeneratorMazeType() {
        String inputAlgorithm = inputCommandUser.getNumberAlgorithm();
        return FactoryType.getAlgorithmGenerateMazeType(inputAlgorithm);
    }

    public BoundType getBoundType() {
        int inputBound = inputCommandUser.getNumberUserBound();
        return FactoryType.getBoundType(inputBound);
    }

    public FindPathType getSearcherType() {
        String findPathType = inputCommandUser.getNumberAlgorithm();
        return FactoryType.getFindPathMazeType(findPathType);
    }
}
