package testing.functional;

import main.SudokuValidator;
import main.SudokuXmlParser;

/**
 * Functional test for SudokuValidator class.
 *
 * Created by sofia on 10/30/15.
 */
public class SudokuValidatorFunctionalTest {

    private static final String ORIGINAL = "4-coding-tests//liveintent//test//sudoku_3x3_original.xml";
    private static final String MODIFIED = "4-coding-tests//liveintent//test//sudoku_3x3_modified.xml";


    public static void main(String[] args) {
        try {
            SudokuXmlParser sudokuXmlParser = new SudokuXmlParser();
            int[][] board = sudokuXmlParser.extractSudokuBoardInfo(ORIGINAL);

            System.out.println("Board:");
            for (int i = 0; i < SudokuValidator.SIZE; i++) {
                for (int j = 0; j < SudokuValidator.SIZE; j++) {
                    System.out.print("[" + board[i][j] + "]");
                }
                System.out.println();
            }
            System.out.println();

            SudokuValidator sudokuValidator = new SudokuValidator(board);
            boolean isValidSudoku = sudokuValidator.validateSudoku();

            System.out.println("Validation result: " + isValidSudoku);
            System.out.println();

            System.out.println("----------------------------------------");

            board = sudokuXmlParser.extractSudokuBoardInfo(MODIFIED);

            System.out.println("Board:");
            for (int i = 0; i < SudokuValidator.SIZE; i++) {
                for (int j = 0; j < SudokuValidator.SIZE; j++) {
                    System.out.print("[" + board[i][j] + "]");
                }
                System.out.println();
            }
            System.out.println();

            sudokuValidator = new SudokuValidator(board);
            isValidSudoku = sudokuValidator.validateSudoku();

            System.out.println("Validation result: " + isValidSudoku);
            System.out.println();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
