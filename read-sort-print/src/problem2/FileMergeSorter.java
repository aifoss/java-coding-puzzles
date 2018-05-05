package problem2;

import java.io.File;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

/**
 * Created by sofia on 5/4/18.
 */

/**
 * Solution for problem 2.
 */

/**
 * Assumption:
 * The two input files contain integer numbers in sorted order, one number per line, with no invalid lines.
 */
public class FileMergeSorter {

    private static final int SIZE = 10;

    private static final String INPUT_FILE_1 = "../problem2_input1.txt";
    private static final String INPUT_FILE_2 = "../problem2_input2.txt";


    public static void main(String[] args) { // working directory: src
        printInSortedOrder(INPUT_FILE_1, INPUT_FILE_2, SIZE);
    }

    protected static void printInSortedOrder(String inputFile1, String inputFile2, int size) {
        Queue<Integer> minHeap = new PriorityQueue<>(2*size);

        try (Scanner scanner1 = new Scanner(new File(inputFile1));
            Scanner scanner2 = new Scanner(new File(inputFile2))) {

            while (scanner1.hasNextInt() || scanner2.hasNextInt()) {
                int count1 = 0;
                int count2 = 0;

                while (scanner1.hasNextInt() && count1 < size) {
                    minHeap.offer(scanner1.nextInt());
                    count1++;
                }

                while (scanner2.hasNextInt() && count2 < size) {
                    minHeap.offer(scanner2.nextInt());
                    count2++;
                }

                for (int i = 0; i < size && !minHeap.isEmpty(); i++) {
                    System.out.println(minHeap.poll());
                }
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        while (!minHeap.isEmpty()) {
            System.out.println(minHeap.poll());
        }
    }

}
