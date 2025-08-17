package part1.module4;

public class LinkedStackOfStrings {

    // top of the stack
    private Node first = null;

    // inner class Node (linked list node)
    private class Node {
        String item;
        Node next;
    }

    // is the stack empty?
    public boolean isEmpty() {
        return first == null;
    }

    // push: insert new string onto stack
    public void push(String item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
    }

    // pop: remove and return most recently added item
    public String pop() {
        String item = first.item;
        first = first.next;
        return item;
    }
}
