package backend.academy.maze;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Getter
@Setter
@Log4j2
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
        int rootX = root(cellX);
        int rootY = root(cellY);

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
        return root(cellX) == root(cellY);
    }


    // Инициализация: каждый представитель (клетка) - множество
    public void initialize(int size) {
        for (int i = 0; i < size; i++) {
            nodes[i] = i;
            this.size[i] = 1;
        }
    }

    //  Возвращает корень дерева - представителя множества
    public int root(int p) {
        int parent = p;
        while (parent != nodes[parent]) {
            nodes[parent] = nodes[nodes[parent]];
            parent = nodes[parent];
        }
        return parent;
    }
}

