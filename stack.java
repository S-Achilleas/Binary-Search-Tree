import java.util.NoSuchElementException;
import java.io.PrintStream;



public class stack<T>{

    private class Node<T>{
        public Node<T> next;
        public T value;
        public Node(Node<T> next, T value){
            this.next = next;
            this.value = value;
        }
    }
    private Node<T> head;
    private int counter;

    public stack(){
        this.head = null;
    }

    public boolean isEmpty(){
        return head==null;
    }

    public void push(T item){
        this.head = new Node<>(head, item);
        counter++;
    }

    public T pop() throws NoSuchElementException{
        if(isEmpty()){
            throw new NoSuchElementException("Empty stack!");
        }
        T temp = head.value;
        this.head = head.next;
        counter--;
        return temp;
    }

    public T peek() throws NoSuchElementException{
        if(isEmpty()){
            throw new NoSuchElementException("Empty stack!");
        }
        return head.value;
    }

    public void printStack(PrintStream stream){
        for(Node<T> x=head;x!=null;x=x.next){
            stream.println(x.value);
        }
    }

    public int size(){
        return counter;
    }
}