package 并查集;

/**
 * 加权quick-union 路径压缩
 */
public class WeightedQuickUnionUF2 {
    private int[] id;
    private int[] sz;
    private int count;

    public WeightedQuickUnionUF2(int N) {
        count = N;
        id = new int[N];
        for(int i =0;i<N;i++) id[i]=i;
        sz = new int[N];
        for(int i=0;i<N;i++) sz[i] = 1;
    }

    private int count(){
        return count;
    }

    public boolean connected(int p, int q){
        return find(p)==find(q);
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
        int i = find(p);
        int j = find(q);
        if (i == j) return;
        if(sz[i]<sz[j]){
            id[i] = j;
            sz[j] += sz[i];
        }else{
            id[j]=i;
            sz[i]+=sz[j];
        }
        count--;
        }
    }
