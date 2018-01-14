import java.util.*;

/**
 * Created by sofia on 11/18/16.
 */

/**
 Dice
 Write a program that simulates throwing two six-sided dice 200 times,
 and creates a histogram of the results, like this:

 2: XXXX
 3: XXXXXXXXXX
 4: XXXXXXXXXXXXXXXXXXXXXX
 5: XXXXXXXXXXXXXXXXXXXXX
 6: XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
 7: XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
 8: XXXXXXXXXXXXXXXXXXXXXXXXXX
 9: XXXXXXXXXXXXXXXXXXXXX
 10: XXXXXXXXXXXXXX
 11: XXXXXXXXXXXX
 12: XXXX
 */
public class DiceThrowSimulation {

    public static void simulate(int n, int m, int d) { //  n = # of trials, m = # of dice, d = # of sides
        Random random = new Random();
        Map<Integer,Integer> map = new TreeMap<>();

        for (int i = 0; i < n; i++) {
            int sum = 0;

            for (int j = 0; j < m; j++) {
                int val = random.nextInt(d) + 1;
                sum += val;
            }

            if (!map.containsKey(sum)) map.put(sum, 1);
            else map.put(sum, map.get(sum)+1);
        }

        displayHistogram(map);
    }

    private static void displayHistogram(Map<Integer,Integer> map) {
        for (Map.Entry<Integer,Integer> entry : map.entrySet()) {
            int sum = entry.getKey();
            int freq = entry.getValue();

            System.out.print(sum+": ");
            for (int i = 0; i < freq; i++) {
                System.out.print("X");
            }
            System.out.println();
        }
    }





    public static void main(String[] args) {
        simulate(200, 2, 6);
    }

}
