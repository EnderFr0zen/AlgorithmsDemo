package part1.module2.lecture;

public class QuickUnion {

    // Array to keep track of parent connections
    private int[] id;

    public QuickUnion(int N) {
        // Create an array of size N
        id = new int[N];
        for (int i = 0; i < N; i++) {
            // Initialize each element to point to itself
            id[i] = i;
        }
    }

    private int root(int i) {
        // While i is not its own parent
        while (i != id[i]) {
            // Move up to i's parent
            i = id[i];
        }
        // Return the root element
        return i;
    }

    public boolean connected(int p, int q) {
        // Elements are connected if they have the same root
        return root(p) == root(q);
    }

    public void union(int p, int q) {
        // Find root of p
        int i = root(p);
        // Find root of q
        int j = root(q);
        // Make root of p point to root of q
        id[i] = j;
    }
}
