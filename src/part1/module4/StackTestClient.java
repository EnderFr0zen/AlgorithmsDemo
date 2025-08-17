package part1.module4;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class StackTestClient {

    public static void main(String[] args) {

//        StackOfStrings stack = new StackOfStrings();
//        LinkedStackOfStrings stack = new LinkedStackOfStrings();
//        FixedCapacityStackOfStrings stack = new FixedCapacityStackOfStrings(100);
        ResizingArrayStackOfStrings stack = new ResizingArrayStackOfStrings();

        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (s.equals("-")) {
                StdOut.print(stack.pop() + " ");
            } else {
                stack.push(s);
            }
        }
    }
}
