package problem1;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

import static java.lang.String.format;

/**
 * Created by sofia on 5/4/18.
 */

/**
 * Solution for problem 1.
 */

/**
 * Assumptions:
 * The input file conforms to the specified format and contains each valid CUSIP line followed by one or more valid price lines.
 * (No checks performed on presence/validity of CUSIP or price values.)
 */
public class ClosingPricePrinter {

    private static final String INPUT_FILE = "../problem1_input.txt";
    private static final String ENCODING = "UTF-8";
    private static final String PRICE_REGEX = "\\d+\\.\\d{3}";


    public static void main(String[] args) { // working directory: src
        readPricesAndDisplayLatest(INPUT_FILE, ENCODING);
    }

    protected static void readPricesAndDisplayLatest(String inputFile, String encoding) {
        String line = null;
        Double price = null;

        try (FileInputStream fis = new FileInputStream(new File(inputFile));
             Scanner scanner = new Scanner(fis, encoding)) {

            while (scanner.hasNextLine()) {
                if (line == null) {
                    line = scanner.nextLine().trim();
                }

                String cusip = line;

                while (scanner.hasNextLine()) {
                    line = scanner.nextLine().trim();

                    if (!isPriceLine(line)) {
                        break;
                    }

                    price = Double.parseDouble(line);
                }

                System.out.println(format("%s: %.3f", cusip, price));
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static boolean isPriceLine(String line) {
        return line.matches(PRICE_REGEX);
    }

}
