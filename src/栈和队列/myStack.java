package 栈和队列;

public class myStack<Item> {
    private class Node{
        Item item;
        Node next;
    }
    private Node top = null;
    private int N =0;

    public void push(Item item){
        Node temp = new Node();
        temp.item = item;
        temp.next = top;
        top = temp;
        N++;
    }
    public Item pop(){
        Item item = top.item;
        top = top.next;
        N--;
        return item;
    }
    public boolean isEmpty(){
        return N==0;
    }
    public int size(){
        return N;
    }
}
