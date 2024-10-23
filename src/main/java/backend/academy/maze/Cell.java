package backend.academy.maze;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Cell {
    int row;
    int column;
    CellType type;

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
