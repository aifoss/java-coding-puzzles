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
 * Write a function called isValid that takes a string of braces,
 * and determines if the order of the braces is valid.
 * isValid should return true if the string is valid, and false if it's invalid.
 *
 * All input strings will be nonempty, and will only consist of open parentheses '(' , closed parentheses ')',
 * open brackets '[', closed brackets ']', open curly braces '{' and closed curly braces '}'.
 *
 * What is considered Valid?
 *
 * A string of braces is considered valid if all braces are matched with the correct brace.
 *
 * For example:
 * '(){}[]' and '([{}])' would be considered valid, while '(}', '[(])', and '[({})](]' would be considered invalid.
 *
 * Examples:
 *
 * isValid( "(){}[]" ) // true
 * isValid( "(}" ) // false
 * isValid( "[(])" ) // false
 * isValid( "([{}])" ) // true
 */
public class ParenthesisValidation {

    public static boolean isValid(String braces) {
        if (braces == null || braces.isEmpty()) return true;

        Map<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < braces.length(); i++) {
            char c = braces.charAt(i);

            if (map.containsKey(c)) {
                stack.push(c);
            } else if (map.containsValue(c)) {
                if (!stack.isEmpty() && map.get(stack.peek()) == c) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }





    public static void main(String[] args) {
        String input;
        boolean expected;
        boolean actual;

        input = "(){}[]";
        expected = true;
        actual = isValid(input);
        System.out.println(input+" -> "+actual+" (vs "+expected+")");

        input = "(}";
        expected = false;
        actual = isValid(input);
        System.out.println(input+" -> "+actual+" (vs "+expected+")");

        input = "[(])";
        expected = false;
        actual = isValid(input);
        System.out.println(input+" -> "+actual+" (vs "+expected+")");

        input = "([{}])";
        expected = true;
        actual = isValid(input);
        System.out.println(input+" -> "+actual+" (vs "+expected+")");
    }

}
