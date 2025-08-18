package part1.module4.lecture;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class DijkstraTwoStackEvaluator {

    public static void main(String[] args) {
        // Use algs4's Stack explicitly to avoid name clash with your own Stack class
        Stack<String> ops = new Stack<>();
        Stack<Double> vals = new Stack<>();

        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();

            if (s.equals("(")) {
                // ignore
            } else if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")) {
                ops.push(s);
            } else if (s.equals(")")) {
                String op = ops.pop();
                double val = vals.pop();
                if (op.equals("+")) {
                    val = vals.pop() + val;
                } else if (op.equals("-")) {
                    val = vals.pop() - val;
                } else if (op.equals("*")) {
                    val = vals.pop() * val;
                } else if (op.equals("/")) {
                    val = vals.pop() / val;
                }
                vals.push(val);
            } else {
                // number token
                vals.push(Double.parseDouble(s));
            }
        }
        StdOut.println(vals.pop());
    }
}
