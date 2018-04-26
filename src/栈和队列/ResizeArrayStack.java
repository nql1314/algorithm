package 栈和队列;

import java.util.Iterator;

/**栈 数组实现
 * 栈特性：First In Last Out
 */
public class ResizeArrayStack<Item> implements Iterable<Item> {
    private Item[] a = (Item[]) new Object[1];
    private int N = 0;
    public void push(Item item){
        if(N>=a.length){
            resize(2 * a.length);
        }
        a[N++] = item;
    }
    public Item pop(){
        Item item = a[--N];
        if(N<=a.length/4){
            resize(a.length/2);
        }
        return item;
    }
    private void resize(int size){
        Item[] temp = (Item[])new Object[size];
        for(int i=0;i<N;i++){
            temp[i] = a[i];
        }
        a = temp;
    }

    public boolean isEmpty(){
        return N==0;
    }

    public int size(){
        return N;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<Item>{
        private int i = N;
        @Override
        public boolean hasNext() {
            return i>0;
        }

        @Override
        public Item next() {
            return a[--i];
        }
    }
}
