package mars.rover;

/**
 * Created by sofia on 4/25/17.
 */

/**
 * Class representing rover position on the grid.
 */
public class Position {

    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position moveForward(Orientation orientation, Grid grid) {
        int newX = x;
        int newY = y;

        switch(orientation) {
            case N: newY++; break;
            case S: newY--; break;
            case W: newX--; break;
            case E: newX++; break;
            default: break;
        }

        if (!grid.isValidPosition(newX, newY)) {
            return new Position(x, y);
        }

        return new Position(newX, newY);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return x + " " + y + " ";
    }

}
