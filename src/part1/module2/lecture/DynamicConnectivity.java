package part1.module2.lecture;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.UF;

public class DynamicConnectivity {

    public static void main(String[] args) {
        // Read the number of elements (N) from standard input
        int N = StdIn.readInt();
        // Create a new Union-Find data structure with N elements (0 to N-1)
        UF unionFind = new UF(N);
        // Continue reading pairs of integers until input is exhausted
        while (!StdIn.isEmpty()) {
            // Read a pair of integers p and q
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            // If p and q are not already connected
            if (!unionFind.connected(p, q)) {
                // Connect p and q
                unionFind.union(p, q);
                // Print the connection that was just made
                StdOut.println(p + " " + q);
            }
        }
    }
}
