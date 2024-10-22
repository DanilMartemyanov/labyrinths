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


    public Edge(Coordinate firstNode, Coordinate secondNode) {
        this.firstNode = firstNode;
        this.secondNode = secondNode;
        this.weight = 0;
    }

    @Override
    public String toString() {
        return "Edge firstNode: " + firstNode + ", secondNode: " + secondNode + ", weight: " + weight;
    }

    @Override
    public int compareTo(Edge edge) {
        return Integer.compare(weight, edge.weight);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Edge edge = (Edge) obj;
        return weight == edge.weight
            && firstNode.equals(edge.firstNode)
            && secondNode.equals(edge.secondNode);
    }

    @Override
    public int hashCode() {
        int result = firstNode.hashCode();
        result = 31 * result + secondNode.hashCode();
        result = 31 * result + weight;
        return result;
    }

}
