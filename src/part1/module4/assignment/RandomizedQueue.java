package part1.module4.assignment;

import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;

public class RandomizedQueue<Item> implements Iterable<Item> {

    // underlying array buffer
    private Item[] a;
    // number of items currently stored
    private int n;

    @SuppressWarnings("unchecked")
    public RandomizedQueue() {
        // start small; will double as needed
        a = (Item[]) new Object[2];
        // queue starts empty
        n = 0;
    }

    // true iff no items
    public boolean isEmpty() {
        return n == 0;
    }

    // current item count
    public int size() {
        return n;
    }

    // Validate that client did not try to add null (forbidden by spec).
    private void checkNotNull(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
    }

    // Grow to 2x when array is full (keeps amortized O(1) inserts).
    private void ensureCapacityForInsert() {
        if (n == a.length) {
            resize(2 * a.length);
        }
    }

    // Shrink to 1/2 when array is 1/4 full (avoids loitering, saves memory).
    private void ensureCapacityForRemove() {
        if (n > 0 && n == a.length / 4) {
            resize(a.length / 2);
        }
    }

    // Resize backing array to cap (at least 2), copying 0..n-1 in order.
    @SuppressWarnings("unchecked")
    private void resize(int cap) {
        // never go below 2
        if (cap < 2) {
            cap = 2;
        }
        Item[] copy = (Item[]) new Object[cap];
        // copy active items
        for (int i = 0; i < n; i++) {
            copy[i] = a[i];
        }
        // switch to new array
        a = copy;
    }

    // Add the item to the queue (append at end).
    public void enqueue(Item item) {
        // spec: forbid null inserts
        checkNotNull(item);
        // grow if full
        ensureCapacityForInsert();
        // place at end, then increment n
        a[n++] = item;
    }

    // Remove and return one uniformly random item.
    public Item dequeue() {
        if (isEmpty()) {
            // spec: throw on empty
            throw new NoSuchElementException();
        }
        // pick random index in [0, n)
        int i = StdRandom.uniform(n);
        // item to return
        Item item = a[i];
        // move last element into hole at i
        a[i] = a[n - 1];
        // avoid loitering (let GC reclaim)
        a[n - 1] = null;
        // one fewer items
        n--;
        // shrink if 1/4 full
        ensureCapacityForRemove();
        // return removed item
        return item;
    }

    // Return a random item without removing it.
    public Item sample() {
        if (isEmpty()) {
            // spec: throw on empty
            throw new NoSuchElementException();
        }
        // pick random live index
        return a[StdRandom.uniform(n)];
    }

    // Each call returns an independent iterator that yields items in random order.
    @Override public Iterator<Item> iterator() {
        return new RQIterator();
    }

    // Iterator implementation: precompute a random permutation of indices 0..n-1.
    private final class RQIterator implements Iterator<Item> {
        // shuffled index order
        private final int[] order;
        // next position in 'order' to serve
        private int idx;

        RQIterator() {
            // allocate array of indices
            order = new int[n];
            for (int i = 0; i < n; i++) {
                // fill 0..n-1
                order[i] = i;
            }
            // Fisher–Yates shuffle to make a uniform random permutation.
            for (int i = n - 1; i > 0; i--) {
                // pick 0..i
                int j = StdRandom.uniform(i + 1);
                // swap
                int tmp = order[i];
                order[i] = order[j];
                order[j] = tmp;
            }
            // start from first shuffled index
            idx = 0;
        }

        @Override
        public boolean hasNext() {
            // any left?
            return idx < order.length;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                // spec: throw at end
                throw new NoSuchElementException();
            }
            // map shuffled index to item
            return a[order[idx++]];
        }

        @Override
        public void remove() {
            // not supported
            throw new UnsupportedOperationException();
        }
    }

    // Unit testing: exercise all public methods and print results.
    public static void main(String[] args) {
        // constructor
        RandomizedQueue<Integer> rq = new RandomizedQueue<>();
        // expect true,0
        StdOut.println("empty? " + rq.isEmpty() + ", size=" + rq.size());

        for (int i = 1; i <= 5; i++) {
            // enqueue 1..5
            rq.enqueue(i);
        }
        // expect 5
        StdOut.println("size after enqueue 1..5 = " + rq.size());

        // peek random
        StdOut.println("sample (no remove): " + rq.sample());

        // random order
        StdOut.print("iterator #1: ");
        for (int x : rq) {
            StdOut.print(x + " ");
        }
        StdOut.println();

        // different random order
        StdOut.print("iterator #2: ");
        for (int x : rq) {
            StdOut.print(x + " ");
        }
        StdOut.println();

        // remove all randomly
        StdOut.print("dequeue all: ");
        while (!rq.isEmpty()) {
            StdOut.print(rq.dequeue() + " ");
        }
        StdOut.println();

        // Expected exceptions per spec:
        try {
            rq.dequeue();
        } catch (NoSuchElementException e) {
            StdOut.println("OK: dequeue() on empty");
        }

        try {
            rq.sample();
        } catch (NoSuchElementException e) {
            StdOut.println("OK: sample() on empty");
        }

        try {
            rq.enqueue(null);
        } catch (IllegalArgumentException e) {
            StdOut.println("OK: enqueue(null)");
        }
    }
}
