public class List {
    private class Node {
        private String data;
        private Node next;

        private Node(String data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head;

    public void insert(String data) {
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
    }

    public void delete(String data) {
        if (head == null) return;
        if (head.data.equals(data)) {
            head = head.next;
            return;
        }
        Node current = head;
        while (current.next != null) {
            if (current.next.data.equals(data)) {
                current.next = current.next.next;
                return;
            }
            current = current.next;
        }
    }

    // Έλεγχος ύπαρξης stop word
    public boolean contains(String data) {
        Node current = head;
        while (current != null) {
            if (current.data.equals(data)) return true;
            current = current.next;
        }
        return false;
    }
}