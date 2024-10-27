package backend.academy.maze.models;

import backend.academy.maze.enums.CellType;
import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class Cell {
    private int row;
    private int column;
    public CellType type;

    public Cell(Cell originalCell) {
        this.row = originalCell.row;
        this.column = originalCell.column;
        this.type = originalCell.type; // Можно использовать, так как enum не изменяем
    }

    @Override
    public String toString() {
        return "row: " + row + ", column: " + column + ", type: " + type;
    }

}
