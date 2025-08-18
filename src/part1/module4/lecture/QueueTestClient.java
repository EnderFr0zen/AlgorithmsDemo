package part1.module4.lecture;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class QueueTestClient {

    public static void main(String[] args) {
//        LinkedQueueOfStrings q = new LinkedQueueOfStrings();
        ResizingArrayQueueOfStrings q = new ResizingArrayQueueOfStrings();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (s.equals("-")) {
                StdOut.print(q.dequeue() + " ");
            } else {
                q.enqueue(s);
            }
        }
        StdOut.println();
    }
}
