package maze;

import backend.academy.maze.tree.BinaryTree;
import backend.academy.maze.tree.Node;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BinaryTreeTest {


    @Test
    void add() {
        BinaryTree tree = new BinaryTree();
        tree.add(4);
        tree.add(3);
        tree.add(6);
        tree.add(5);

    }
}
