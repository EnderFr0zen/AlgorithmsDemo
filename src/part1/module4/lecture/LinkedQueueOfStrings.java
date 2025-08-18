package part1.module4.lecture;

public class LinkedQueueOfStrings {

    // beginning of queue
    private Node first;
    // end of queue
    private Node last;
    // number of items
    private int n;

    // helper linked list class
    private static class Node {
        String item;
        Node next;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return n;
    }

    public void enqueue(String item) {
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) {
            first = last;
        }
        else {
            oldlast.next = last;
        }
        n++;
    }

    public String dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue underflow");
        }
        String item = first.item;
        first = first.next;
        // avoid loitering and fix tail on empty
        if (isEmpty()) {
            last = null;
        }
        n--;
        return item;
    }

    public String peek() {
        if (isEmpty()) {
            throw new RuntimeException("Queue underflow");
        }
        return first.item;
    }
}
