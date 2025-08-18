package part1.module4.assignment;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {

    public static void main(String[] args) {
        // Validate command-line usage: must supply exactly one argument (k).
        if (args.length != 1) {
            // Friendly usage message (and early return) if k is missing.
            StdOut.println("Usage: java part1.module4.Permutation <k>");
            return;
        }

        // Parse k from the first command-line argument.
        // Assumption per spec: 0 <= k <= n (n = number of tokens on stdin).
        final int k = Integer.parseInt(args[0]);

        // Create an empty randomized queue of strings.
        // We'll enqueue every token read from stdin; later we'll dequeue k of them.
        RandomizedQueue<String> rq = new RandomizedQueue<>();

        // Read tokens until EOF (Ctrl+D on macOS/Linux, Ctrl+Z then Enter on Windows,
        // or use input redirection). Each token is enqueued once.
        while (!StdIn.isEmpty()) {
            rq.enqueue(StdIn.readString());
        }

        // Remove and print exactly k items. RandomizedQueue.dequeue()
        // returns a uniformly random item from those currently present
        // and removes it, so no item is printed more than once.
        for (int i = 0; i < k; i++) {
            StdOut.println(rq.dequeue());
        }
    }
}
