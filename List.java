public class List {
    private class Node{
        private String data;
        private Node next;
        private Node(String data){
            this.data = data;
            this.next = null;
        }
    }
    private Node head;

    public void insert(String data){
        Node newNode = new Node(data);
        if (head == null){
            head = newNode;
        }else{
            Node current = head;
            while (current.next != null){
                current = current.next;
            }
            current.next = newNode;
        }
    }

    public void delete(String data){
        if (head == null){
            return;
        }
        if (head.data.equals(data)){
            head = head.next;
            return;
        }
        Node current = head;
        Node parent = current;
        while (current != null){
            if (current.data.equals(data)){
                parent.next = current.next;
                return;
            }
            parent = current;
            current = current.next;
        }
    }
}
