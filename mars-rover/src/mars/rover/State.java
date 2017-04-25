package mars.rover;

/**
 * Created by sofia on 4/25/17.
 */

/**
 * Class representing rover state.
 */
public class State {

    private Position position;
    private Orientation orientation;

    public State() {}

    public State(Position position, Orientation orientation) {
        this.position = position;
        this.orientation = orientation;
    }

    public State applyMove(Move move, Grid grid) {
        State newState = new State();
        newState.setPosition(position);
        newState.setOrientation(orientation);

        switch(move) {
            case L: newState.setOrientation(orientation.rotateLeft()); break;
            case R: newState.setOrientation(orientation.rotateRight()); break;
            case M: newState.setPosition(position.moveForward(orientation, grid)); break;
        }

        return newState;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    @Override
    public String toString() {
        return position.toString() + orientation;
    }

}
