package mars.rover;

/**
 * Created by sofia on 4/25/17.
 */

/**
 * Enumeration of valid orientations for the rover on the grid.
 */
public enum Orientation {

    N,
    S,
    W,
    E;

    public Orientation rotateLeft() {
        switch(this) {
            case N: return W;
            case S: return E;
            case W: return S;
            case E: return N;
            default: return null;
        }
    }

    public Orientation rotateRight() {
        switch(this) {
            case N: return E;
            case S: return W;
            case W: return N;
            case E: return S;
            default: return null;
        }
    }

}
