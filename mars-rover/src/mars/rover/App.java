package mars.rover;

import java.util.Scanner;

/**
 * Created by sofia on 4/25/17.
 */

/**
 * Application demonstrating rover game play.
 */
public class App {

    public static void main(String[] args) {
        Game game = new Game();

        Scanner scanner = new Scanner(System.in);

        String debugModeLine = scanner.nextLine();
        boolean debugMode = Boolean.valueOf(debugModeLine);
        game.setDebugMode(debugMode);

        String gridLine = scanner.nextLine();
        String[] gridCoordinates = gridLine.split(" ");
        int topRightX = Integer.parseInt(gridCoordinates[0]);
        int topRightY = Integer.parseInt(gridCoordinates[1]);
        game.setGrid(new Grid(topRightX, topRightY));

        while (scanner.hasNextLine()) {
            String roverLine = scanner.nextLine();
            if (roverLine.equals("")) break;

            String[] roverFields = roverLine.split(" ");
            int initX = Integer.parseInt(roverFields[0]);
            int initY = Integer.parseInt(roverFields[1]);
            Position initPosition = new Position(initX, initY);
            Orientation initOrientation = Orientation.valueOf(roverFields[2]);
            State initState = new State(initPosition, initOrientation);
            Rover rover = new Rover(initState);
            game.getRovers().add(rover);

            String movementLine = scanner.nextLine();
            game.getMovements().add(movementLine);
        }

        scanner.close();

        game.play();
    }

}
