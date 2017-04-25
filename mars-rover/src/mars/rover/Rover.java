package mars.rover;

/**
 * Created by sofia on 4/25/17.
 */

/**
 * Class representing rover.
 */
public class Rover {

    private State state;

    public Rover(State state) {
        this.state = state;
    }

    public void applyMoves(String moves, Grid grid, boolean debugMode) {
        if (debugMode) {
            System.out.println("Before moves: " + state);
        }

        for (char move : moves.toCharArray()) {
            State newState = state.applyMove(Move.valueOf(String.valueOf(move)), grid);
            setState(newState);

            if (debugMode) {
                System.out.println(newState);
            }
        }

        if (debugMode) {
            System.out.println("After moves: " + state);
            System.out.println();
        }
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return state.toString();
    }

}
