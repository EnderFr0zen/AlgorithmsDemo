package part1.module2.assignment;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    // Size of the grid (n x n)
    private final int n;
    // Array to track which sites are open (true) or blocked (false)
    private final boolean[] open;
    // Counter for number of open sites
    private int openCount = 0;

    // Union-find data structure with both virtual top and bottom nodes
    private final WeightedQuickUnionUF uf;

    // Second union-find structure with only virtual top node (to prevent backwash)
    private final WeightedQuickUnionUF ufTop;

    // Index for virtual top site
    private final int top;
    // Index for virtual bottom site
    private final int bottom;

    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException("n must be > 0");
        this.n = n;
        int sites = n * n;

        // Create array for tracking open sites (using 1-based indexing)
        this.open = new boolean[sites + 1];

        // Set indices for virtual sites
        this.top = sites;
        this.bottom = sites + 1;

        // Initialize union-find data structure with virtual top and bottom sites
        this.uf = new WeightedQuickUnionUF(sites + 2);

        // Initialize second union-find with only virtual top (prevents backwash)
        this.ufTop = new WeightedQuickUnionUF(sites + 1);
    }

    public void open(int row, int col) {
        validate(row, col);
        int i = index(row, col);

        // Return early if already open
        if (open[i]) {
            return;
        }

        // Mark site as open and increment counter
        open[i] = true;
        openCount++;

        // Connect to virtual top node if in first row
        if (row == 1) {
            uf.union(i, top);
            ufTop.union(i, top);
        }

        // Connect to virtual bottom node if in last row
        if (row == n) {
            uf.union(i, bottom);
        }

        // Connect to adjacent open sites (up, down, left, right)

        // Check and connect to site above
        if (row > 1 && isOpen(row - 1, col)) {
            uf.union(i, index(row - 1, col));
            ufTop.union(i, index(row - 1, col));
        }

        // Check and connect to site below
        if (row < n && isOpen(row + 1, col)) {
            uf.union(i, index(row + 1, col));
            ufTop.union(i, index(row + 1, col));
        }

        // Check and connect to site to the left
        if (col > 1 && isOpen(row, col - 1)) {
            uf.union(i, index(row, col - 1));
            ufTop.union(i, index(row, col - 1));
        }

        // Check and connect to site to the right
        if (col < n && isOpen(row, col + 1)) {
            uf.union(i, index(row, col + 1));
            ufTop.union(i, index(row, col + 1));
        }
    }

    public boolean isOpen(int row, int col) {
        validate(row, col);
        return open[index(row, col)];
    }

    public boolean isFull(int row, int col) {
        validate(row, col);
        int i = index(row, col);

        // Site is full if it's open and connected to the virtual top node
        // Uses ufTop to prevent backwash
        return open[i] && ufTop.find(i) == ufTop.find(top);
    }

    public int numberOfOpenSites() {
        return openCount;
    }

    public boolean percolates() {
        // System percolates if virtual top and bottom are connected
        return uf.find(top) == uf.find(bottom);
    }

    private int index(int row, int col) {
        return (row - 1) * n + col;
    }

    private void validate(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n) {
            throw new IllegalArgumentException("row/col out of range");
        }
    }
}
