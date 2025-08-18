package part1.module4;

import java.util.Iterator;
import java.util.NoSuchElementException;

// A generic Bag (multiset) backed by a singly linked list
public class Bag<Item> implements Iterable<Item> {

    // head of list
    private Node<Item> first;
    // number of items
    private int n;

    private static class Node<Item> {
        Item item;
        Node<Item> next;
    }

    public Bag() {

    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    // Adds an item; order is unspecified (here: most-recent-first)
    public void add(Item item) {
        Node<Item> oldFirst = first;
        first = new Node<>();
        first.item = item;
        first.next = oldFirst;
        n++;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node<Item> current = first;

        @Override public boolean hasNext() {
            return current != null;
        }

        @Override public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
