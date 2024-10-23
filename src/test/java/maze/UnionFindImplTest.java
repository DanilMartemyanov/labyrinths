package maze;

import backend.academy.maze.models.UnionFindImpl;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
@Log4j2
public class UnionFindImplTest {
    private UnionFindImpl unionFind;
    @BeforeEach
     void setUp() {
        unionFind = new UnionFindImpl(10);
    }
    @Test
    void unionTest(){
        unionFind.union(1, 2);
        unionFind.union(1, 9);
        log.info(unionFind.nodes()[1]);
        log.info(unionFind.nodes()[2]);
        log.info(unionFind.nodes()[9]);

    }

    @Test
    void rootTest(){
        unionFind.union(1, 2);
        unionFind.union(1, 9);
        log.info(unionFind.root(9));
    }

    @Test
    void findTest(){
        unionFind.union(1, 2);
        unionFind.union(1, 9);
        log.info(unionFind.find(1, 9));
    }
}
