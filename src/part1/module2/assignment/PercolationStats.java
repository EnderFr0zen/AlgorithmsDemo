package part1.module2.assignment;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    public static void main(String[] args) {
        // Grid size (n x n)
        int n = Integer.parseInt(args[0]);
        // Number of trials
        int T = Integer.parseInt(args[1]);

        // Run the simulation
        PercolationStats stats = new PercolationStats(n, T);

        // Print the results
        StdOut.printf("mean                    = %.16f%n", stats.mean());
        StdOut.printf("stddev                  = %.16f%n", stats.stddev());
        StdOut.printf("95%% confidence interval = [%.16f, %.16f]%n", stats.confidenceLo(), stats.confidenceHi());
    }

    // Number of simulation trials
    private final int trials;
    // Array to store percolation thresholds
    private final double[] thresholds;
    // Z-score for 95% confidence interval
    private static final double CONFIDENCE_95 = 1.96;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }
        this.trials = trials;
        this.thresholds = new double[trials];

        // Run each trial
        for (int t = 0; t < trials; t++) {
            // Create a new percolation system
            Percolation p = new Percolation(n);
            // Count of opened sites
            int opened = 0;

            // Keep opening sites until the system percolates
            while (!p.percolates()) {
                // Select a random site (using 1-based indexing)
                // Random row (1..n)
                int row = StdRandom.uniform(n) + 1;
                // Random column (1..n)
                int col = StdRandom.uniform(n) + 1;

                // Open the site if it's not already open
                if (!p.isOpen(row, col)) {
                    p.open(row, col);
                    opened++;
                }
            }

            // Calculate the percolation threshold for this trial
            // (fraction of sites that needed to be open for percolation)
            thresholds[t] = opened / (double) (n * n);
        }
    }

    public double mean() {
        return StdStats.mean(thresholds);
    }

    public double stddev() {
        return StdStats.stddev(thresholds);
    }

    public double confidenceLo() {
        return mean() - (CONFIDENCE_95 * stddev() / Math.sqrt(trials));
    }

    public double confidenceHi() {
        return mean() + (CONFIDENCE_95 * stddev() / Math.sqrt(trials));
    }
}
