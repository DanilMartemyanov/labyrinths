package backend.academy.maze;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.security.SecureRandom;
import java.util.ArrayList;


@Getter
@Setter
public class Edge {
    private Coordinate firstNode;
    private Coordinate secondNode;
    private int weight;
    private final SecureRandom random = new SecureRandom();

    public Edge(Coordinate firstNode, Coordinate secondNode) {
        this.firstNode = firstNode;
        this.secondNode = secondNode;
        this.weight = random.nextInt(20) ;
    }

    @Override
    public String toString() {
        return "Edge firstNode: " + firstNode + ", secondNode: " + secondNode + ", weight: " + weight;
    }
}
