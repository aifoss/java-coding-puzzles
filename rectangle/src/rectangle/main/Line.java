package rectangle.main;

/**
 * Created by sofia on 8/15/15.
 */

/**
 * Class to represent Line object.
 *
 * Line is modeled with two endpoints of Point type.
 * For the purpose of use in modeling Rectangle object, only vertical and horizontal lines are modeled.
 */
public class Line {

    private Point p1;   // leftmost or bottommost point
    private Point p2;   // rightmost or topmost point

    public Line(Point p1, Point p2) {
        if (null == p1 || null == p2) throw new IllegalArgumentException("Null point(s)");
        if (!Point.verticallyAligned(p1, p2) && !Point.horizontallyAligned(p1, p2)) {
            throw new IllegalArgumentException("Line should be either vertical or horizontal");
        }
        this.p1 = p1;
        this.p2 = p2;
    }

    public Point getP1() {
        return p1;
    }

    public Point getP2() {
        return p2;
    }

    public int getX() {
        return isVertical() ? p1.getX() : Integer.MIN_VALUE;
    }

    public int getY() {
        return isHorizontal() ? p1.getY() : Integer.MIN_VALUE;
    }

    /**
     * Checks if this line is a vertical line.
     */
    public boolean isVertical() {
        return Point.verticallyAligned(p1, p2);
    }

    /**
     * Checks if this line is a horizontal line.
     */
    public boolean isHorizontal() {
        return Point.horizontallyAligned(p1, p2);
    }

    /**
     * Returns length of this line.
     */
    public int length() {
        return isVertical() ? Math.abs(p2.getY() - p1.getY()) : Math.abs(p2.getX() - p1.getX());
    }

    /**
     * Checks if this line overlaps with another line.
     */
    public boolean overlaps(Line other) {
        if (this.isVertical() && other.isVertical()) {
            if (this.getX() == other.getX()) {
                return (this.p1.getY() <= other.p1.getY() && this.p2.getY() >= other.p1.getY()) ||
                        (this.p1.getY() >= other.p1.getY() && this.p1.getY() <= other.p2.getY());
            } else {
                return false;
            }
        } else if (this.isHorizontal() && other.isHorizontal()) {
            if (this.getY() == other.getY()) {
                return (this.p1.getX() <= other.p1.getX() && this.p2.getX() >= other.p1.getX()) ||
                        (this.p1.getX() >= other.p1.getX() && this.p1.getX() <= other.p2.getX());
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Checks if this line contains another line.
     */
    public boolean contains(Line other) {
        if (this.isVertical() && other.isVertical()) {
            if (this.getX() == other.getX()) {
                return this.p1.getY() <= other.p1.getY() && this.p2.getY() >= other.p2.getY();
            } else {
                return false;
            }
        } else if (this.isHorizontal() && other.isHorizontal()) {
            if (this.getX() == other.getX()) {
                return this.p1.getX() <= other.p1.getX() && this.p2.getX() >= other.p2.getX();
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Checks if this line contains the specified point.
     */
    public boolean containsPoint(Point p) {
        if (isVertical()) {
            for (int i = p1.getY(); i <= p2.getY(); i++) {
                if (p.equals(new Point(getX(), i))) return true;
            }
            return false;
        } else if (isHorizontal()) {
            for (int i = p1.getX(); i <= p2.getX(); i++) {
                if (p.equals(new Point(i, getY()))) return true;
            }
            return false;
        } else {
            return false;
        }
    }

    /**
     * Checks if this line cuts (intersects) another line orthogonally.
     */
    public boolean cuts(Line other) {
        if (this.isVertical() && other.isHorizontal()) {
            for (int i = other.p1.getX(); i <= other.p2.getX(); i++) {
                if (this.containsPoint(new Point(i, other.getY()))) return true;
            }
            return false;
        } else if (this.isHorizontal() && other.isVertical()) {
            for (int i = other.p1.getY(); i <= other.p2.getY(); i++) {
                if (this.containsPoint(new Point(other.getX(), i))) return true;
            }
            return false;
        } else {
            return false;
        }
    }

    /**
     * Returns point of cut.
     */
    public Point getCutPoint(Line other) {
        if (this.isVertical() && other.isHorizontal()) {
            for (int i = other.p1.getX(); i <= other.p2.getX(); i++) {
                if (this.containsPoint(new Point(i, other.getY()))) return new Point(i, other.getY());
            }
            return null;
        } else if (this.isHorizontal() && other.isVertical()) {
            for (int i = other.p1.getY(); i <= other.p2.getY(); i++) {
                if (this.containsPoint(new Point(other.getX(), i))) return new Point(other.getX(), i);
            }
            return null;
        } else {
            return null;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (null == o) return false;
        if (o == this) return true;
        if (o.getClass() != this.getClass()) return false;
        Line other = (Line) o;
        return this.p1.equals(other.p1) && this.p2.equals(other.p2);
    }

    @Override
    public int hashCode() {
        return p1.hashCode() + p2.hashCode();
    }

    @Override
    public String toString() {
        return "[" + p1 + "-" + p2 + "]";
    }

}
