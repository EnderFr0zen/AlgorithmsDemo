package part1.module4;

public class StackOfStrings {

    private final String[] s;
    private int N;

    public StackOfStrings() {
        // fixed capacity
        s = new String[100];
        N = 0;
    }

    public void push(String item) {
        if (N == s.length) {
            throw new RuntimeException("Stack overflow");
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
        return item;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }
}
