package part1.module3.lecture;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class BinarySearch {

    public static void main(String[] args) {
        // In.readInts returns an int[] array containing all integers from resources/numbers.txt
        int[] a = In.readInts(args[0]);
        // Sort the array so that we can use binary search
        Arrays.sort(a);
        // The value we want to search for in the array
        int key = 7;
        // Call the binary search method to find 'key' in array 'a'
        int index = binarySearch(a, key);

        if (index != -1) {
            // If index is not -1, the key was found at that position
            StdOut.println("Found " + key + " at index " + index);
        } else {
            // If index is -1, the key was not found
            StdOut.println(key + " not found.");
        }
    }

    // Returns the index of 'key' in the sorted array 'a', or -1 if not found.
    public static int binarySearch(int[] a, int key) {
        // Lowest index of the current search range
        int lo = 0;
        // Highest index of the current search range
        int hi = a.length - 1;
        // Continue searching while the search range is valid
        while (lo <= hi) {
            // Calculate the middle index to avoid integer overflow
            int mid = lo + (hi - lo) / 2;
            // Compare the key with the middle element
            if (key < a[mid]) {
                // If key is smaller, search in the left half
                hi = mid - 1;
            } else if (key > a[mid]) {
                // If key is larger, search in the right half
                lo = mid + 1;
            } else {
                // If equal, we found the key; return its index
                return mid;
            }
        }
        // If we exit the loop, the key was not found
        return -1;
    }
}
