import java.util.HashMap;
import java.util.Map;

/**
 * Created by sofia on 4/28/18.
 */

/**
 * Code written during a timed online coding test.
 */

/**
 * Problem:
 *
 * You are processing plane seat reservation.
 * The plane has N rows of sets, numbered from 1 to N.
 * There are ten seats in row (labelled from A to K, with letter I omitted).
 *
 * Some of the seats are already reserved.
 * The list of reserved seats is given as a string S (of length M), containing seat numbers separated by single spaces:
 * for example, "1A 3C 2B 40G 5A".
 * The reserved seats can be listed in S in any order.
 *
 * Your task is to allocate seats for as many three-person families as possible.
 * A three-person family occupies three seats that are next to each other in the same row.
 * Seats across aisle (such as 2C and 2D) are not considered to be next to each other.
 *
 * Write a function:
 *      public int solution(int N, String S);
 *
 * that, given the number of rows N and a list of reserved seats as string S,
 * returns the maximum number of three-person families that can be seated in the remaining unreserved seats.
 *
 * Assume that:
 * - N is an integer within the range [1..50].
 * - M is an integer within the range [0..1,909].
 * - String S consists of valid seat names separated with spaces.
 * - Every seat number appears in string S at most once.
 */
public class PlaneSeatReservation {

    private static final int ROW_LENGTH = 10;
    private static final int[] SECTION_SIZES = { 3, 4, 3 };

    private static final Map<Character, Integer> COL_MAPPING;
    private static final Map<Integer, Character> REVERSE_COL_MAPPING;

    static {
        COL_MAPPING = new HashMap<>();
        REVERSE_COL_MAPPING = new HashMap<>();

        for (char c = 'A'; c <= 'K'; c++) {
            if (c == 'I') continue;
            COL_MAPPING.put(c, c < 'I' ? c-'A' : c-'A'-1);
            REVERSE_COL_MAPPING.put(c < 'I' ? c-'A' : c-'A'-1, c);
        }
    }


    public static int solution(int N, String S) {
        Plane plane = new Plane(N, S);
        return plane.countAvailableFamilySeats();
    }


    public static class Plane {
        int n;
        boolean[][] filledSeats;

        public Plane(int N,  String S) {
            n = N;
            filledSeats = new boolean[n][ROW_LENGTH];

            if (S.length() > 0) {
                markReservedSeats(S);
            }
        }

        private void markReservedSeats(String S) {
            String[] reservedSeats = S.split(" ");
            for (String seat : reservedSeats) {
                int row = Integer.parseInt(seat.substring(0, seat.length()-1)) - 1;
                int col = COL_MAPPING.get(seat.charAt(seat.length()-1));
                filledSeats[row][col] = true;
            }
        }

        public int countAvailableFamilySeats() {
            int count = 0;

            for (int i = 0; i < n; i++) {
                int j = 0;

                for (int size : SECTION_SIZES) {
                    int start = j;
                    int end = j+size-1;

                    if (isAvailable(i, start, end, size)) {
                        count++;
                    }

                    j = end+1;
                }
            }

            return count;
        }

        private boolean isAvailable(int row, int start, int end, int size) {
            switch(size) {
                case 3:
                    if (available(row, start, end)) {
                        printAvailableSeats(row, start, end);
                        return true;
                    }
                    return false;
                case 4:
                    if (available(row, start, end-1)) {
                        printAvailableSeats(row, start, end-1);
                        return true;
                    } else if (available(row, start+1, end)) {
                        printAvailableSeats(row, start+1, end);
                        return true;
                    } else {
                        return false;
                    }
                default:
                    return true;
            }
        }

        private boolean available(int row, int start, int end) {
            for (int j = start; j <= end; j++) {
                if (filledSeats[row][j]) {
                    return false;
                }
            }
            return true;
        }

        private void printAvailableSeats(int row, int start, int end) {
            System.out.print("Available:");
            for (int j = start; j <= end; j++) {
                System.out.print(" "+(row+1)+(REVERSE_COL_MAPPING.get(j)));
            }
            System.out.println();
        }
    }





    public static void main(String[] args) {
        int N;
        String S;
        int res;

        N = 40;
        S = "1A 3C 2B 40G 5A";
        res = solution(N, S);
        System.out.println(res);
        System.out.println();

        N = 2;
        S = "1A 2F 1C";
        res = solution(N, S);
        System.out.println(res);
        System.out.println();

        N = 1;
        S = "";
        res = solution(N, S);
        System.out.println(res);
        System.out.println();
    }

}
