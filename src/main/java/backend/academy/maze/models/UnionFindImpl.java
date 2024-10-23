package backend.academy.maze.models;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UnionFindImpl implements UnionFind {
    /**
     * UnionFindImpl - класс, который представляет реализацию структуры данных UnionFind
     * для работы с непересекающимися множествами
     */

    private int[] nodes;
    private int[] size;

    public UnionFindImpl(int size) {
        nodes = new int[size];
        this.size = new int[size];
        initialize(size);
    }

    @Override
    public void union(int cellX, int cellY) {
        int rootX = getRoot(cellX);
        int rootY = getRoot(cellY);

        /**
         * Объединяем меньший корень под больший (по размеру)
         * Меньшее поддерево обхединяем с большим
         */

        if (rootX != rootY) {
            if (size[rootX] < size[rootY]) {
                nodes[rootX] = rootY;
                size[rootY] += size[rootX];
            } else {
                nodes[rootY] = rootX;
                size[rootX] += size[rootY];
            }
        }
    }

    @Override
    public boolean find(int cellX, int cellY) {
        return getRoot(cellX) == getRoot(cellY);
    }


    // Инициализация: каждый представитель (клетка) - множество
    public final void initialize(int size) {
        for (int i = 0; i < size; i++) {
            nodes[i] = i;
            this.size[i] = 1;
        }
    }

    //  Возвращает корень дерева - представителя множества
    public int getRoot(int p) {
        int parent = p;
        while (parent != nodes[parent]) {
            nodes[parent] = nodes[nodes[parent]];
            parent = nodes[parent];
        }
        return parent;
    }
}

