package util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class GenerateNumbers {
    public static void main(String[] args) throws IOException {
        Random rand = new Random();
        FileWriter fw = new FileWriter("src/resources/numbers.txt");
        int N = 5000;
        for (int i = 0; i < N; i++) {
            fw.write((rand.nextInt(20001) - 10000) + "\n");
        }
        fw.close();
        System.out.println("numbers.txt generated with " + N + " integers.");
    }
}
