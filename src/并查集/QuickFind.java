package 并查集;

/**
 * quick-find
 */
public class QuickFind extends UF {
    private int[] id;
    private int count;

    public QuickFind(int N) {
        super(N);
    }

    public int find(int p) {
        return id[p];
    }

    public void union(int p, int q) {
        int pId = find(p);
        int qId = find(q);
        if (pId == qId) return;
        for (int i = 0; i < id.length; i++) {
            if (id[i] == pId) {
                id[i] = qId;
            }
            count--;
        }
    }

}
