package dpap.algorithm;

import java.util.Arrays;

/**
 * Created by sofia on 2/8/14.
 */

/**
 * Implementation of Hungarian algorithm for solving the minimum/maximum sum assignment problem.
 * Based on the references found on the Web.
 * Modifiable to handle rectangular matrices as well as square matrices.
 *
 * @author sofia
 */
public class HungarianAlgorithm {

    private static final double ZERO = 0.0;         // zero value

    private static final int CLEARED = 0;           // value to mark cleared zero
    private static final int STARRED = 1;           // value to mark starred zero
    private static final int PRIMED = 2;            // value to mark primed zero
    private static final int COVERED = 1;           // value to mark covered row or column
    private static final int UNCOVERED = 0;         // value to mark uncovered row or column

    private static final int INVALID = -1;          // value to mark invalid index

    private static final int ROW = 0;               // value for index for storing row index
    private static final int COL = 1;               // value for index for storing column index

    private static final int STEP_1 = 1;            // value to represent step 1 in execution of algorithm
    private static final int STEP_2 = 2;            // value to represent step 2 in execution of algorithm
    private static final int STEP_3 = 3;            // value to represent step 3 in execution of algorithm
    private static final int STEP_4 = 4;            // value to represent step 4 in execution of algorithm
    private static final int STEP_5 = 5;            // value to represent step 5 in execution of algorithm
    private static final int STEP_6 = 6;            // value to represent step 6 in execution of algorithm
    private static final int STEP_7 = 7;            // value to represent step 7 in execution of algorithm

    private static final int DONE = 0;              // value to represent completion of the execution of algorithm

    /**
     * Applies Hungarian algorithm step by step and returns matrix representing minimum/maximum sum assignment.
     *
     * @param squareMatrix input matrix with equal number of rows and columns
     * @param maxSumProblem indicates whether or not the problem is to find maximum sum assignment
     * @return assignment matrix representing minimum/maximum sum assignment
     */
    public static int[][] applyAlgorithm (double[][] squareMatrix, boolean maxSumProblem) {

        ///////////////////////////////////////////////////////////////////////////////////////////
        // Initialization Phase
        ///////////////////////////////////////////////////////////////////////////////////////////

        // number of rows in matrix = number of columns in matrix
        int size = squareMatrix.length;

        // copy input matrix
        double[][] matrix = copyMatrix(size, squareMatrix);

        // adjust matrix in case of maximum sum assignment problem
        if (maxSumProblem) {
            adjustMatrix(size, matrix);
        }

        // auxiliary matrix for marking zeros in the main matrix
        int[][] zeroMatrix = new int[size][size];

        // arrays to mark covered rows and columns in the main matrix
        int[] rowCover = new int[size];
        int[] colCover = new int[size];

        // array to hold row and column indices of zero
        int[] zeroIdx = new int[2];

        // matrix to contain assignment result
        int[][] assignment = new int[size][2];

        // flag to indicate whether or not algorithm execution is completed
        boolean done = false;

        // indicator of the current step in the execution of the algorithm
        int step = STEP_1;

        ///////////////////////////////////////////////////////////////////////////////////////////
        // Execution Phase
        ///////////////////////////////////////////////////////////////////////////////////////////

        // while not finished, execute an appropriate step of the algorithm, repeating if necessary
        while (!done) {
            switch (step) {
                case STEP_1:
                    step = executeStep1(size, matrix);
                    break;
                case STEP_2:
                    step = executeStep2(size, matrix, zeroMatrix, rowCover, colCover);
                    break;
                case STEP_3:
                    step = executeStep3(size, zeroMatrix, colCover);
                    break;
                case STEP_4:
                    step = executeStep4(size, matrix, zeroMatrix, rowCover, colCover, zeroIdx);
                    break;
                case STEP_5:
                    step = executeStep5(size, zeroMatrix, rowCover, colCover, zeroIdx);
                    break;
                case STEP_6:
                    step = executeStep6(size, matrix, rowCover, colCover);
                    break;
                case STEP_7:
                    step = executeStep7(size, zeroMatrix, assignment);
                    break;
                case DONE:
                    done = true;
                    break;
            }
        }

        return assignment;
    }

    /*
     * Step 1:
     * Finds the minimum value for each row and subtracts it from each value in the given row.
     */
    protected static int executeStep1(int size, double[][] matrix) {

        for (int row = 0; row < size; row++) {
            double rowMin = matrix[row][0];
            for (int col = 1; col < size; col++) {
                if (rowMin > matrix[row][col]) {
                    rowMin = matrix[row][col];
                }
            }
            for (int col = 0; col < size; col++) {
                matrix[row][col] -= rowMin;
            }
        }

        return STEP_2;
    }

