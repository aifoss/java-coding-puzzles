import java.util.*;

/**
 * Created by sofia on 4/28/18.
 */

/**
 * Code written during a timed online coding test.
 */

/**
 * Problem:
 *
 * The battleship game is played on a square map of N rows, numbered from 1 to N.
 * Each row contains N cells, labeled with consecutive English upper-case letters (A, B, C, etc.).
 * Each cell is identified by a string composed of its row number followed by its column number:
 * for example, "9C" denotes the third cell in the 9th row, and "15D" denotes the fourth cell in the 15th row.
 *
 * Ships are defined by rectangles with a maximum area of 4 cells.
 * A ship is considered to be hit if at least one of its constituent cells is hit.
 * If all of a ship's cells are hit, the ship is sunk.
 *
 * The goal is to count the number of sunk ships and the number of ships that have been hit but not sunk.
 *
 * The positions of ships are given as a string S, containing pairs of positions
 * describing respectively the top-left and bottom-right corner cells of each ship.
 * Ship's descriptions are separated with commas.
 * The positions of hits are given as a string T, containing positions describing the map cells that were hit.
 *
 * Write a function:
 *      public String solution(int N, String S, String T);
 *
 * that, given the size of the map N and two strings S, T that describe the positions of ships and hits respectively,
 * returns a string with two numbers:
 * the count of sunken ships and the count of ships that have been hit but not sunk, separated with a comma.
 *
 * Assume that:
 * - N is an integer within range [1..26].
 * - String S contains the descriptions of rectangular ships of area not greater than 4 cells.
 * - There can be at most once ship located on any map cell (ships do not overlap).
 * - Each map cell can appear in string T at most once.
 * - String S and string T contain only valid positions given in specified format.
 */
public class BattleshipGame {

    private static final Map<Character, Integer> COL_MAPPING;

    static {
        COL_MAPPING = new HashMap<>();

        for (char c = 'A'; c <= 'Z'; c++) {
            COL_MAPPING.put(c, c-'A');
        }
    }


    public static String solution(int N, String S, String T) {
        BattleMap battleMap = new BattleMap(N, S, T);
        int[] res = battleMap.getNumberOfSunkenAndHitShips();
        return res[0]+","+res[1];

    }


    public static class BattleMap {
        private List<Battleship> battleships;
        private Map<Integer, Set<Integer>> hits;

        public BattleMap(int N, String S, String T) {
            createBattleships(S);
            recordHits(T);
        }

        private void createBattleships(String S) {
            battleships = new ArrayList<>();

            String[] shipCoordinates = S.split(",");

            for (String coordinate : shipCoordinates) {
                String[] cells = coordinate.split(" ");

                Battleship battleship = new Battleship(cells[0], cells[1]);
                battleships.add(battleship);
            }
        }

        private void recordHits(String T) {
            hits = new HashMap<>();

            for (String hit : T.split(" ")) {
                int row = Integer.parseInt(hit.substring(0, hit.length()-1))-1;
                int col = COL_MAPPING.get(hit.charAt(hit.length()-1));
                hits.computeIfAbsent(row, k -> new HashSet<>()).add(col);
            }
        }

        public int[] getNumberOfSunkenAndHitShips() {
            int numSunkenShips = 0;
            int numHitShips = 0;

            for (Battleship battleship : battleships) {
                battleship.checkIfSunken(hits);
                if (battleship.isSunken()) {
                    numSunkenShips++;
                    continue;
                }

                battleship.checkIfHitButNotSunken(hits);
                if (battleship.isHitButNotSunken()) {
                    numHitShips++;
                }
            }

            return new int[] { numSunkenShips, numHitShips };
        }
    }


    public static class Battleship {
        private List<int[]> cells;
        private boolean sunken;
        private boolean hitButNotSunken;

        public Battleship(String topLeft, String bottomRight) {
            cells = new ArrayList<>();

            int top = Integer.parseInt(topLeft.substring(0, topLeft.length()-1))-1;
            int left = COL_MAPPING.get(topLeft.charAt(topLeft.length()-1));
            int bottom = Integer.parseInt(bottomRight.substring(0, bottomRight.length()-1))-1;
            int right = COL_MAPPING.get(bottomRight.charAt(bottomRight.length()-1));

            populateCells(top, left, bottom, right);
        }

        private void populateCells(int top, int left, int bottom, int right) {
            for (int i = top; i <= bottom; i++) {
                for (int j = left; j <= right; j++) {
                    cells.add(new int[] {i, j});
                }
            }
        }

        public void checkIfSunken(Map<Integer, Set<Integer>> hits) {
            for (int[] cell : cells) {
                if (!hits.containsKey(cell[0])) return;
                if (!hits.get(cell[0]).contains(cell[1])) return;
            }
            sunken = true;
        }

        public void checkIfHitButNotSunken(Map<Integer, Set<Integer>> hits) {
            if (sunken) return;

            for (int[] cell : cells) {
                if (!hits.containsKey(cell[0])) continue;
                if (!hits.get(cell[0]).contains(cell[1])) continue;
                hitButNotSunken = true;
                return;
            }
        }

        public boolean isSunken() {
            return sunken;
        }

        public boolean isHitButNotSunken() {
            return hitButNotSunken;
        }
    }





    public static void main(String[] args) {
        int N;
        String S;
        String T;
        String res;

        N = 4;
        S = "1B 2C,2D 4D";
        T = "2B 2D 3D 4D 4A";
        res = solution(N, S, T);
        System.out.println(res);

        N = 3;
        S = "1A 1B,2C 2C";
        T = "1B";
        res = solution(N, S, T);
        System.out.println(res);

        N = 12;
        S = "1A 2A,12A 12A";
        T = "12A";
        res = solution(N, S, T);
        System.out.println(res);
    }

}
