package misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by sofia on 2019-03-19.
 */

/**
 * Problem:
 *
 * Write me a program that that accepts an array of integers,
 * and prints out the pairs of these integers that sum to 7.
 *
 * For example,
 * If the input is [1, 2, 3, 4, 5, 6], the output could be (1, 6), (2, 5), (3, 4), or (6, 1), (5, 2), (4, 3).
 * Different combinations of the same pair of numbers e.g, (1, 6) and (6, 1) are not distinct.
 */

/**
 * Assumptions:
 *
 * 1. Input array is not null and not empty (size >= 2).
 * 2. Input array contains distinct non-negative integers.
 * 3. Input x (target sum number) is a positive integer.
 * 4. Order of output (between pairs and within a pair) does not matter.
 */
public class TwoSum {

    /**
     * Version for Unsorted Input Array
     */
    public static List<List<Integer>> findTwoSumPairs(int[] a, int x) {
        List<List<Integer>> res = new ArrayList<>();

        Set<Integer> set = new HashSet<>();

        for (int num : a) {
            if (set.contains(x-num)) {
                res.add(Arrays.asList(x-num, num));
            } else {
                set.add(num);
            }
        }

        return res;
    }


    /**
     * Version for Sorted Input Array
     */
    public static List<List<Integer>> findTwoSumPairs2(int[] a, int x) {
        List<List<Integer>> res = new ArrayList<>();

        int n = a.length;
        int i = 0;
        int j = n-1;

        while (i < j) {
            int sum = a[i] + a[j];

            if (sum < x) i++;
            else if (sum > x) j--;
            else res.add(Arrays.asList(a[i++], a[j--]));
        }

        return res;
    }





    public static void main(String[] args) {
        int[] a;
        int x;
        List<List<Integer>> res;

        a = new int[] {1, 5, 2, 6, 7, 3, 9, 0, 8, 4, 10};
        x = 10;
        res = findTwoSumPairs(a, x);
        System.out.println(res);

        a = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        x = 10;
        res = findTwoSumPairs2(a, x);
        System.out.println(res);
    }

}
