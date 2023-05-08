/**
 * Created by sofia on 2023-05-08.
 */

/**
 * Sources:
 * Timed online coding test
 */

/**
 * Problem:
 *
 * Given a palindrome string, change one character to break the palindrome, and return the string.
 *
 * The result should be lexicographically smaller than the original string,
 * and lexicographically smallest among all possible candidates.
 *
 * If impossible, return "IMPOSSIBLE".
 */
public class PalindromeBreaker {

    public static String breakPalindrome(String s) {
        if (s.length() <= 1) return "IMPOSSIBLE";

        char[] str = s.toCharArray();
        int n = str.length;
        int i = 0;
        int mid = (n-1)/2;
        int aCnt = 0;

        boolean broken = false;

        while (i < mid) {
            if (str[i] == 'a') {
                aCnt++;
                i++;
            } else {
                str[i] = 'a';
                broken = true;
                break;
            }
        }

        if (!broken) {
            if (aCnt == mid-1) return "IMPOSSIBLE";
            if (n % 2 != 0) return "IMPOSSIBLE";
            if (str[i] == 'a') return "IMPOSSIBLE";

            str[i] = 'a';
        }

        return String.valueOf(str);
    }





    public static void main(String[] args) {
        String s;
        String res;

        s = "abcdedcba";
        res = breakPalindrome(s);
        System.out.println(res);

        s = "bbccbb";
        res = breakPalindrome(s);
        System.out.println(res);

        s = "aaa";
        res = breakPalindrome(s);
        System.out.println(res);
    }

}
