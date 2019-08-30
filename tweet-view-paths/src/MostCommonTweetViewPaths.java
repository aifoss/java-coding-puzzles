import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

/**
 * Created by sofia on 2019-08-25.
 */

/**
 * Code written during a timed online coding test.
 */

/**
 * Problem:
 *
 * Each line of STDIN input consists of log entries in the format of @[userHandle],T[tweetId], for example:
 *
 * @Biz,T1
 * @Biz,T2
 * @Techie,T1
 * @Techie,T3
 * @Biz,T4
 * @Techie,T5
 *
 * A tweet view path consists of 3 tweets successively viewed by a user.
 *
 * Given the input, find and print the most common tweet view path in the format:
 *
 * T1,T2,T3
 *
 * If multiple tweet view paths have the same frequency, they should be printed in lexicographical order:
 *
 * T1,T2,T3
 * T1,T2,T4
 * T1,T3,T4
 */
public class MostCommonTweetViewPaths {

    private static final String MALFORMED_INPUT_MESSAGE = "Log malformed!";
    private static final String EMPTY_RESULT_MESSAGE = "No tweet path found!";

    private static final String USER_HANDLE_START = "@";
    private static final String DELIMITER = ",T";


    public static void main(String[] args) {
        MostCommonTweetViewPathFinder finder = new MostCommonTweetViewPathFinder();

        Scanner scanner = new Scanner(System.in);

        if (!scanner.hasNextLine()) {
            handleMalformedInput(scanner);
            return;
        }

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            if (line == null || line.isEmpty()) break;

            int splitIdx = line.indexOf(DELIMITER);
            int extraSplitIdx = line.indexOf(DELIMITER, splitIdx+1);

            if (!line.startsWith(USER_HANDLE_START) || splitIdx < 0 || extraSplitIdx >= 0) {
                handleMalformedInput(scanner);
                return;
            }

            String user = line.substring(0, splitIdx);
            String tweetId = line.substring(splitIdx+1);

            if (invalidTweetId(tweetId)) {
                handleMalformedInput(scanner);
                return;
            }

            finder.addTweetView(user, tweetId);
        }

        scanner.close();

        List<TweetViewPath> res = finder.getMostCommonTweetViewPaths();

        if (res.isEmpty()) {
            System.out.println(EMPTY_RESULT_MESSAGE);
            return;
        }

        for (TweetViewPath path : res) {
            System.out.println(path);
        }
    }

    private static boolean invalidTweetId(String tweetId) {
        if (tweetId.length() <= 1) return true;

        char[] chars = tweetId.toCharArray();
        int n = chars.length;

        for (int i = 1; i < n; i++) {
            if (!Character.isDigit(chars[i])) return true;
        }

        return false;
    }

    private static void handleMalformedInput(Scanner scanner) {
        System.out.println(MALFORMED_INPUT_MESSAGE);
        scanner.close();
    }

}


class MostCommonTweetViewPathFinder {

    Map<String, LinkedList<String>> userToTweetViews;
    Map<TweetViewPath, Long> tweetViewPathToFreq;

    long maxFreq;

    public MostCommonTweetViewPathFinder() {
        userToTweetViews = new HashMap<>();
        tweetViewPathToFreq = new HashMap<>();

        maxFreq = 0L;
    }

    public void addTweetView(String userHandle, String tweetId) {
        userToTweetViews.putIfAbsent(userHandle, new LinkedList<>());
        userToTweetViews.get(userHandle).addLast(tweetId);

        if (hasTweetViewPath(userHandle)) {
            addTweetViewPathForUser(userHandle);
        }
    }

    private boolean hasTweetViewPath(String userHandle) {
        if (!userToTweetViews.containsKey(userHandle)) return false;
        if (userToTweetViews.get(userHandle).size() < 3) return false;

        return true;
    }

    private void addTweetViewPathForUser(String userHandle) {
        LinkedList<String> tweets = userToTweetViews.get(userHandle);

        addTweetViewPath(new TweetViewPath(tweets.pollFirst(), tweets.getFirst(), tweets.getLast()));
    }

    private void addTweetViewPath(TweetViewPath tweetViewPath) {
        long oldFreq = tweetViewPathToFreq.getOrDefault(tweetViewPath, 0L);
        long newFreq = oldFreq+1;

        maxFreq = Math.max(maxFreq, newFreq);

        tweetViewPathToFreq.put(tweetViewPath, newFreq);
    }

    public List<TweetViewPath> getMostCommonTweetViewPaths() {
        List<TweetViewPath> res = new ArrayList<>();

        if (maxFreq == 0L) return res;

        for (TweetViewPath path : tweetViewPathToFreq.keySet()) {
            if (tweetViewPathToFreq.get(path) == maxFreq) {
                res.add(path);
            }
        }

        Collections.sort(res);

        return res;
    }

}


class TweetViewPath implements Comparable<TweetViewPath> {

    String tweet1;
    String tweet2;
    String tweet3;

    public TweetViewPath(String t1, String t2, String t3) {
        tweet1 = t1;
        tweet2 = t2;
        tweet3 = t3;
    }

    @Override
    public int compareTo(TweetViewPath other) {
        int cmp = this.tweet1.compareTo(other.tweet1);
        if (cmp != 0) return cmp;

        cmp = this.tweet2.compareTo(other.tweet2);
        if (cmp != 0) return cmp;

        return this.tweet3.compareTo(other.tweet3);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (o == this) return true;
        if (o.getClass() != this.getClass()) return false;

        TweetViewPath other = (TweetViewPath) o;

        return this.tweet1.equals(other.tweet1) &&
                this.tweet2.equals(other.tweet2) &&
                this.tweet3.equals(other.tweet3);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tweet1, tweet2, tweet3);
    }

    @Override
    public String toString() {
        return tweet1 +","+ tweet2 +","+ tweet3;
    }

}
