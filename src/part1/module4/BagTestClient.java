package part1.module4;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class BagTestClient {

    public static void main(String[] args) {
        Bag<String> bag = new Bag<>();

        StdOut.println("Type tokens. Press ENTER on an empty line or type END to finish.");

        while (true) {
            // blocks until you press Enter
            String line = StdIn.readLine();
            // EOF (redirected input)
            if (line == null) {
                break;
            }
            line = line.trim();
            if (line.isEmpty() || line.equalsIgnoreCase("END")) {
                break;
            }

            for (String tok : line.split("\\s+")) {
                bag.add(tok);
            }
        }

        StdOut.println("size = " + bag.size());
        for (String x : bag) {
            StdOut.print(x + " ");
        }
        StdOut.println();
    }
}
