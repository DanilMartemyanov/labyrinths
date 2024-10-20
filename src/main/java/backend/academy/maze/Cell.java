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
    Type type;

    @Override
    public String toString() {
        return "row: " + row + ", column: " + column + ", type: " + type;
    }
}
