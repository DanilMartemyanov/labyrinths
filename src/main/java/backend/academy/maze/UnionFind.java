package backend.academy.maze;

public interface UnionFind {
    void union(int cellX, int cellY); // объединение множеств
    boolean find(int cellX, int cellY); // проверка на принадлежность к одному множеству
}
