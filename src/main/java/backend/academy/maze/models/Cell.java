package backend.academy.maze.models;

import backend.academy.maze.enums.CellType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Cell {
    private int row;
    private int column;
    private CellType type;

    public Cell(Cell originalCell) {
        this.row = originalCell.row;
        this.column = originalCell.column;
        this.type = originalCell.type;
    }

    @Override
    public String toString() {
        return "row: " + row + ", column: " + column + ", type: " + type;
    }

}
