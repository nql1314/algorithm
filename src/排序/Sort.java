package 排序;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Sort {
    /**
     * 选择排序
     * 找到数组中的最小元素，将它与数组的第一个元素交换位置。再从剩下的元素中找到最小的元素，
     * 将它与数组的第二个元素交换位置。不断进行这样的操作，直到将整个数组排序。
     * 复杂度：需要 ~N2/2 次比较和 ~N 次交换
     */
    private class Selection{
        public void sort(Comparable[] a){
            int N = a.length;
            for(int i=0;i<N;i++){
                int min = i;
                for(int j = i+1;j<N;j++){
                    if(less(a[j],a[min])) min = j;
                }
                exch(a,i,min);
            }
        }
    }

    /**
     *插入排序
     * 插入排序从左到右进行，每次都将当前元素插入到左部已经排序的数组中，使得插入之后左部数组依然有序。
     * 平均情况下插入排序需要 ~N2/4 比较以及 ~N2/4 次交换；
     * 最坏的情况下需要 ~N2/2 比较以及 ~N2/2 次交换，最坏的情况是数组是逆序的；
     * 最好的情况下需要 N-1 次比较和 0 次交换，最好的情况就是数组已经有序了。
     * 插入排序对于部分有序数组和小规模数组特别高效。
     */
    private class Insertion{
        public void sort(Comparable[] a){
            int N = a.length;
            for(int i = 1;i<N;i++){
                for(int j = i;j>0&&less(a[j],a[j-1]);j--){
                    exch(a,j,j-1);
                }
            }
        }
    }

    /**
     * 希尔排序
     * 希尔排序使用插入排序对间隔 h 的序列进行排序，如果 h 很大，那么元素就能很快的移到很远的地方。
     * 通过不断减小 h，最后令 h=1，就可以使得整个数组是有序的。
     * 希尔排序的运行时间达不到平方级别，使用递增序列 1, 4, 13, 40, ... 的希尔排序所需要的比较次数不会超过 N 的若干倍乘于递增序列的长度。
     */
    private class Shell{
        public void sort(Comparable[] a){
            int N = a.length;
            int h = 1;
            while(h<N/3){
                h = 3*h+1;
            }
            while(h>=1){
                for(int i = h;i<N;i++){
                    for(int j = i;j>=h && less(a[j],a[j-h]);j-=h){
                        exch(a,j,j-h);
                    }
                    h = h/3;
                }
            }
        }
    }

    /**
     * 归并方法
     */
    private class MergeSort{
        private Comparable[] aux;

        private void merge(Comparable[] a, int lo, int mid, int hi) {
            int i = lo, j = mid + 1;

            for (int k = lo; k <= hi; k++) {
                aux[k] = a[k]; // 将数据复制到辅助数组
            }

            for (int k = lo; k <= hi; k++) {
                if (i > mid) a[k] = aux[j++];
                else if (j > hi) a[k] = aux[i++];
                else if (aux[i].compareTo(a[j]) <= 0) a[k] = aux[i++]; // 先进行这一步，保证稳定性
                else a[k] = aux[j++];
            }
        }
        public void sort(Comparable[] a) {
            aux = new Comparable[a.length];
            sort(a, 0, a.length - 1);
        }

        /**
         * 自顶向下归并排序
         * @param a
         * @param lo
         * @param hi
         */
        private void sort(Comparable[] a, int lo, int hi) {
            if (hi <= lo) return;
            int mid = lo + (hi - lo) / 2;
            sort(a, lo, mid);
            sort(a, mid + 1, hi);
            merge(a, lo, mid, hi);
        }

        /**
         * 自底向上归并排序
         * @param a
         */
        public void busort(Comparable[] a) {
            int N = a.length;
            aux = new Comparable[N];
            for (int sz = 1; sz < N; sz += sz) {
                for (int lo = 0; lo < N - sz; lo += sz + sz) {
                    merge(a, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, N - 1));
                }
            }
        }
    }

    /**
     * 快速排序
     * 归并排序将数组分为两个子数组分别排序，并将有序的子数组归并使得整个数组排序；
     * 快速排序通过一个切分元素将数组分为两个子数组，左子数组小于等于切分元素，右子数组大于等于切分元素，将这两个子数组排序也就将整个数组排序了。
     */
    private class QuickSort{
        public void sort(Comparable[] a){
            shuffle(a);
            sort(a,0,a.length-1);
        }
        private void sort(Comparable[] a,int lo,int hi){
            if(hi<=lo) return;
            int j = partition(a,lo,hi);
            sort(a,lo,j-1);
            sort(a,j+1,hi);
        }
        private void shuffle(Comparable[] array) {
            List<Comparable> list = Arrays.asList(array);
            Collections.shuffle(list);
            list.toArray(array);
        }
        private int partition(Comparable[] a, int lo, int hi) {
            int i = lo, j = hi + 1;
            Comparable v = a[lo];
            while (true) {
                while (less(a[++i], v)) if (i == hi) break;
                while (less(v, a[--j])) if (j == lo) break;
                if (i >= j) break;
                exch(a, i, j);
            }
            exch(a, lo, j);
            return j;
        }
    }
    private boolean less(Comparable v,Comparable w){
        return v.compareTo(w)<0;
    }
    private void exch(Comparable[] a,int i,int j){
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}
