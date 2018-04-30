import java.util.*;

/**
 * Created by sofia on 4/30/18.
 */

/**
 * Code written during a timed online coding test.
 */

/**
 * Problem:
 *
 * Write a function that counts how many different ways you can make change for an amount of money,
 * given an array of coin denominations.
 *
 * For example, there are 3 ways to give change for 4 if you have coins with denomination 1 and 2:
 * 1+1+1+1, 1+1+2, 2+2.
 *
 * The order of coins does not matter:
 * 1+1+2 == 2+1+1
 *
 * Also, assume that you have an infinite amount of coins.
 *
 * Your function should take an amount to change and an array of unique denominations for the coins:
 *
 * countChange(4, [1,2]) // => 3
 * countChange(10, [5,2,3]) // => 4
 * countChange(11, [5,7]) //  => 0
 */
public class CoinChange {

    public static Integer countChange(Integer money, List<Integer> coins) {
        if (money == null || money == 0) return 1;
        if (coins == null || coins.isEmpty()) return 0;

        int[] T = new int[money+1];
        T[0] = 1;

        for (int coin : coins) {
            for (int i = coin; i <= money; i++) {
                T[i] += T[i-coin];
            }
        }

        return T[money];
    }





    public static void main(String[] args) {
        Integer money;
        List<Integer> coins;
        Integer res;

        money = 4;
        coins = Arrays.asList(1, 2);
        res = countChange(money, coins);
        System.out.println(res+" (3)");

        money = 10;
        coins = Arrays.asList(5, 2, 3);
        res = countChange(money, coins);
        System.out.println(res+" (4)");

        money = 11;
        coins = Arrays.asList(5, 7);
        res = countChange(money, coins);
        System.out.println(res+" (0)");
    }

}
