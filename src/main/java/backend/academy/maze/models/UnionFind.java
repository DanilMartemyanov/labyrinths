package backend.academy.maze.models;

public interface UnionFind {
    // объединение множеств
    void union(int cellX, int cellY);

    // проверка на принадлежность к одному множеству
    boolean find(int cellX, int cellY);
}
