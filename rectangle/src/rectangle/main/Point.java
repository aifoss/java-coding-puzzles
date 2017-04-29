package rectangle.main;

/**
 * Created by sofia on 4/28/17.
 */

/**
 * Class to represent Point object on 2D plane.
 *
 * Point is modeled with x-coordinate and y-coordinate.
 * For simplicity, both coordinates are typed as +/-/0 integers.
 */
public class Point {

    private int x;  // x-coordinate
    private int y;  // y-coordinate

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /**
     * Checks if two points are vertically aligned.
     */
    public static boolean verticallyAligned(Point p1, Point p2) {
        return p1.x == p2.x;
    }

    /**
     * Checks if two points are horizontally aligned.
     */
    public static boolean horizontallyAligned(Point p1, Point p2) {
        return p1.y == p2.y;
    }

    @Override
    public boolean equals(Object o) {
        if (null == o) return false;
        if (o == this) return true;
        if (o.getClass() != this.getClass()) return false;
        Point other = (Point) o;
        return this.x == other.x && this.y == other.y;
    }

    @Override
    public int hashCode() {
        return ((Integer)x).hashCode() + ((Integer)y).hashCode();
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

}
