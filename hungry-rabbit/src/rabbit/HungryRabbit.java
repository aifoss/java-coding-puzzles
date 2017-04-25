package rabbit;

/**
 * Created by sofia on 4/25/17.
 */
public class HungryRabbit {

    // auxiliary class for accumulating carrot counts
    private static class Count {
        private int cnt = 0;
    }

    // directions for rabbit's movement
    private static int[][] dirs = { {-1,0},{1,0},{0,-1},{0,1} };


    /**
     * Main method to compute the number of carrots eaten by the rabbit.
     *
     * @param mat m x n matrix in which the rabbit moves
     * @return number of carrots eaten by rabbit
     */
    public static int numCarrotsEaten(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        int[] startCell = getStartCell(mat, m, n);
        boolean[][] visited = new boolean[m][n];
        Count count = new Count();

        dfs(mat, startCell[0], startCell[1], visited, count);

        return count.cnt;
    }

    // do depth-first search, selecting the next cell with max carrot count, while accumulating carrot counts
    protected static void dfs(int[][] mat, int x, int y, boolean[][] visited, Count count) {
        visited[x][y] = true;
        count.cnt += mat[x][y];

        int[] maxCell = new int[2];
        int max = 0;

        for (int i = 0; i < dirs.length; i++) {
            int nx = x + dirs[i][0];
            int ny = y + dirs[i][1];

            if (isValid(nx, ny, mat, visited)) {
                if (mat[nx][ny] > max) {
                    max = mat[nx][ny];
                    maxCell[0] = nx;
                    maxCell[1] = ny;
                }
            }
        }

        if (max == 0) return;
        else dfs(mat, maxCell[0], maxCell[1], visited, count);
    }

    // get the coordinates of the start cell
    private static int[] getStartCell(int[][] mat, int m, int n) {
        int x1, x2;
        int y1, y2;

        if (m % 2 != 0) {
            x1 = m/2;
            x2 = m/2;
        } else {
            x1 = m/2-1;
            x2 = m/2;
        }

        if (n % 2 != 0) {
            y1 = n/2;
            y2 = n/2;
        } else {
            y1 = n/2-1;
            y2 = n/2;
        }

        return getMaxCell(mat, x1, x2, y1, y2);
    }

    // get the cell with the max number of carrots
    private static int[] getMaxCell(int[][] mat, int x1, int x2, int y1, int y2) {
        int max1 = Math.max(mat[x1][y1], mat[x1][y2]);
        int max2 = Math.max(mat[x2][y1], mat[x2][y2]);
        int max = Math.max(max1, max2);

        if (max == mat[x1][y1]) return new int[] { x1, y1 };
        else if (max == mat[x1][y2]) return new int[] { x1, y2 };
        else if (max == mat[x2][y1]) return new int[] { x2, y1 };
        else return new int[] { x2, y2 };
    }

    // check if the given cell is valid for next movement
    private static boolean isValid(int x, int y, int[][] mat, boolean[][] visited) {
        return x >= 0 && x < mat.length && y >= 0 && y < mat[0].length && !visited[x][y];
    }





    public static void main(String[] args) {
        int[][] mat = {
                {5, 7, 8, 6, 3},
                {0, 0, 7, 0, 4},
                {4, 6, 3, 4, 9},
                {3, 1, 0, 5, 8}
        };

        int ans = numCarrotsEaten(mat);

        System.out.println(ans);
    }

}
