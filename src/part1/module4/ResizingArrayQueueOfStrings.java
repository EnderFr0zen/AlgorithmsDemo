package part1.module4;

public class ResizingArrayQueueOfStrings {

    // queue items
    private String[] q;
    // number of items
    private int n;
    // index of first item
    private int head;
    // index of next insert
    private int tail;

    public ResizingArrayQueueOfStrings() {
        // small starting capacity
        q = new String[2];
        n = 0;
        head = 0;
        tail = 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public void enqueue(String item) {
        // grow when full
        if (n == q.length) {
            resize(2 * q.length);
        }
        q[tail] = item;
        tail = (tail + 1) % q.length;
        n++;
    }

    public String dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue underflow");
        }
        String item = q[head];
        // avoid loitering
        q[head] = null;
        head = (head + 1) % q.length;
        n--;
        if (n > 0 && n == q.length / 4) {
            // shrink when 25% full
            resize(q.length / 2);
        }
        return item;
    }

    public String peek() {
        if (isEmpty()) {
            throw new RuntimeException("Queue underflow");
        }
        return q[head];
    }

    private void resize(int capacity) {
        String[] copy = new String[Math.max(1, capacity)];
        for (int i = 0; i < n; i++) {
            copy[i] = q[(head + i) % q.length];
        }
        q = copy;
        head = 0;
        tail = n;
    }
}
