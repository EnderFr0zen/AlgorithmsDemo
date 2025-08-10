package part1.module3;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;
import java.util.Arrays;

public class ImprovedThreeSum {

    public static void main(String[] args) {
        // In.readInts returns an int[] array containing all integers from resources/numbers.txt
        int[] a = In.readInts(args[0]);
        Stopwatch stopwatch = new Stopwatch();
        StdOut.println(count(a));
        double time = stopwatch.elapsedTime();
        StdOut.println("Time: " + time + " seconds");
    }

    // Counts the number of index triples (i, j, k) with i < j < k and a[i] + a[j] + a[k] == 0
    public static int count(int[] a) {
        // Sort the array so that we can use binary search
        Arrays.sort(a);
        int N = a.length;
        int count = 0;

        // Loop through the first element of the triple
        for (int i = 0; i < N; i++) {
            // Loop through the second element of the triple
            for (int j = i + 1; j < N; j++) {
                // The third element must be -(a[i] + a[j]) to make the sum zero
                int target = -(a[i] + a[j]);

                // Search for 'target' in the subarray starting at index j+1 to N-1
                int idx = Arrays.binarySearch(a, j + 1, N, target);

                // If idx > j, a match was found somewhere after j
                if (idx > j) {
                    // Extend to the left: find the first occurrence of 'target' after j
                    int left = idx;
                    while (left > j + 1 && a[left - 1] == target) {
                        left--;
                    }

                    // Extend to the right: find the last occurrence of 'target'
                    int right = idx;
                    while (right + 1 < N && a[right + 1] == target) {
                        right++;
                    }

                    // Number of matches for this (i, j) pair
                    int matches = right - left + 1;

                    // Print each matching triple (i, j, m) where m is the index of the third element
                    for (int m = left; m <= right; m++) {
                        StdOut.println(a[i] + " " + a[j] + " " + a[m]);
                    }

                    // Add the number of matches to the total count
                    count += matches;
                }
            }
        }
        return count;
    }
}
