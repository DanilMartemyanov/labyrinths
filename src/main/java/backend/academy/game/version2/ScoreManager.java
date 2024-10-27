package backend.academy.game.version2;

import backend.academy.maze.models.Coordinate;
import backend.academy.maze.services.interfaces.ConsoleInputUser;
import java.util.Map;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ScoreManager {
    public static void calculatingBestPath(
        Map<Coordinate, Integer> possiblePath,
        Coordinate bestPoint,
        int distanceUser,
        ConsoleInputUser consoleInputUser
    ) {
        if (possiblePath.get(bestPoint) < distanceUser) {
            consoleInputUser.printlnMessage("Был маршрут оптимальнее");
            consoleInputUser.printlnMessage(
                "Вход: " + bestPoint + " со стоимостью пути: " + possiblePath.get(bestPoint));
            consoleInputUser.printlnMessage("Ваш маршрут составил: " + distanceUser);
        } else {
            consoleInputUser.printlnMessage("Ееееее, ваш маршрут самый крутой!");
        }
    }
}
