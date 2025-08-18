package part1.module4;

public class GenericFixedCapacityStack<Item> {

    // array of items
    private final Item[] s;
    // number of items
    private int n = 0;

    @SuppressWarnings("unchecked")
    public GenericFixedCapacityStack(int capacity) {
        // Java doesn't let us create a generic array directly.
        // We use an Object[] and cast.
        s = (Item[]) new Object[capacity];
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public void push(Item item) {
        if (n == s.length) {
            throw new RuntimeException("Stack overflow");
        }
        s[n++] = item;
    }

    public Item pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack underflow");
        }
        Item item = s[--n];
        // avoid loitering
        s[n] = null;
        return item;
    }

    public Item peek() {
        if (isEmpty()) {
            throw new RuntimeException("Stack underflow");
        }
        return s[n - 1];
    }
}
