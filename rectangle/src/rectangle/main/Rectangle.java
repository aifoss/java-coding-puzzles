package rectangle.main;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by sofia on 8/15/15.
 */

/**
 * Class to represent Rectangle object.
 */
public class Rectangle {

    private int leftX;      // leftmost x-coordinate for horizontal sides
    private int rightX;     // rightmost x-coordinate for horizontal sides
    private int topY;       // topmost y-coordinate for vertical sides
    private int bottomY;    // bottommost y-coordinate for vertical sides

    public Rectangle(int leftX, int rightX, int topY, int bottomY) {
        this.leftX = leftX;
        this.rightX = rightX;
        this.topY = topY;
        this.bottomY = bottomY;
    }

    public int getLeftX() {
        return leftX;
    }

    public int getRightX() {
        return rightX;
    }

    public int getTopY() {
        return topY;
    }

    public int getBottomY() {
        return bottomY;
    }

    public Line getLeftSide() {
        return new Line(new Point(leftX, bottomY), new Point(leftX, topY));
    }

    public Line getRightSide() {
        return new Line(new Point(rightX, bottomY), new Point(rightX, topY));
    }

    public Line getTopSide() {
        return new Line(new Point(leftX, topY), new Point(rightX, topY));
    }

    public Line getBottomSide() {
        return new Line(new Point(leftX, bottomY), new Point(rightX, bottomY));
    }

    public int height() {
        return getTopY() - getBottomY();
    }

    public int width() {
        return getRightX() - getLeftX();
    }

    /**
     * Checks if two rectangles intersect.
     */
    public static boolean intersect(Rectangle r1, Rectangle r2) {
        return r1.intersects(r2);
    }

    /**
     * Checks if this rectangle intersects another rectangle.
     */
    public boolean intersects(Rectangle other) {
        return (this.getLeftX() <= other.getRightX() &&
                this.getRightX() >= other.getLeftX() &&
                this.getTopY() >= other.getBottomY() &&
                this.getBottomY() <= other.getTopY());
    }

    /**
     * Checks if this rectangle contains another rectangle.
     */
    public boolean contains(Rectangle other) {
        return this.getLeftX() < other.getLeftX() &&
                this.getRightX() > other.getRightX() &&
                this.getTopY() > other.getTopY() &&
                this.getBottomY() < other.getBottomY();
    }

    /**
     * Checks if two rectangles are adjacent to each other.
     */
    public static boolean adjacent(Rectangle r1, Rectangle r2) {
        return r1.adjacentTo(r2);
    }

    /**
     * Checks if this rectangle is adjacent to another rectangle.
     */
    public boolean adjacentTo(Rectangle other) {
        return this.getLeftSide().overlaps(other.getRightSide()) ||
                this.getRightSide().overlaps(other.getLeftSide()) ||
                this.getTopSide().overlaps(other.getBottomSide()) ||
                this.getBottomSide().overlaps(other.getTopSide());
    }

    /**
     * Returns intersecting rectangle between two rectangles.
     */
    public static Rectangle getIntersectingRectangle(Rectangle r1, Rectangle r2) {
        return r1.getIntersectingRectangle(r2);
    }

    /**
     * Returns intersecting rectangle between this rectangle and another rectangle.
     */
    public Rectangle getIntersectingRectangle(Rectangle other) {
        if (!intersects(other)) return null;
        int leftX = Math.max(this.getLeftX(), other.getLeftX());
        int rightX = Math.min(this.getRightX(), other.getRightX());
        int topY = Math.min(this.getTopY(), other.getTopY());
        int bottomY = Math.max(this.getBottomY(), other.getBottomY());
        return new Rectangle(leftX, rightX, topY, bottomY);
    }

    /**
     * Returns points of intersection between two rectangles.
     */
    public static Set<Point> getPointsOfIntersection(Rectangle r1, Rectangle r2) {
        return r1.getPointsOfIntersection(r2);
    }

    /**
     * Returns points of intersection between this rectangle and another rectangle.
     */
    public Set<Point> getPointsOfIntersection(Rectangle other) {
        Set<Point> result = new HashSet<>();
        if (this.getLeftSide().cuts(other.getTopSide())) {
            result.add(this.getLeftSide().getCutPoint(other.getTopSide()));
        }
        if (this.getLeftSide().cuts(other.getBottomSide())) {
            result.add(this.getLeftSide().getCutPoint(other.getBottomSide()));
        }
        if (this.getRightSide().cuts(other.getTopSide())) {
            result.add(this.getRightSide().getCutPoint(other.getTopSide()));
        }
        if (this.getRightSide().cuts(other.getBottomSide())) {
            result.add(this.getRightSide().getCutPoint(other.getBottomSide()));
        }
        if (this.getTopSide().cuts(other.getLeftSide())) {
            result.add(this.getTopSide().getCutPoint(other.getLeftSide()));
        }
        if (this.getTopSide().cuts(other.getRightSide())) {
            result.add(this.getTopSide().getCutPoint(other.getRightSide()));
        }
        if (this.getBottomSide().cuts(other.getLeftSide())) {
            result.add(this.getBottomSide().getCutPoint(other.getLeftSide()));
        }
        if (this.getBottomSide().cuts(other.getRightSide())) {
            result.add(this.getBottomSide().getCutPoint(other.getRightSide()));
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (null == o) return false;
        if (o == this) return true;
        if (o.getClass() != this.getClass()) return false;
        Rectangle other = (Rectangle) o;
        return this.leftX == other.leftX &&
                this.rightX == other.rightX &&
                this.topY == other.topY &&
                this.bottomY == other.bottomY;
    }

    @Override
    public int hashCode() {
        return ((Integer)leftX).hashCode() + ((Integer)rightX).hashCode() + ((Integer)topY).hashCode() + ((Integer)bottomY).hashCode();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("leftX: ").append(leftX).append(System.getProperty("line.separator"));
        sb.append("rightX: ").append(rightX).append(System.getProperty("line.separator"));
        sb.append("topY: ").append(topY).append(System.getProperty("line.separator"));
        sb.append("bottomY: ").append(bottomY).append(System.getProperty("line.separator"));
        return sb.toString();
    }

}
