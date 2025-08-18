package part1.module4.assignment;

import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item> {

    // Node is a minimal doubly-linked list node carrying an Item payload.
    private static final class Node<Item> {
        // the data
        Item item;
        // link to previous node (null at first)
        Node<Item> prev;
        // link to next node (null at last)
        Node<Item> next;
    }

    // pointer to front of deque (null when empty)
    private Node<Item> first;
    // pointer to back  of deque (null when empty)
    private Node<Item> last;
    // number of items currently in the deque
    private int n;

    // construct an empty deque
    public Deque() {

    }

    // is the deque empty?
    public boolean isEmpty() {
        // true when no items
        return n == 0;
    }

    // return the number of items on the deque
    public int size() {
        // O(1) size
        return n;
    }

    // add the item to the front
    public void addFirst(Item item) {
        // spec: forbid null inserts
        if (item == null) {
            throw new IllegalArgumentException();
        }
        // allocate a new node
        Node<Item> x = new Node<>();
        // set payload
        x.item = item;
        // new node points forward to old first (may be null)
        x.next = first;
        if (first != null) {
            // if deque non-empty, old first points back to new
            first.prev = x;
        } else {
            // else (was empty) last must also point to new node
            last = x;
        }
        // move front pointer to new node
        first = x;
        // increment item count
        n++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            // spec: forbid null inserts
            throw new IllegalArgumentException();
        }
        // allocate a new node
        Node<Item> x = new Node<>();
        // set payload
        x.item = item;
        // new node points back to old last (may be null)
        x.prev = last;
        if (last != null) {
            // if deque non-empty, old last points forward to new
            last.next = x;
        } else {
            // else (was empty) first must also point to new node
            first = x;
        }
        // move back pointer to new node
        last = x;
        // increment item count
        n++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            // spec: removing from empty is illegal
            throw new NoSuchElementException();
        }
        // save value to return
        Item item = first.item;
        // remember the next node
        Node<Item> next = first.next;
        // clear payload (helps GC)
        first.item = null;
        // detach forward link (helps GC)
        first.next = null;
        // advance front pointer
        first = next;
        if (first != null) {
            // new first has no previous
            first.prev = null;
        } else {
            // became empty: also clear last
            last = null;
        }
        // decrement item count
        n--;
        // return removed value
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            // spec: removing from empty is illegal
            throw new NoSuchElementException();
        }
        // save value to return
        Item item = last.item;
        // remember the previous node
        Node<Item> prev = last.prev;
        // clear payload (helps GC)
        last.item = null;
        // detach backward link (helps GC)
        last.prev = null;
        // move back pointer backward
        last = prev;
        if (last != null) {
            // new last has no next
            last.next = null;
        } else {
            // became empty: also clear first
            first = null;
        }
        // decrement item count
        n--;
        // return removed value
        return item;
    }

    // return an iterator over items in order from front to back
    @Override public Iterator<Item> iterator() {
        // construct a new iterator
        return new ListIter();
    }

    // Simple forward iterator (front -> back). Each op is O(1).
    private final class ListIter implements Iterator<Item> {
        // start at front
        private Node<Item> cur = first;

        @Override
        public boolean hasNext() {
            // any node left?
            return cur != null;
        }

        @Override public Item next() {
            if (cur == null) {
                // spec: next() on end is illegal
                throw new NoSuchElementException();
            }
            // read payload
            Item it = cur.item;
            // advance
            cur = cur.next;
            // yield previous payload
            return it;
        }

        @Override
        public void remove() {
            // spec: not supported
            throw new UnsupportedOperationException();
        }
    }

    // unit testing: exercises all public methods and prints results
    public static void main(String[] args) {
        // construct
        Deque<String> d = new Deque<>();
        // expect true, 0
        StdOut.println("empty? " + d.isEmpty() + ", size=" + d.size());

        // state: a b c
        d.addFirst("b"); d.addLast("c"); d.addFirst("a");
        StdOut.print("iterate: ");
        for (String s : d) {
            // prints: a b c
            StdOut.print(s + " ");
        }
        StdOut.println();

        // remove a
        StdOut.println("removeFirst: " + d.removeFirst());
        // remove c
        StdOut.println("removeLast : " + d.removeLast());
        // expect 1
        StdOut.println("left size  : " + d.size());
        // remove b, now empty
        StdOut.println("removeLast : " + d.removeLast());
        // expect true
        StdOut.println("empty? " + d.isEmpty());

        // Expected exceptions per spec:
        try {
            d.removeFirst();
        } catch (NoSuchElementException e) {
            StdOut.println("OK: removeFirst() on empty");
        }
        try {
            d.addFirst(null);
        } catch (IllegalArgumentException e) {
            StdOut.println("OK: addFirst(null)");
        }
    }
}
