/**
 * Created by sofia on 2021-11-28.
 */

/**
 * Code written during a timed online coding test.
 */

/**
 * Problem:
 * Given a palindrome string, change one character to break the palindrome, and return the string.
 * The result should be lexicographically smaller than the original string,
 * and lexicographically smallest among all possible candidates.
 * If impossible, return "IMPOSSIBLE".
 */
public class PalindromeBreaker {

    public static String breakPalindrome(String s) {
        if (s.length() <= 1) return "IMPOSSIBLE";

        char[] str = s.toCharArray();
        int n = str.length;
        int mid = (n-1)/2;
        int aCnt = 0;

        boolean broken = false;
        int i = 0;

        while (i < mid) {
            if (str[i] == 'a') {
                aCnt++;
                i++;
                continue;
            } else {
                str[i] = 'a';
                broken = true;
                break;
            }
        }

        if (broken) {
            return String.valueOf(str);
        } else {
            if (aCnt == mid-1) return "IMPOSSIBLE";
            if (n % 2 != 0) return "IMPOSSIBLE";
            if (str[i] == 'a') return "IMPOSSIBLE";

            str[i] = 'a';
            return String.valueOf(str);
        }
    }

}
