package part1.module1.assignment;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {

    public static void main(String[] args) {
        String champion = "";
        int count = 0;

        for (String word : args) {
            count++;
            if (StdRandom.bernoulli(1.0 / count)) {
                champion = word;
            }
        }
        StdOut.println(champion);
    }
}
