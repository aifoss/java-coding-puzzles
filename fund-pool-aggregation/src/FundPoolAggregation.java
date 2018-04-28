import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by sofia on 4/23/18.
 */

/**
 * Code written during a timed online coding test.
 */

/**
 * Input:
 * Array of integers representing fund amounts (where amounts are in 1K denomination).
 *
 * Output:
 * 2D array of aggregated fund pools.
 *
 * Rules for aggregation:
 * If amount >= 1 MM, the pool itself becomes an aggregated pool.
 * If amount < 1 MM, the pool can be combined with at most 2 other pools, if sum == 1 MM.
 */
public class FundPoolAggregation {

    static final int THRESHOLD = 1000;

    static int[][] aggregatePools(int[] pool) {
        if (pool == null || pool.length == 0) return new int[0][0];

        Arrays.sort(pool);

        List<int[]> oneSumSolutions = new ArrayList<>();

        int n = pool.length;
        int i = n-1;

        while (i >= 0 && pool[i] >= THRESHOLD) {
            oneSumSolutions.add(new int[] { pool[i--] });
        }

        if (i == -1) {
            int[][] res = new int[oneSumSolutions.size()][];
            addOneSumSolutionsToRes(res, oneSumSolutions);
            return res;
        }

        int[] remaining = Arrays.copyOfRange(pool, 0, i+1);
        n = remaining.length;
        boolean[] used = new boolean[n];

        List<int[]> twoSumSolutions = solve2Sum(remaining, n, THRESHOLD, used);
        List<int[]> threeSumSolutions = solve3Sum(remaining, n, THRESHOLD, used);

        int n1 = oneSumSolutions.size();
        int n2 = twoSumSolutions.size();
        int n3 = threeSumSolutions.size();

        int[][] res = new int[n1+n2+n3][];

        if (n1 > 0) addToRes(res, oneSumSolutions, n1, 0);
        if (n2 > 0) addToRes(res, twoSumSolutions, n2, n1);
        if (n3 > 0) addToRes(res, threeSumSolutions, n3, n2);

        return res;
    }

    protected static List<int[]> solve2Sum(int[] a, int n, int x, boolean[] used) {
        List<int[]> res = new ArrayList<>();

        int i = 0;
        int j = n-1;

        while (i < j) {
            if (used[i]) { i++; continue; }
            if (used[j]) { j--; continue; }

            int sum = a[i] + a[j];

            if (sum < x) i++;
            else if (sum > x) j--;
            else {
                res.add(new int[] {a[i], a[j]});
                used[i] = true;
                used[j] = true;
                i++; j--;
            }
        }

        return res;
    }

    protected static List<int[]> solve3Sum(int[] a, int n, int x, boolean[] used) {
        List<int[]> res = new ArrayList<>();

        for (int i = 0; i <= n-3; i++) {
            if (used[i]) continue;

            int j = i+1;
            int k = n-1;

            while (j < k) {
                if (used[j]) { j++; continue; }
                if (used[k]) { k--; continue; }

                int sum = a[i] + a[j] + a[k];

                if (sum < x) j++;
                else if (sum > x) k--;
                else {
                    res.add(new int[] { a[i], a[j], a[k] });
                    used[i] = true;
                    used[j] = true;
                    used[k] = true;
                    j++; k--;
                }
            }
        }

        return res;
    }

    private static void addOneSumSolutionsToRes(int[][] res, List<int[]> oneSumSolutions) {
        for (int j = 0; j < res.length; j++) {
            res[j] = oneSumSolutions.get(j);
        }
    }

    private static void addToRes(int[][] res, List<int[]> list, int n, int start) {
        for (int i = start; i < start+n; i++) {
            res[i] = list.get(i-start);
        }
    }





    public static void main(String[] args) {
        int[] input1 = { 12000, 300, 500, 600, 700 }; // 300, 500, 600, 700, 12000
        int[][] expected1 = { {12000}, { 300, 700 } };
        int[][] actual1 = aggregatePools(input1);

        System.out.println(Arrays.toString(input1));
        for (int i = 0; i < expected1.length; i++) {
            System.out.print(Arrays.toString(expected1[i])+" ");
        }
        System.out.println();
        for (int i = 0; i < actual1.length; i++) {
            System.out.print(Arrays.toString(actual1[i])+" ");
        }
        System.out.println();
        System.out.println();

        int[] input2 = { 100, 100, 100, 200, 200, 200, 300 };
        int[][] expected2 = {};
        int[][] actual2 = aggregatePools(input2);

        System.out.println(Arrays.toString(input2));
        for (int i = 0; i < expected2.length; i++) {
            System.out.print(Arrays.toString(expected2[i])+" ");
        }
        System.out.println();
        for (int i = 0; i < actual2.length; i++) {
            System.out.print(Arrays.toString(actual2[i])+" ");
        }
        System.out.println();
        System.out.println();

        int[] input3 = { 200, 300, 200, 300, 300, 700 }; // 200, 200, 300, 300, 700
        int[][] expected3 = { { 300, 700 } };
        int[][] actual3 = aggregatePools(input3);

        System.out.println(Arrays.toString(input3));
        for (int i = 0; i < expected3.length; i++) {
            System.out.print(Arrays.toString(expected3[i])+" ");
        }
        System.out.println();
        for (int i = 0; i < actual3.length; i++) {
            System.out.print(Arrays.toString(actual3[i])+" ");
        }
        System.out.println();
        System.out.println();

        int[] input4 = { 200, 300, 200, 300, 600, 300, 700 }; // 200, 200, 300, 300, 300, 600, 700
        int[][] expected4 = { {200, 200, 600}, {300, 700} };
        int[][] actual4 = aggregatePools(input4);

        System.out.println(Arrays.toString(input4));
        for (int i = 0; i < expected4.length; i++) {
            System.out.print(Arrays.toString(expected4[i])+" ");
        }
        System.out.println();
        for (int i = 0; i < actual4.length; i++) {
            System.out.print(Arrays.toString(actual4[i])+" ");
        }
        System.out.println();
        System.out.println();
    }

}
