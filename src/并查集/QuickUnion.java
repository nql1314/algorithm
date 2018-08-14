package 并查集;

/**
 * quick-union
 */
public class QuickUnion extends UF {
    private int[] id;
    private int count;

    public QuickUnion(int N) {
        super(N);
    }

    public int find(int p) {
        while(p!=id[p]){
            p = id[p];
        }
        return p;
    }

    public void union(int p, int q) {
        int pId = find(p);
        int qId = find(q);
        if (pId == qId) return;
        id[p] = qId;
        count--;
        }
    }
