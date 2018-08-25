package 排序;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class IndexMaxPQ<Key extends Comparable<Key>> implements Iterable<Integer> {
    private int N;           // pq中元素个数
    private int[] pq;        // 优先级堆
    private int[] qp;        // pq的反置 满足qp[pq[i]] = pq[qp[i]] = i
    private Key[] keys;      // 索引-元素，k-v对

    public IndexMaxPQ(int NMAX) {
        keys = (Key[]) new Comparable[NMAX + 1];    // make this of length NMAX??
        pq   = new int[NMAX + 1];
        qp   = new int[NMAX + 1];                   // make this of length NMAX??
        for (int i = 0; i <= NMAX; i++) qp[i] = -1;//一开始pq没元素，所以qp值置-1
    }

    // is the priority queue empty?
    public boolean isEmpty() { return N == 0; }

    // is the index k in the priority queue?
    public boolean contains(int k) {
        return qp[k] != -1; //qp[k] 是索引k在pq中的位置，不是-1就存在
    }

    // number of elements in the priority queue
    public int size() {
        return N;
    }

    // associate key with index k
    public void insert(int k, Key key) {
        if (contains(k)) throw new RuntimeException("item is already in pq"); //k存在
        N++;
        qp[k] = N; //新的位置赋值给qp[k]
        pq[N] = k;
        keys[k] = key;
        swim(N);
    }

    // return index of a maximal key
    public int max() {
        if (N == 0) throw new RuntimeException("Priority queue underflow");
        return pq[1];//顶部为最大值的索引
    }

    // return a maximal key
    public Key maxKey() {
        if (N == 0) throw new RuntimeException("Priority queue underflow");
        return keys[pq[1]];//索引对应的值
    }

    // delete a maximal key and returns its associated index
    public int delMax() {
        if (N == 0) throw new RuntimeException("Priority queue underflow");
        int max = pq[1]; //最大值索引
        exch(1, N--);
        sink(1);
        qp[max] = -1;            // delete，删除最大值索引
        keys[pq[N+1]] = null;    // to help with garbage collection
        pq[N+1] = -1;            // not needed
        return max;
    }

    // associate key with index k (change key is index k is alread present)
    public void put(int k, Key key) {
        if (!contains(k)) insert(k, key);
        else changeKey(k, key);
    }

    // return key associated with index k
    public Key get(int k) {
        if (!contains(k)) throw new RuntimeException("item is not in pq");
        else return keys[pq[k]]; //存疑 感觉应该返回keys[k]？
    }

    // change the key associated with index k
    public void changeKey(int k, Key key) {
        if (!contains(k)) throw new RuntimeException("item is not in pq");
        keys[k] = key;
        swim(qp[k]);
        sink(qp[k]);
    }

    // increase the key associated with index k
    public void increaseKey(int k, Key key) {
        if (!contains(k)) throw new RuntimeException("item is not in pq");
        if (keys[k].compareTo(key) >= 0) throw new RuntimeException("illegal increase");
        keys[k] = key;
        swim(qp[k]);
    }

    // decrease the key associated with index k
    public void decreaseKey(int k, Key key) {
        if (!contains(k)) throw new RuntimeException("item is not in pq");
        if (keys[k].compareTo(key) <= 0) throw new RuntimeException("illegal increase");
        keys[k] = key;
        sink(qp[k]);
    }


    /**************************************************************
     * General helper functions
     **************************************************************/
    private boolean less(int i, int j) {
        return keys[pq[i]].compareTo(keys[pq[j]]) < 0;
    }

    private void exch(int i, int j) {
        int swap = pq[i]; pq[i] = pq[j]; pq[j] = swap;
        qp[pq[i]] = i; qp[pq[j]] = j;
    }


    /**************************************************************
     * Heap helper functions
     **************************************************************/
    private void swim(int k)  {
        while (k > 1 && less(k/2, k)) {
            exch(k, k/2);
            k = k/2;
        }
    }

    private void sink(int k) {
        while (2*k <= N) {
            int j = 2*k;
            if (j < N && less(j, j+1)) j++;
            if (!less(k, j)) break;
            exch(k, j);
            k = j;
        }
    }


    /***********************************************************************
     * Iterators
     **********************************************************************/

    /**
     * Return an iterator that iterates over all of the elements on the
     * priority queue in descending order.
     * <p>
     * The iterator doesn't implement <tt>remove()</tt> since it's optional.
     */
    public Iterator<Integer> iterator() { return new HeapIterator(); }

    private class HeapIterator implements Iterator<Integer> {
        // create a new pq
        private IndexMaxPQ<Key> copy;

        // add all elements to copy of heap
        // takes linear time since already in heap order so no keys move
        public HeapIterator() {
            copy = new IndexMaxPQ<Key>(pq.length - 1);
            for (int i = 1; i <= N; i++)
                copy.put(pq[i], keys[pq[i]]);
        }

        public boolean hasNext()  { return !copy.isEmpty();                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Integer next() {
            if (!hasNext()) throw new NoSuchElementException();
            return copy.delMax();
        }
    }


    public static void main(String[] args) {
        // insert a bunch of strings
        String[] strings = { "it", "was", "the", "best", "of", "times", "it", "was", "the", "worst" };

        IndexMaxPQ<String> pq = new IndexMaxPQ<String>(strings.length);
        for (int i = 0; i < strings.length; i++) {
            pq.put(i, strings[i]);
        }

        // delete and print each key
        while (!pq.isEmpty()) {
            int i = pq.delMax();
            System.out.println(i + " " + strings[i]);
        }
        System.out.println();

        // reinsert the same strings
        for (int i = 0; i < strings.length; i++) {
            pq.put(i, strings[i]);
        }

        // print each key using the iterator
        for (int i : pq) {
            System.out.println(i + " " + strings[i]);
        }
        while (!pq.isEmpty()) {
            pq.delMax();
        }

    }
}
