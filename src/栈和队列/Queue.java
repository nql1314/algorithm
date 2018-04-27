package 栈和队列;

/**
 * 队列
 * Fist In First Out
 */
public class Queue<Item> {
    private Node first;
    private Node last;
    private int N = 0;

    private class Node{
        Item item;
        Node next;
    }

    public boolean isEmpty(){
        return N ==0;
    }
    public int size(){
        return N;
    }
    public void enqueue(Item item){
        Node node = new Node();
        node.item = item;
        if(isEmpty()){
            first = node;
            last = node;
        }else{
            last.next = node;
            last = node;
        }
        N++;
    }
    public Item dequeue(){
        Item item = first.item;
        first = first.next;
        N--;
        if(isEmpty()){
            last = null;
        }
        return item;
    }

}
