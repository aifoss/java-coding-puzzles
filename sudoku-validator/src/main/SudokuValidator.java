package main;

/**
 * Class to validate a Sudoku board.
 *
 * Created by sofia on 10/30/15.
 */

public class SudokuValidator {

    public static final int SIZE = 3;   // size of Sudoku board (3X3)

    private int[][] board;  // Sudoku board

    public SudokuValidator(int[][] board) {
        this.board = board;
    }

    /**
     * Method to validate a Sudoku board.
     */
    public boolean validateSudoku() {
        System.out.println("Validating Sudoku grid");

        boolean[][] invalidPositions = new boolean[SIZE][SIZE];

        int invalidCount = 0;
        invalidCount = areRowsValid(board, invalidCount, invalidPositions);
        invalidCount = areColumnsValid(board, invalidCount, invalidPositions);

        if (invalidCount == 0) {
            System.out.println("Sudoku grid is valid");
        }

        System.out.println();

        return invalidCount == 0;
    }

    // auxiliary method to check rows for validity
    private int areRowsValid(int[][] board, int invalidCount, boolean[][] invalidPositions) {
        for (int row = 0; row < SIZE; row++) {
            boolean[] isPresent = new boolean[SIZE+1];

            for (int col = 0; col < SIZE; col++) {
                if (invalidPositions[row][col]) continue;
                int num = board[row][col];

                if (num <= 0 || num > SIZE || isPresent[num]) {
                    invalidCount++;
                    invalidPositions[row][col] = true;
                    if (invalidCount == 1) {
                        System.out.println("Found following invalid numbers:");
                    }
                    System.out.println(invalidCount + ". Row " + String.valueOf(row+1) + " Column " + String.valueOf(col+1) + " Value " + num);
                } else {
                    isPresent[num] = true;
                }
            }
        }
        return invalidCount;
    }

    // auxiliary method to check columns for validity
    private int areColumnsValid(int[][] board, int invalidCount, boolean[][] invalidPositions) {
        for (int col = 0; col < SIZE; col++) {
            boolean[] isPresent = new boolean[SIZE+1];

            for (int row = 0; row < SIZE; row++) {
                if (invalidPositions[row][col]) continue;
                int num = board[row][col];

                if (num <= 0 || num > SIZE || isPresent[num]) {
                    invalidCount++;
                    invalidPositions[row][col] = true;
                    if (invalidCount == 1) {
                        System.out.println("Found following invalid numbers:");
                    }
                    System.out.println(invalidCount + ". Row " + String.valueOf(row+1) + " Column " + String.valueOf(col+1) + " Value " + num);
                } else {
                    isPresent[num] = true;
                }
            }
        }
        return invalidCount;
    }

}