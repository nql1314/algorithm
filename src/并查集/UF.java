package 并查集;

/**
 * 并查集 抽象类
 */
public abstract class UF {
    private int[] id;
    private int count;

    public UF(int N) {
        id = new int[N];
        count++;
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }
    public int count() {
        return count;
    }
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public abstract int find(int p);

    public abstract void union(int p, int q);
}
