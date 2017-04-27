package city.connections;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by sofia on 4/27/17.
 */

/**
 * Program to read from input file containing info on connections between cities
 * and to decide whether or not whether is a path between 2 given cities.
 */
public class Connected {

    private static final String DELIMITER = ", ";
    private static final String YES = "yes";
    private static final String NO = "no";
    private static final String DONE = "done";


    /**
     * Main program
     *
     * @param args
     */
    public static void main(String[] args) {
        if (args.length != 1) {
            printUsageMessage();
            System.exit(1);
        }

        Graph graph = null;
        try {
            graph = buildGraph(args[0]);
        } catch (IOException e) {
            printErrorMessage(e.getMessage());
            System.exit(1);
        }

        checkConnectionBetweenCities(graph);

        printExitMessage();
    }

    /**
     * Method to build a graph consisting of city nodes and edges between cities.
     *
     * @param fileName name of the input file containing connection info
     * @return {@link Graph} object representing the constructed graph
     * @throws IOException
     */
    protected static Graph buildGraph(String fileName) throws IOException {
        Graph graph = new Graph();

        Scanner scanner = new Scanner(new File(fileName));

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();

            String[] cityNames = line.split(DELIMITER);
            if (cityNames.length != 2) {
                continue;
            }

            graph.addEdge(cityNames[0], cityNames[1]);
        }

        scanner.close();

        return graph;
    }

    /**
     * Method to interactively get input city names and check if there is a connection between the two cities.
     *
     * @param graph {@link Graph} object constructed from the input file
     */
    protected static void checkConnectionBetweenCities(Graph graph) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine().trim();
            if (input.equals(DONE)) {
                break;
            }

            String[] cityNames = input.split(DELIMITER);
            if (cityNames.length != 2) {
                printInputFormatMessage();
                break;
            }

            boolean connected = graph.containsPathBetween(cityNames[0], cityNames[1]);

            System.out.println(connected ? YES : NO);
        }

        scanner.close();
    }

    private static void printUsageMessage() {
        System.out.println("Usage: java Connected <filename>");
        System.out.println("Example: java Connected ../cities.txt");
    }

    private static void printErrorMessage(String errorMessage) {
        System.out.println("Problem reading from input file: "+errorMessage);
    }

    private static void printInputFormatMessage() {
        System.out.println("Input Format: <cityname1>, <cityname2>");
        System.out.println("Example: New York, Boston");
    }

    private static void printExitMessage() {
        System.out.println("Exiting the program");
    }

}
