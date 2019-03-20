package misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sofia on 2019-03-19.
 */

/**
 * Problem:
 *
 * Write me a program that accepts a block of text (English words),
 * and outputs the groups of words that are anagrams of each other.
 *
 * For example, “apt”, “tap” and “pat” are anagrams of each other.
 */

/**
 * Assumptions:
 *
 * 1. Input text is not null or empty.
 * 2. Input text contains lowercase words only (no punctuations), separated by single spaces.
 * 3. An anagram group can consist of a single word.
 * 4. Order of output (among anagram groups and within an anagram group) does not matter.
 */
public class AnagramGroups {

    public static List<List<String>> groupAnagrams(String text) {
        Map<String, List<String>> map = new HashMap<>();

        String[] words = text.split(" ");

        for (String word : words) {
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            String key = String.valueOf(chars);

            map.computeIfAbsent(key, k -> new ArrayList<>()).add(word);
        }

        return new ArrayList<>(map.values());
    }


    /**
     * Faster Version w/o Using Lambda
     */
    public static List<List<String>> groupAnagrams2(String text) {
        Map<String, List<String>> map = new HashMap<>();

        String[] words = text.split(" ");

        for (String word : words) {
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            String key = String.valueOf(chars);

            if (!map.containsKey(key)) map.put(key, new ArrayList<>());
            map.get(key).add(word);
        }

        return new ArrayList<>(map.values());
    }


    /**
     * Alternative Faster Version w/o Using Lambda
     */
    public static List<List<String>> groupAnagrams3(String text) {
        Map<String, List<String>> map = new HashMap<>();

        String[] words = text.split(" ");

        for (String word : words) {
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            String key = String.valueOf(chars);

            List<String> anagrams = map.get(key);
            if (anagrams == null) anagrams = new ArrayList<>();
            anagrams.add(word);
            map.put(key, anagrams);
        }

        return new ArrayList<>(map.values());
    }








    public static void main(String[] args) {
        String text = "apt race top pat acre opt tap pot man";
        List<List<String>> res;

        long start, end;

        start = System.currentTimeMillis();
        res = groupAnagrams(text);
        end = System.currentTimeMillis();

        System.out.println(res.toString());
        System.out.println(end-start);
        System.out.println();

        start = System.currentTimeMillis();
        res = groupAnagrams2(text);
        end = System.currentTimeMillis();

        System.out.println(res.toString());
        System.out.println(end-start);
        System.out.println();

        start = System.currentTimeMillis();
        res = groupAnagrams3(text);
        end = System.currentTimeMillis();

        System.out.println(res.toString());
        System.out.println(end-start);
        System.out.println();
    }

}