    /*
     * Step 2:
     * Finds uncovered zeros, marks them as starred, and covers the given row and column.
     */
    protected static int executeStep2(int size, double[][] matrix, int[][] zeroMatrix, int[] rowCover, int[] colCover) {

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (ZERO == matrix[row][col] && UNCOVERED == rowCover[row] && UNCOVERED == colCover[col]) {
                    zeroMatrix[row][col] = STARRED;
                    rowCover[row] = COVERED;
                    colCover[col] = COVERED;
                }
            }
        }

        // reset covered row/column arrays
        clearCovers(size, rowCover, colCover);

        return STEP_3;
    }

    /*
     * Step 3:
     * Covers each column containing a starred zero and checks if all columns are covered.
     */
    protected static int executeStep3(int size, int[][] zeroMatrix, int[] colCover) {

        // mark each column containing a starred zero as covered
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (STARRED == zeroMatrix[row][col]) {
                    colCover[col] = COVERED;
                }
            }
        }

        // count the number of covered columns
        int coveredColCount = 0;
        for (int col = 0; col < size; col++) {
            if (COVERED == colCover[col]) {
                coveredColCount++;
            }
        }

        // done if all columns are covered; need to proceed to Step 4 otherwise
        if (coveredColCount >= size) {
            return STEP_7;
        } else {
            return STEP_4;
        }
    }

    /*
     * Step 4:
     * Finds uncovered zeros and mark them as primed.
     */
    protected static int executeStep4(
            int size,
            double[][] matrix,
            int[][] zeroMatrix,
            int[] rowCover,
            int[] colCover,
            int[] zeroIdx) {

        int nextStep = STEP_4;
        boolean done = false;

        while (!done) {
            // find uncovered zero
            int[] idx = findUncoveredZero(size, matrix, rowCover, colCover);

            // if there is no uncovered zero, go to Step 6
            if (INVALID == idx[ROW]) {
                done = true;
                nextStep = STEP_6;
            // otherwise
            } else {
                // mark uncovered zero as primed
                zeroMatrix[idx[ROW]][idx[COL]] = PRIMED;

                // check if there is a starred zero in the row containing the primed zero
                boolean starredZeroInRow = false;
                for (int col = 0; col < size; col++) {
                    if (STARRED == zeroMatrix[idx[ROW]][col]) {
                        starredZeroInRow = true;
                        idx[COL] = col;
                    }
                }

                // if there is a starred zero, cover the row and uncover the column 
                if (starredZeroInRow) {
                    rowCover[idx[ROW]] = COVERED;
                    colCover[idx[COL]] = UNCOVERED;
                // otherwise, save row and column indices for the primed zero, and go to Step 5
                } else {
                    zeroIdx[ROW] = idx[ROW];
                    zeroIdx[COL] = idx[COL];
                    done = true;
                    nextStep = STEP_5;
                }
            }
        }

        return nextStep;
    }

    /*
     * Step 5:
     * Constructs a series of alternating starred and primed zeros.
     */
    protected static int executeStep5(int size, int[][] zeroMatrix, int[] rowCover, int[] colCover, int[] zeroIdx) {

        // row count
        int count = 0;

        // matrix to store row and column indices 
        int[][] series = new int[size*size][2];

        // store row and column indices for the last primed zero
        series[count][ROW] = zeroIdx[ROW];
        series[count][COL] = zeroIdx[COL];

        boolean done = false;

        while (!done) {
            // find row index for a starred zero in the column for the last primed zero
            int row = findRowForStarredZeroInColumn(size, series[count][COL], zeroMatrix);

            // if there is a starred zero, update line matrix with row/column indices for the starred zero
            if (row >= 0) {
                count++;
                series[count][ROW] = row;
                series[count][COL] = series[count-1][COL];
            // otherwise set done to true
            } else {
                done = true;
            }

            if (!done) {
                // find column index for a primed zero in the row for the starred zero
                // update series matrix with row/column indices for the primed zero
                int col = findColumnForPrimedZeroInRow(size, series[count][ROW], zeroMatrix);
                count++;
                series[count][ROW] = series [count-1][ROW];
                series[count][COL] = col;
            }
        }

        // flip zero matrix values by marking starred zeros as cleared and marking primed zeros as starred
        flipZeroMatrixValues(size, count, zeroMatrix, series);

        // clear covered rows and columns
        clearCovers(size, rowCover, colCover);

        // clear primed zeros
        clearPrimedZeros(size, zeroMatrix);

        return STEP_3;
    }

    /*
     * Step 6:
     * Finds the minimum uncovered value, adds it to covered rows, and subtracts it from uncovered columns
     */
    protected static int executeStep6(int size, double[][] matrix, int[] rowCover, int[] colCover) {

        double min = findMinimumUncoveredValue(size, matrix, rowCover, colCover);

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (COVERED == rowCover[row]) {
                    matrix[row][col] += min;
                }
                if (UNCOVERED == colCover[col]) {
                    matrix[row][col] -= min;
                }
            }
        }

        return STEP_4;
    }

    /*
     * Step 7:
     * Constructs the resultant assignment matrix.
     */
    protected static int executeStep7(int size, int[][] zeroMatrix, int[][] assignment) {

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (STARRED == zeroMatrix[row][col]) {
                    assignment[row][ROW] = row;
                    assignment[row][COL] = col;
                }
            }
        }

        return DONE;
    }

    // (for initialization) copy input matrix
    private static double[][] copyMatrix(int size, double[][] input) {
        double[][] copy = new double[size][size];
        for (int row = 0; row < size; row++) {
            System.arraycopy(input[row], 0, copy[row], 0, size);
        }
        return copy;
    }

    // (for initialization) adjust matrix for max sum assignment problem
    private static void adjustMatrix(int size, double[][] matrix) {
        double max = findMaxValue(size, matrix);
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                matrix[row][col] = (max - matrix[row][col]);
            }
        }
    }

    // (for initialization) find the maximum value in the matrix
    private static double findMaxValue(int size, double[][] matrix) {
        double max = Integer.MIN_VALUE;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (max < matrix[row][col]) {
                    max = matrix[row][col];
                }
            }
        }
        return max;
    }

    // (for step 2 and step 5) reset row and column cover arrays
    private static void clearCovers(int size, int[] rowCover, int[] colCover) {
        for (int index = 0; index < size; index++) {
            rowCover[index] = UNCOVERED;
            colCover[index] = UNCOVERED;
        }
    }

    // (for step 4) find uncovered zero
    private static int[] findUncoveredZero(int size, double[][] matrix, int[] rowCover, int[] colCover) {

        int[] idx = new int[2];

        idx[ROW] = INVALID;
        idx[COL] = INVALID;

        int row = 0;
        boolean done = false;

        while (!done) {
            int col = 0;

            while (col < size) {
                if (ZERO == matrix[row][col] && UNCOVERED == rowCover[row] && UNCOVERED == colCover[col]) {
                    idx[ROW] = row;
                    idx[COL] = col;
                    done = true;
                }
                col++;
            }

            if (++row >= size) {
                done = true;
            }
        }

        return idx;
    }

    // (for step 5) find the row index for a starred zero in a given column
    private static int findRowForStarredZeroInColumn(int size, int col, int[][] zeroMatrix) {
        for (int row = 0; row < size; row++) {
            if (STARRED == zeroMatrix[row][col]) {
                return row;
            }
        }
        return INVALID;
    }

    // (for step 5) find the column index for a primed zero in a given row
    private static int findColumnForPrimedZeroInRow(int size, int row, int[][] zeroMatrix) {
        for (int col = 0; col < size; col++) {
            if (PRIMED == zeroMatrix[row][col]) {
                return col;
            }
        }
        return INVALID;
    }

    // (for step 5) mark starred zeros as cleared and mark primed zeros as starred for each covering line row/column
    private static void flipZeroMatrixValues(int size, int count, int[][] zeroMatrix, int[][] line) {
        for (int i = 0; i <= count; i++) {
            int row = line[i][ROW];
            int col = line[i][COL];
            if (STARRED == zeroMatrix[row][col]) {
                zeroMatrix[row][col] = CLEARED;
            } else {
                zeroMatrix[row][col] = STARRED;
            }
        }
    }

    // (for step 5) clear primed zeros
    private static void clearPrimedZeros(int size, int[][] zeroMatrix) {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (PRIMED == zeroMatrix[row][col]) {
                    zeroMatrix[row][col] = CLEARED;
                }
            }
        }
    } 

    // (for step 6) find the minimum uncovered value
    private static double findMinimumUncoveredValue(int size, double[][] matrix, int[] rowCover, int[] colCover) {
        double min = Integer.MAX_VALUE;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (UNCOVERED == rowCover[row] && UNCOVERED == colCover[col]) {
                    if (min > matrix[row][col]) {
                        min = matrix[row][col];
                    }
                }
            }
        }
        return min;
    }

    /* 
     * Simple test program
     */
    public static void main(String[] args) {

        int size = 3;

        double[][] matrix = new double[size][size];

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                matrix[row][col] = (double)(row+1) * (double)(col+1);
            }
        }

        System.out.println("Matrix:");
        for (int row = 0; row < size; row++) {
            System.out.println(Arrays.toString(matrix[row]));
        }
        System.out.println();

        int[][] result = applyAlgorithm(matrix, false);

        System.out.println("Assignment:");
        for (int row = 0; row < size; row++) {
            System.out.println(Arrays.toString(result[row]));
        }
        System.out.println();

        double sum = 0.0;
        for (int index = 0; index < size; index++) {
            int row = result[index][ROW];
            int col = result[index][COL];
            sum += matrix[row][col];
        }

        System.out.println("Min Sum:");
        System.out.println(sum);

        result = applyAlgorithm(matrix, true);

        System.out.println("Assignment:");
        for (int row = 0; row < size; row++) {
            System.out.println(Arrays.toString(result[row]));
        }
        System.out.println();

        sum = 0.0;
        for (int index = 0; index < size; index++) {
            int row = result[index][ROW];
            int col = result[index][COL];
            sum += matrix[row][col];
        }

        System.out.println("Max Sum:");
        System.out.println(sum);
    }

}
