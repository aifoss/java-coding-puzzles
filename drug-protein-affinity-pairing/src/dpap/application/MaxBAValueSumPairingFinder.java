package dpap.application;

import dpap.algorithm.HungarianAlgorithm;
import dpap.datastructure.BATriple;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by sofia on 2/8/14.
 */

/**
 * Application to find protein-drug pairing assignment to maximize the binding affinity value sum.
 *
 * @author sofia
 */
public class MaxBAValueSumPairingFinder {

    private static final String REGEX_ALPHA = "[a-zA-Z]";
    private static final String REGEX_VOWEL = "[a|e|i|o|u]";

    private static final double STARTING_BA_VALUE_EVEN_FACTOR = 2.0;
    private static final double STARTING_BA_VALUE_ODD_FACTOR = 2.5;
    private static final double FINAL_BA_VALUE_INCREASE_FACTOR = 1.25; 

    public static void main(String[] args) {

        try {
            // mark execution start time
            final long startTime = System.currentTimeMillis();

            // input arguments specifying paths to protein and drug name list files
            String proteinNameFilePath = args[0];
            String drugNameFilePath = args[1];

            // hash maps to store <drug name, starting BA value> pairs 
            Map<String, Double> startingBaValueMapEven = new HashMap<String, Double>();
            Map<String, Double> startingBaValueMapOdd = new HashMap<String, Double>();

            // build protein name and drug name lists
            List<String> proteinNameList = buildProteinNameList(proteinNameFilePath);
            List<String> drugNameList = buildDrugNameList(
                    drugNameFilePath,
                    startingBaValueMapEven,
                    startingBaValueMapOdd);

            // number of protein names = number of drug names
            int matrixSize = proteinNameList.size();

            // build matrix containing BA values for each <protein, drug> pair
            double[][] baValueMatrix = buildBaValueMatrix(
                    matrixSize,
                    proteinNameList,
                    drugNameList,
                    startingBaValueMapEven,
                    startingBaValueMapOdd); 

            // obtain matrix representing max BA value sum pairing assignment by applying Hungarian algorithm 
            int[][] pairingResult = HungarianAlgorithm.applyAlgorithm(baValueMatrix, true);

            // build list of BA triples from the paring result
            List<BATriple> baTripleList = buildBaTripleList(
                    matrixSize,
                    pairingResult,
                    baValueMatrix,
                    proteinNameList,
                    drugNameList);

            // sort BA triple list
            Collections.sort(baTripleList);

            // compute max BA value sum resulting from the pairing assignment
            double maxBaValueSum = computeMaxBaValueSum(baTripleList);

            // print result
            printResult(baTripleList, maxBaValueSum);

            // mark execution end time
            final long endTime = System.currentTimeMillis();

            // print time taken to execute program
            printTime(startTime, endTime);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * Builds list of protein names by reading from data file.
     */
    protected static List<String> buildProteinNameList(String proteinNameFilePath) {

        List<String> proteinNameList = new ArrayList<String>();

        try {
            Scanner scanner = new Scanner(new File(proteinNameFilePath));
            while (scanner.hasNextLine()) {
                String proteinName = scanner.nextLine();
                proteinNameList.add(proteinName);
            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return proteinNameList;
    }

    /*
     * Builds list of drug names and populates starting BA value hash maps.
     */
    protected static List<String> buildDrugNameList(
            String drugNameFilePath,
            Map<String, Double> startingBaValueMapEven,
            Map<String, Double> startingBaValueMapOdd) {

        List<String> drugNameList = new ArrayList<String>();

        try {
            Scanner scanner = new Scanner(new File(drugNameFilePath));

            while (scanner.hasNextLine()) {
                String drugName = scanner.nextLine();
                drugNameList.add(drugName);

                int alphaCount = computePatternMatchingCharCount(drugName, REGEX_ALPHA);
                int vowelCount = computePatternMatchingCharCount(drugName.toLowerCase(), REGEX_VOWEL);
                int consonantCount = alphaCount - vowelCount;

                double startingBaValueEven = STARTING_BA_VALUE_EVEN_FACTOR * vowelCount;
                double startingBaValueOdd = STARTING_BA_VALUE_ODD_FACTOR * consonantCount;

                startingBaValueMapEven.put(drugName, startingBaValueEven);
                startingBaValueMapOdd.put(drugName, startingBaValueOdd);
            }

            scanner.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return drugNameList;
    }

    /*
     * Builds matrix containing pairwise BA values for each protein and drug.
     */
    protected static double[][] buildBaValueMatrix(
            int matrixSize,
            List<String> proteinNameList,
            List<String> drugNameList,
            Map<String, Double> startingBaValueMapEven,
            Map<String, Double> startingBaValueMapOdd) {

        double[][] baValueMatrix = new double[matrixSize][matrixSize]; 

        for (int i = 0; i < matrixSize; i++) {
            String proteinName = proteinNameList.get(i);
            int proteinNameLen = proteinName.length();

            for (int j = 0; j < matrixSize; j++) {
                String drugName = drugNameList.get(j);
                int drugNameLen = drugName.length();

                double startingBaValue = 0.0;
                double finalBaValue = 0.0;

                if ((proteinNameLen & 1) == 0) {
                    startingBaValue = startingBaValueMapEven.get(drugName);
                } else {
                    startingBaValue = startingBaValueMapOdd.get(drugName);
                }

                if (haveCommonFactors(proteinNameLen, drugNameLen)) {
                    finalBaValue = startingBaValue * FINAL_BA_VALUE_INCREASE_FACTOR;
                } else {
                    finalBaValue = startingBaValue;
                }

                baValueMatrix[i][j] = finalBaValue;
            }
        }

        return baValueMatrix;
    }

    /*
     * Builds list of <protein name, drug name, BA value> triples, based on the maximum sum pairing result.
     */
    protected static List<BATriple> buildBaTripleList(
            int matrixSize,
            int[][] pairingResult,
            double[][] baValueMatrix,
            List<String> proteinNameList,
            List<String> drugNameList) {

        List<BATriple> baTripleList = new ArrayList<BATriple>(matrixSize);

        for (int index = 0; index < matrixSize; index++) {
            int pIdx = pairingResult[index][0];
            int dIdx = pairingResult[index][1];

            String proteinName = proteinNameList.get(pIdx);
            String drugName = drugNameList.get(dIdx);
            double baValue = baValueMatrix[pIdx][dIdx];

            baTripleList.add(new BATriple(proteinName, drugName, baValue));
        }

        return baTripleList;
    }

    // compute the number of regex-matching chars in a given string 
    private static int computePatternMatchingCharCount(String str, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        int count = 0;
        while (matcher.find()) {
            count++;
        }
        return count;
    }

    // check if two integers have common factors (other than 1)
    private static boolean haveCommonFactors(int num1, int num2) {
        Set<Integer> factorSet1 = findFactors(num1);
        Set<Integer> factorSet2 = findFactors(num2);
        Set<Integer> intersection = new TreeSet<Integer>(factorSet1);
        intersection.retainAll(factorSet2);
        return (intersection.size() > 0);
    }

    // find factors for a given integer
    private static Set<Integer> findFactors(int n) {
        Set<Integer> factors = new TreeSet<Integer>();
        for (int i = 2; i <= n; i++) {
            if ((n % i) == 0) {
                factors.add(i);
            }
        }
        return factors;
    }

     // compute the maximum BA value sum obtained from the pairing result
    private static double computeMaxBaValueSum(List<BATriple> baTripleList) {
        double sum = 0.0;
        for (int i = 0; i < baTripleList.size(); i++) {
            sum += baTripleList.get(i).getBaValue();
        }
        return sum;
    }

    // print result
    private static void printResult(List<BATriple> baTripleList, double maxBaValueSum) {
        System.out.println();
        System.out.println("Result:");
        System.out.println();
        System.out.println("Protein-Drug Pairs:");
        System.out.println();
        for (int i = 0; i < baTripleList.size(); i++) {
            System.out.println(String.format("%03d", (i+1)) + ": " + baTripleList.get(i));
        }
        System.out.println();
        System.out.println("BA Value Sum:");
        System.out.println();
        System.out.println(maxBaValueSum);
    }

    // print execution time
    private static void printTime(long startTime, long endTime) {
        double durationInSec = (endTime - startTime) / 1000.0;
        System.out.println();
        System.out.println("Execution Time:");
        System.out.println();
        System.out.println(durationInSec + " secs");
    }

}
