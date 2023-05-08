import java.util.*;
import org.junit.*;
import org.junit.runner.*;

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
 * Given a list of synonym pairs, take an input sentence and generate all "equivalent" sentences.
 *
 * synonyms:
 * quick rapid
 * quick fast
 *
 * input: "the cat is quick"
 * output: ["the cat is quick", "the cat is fast", "the cat is rapid"]
 */
public class EquivalentSentenceGenerator {

    static class EquivSentenceGenerator {
        Map<String, Set<String>> graph;

        public EquivSentenceGenerator(List<List<String>> synonymPairs) {
            graph = buildGraph(synonymPairs);
        }

        public List<String> generateEquivalentSentences(String sentence) {
            List<String> res = new ArrayList<>();

            Set<String> seen = new HashSet<>();
            Queue<String> q = new LinkedList<>();

            seen.add(sentence);
            q.offer(sentence);

            while (!q.isEmpty()) {
                String curr = q.poll();
                res.add(curr);

                String[] words = curr.split(" ");
                int n = words.length;

                // let's go through the words in the sentence
                // to see if we can replace any with a synonym
                for (int i = 0; i < n; i++) {
                    String word = words[i];
                    if (!graph.containsKey(word)) continue;

                    for (String syn : graph.get(word)) {
                        words[i] = syn;
                        String next = String.join(" ", words);

                        if (!seen.contains(next)) {
                            seen.add(next);
                            q.offer(next);
                        }
                    }

                    words[i] = word;
                }
            }

            Collections.sort(res);

            return res;
        }

        protected Map<String, Set<String>> buildGraph(List<List<String>> synonymPairs) {
            Map<String, Set<String>> graph = new HashMap<>();

            for (List<String> pair : synonymPairs) {
                if (pair.size() != 2) continue;

                String a = pair.get(0);
                String b = pair.get(1);

                graph.putIfAbsent(a, new HashSet<>());
                graph.putIfAbsent(b, new HashSet<>());

                graph.get(a).add(b);
                graph.get(b).add(a);
            }

            return graph;
        }
    }


    @Test
    public void testSampleSentence() {
        String sentence;
        List<List<String>> synonymPairs;
        List<String> res;

        synonymPairs = Arrays.asList(
                Arrays.asList("quick", "fast"),
                Arrays.asList("quick", "rapid")
        );
        sentence = "the cat is quick";

        EquivSentenceGenerator sentenceGenerator = new EquivSentenceGenerator(synonymPairs);

        res = sentenceGenerator.generateEquivalentSentences(sentence);
        Assert.assertEquals(3, res.size());
        Assert.assertEquals("the cat is fast", res.get(0));
        Assert.assertEquals("the cat is quick", res.get(1));
        Assert.assertEquals("the cat is rapid", res.get(2));
    }





    public static void main(String[] args) {
        String sentence;
        List<List<String>> synonymPairs;
        List<String> res;

        synonymPairs = Arrays.asList(
                Arrays.asList("quick", "fast"),
                Arrays.asList("quick", "rapid")
        );
        sentence = "the cat is quick";

        EquivSentenceGenerator sentenceGenerator = new EquivSentenceGenerator(synonymPairs);

        res = sentenceGenerator.generateEquivalentSentences(sentence);
        for (String s : res) {
            System.out.println(s);
        }

        System.out.println("---------------------------");

        // test case: no synonyms present
        sentence = "the cat is slow";
        res = sentenceGenerator.generateEquivalentSentences(sentence);
        for (String s : res) {
            System.out.println(s);
        }

        System.out.println("---------------------------");

        synonymPairs = Arrays.asList(
                Arrays.asList("quick", "fast"),
                Arrays.asList("quick", "rapid"),
                Arrays.asList("cat", "feline")
        );
        sentence = "the cat is quick";

        sentenceGenerator = new EquivSentenceGenerator(synonymPairs);
        res = sentenceGenerator.generateEquivalentSentences(sentence);
        for (String s : res) {
            System.out.println(s);
        }

        System.out.println("---------------------------");

        JUnitCore.main("EquivalentSentenceGenerator");
    }

}
