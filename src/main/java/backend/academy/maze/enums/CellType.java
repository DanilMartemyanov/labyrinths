package backend.academy.maze.enums;

import lombok.Getter;

@Getter
public enum CellType {
    WALL("\u26D4"),
    PASSAGE("\u2705"),
    GLASS("\uD83D\uDD0E"),
    BOMB("\uD83D\uDCA3"),
    A("\uD83C\uDF82"),
    B("\uD83C\uDFC3\u200D♂\uFE0F"),
    GIFT("\uD83C\uDF81"),
    ENTRANCE("\uD83D\uDEAA");

    CellType(String value) {
        this.value = value;
    }

    private String value;
}



