package 栈和队列;

public class MyQueue<Item> {
    private class Node{
        Item item;
        Node next;
    }
    private Node first;
    private Node last;
    private int N = 0;

    public boolean isEmpty(){
        return N == 0;
    }
    public int size(){
        return N;
    }
    public void enqueue(Item item){
        Node node = new Node();
        node.item = item;
        node.next = null;
        if(isEmpty()){
            first = node;
            last = node;
        }else{
            last.next = node;
            last  = node;
        }
        N++;
    }
    public Item dequeue(){
        Node node = first;
        first = first.next;
        N--;
        if(isEmpty()){
            last = null;
        }
        return node.item;
    }
}
