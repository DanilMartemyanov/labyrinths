package backend.academy.maze;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class UserInterface {
    private  final PrintStream printStream = new PrintStream(System.out);
    private final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private final GeneratorPrim generatorPrim = new GeneratorPrim();
    private  final GeneratorKruskal generatorKruskal = new GeneratorKruskal();


    public UserInterface() {}

    public  void startGame(){
        while(true) {

            printStream.println("Хэй хэй хэй ! Готовы сгенерировать лабиринт ?");
            printStream.println("Введите y/n");

            String ready = InputUser.startGame(bufferedReader, printStream);

            if (ready.equals(Constant.YES)) {

                printStream.println("Укажите размер лабиринта");
                printStream.print("Введите длину: ");
                int height = InputUser.getNumberUser(bufferedReader, printStream);
                printStream.print("Введите ширину: ");
                int width = InputUser.getNumberUser(bufferedReader, printStream);

                printStream.println("Выберите алгоритм для генерации лабиринта:");
                printStream.println("[1] - алгоритм Прима");
                printStream.println("[2] - алгоритм Крускала");
                printStream.println("Ваш выбор: ");

                AlgorithmType algorithmType = InputUser.algorithmType(bufferedReader, printStream);

                switch (algorithmType) {
                    case PRIM -> generatorPrim.generateMaze(height, width);
                    case KRUSKAL -> generatorKruskal.generateMaze(height, width);
                }
            } else {
                break;
            }
        }
    }
}
