package part1.module4;

public class StackOfStrings {

    private final String[] items;
    private int n;

    public StackOfStrings() {
        // fixed capacity
        items = new String[100];
        n = 0;
    }

    public void push(String item) {
        if (n == items.length) {
            throw new RuntimeException("Stack overflow");
        }
        items[n++] = item;
    }

    public String pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack underflow");
        }
        String item = items[--n];
        // avoid loitering
        items[n] = null;
        return item;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }
}
