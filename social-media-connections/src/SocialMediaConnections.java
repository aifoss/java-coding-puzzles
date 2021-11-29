import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by sofia on 2021-11-28.
 */

/**
 * Code written during a timed online coding test.
 */

/**
 * Problem:
 * Given n, representing the number of people 0 to n-1, and two integer lists representing friendship connections.
 * Find trios of friends and return the minimum friendship score among them.
 * The minimum friendship score is calculated as the sum of the connections that each member of a trio
 * has with outside people.
 */
public class SocialMediaConnections {

    public static int bestTrio(int n, List<Integer> from, List<Integer> to) {
        if (n < 3) return -1;

        List<Integer>[] graph = new ArrayList[n+1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        int len = from.size();
        for (int i = 0; i < len; i++) {
            int f = from.get(i);
            int t = to.get(i);

            graph[f].add(t);
            graph[t].add(f);
        }

        Set<Set<Integer>> candidates = new HashSet<>();

        boolean[][][] used = new boolean[n+1][n+1][n+1];

        for (int i = 1; i <= n; i++) {
            if (graph[i].size() < 2) continue;

            for (int j : graph[i]) {
                for (int k : graph[i]) {
                    if (k == j) continue;
                    if (alreadyUsed(i, j, k, used)) continue;

                    if (isTrio(i, j, k, graph)) {
                        Set<Integer> trio = new HashSet<>(Arrays.asList(i, j, k));
                        candidates.add(trio);
                        markUsed(i, j, k, used);
                    }
                }
            }
        }

        if (candidates.isEmpty()) return -1;

        int minFriendshipScore = Integer.MAX_VALUE;

        for (Set<Integer> trio : candidates) {
            minFriendshipScore = Math.min(minFriendshipScore, calculateFriendshipScore(trio, graph));
        }

        return minFriendshipScore;
    }

    protected static boolean isTrio(int i, int j, int k, List<Integer>[] graph) {
        if (!graph[i].contains(j) || !graph[i].contains(k)) return false;
        if (!graph[j].contains(i) || !graph[j].contains(k)) return false;
        if (!graph[k].contains(i) || !graph[k].contains(j)) return false;

        return true;
    }

    protected static int calculateFriendshipScore(Set<Integer> trio, List<Integer>[] graph) {
        int score = 0;

        for (int i : trio) {
            score += graph[i].size()-2;
        }

        return score;
    }

    private static boolean alreadyUsed(int i, int j, int k, boolean[][][] used) {
        if (used[i][j][k]) return true;
        if (used[i][k][j]) return true;
        if (used[j][i][k]) return true;
        if (used[j][k][i]) return true;
        if (used[k][i][j]) return true;
        if (used[k][j][i]) return true;

        return false;
    }

    private static void markUsed(int i, int j, int k, boolean[][][] used) {
        used[i][j][k] = true;
        used[i][k][j] = true;
        used[j][i][k] = true;
        used[j][k][i] = true;
        used[k][i][j] = true;
        used[k][j][i] = true;
    }

}
