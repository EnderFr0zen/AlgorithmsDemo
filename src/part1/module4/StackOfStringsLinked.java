package part1.module4;

public class StackOfStringsLinked {

    // inner class Node (linked list node)
    private class Node {
        String item;
        Node next;
    }

    // top of the stack
    private Node first;
    // number of items
    private int n;

    // constructor: create an empty stack
    public StackOfStringsLinked() {
        first = null;
        n = 0;
    }

    // push: insert new string onto stack
    public void push(String item) {
        // save a link to the list
        Node oldfirst = first;

        // create a new node
        first = new Node();

        // set instance variables
        first.item = item;
        first.next = oldfirst;

        n++;
    }

    // pop: remove and return most recently added item
    public String pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack underflow");
        }

        // save item to return
        String item = first.item;

        // delete first node
        first = first.next;

        n--;

        // return saved item
        return item;
    }

    // is the stack empty?
    public boolean isEmpty() {
        return first == null;
    }

    // number of items on the stack
    public int size() {
        return n;
    }
}
