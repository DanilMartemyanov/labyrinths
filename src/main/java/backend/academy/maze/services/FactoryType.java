package backend.academy.maze.services;

import backend.academy.maze.enums.AlgorithmType;
import backend.academy.maze.enums.BoundType;
import backend.academy.maze.enums.FindPathType;
import lombok.experimental.UtilityClass;

@UtilityClass
public class FactoryType {
    private static final int NUMBER_1 = 1;
    private static final int NUMBER_2 = 2;
    private static final int NUMBER_4 = 4;

    public static AlgorithmType getAlgorithmGenerateMazeType(String input) {
        switch (input) {
            case "1":
                return AlgorithmType.PRIM;
            case "2":
                return AlgorithmType.KRUSKAL;
            default:
                return null;
        }
    }

    public static FindPathType getFindPathMazeType(String input) {
        switch (input) {
            case "1":
                return FindPathType.DFS;
            case "2":
                return FindPathType.BFS;
            default:
                return null;
        }
    }

    public static BoundType getBoundType(int input) {
        BoundType boundType;
        switch (input) {
            case NUMBER_1 -> boundType = BoundType.UP;
            case NUMBER_2 -> boundType = BoundType.DOWN;
            case Constant.NUMBER_3 -> boundType = BoundType.RIGHT;
            case NUMBER_4 -> boundType = BoundType.LEFT;
            default -> throw new RuntimeException("Invalid bound type");

        }
        return boundType;
    }
}
