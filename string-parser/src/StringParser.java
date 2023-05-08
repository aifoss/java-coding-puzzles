import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.JUnitCore;

import java.util.*;

/**
 * Created by sofia on 2023-05-08.
 */

/**
 * Sources:
 * Coding interview
 */

/**
 * Problem:
 *
 * Given a string and a set of delimiters, parse the string and sum up the numbers that appear in the string.
 *
 * Modify/extend the code appropriately as each unit test is added.
 */
public class StringParser {

    static class Solution {

        static final String SEP = ",";

        public int add(String token, Set<Character> delimiters) {
            if (token == null) {
                throw new UnsupportedOperationException("Null input token!");
            }

            if (token.length() == 0) {
                return 0;
            }

            if (token.startsWith("//")) {
                return useCustomDelimiters(token);
            }

            if (delimiters.isEmpty()) {
                return useSpecifiedDelimiter(token, SEP);
            }

            int sum = 0;
            int num = 0;

            for (char c : token.toCharArray()) {
                if (delimiters.contains(c)) {
                    sum += num;
                    num = 0;
                } else {
                    num = num*10 + c-'0';
                }
            }

            sum += num;

            return sum;
        }

        protected int useCustomDelimiters(String token) {
            int newLineIndex = token.indexOf("\n");
            String delim = token.substring(2, newLineIndex);
            String actualToken = token.substring(newLineIndex+1);
            return useSpecifiedDelimiter(actualToken, delim);
        }

        protected int useSpecifiedDelimiter(String token, String customDelim) {
            String delim;
            if (customDelim.isEmpty()) {
                delim = SEP;
            } else {
                delim = customDelim;
            }

            int idx = token.indexOf(delim);
            if (idx < 0) {
                return Integer.parseInt(token);
            }

            String[] vals = token.split(delim);

            int sum = 0;

            for (String val : vals) {
                sum += Integer.parseInt(val);
            }

            return sum;
        }

    }


    @Test
    public void shouldReturnZeroForEmptyString() {
        Solution solution = new Solution();
        int result = solution.add("", new HashSet<>());
        Assert.assertEquals(0, result);
    }

    @Test
    public void shouldReturnSingleNumber() {
        Solution solution = new Solution();
        int result = solution.add("3", new HashSet<>());
        Assert.assertEquals(3, result);
    }

    @Test
    public void shouldAddTwoNumbersTogether() {
        Solution solution = new Solution();
        int result = solution.add("2,3", new HashSet<>());
        Assert.assertEquals(5, result);
    }

    @Test
    public void shouldAddThreeNumbersTogether() {
        Solution solution = new Solution();
        int result = solution.add("2,3,1", new HashSet<>());
        Assert.assertEquals(6, result);
    }

    @Test
    public void shouldSupportNewlines() {
        Solution solution = new Solution();
        int result = solution.add("12,23\n5", new HashSet<>(Arrays.asList(',', '\n')));
        Assert.assertEquals(40, result);
    }

    @Test
    public void shouldSupportCustomerDelimiter() {
        Solution solution = new Solution();
        int result = solution.add("//;\n4;5;6", new HashSet<>());
        Assert.assertEquals(15, result);
    }





    public static void main(String[] args) {
        JUnitCore.main("StringParser");
    }

}
