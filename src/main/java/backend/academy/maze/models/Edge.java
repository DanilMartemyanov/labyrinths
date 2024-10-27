package backend.academy.maze.models;


import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
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
    @SuppressFBWarnings("EQ_COMPARETO_USE_OBJECT_EQUALS")
    public int compareTo(Edge edge) {
        return Integer.compare(weight, edge.weight);
    }
}
