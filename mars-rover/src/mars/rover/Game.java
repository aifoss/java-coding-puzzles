package mars.rover;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sofia on 4/25/17.
 */

/**
 * Class representing rover game.
 */
public class Game {

    private Grid grid;
    private List<Rover> rovers;
    private List<String> movements;
    private boolean debugMode;

    public Game() {
        rovers = new ArrayList<>();
        movements = new ArrayList<>();
        debugMode = false;
    }

    public void play() {
        for (int i = 0; i < rovers.size(); i++) {
            Rover rover = rovers.get(i);
            String moves = movements.get(i);
            rover.applyMoves(moves, grid, debugMode);
            System.out.println(rover);

            if (debugMode) {
                System.out.println();
            }
        }
    }

    public Grid getGrid() {
        return grid;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public List<Rover> getRovers() {
        return rovers;
    }

    public void setRovers(List<Rover> rovers) {
        this.rovers = rovers;
    }

    public List<String> getMovements() {
        return movements;
    }

    public void setMovements(List<String> movements) {
        this.movements = movements;
    }

    public boolean isDebugMode() {
        return debugMode;
    }

    public void setDebugMode(boolean debugMode) {
        this.debugMode = debugMode;
    }

}
