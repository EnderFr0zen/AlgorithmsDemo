package part1.module4.lecture;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class IterableFixedCapacityStack<Item> implements Iterable<Item> {

    private final Item[] s;
    private int N = 0;

    @SuppressWarnings("unchecked")
    public IterableFixedCapacityStack(int capacity) {
        s = (Item[]) new Object[capacity];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void push(Item item) {
        if (N == s.length) {
            throw new RuntimeException("Stack overflow");
        }
        s[N++] = item;
    }

    public Item pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack underflow");
        }
        Item item = s[--N];
        // avoid loitering
        s[N] = null;
        return item;
    }

    public Item peek() {
        if (isEmpty()) {
            throw new RuntimeException("Stack underflow");
        }
        return s[N - 1];
    }

    // iterator: LIFO over the array
    @Override
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<Item> {
        private int i = N;
        @Override
        public boolean hasNext() {
            return i > 0;
        }
        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return s[--i];
        }
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
