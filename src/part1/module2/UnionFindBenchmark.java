package part1.module2;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.Random;

public class UnionFindBenchmark {

    public static void main(String[] args) {
        // Number of elements
        int N = 50000;
        // Number of operations
        int ops = 100000;
        Random rand = new Random();

        // QuickFind benchmark
        QuickFind qf = new QuickFind(N);
        Stopwatch timer1 = new Stopwatch();
        for (int i = 0; i < ops; i++) {
            int p = rand.nextInt(N);
            int q = rand.nextInt(N);
            if (!qf.connected(p, q)) {
                qf.union(p, q);
            }
        }
        double time1 = timer1.elapsedTime();
        StdOut.println("QuickFind time: " + time1 + " seconds");

        // QuickUnion benchmark
        QuickUnion qu = new QuickUnion(N);
        Stopwatch timer2 = new Stopwatch();
        for (int i = 0; i < ops; i++) {
            int p = rand.nextInt(N);
            int q = rand.nextInt(N);
            if (!qu.connected(p, q)) {
                qu.union(p, q);
            }
        }
        double time2 = timer2.elapsedTime();
        StdOut.println("QuickUnion time: " + time2 + " seconds");

        // WeightedQuickUnion benchmark
        WeightedQuickUnion wqu = new WeightedQuickUnion(N);
        Stopwatch timer3 = new Stopwatch();
        for (int i = 0; i < ops; i++) {
            int p = rand.nextInt(N);
            int q = rand.nextInt(N);
            if (!wqu.connected(p, q)) {
                wqu.union(p, q);
            }
        }
        double time3 = timer3.elapsedTime();
        StdOut.println("WeightedQuickUnion time: " + time3 + " seconds");
    }
}
