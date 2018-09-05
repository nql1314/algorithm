package 排序;

import StdLib.In;
import StdLib.StdOut;

/*************************************************************************
 *  Compilation:  javac Multiway.java
 *  Execution:    java Multiway input1.txt input2.txt input3.txt ...
 *  Dependencies: IndexMinPQ.java In.java StdOut.java
 * 
 *  Merges together the sorted input stream given as command-line arguments
 *  into a single sorted output stream on standard output.
 *
 *  % more m1.txt 
 *  A B C F G I I Z
 *
 *  % more m2.txt 
 *  B D H P Q Q
 * 
 *  % more m3.txt 
 *  A B E F J N
 *
 *  % java Multiway m1.txt m2.txt m3.txt 
 *  A A B B B C D E F F G H I I J N P Q Q Z 
 *
 *************************************************************************/

public class Multiway { 

    public static void merge(In[] streams) {
        int N = streams.length; 
        IndexMinPQ<String> pq = new IndexMinPQ<String>(N); 
        for (int i = 0; i < N; i++)  //遍历  从每个数据流中取一个数据
            if (!streams[i].isEmpty()) 
                pq.insert(i, streams[i].readString()); 

        // Extract and print min and read next from its stream. 
        while (!pq.isEmpty()) {
            StdOut.print(pq.minKey() + " "); //取出最小的
            int i = pq.delMin(); 
            if (!streams[i].isEmpty()) //用该流的下个元素补上
                pq.insert(i, streams[i].readString()); 
        }
        StdOut.println();
    } 


    public static void main(String[] args) { 
        int N = args.length; 
        In[] streams = new In[N]; 
        for (int i = 0; i < N; i++) 
            streams[i] = new In(args[i]); 
        merge(streams); 
    } 
} 
