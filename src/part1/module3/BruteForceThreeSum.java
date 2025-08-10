package part1.module3;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class BruteForceThreeSum {

    public static void main(String[] args) {
        // In.readInts returns an int[] array containing all integers from that file
        int[] a = In.readInts(args[0]);
        StdOut.println(count(a));
    }

    public static int count(int[] a) {
        // Store the number of elements in the array
        int N = a.length;
        // Counter to track how many triples sum to zero
        int count = 0;
        // Outer loop: choose the first index i
        for (int i = 0; i < N; i++) {
            // Middle loop: choose the second index j (always greater than i to avoid repeats)
            for (int j = i + 1; j < N; j++) {
                // Inner loop: choose the third index k (greater than j to avoid repeats)
                for (int k = j + 1; k < N; k++) {
                    // If the sum of the three selected numbers is exactly zero
                    if (a[i] + a[j] + a[k] == 0) {
                        // Print the triple to output (so you can see which numbers make the sum)
                        StdOut.println(a[i] + " " + a[j] + " " + a[k]);
                        // Increase the triple count
                        count++;
                    }
                }
            }
        }
        // Return the number of triples found
        return count;
    }
}
