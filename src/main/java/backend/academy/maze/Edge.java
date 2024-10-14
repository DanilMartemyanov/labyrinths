package backend.academy.maze;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;


@Getter
@Setter
public class Edge {
    private Coordinate firstNode;
    private Coordinate secondNode;

    public Edge(Coordinate firstNode, Coordinate secondNode) {
        this.firstNode = firstNode;
        this.secondNode = secondNode;
    }

    @Override
    public String toString() {
        return "Edge firstNode: " + firstNode + ", secondNode: " + secondNode + "";
    }
}
