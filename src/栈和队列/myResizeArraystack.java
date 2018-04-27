package 栈和队列;

import java.util.Iterator;

public class myResizeArraystack<Item> implements Iterable<Item> {
    private Item[] array = (Item[])new Object[1];
    private int N = 0;

    public boolean isEmpty(){
        return N ==0;
    }
    public int size(){
        return N;
    }
    public void push(Item item){
        if(N>=array.length){
            resize(array.length*2);
        }
        array[N++]=item;
    }
    public Item pop(){
        Item item = array[--N];
        if(N<=array.length/4){
            resize(array.length/2);
        }
        return item;
    }
    public void resize(int size){
        Item[] temp = (Item[])new Object[size];
        for(int i = 0;i<=N;i++){
            temp[i] = array[i];
        }
        array = temp;
    }
    @Override
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Item> {
        @Override
        public boolean hasNext() {
            return N>0;
        }

        @Override
        public Item next() {
            return array[N-1];
        }
    }
}
