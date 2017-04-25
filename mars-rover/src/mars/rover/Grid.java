package mars.rover;

/**
 * Created by sofia on 4/25/17.
 */

/**
 * Class representing grid for the rover game.
 */
public class Grid {

    private Position bottomLeft;
    private Position topRight;

    public Grid() {}

    public Grid(int x, int y) {
        bottomLeft = new Position(0, 0);
        topRight = new Position(x, y);
    }

    public boolean isValidPosition(int x, int y) {
        if (x < bottomLeft.getX() || x > topRight.getX()) return false;
        if (y < bottomLeft.getY() || y > topRight.getY()) return false;
        return true;
    }

    public Position getBottomLeft() {
        return bottomLeft;
    }

    public void setBottomLeft(Position bottomLeft) {
        this.bottomLeft = bottomLeft;
    }

    public Position getTopRight() {
        return topRight;
    }

    public void setTopRight(Position topRight) {
        this.topRight = topRight;
    }

}
