package part1.module4.lecture;

public class ResizingArrayStackOfStrings {

    // stack items
    private String[] s;
    // number of items on stack
    private int N;

    public ResizingArrayStackOfStrings() {
        // start with capacity 1
        s = new String[1];
        N = 0;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void push(String item) {
        // repeated doubling
        if (N == s.length) {
            resize(2 * s.length);
        }
        s[N++] = item;
    }

    public String pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack underflow");
        }
        String item = s[--N];
        // avoid loitering
        s[N] = null;
        // shrink when 25% full
        if (N > 0 && N == s.length / 4) {
            resize(s.length / 2);
        }
        return item;
    }

    private void resize(int capacity) {
        String[] copy = new String[capacity];
        for (int i = 0; i < N; i++) {
            copy[i] = s[i];
        }
        s = copy;
    }
}
