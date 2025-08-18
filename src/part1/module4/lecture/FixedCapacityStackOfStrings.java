package part1.module4.lecture;

public class FixedCapacityStackOfStrings {

    private final String[] s;
    private int N = 0;

    public FixedCapacityStackOfStrings(int capacity) {
        s = new String[capacity];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void push(String item) {
        if (N == s.length) {
            throw new RuntimeException("Stack overflow");
        }
        // store item then increment
        s[N++] = item;
    }

    public String pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack underflow");
        }
        // decrement then fetch
        String item = s[--N];
        // avoid loitering
        s[N] = null;
        return item;
    }
}
