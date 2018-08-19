package 并查集;

/**
 * quick-union路径压缩
 */
public class QuickUnion2 extends UF {
    private int[] id;
    private int count;

    public QuickUnion2(int N) {
        super(N);
    }

    public int find(int x) {
        int p = x,t;
        while(p!=id[p]){
            p = id[p];
        }
        while(x!=p){
            t = id[x];
            id[x] = p;
            x = t;
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
