package backend.academy.maze;

import java.security.SecureRandom;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Edge implements Comparable<Edge> {
    private Coordinate firstNode;
    private Coordinate secondNode;
    private int weight;
    private final SecureRandom random = new SecureRandom();

    public Edge(Coordinate firstNode, Coordinate secondNode) {
        this.firstNode = firstNode;
        this.secondNode = secondNode;
        this.weight = random.nextInt(1, Constant.BOUND_20);
    }

    @Override
    public String toString() {
        return "Edge firstNode: " + firstNode + ", secondNode: " + secondNode + ", weight: " + weight;
    }

    @Override
    public int compareTo(Edge edge) {
        return this.weight - edge.weight;
    }

}
