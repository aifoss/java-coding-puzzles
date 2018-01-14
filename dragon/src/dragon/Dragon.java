package dragon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created by sofia on 11/3/15.
 */
public class Dragon {

    public static final String END = "E";
    public static final String FAILURE = "failure";


    public static String solveDragon(int[] a, int n) {
        if (a[0] == 0) return FAILURE;
        return findMinFlightsDP(a, n);
    }

    private static String findMinFlightsDP(int[] a, int n) {
        int[] T = new int[n+1];
        int[] I = new int[n+1];

        Arrays.fill(T, Integer.MAX_VALUE - 1);
        T[0] = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                if (i < n && a[i] == 0) continue;
                if (i <= j+a[j]) {
                    if (T[i] > T[j]+1) {
                        T[i] = T[j]+1;
                        I[i] = j;
                    }
                }
            }
        }

        if (T[n] == Integer.MAX_VALUE-1) {
            return FAILURE;
        }

        return getFlightPaths(I, n);
    }

    private static String getFlightPaths(int[] indices, int n) {
        StringBuilder result = new StringBuilder();
        result.append("out");

        StringBuilder sb = new StringBuilder();
        sb.append(indices[n]).append(", ");
        result.insert(0, sb);

        int i = indices[n];
        while (i > 0) {
            sb = new StringBuilder();
            sb.append(indices[i]).append(", ");
            result.insert(0, sb);
            i = indices[i];
        }

        return result.toString();
    }





    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> array = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.isEmpty() || line.equals(END)) break;
            try {
                int number = Integer.parseInt(line);
                array.add(number);
            } catch (NumberFormatException e) {
                System.out.println(FAILURE);
                return;
            }
        }

        scanner.close();

        int n = array.size();
        if (n == 0) {
            System.out.println(FAILURE);
            return;
        }

        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = array.get(i);
        }

        String result = solveDragon(a, n);

        System.out.println(result);
    }

}
