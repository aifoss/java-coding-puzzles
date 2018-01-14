package testing.unit;

import main.SudokuValidator;
import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for SudokuValidator class.
 *
 * Created by sofia on 10/30/15.
 */
public class SudokuValidatorUnitTest {

    @Test
    public void testValidateSudoku_PositiveCase() {
        int[][] board = new int[SudokuValidator.SIZE][SudokuValidator.SIZE];
        for (int i = 0; i < SudokuValidator.SIZE; i++) {
            for (int j = 0; j < SudokuValidator.SIZE; j++) {
                board[i][j] = (i+j) % SudokuValidator.SIZE + 1;
            }
        }
        SudokuValidator sudokuValidator = new SudokuValidator(board);
        boolean result = sudokuValidator.validateSudoku();
        Assert.assertTrue(result);
    }

    @Test
    public void testValidateSudoku_NegativeCase() {
        int[][] board = new int[SudokuValidator.SIZE][SudokuValidator.SIZE];
        for (int i = 0; i < SudokuValidator.SIZE; i++) {
            for (int j = 0; j < SudokuValidator.SIZE; j++) {
                board[i][j] = (i+1)*j;
            }
        }
        SudokuValidator sudokuValidator = new SudokuValidator(board);
        boolean result = sudokuValidator.validateSudoku();
        Assert.assertFalse(result);
    }

}
