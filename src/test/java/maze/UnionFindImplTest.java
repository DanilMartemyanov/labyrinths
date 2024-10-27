package maze;

import backend.academy.maze.models.UnionFindImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UnionFindImplTest {
    private UnionFindImpl unionFind;

    @BeforeEach
    void setUp() {
        unionFind = new UnionFindImpl(10);
    }

    @Test
    void unionTest() {
        unionFind.union(1, 2);
        unionFind.union(1, 9);
        Assertions.assertEquals(unionFind.getRoot(2), unionFind.getRoot(9));
    }

    @Test
    void getRootTest() {
        unionFind.union(1, 2);
        unionFind.union(1, 9);
        Assertions.assertEquals(1, unionFind.getRoot(9));
    }

    @Test
    void findTest() {
        unionFind.union(1, 2);
        unionFind.union(1, 9);
        Assertions.assertTrue(unionFind.find(1, 9));
    }
}
