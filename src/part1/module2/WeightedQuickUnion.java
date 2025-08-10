package part1.module2;

public class WeightedQuickUnion {

    // parent link (site indexed)
    private int[] id;
    // size of component for roots (site indexed)
    private int[] sz;

    public WeightedQuickUnion(int N) {
        id = new int[N];
        sz = new int[N];
        for (int i = 0; i < N; i++) {
            // Each element is initially its own root
            id[i] = i;
            // Each tree initially has size 1
            sz[i] = 1;
        }
    }

    private int root(int i) {
        // Find the root of element i
        while (i != id[i]) {
            // Path compression by pointing to grandparent
            id[i] = id[id[i]];
            i = id[i];
        }
        return i;
    }

    public boolean connected(int p, int q) {
        // Check if p and q have the same root
        return root(p) == root(q);
    }

    public void union(int p, int q) {
        int i = root(p);
        int j = root(q);
        // If already in the same component, do nothing
        if (i == j) {
            return;
        }
        // Link root of smaller tree to root of larger tree
        if (sz[i] < sz[j]) {
            // Make j the parent of i
            id[i] = j;
            // Update the size of j's tree
            sz[j] += sz[i];
        } else {
            // Make i the parent of j
            id[j] = i;
            // Update the size of i's tree
            sz[i] += sz[j];
        }
    }
}
