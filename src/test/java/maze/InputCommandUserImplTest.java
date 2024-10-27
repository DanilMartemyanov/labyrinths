package maze;

import backend.academy.maze.enums.AlgorithmType;
import backend.academy.maze.enums.BoundType;
import backend.academy.maze.services.FactoryType;
import backend.academy.maze.services.InputCommandUserImpl;
import backend.academy.maze.services.MazeTypeProviderImpl;
import backend.academy.maze.services.interfaces.MazeTypeProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.LinkedList;
import java.util.Queue;

public class InputCommandUserImplTest {

    @Test
    void algorithmTypeTest() {
        Queue<String> input = new LinkedList<>();
        MockInputUser mockInputUser = new MockInputUser(input);
        InputCommandUserImpl inputCommandUser = new InputCommandUserImpl(mockInputUser);
        MazeTypeProvider workWithTypes = new MazeTypeProviderImpl(inputCommandUser);
        input.add("1");

        AlgorithmType result = workWithTypes.getGeneratorMazeType();
        Assertions.assertEquals(AlgorithmType.PRIM, result);
    }

    @Test
    void getNumberUserTest() {
        Queue<String> input = new LinkedList<>();
        MockInputUser mockInputUser = new MockInputUser(input);
        InputCommandUserImpl inputCommandUser = new InputCommandUserImpl(mockInputUser);
        input.add("2");

        String result = String.valueOf(inputCommandUser.getNumberUser());
        Assertions.assertEquals("2", result);
    }

    @Test
    void getBoundTest() {
        Queue<String> input = new LinkedList<>();
        MockInputUser mockInputUser = new MockInputUser(input);
        InputCommandUserImpl inputCommandUser = new InputCommandUserImpl(mockInputUser);
        MazeTypeProvider workWithTypes = new MazeTypeProviderImpl(inputCommandUser);
        input.add("1");

        BoundType result = FactoryType.getBoundType(1);
        Assertions.assertEquals(BoundType.UP, result);
    }

}
