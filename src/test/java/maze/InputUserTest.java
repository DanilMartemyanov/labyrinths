package maze;

import backend.academy.maze.enums.AlgorithmType;
import backend.academy.maze.services.InputUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class InputUserTest {
    private PrintStream printStream;
    @BeforeEach
    void setUp() {
        printStream = new PrintStream(System.out);
    }

    @Test
    void algorithmTypeTest(){
        String inputUser = "1";
        BufferedReader bufferedReader =
            new BufferedReader(new InputStreamReader(new ByteArrayInputStream(inputUser.getBytes())));
        Assertions.assertEquals(InputUser.getAlgorithmGenerateMazeType(bufferedReader,printStream), AlgorithmType.PRIM);
    }

    @Test
    void getNumberUserTest(){
        String inputUser = "1";
        BufferedReader bufferedReader =
            new BufferedReader(new InputStreamReader(new ByteArrayInputStream(inputUser.getBytes())));
        Assertions.assertEquals(InputUser.getNumberUser(bufferedReader,printStream), 1);
    }


    @Test
    void getNumberUserBoundTest(){
        String inputUser = "1";
        BufferedReader bufferedReader =
            new BufferedReader(new InputStreamReader(new ByteArrayInputStream(inputUser.getBytes())));
        Assertions.assertEquals(InputUser.getNumberUserBound(bufferedReader,printStream), 1);
    }

}
