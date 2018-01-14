package rectangle.test.functional;

import rectangle.main.Point;
import rectangle.main.Rectangle;

import java.util.Set;

/**
 * Created by sofia on 8/15/15.
 */
public class RectangleFunctionalTest {

    public static void main(String[] args) {
        Rectangle r1 = new Rectangle(0, 7, 5, 0);
        Rectangle r2 = new Rectangle(3, 5, 3, -2);

        System.out.println("Rectangle 1:");
        System.out.println(r1);
        System.out.println("Rectangle 2:");
        System.out.println(r2);

        System.out.println("Left side of R1: " + r1.getLeftSide());
        System.out.println("Top side of R1: " + r1.getTopSide());
        System.out.println("Right side of R2: " + r2.getRightSide());
        System.out.println("Bottom side of R2: " + r2.getBottomSide());
        System.out.println();

        System.out.println("Width of R1 = " + r1.width());
        System.out.println("Height of R2 = " + r2.height());
        System.out.println();

        boolean answer;

        answer = r1.intersects(r2);
        System.out.println("R1 intersects R2 (true)? " + answer);
        answer = r2.intersects(r1);
        System.out.println("R2 intersects R1 (true)? " + answer);
        answer = Rectangle.intersect(r1, r2);
        System.out.println("R1 and R2 intersect (true)? " + answer);
        System.out.println();

        Rectangle intersect = r1.getIntersectingRectangle(r2);
        System.out.println("Intersecting rectangle between R1 and R2:");
        System.out.println(intersect);
        intersect = Rectangle.getIntersectingRectangle(r2, r1);
        System.out.println("Intersecting rectangle between R2 and R1:");
        System.out.println(intersect);

        Set<Point> points = r1.getPointsOfIntersection(r2);
        System.out.println("Points of intersection between R1 and R2:");
        System.out.println(points.toString());
        System.out.println();

        r1 = new Rectangle(0, 10, 5, -5);
        r2 = new Rectangle(3, 7, 2, -2);

        System.out.println("Rectangle 1:");
        System.out.println(r1);
        System.out.println("Rectangle 2:");
        System.out.println(r2);

        answer = r1.contains(r2);
        System.out.println("R1 contains R2 (true)? " + answer);
        answer = r2.contains(r1);
        System.out.println("R2 contains R1 (false)? " + answer);
        System.out.println();

        r1 = new Rectangle(0, 10, 5, 0);
        r2 = new Rectangle(2, 7, 0, -5);

        System.out.println("Rectangle 1:");
        System.out.println(r1);
        System.out.println("Rectangle 2:");
        System.out.println(r2);

        answer = r1.adjacentTo(r2);
        System.out.println("R1 adjacent to R2 (true)? " + answer);
        System.out.println();

        r1 = new Rectangle(0, 10, 5, 0);
        r2 = new Rectangle(2, 7, 3, 1);

        System.out.println("Rectangle 1:");
        System.out.println(r1);
        System.out.println("Rectangle 2:");
        System.out.println(r2);

        answer = r1.adjacentTo(r2);
        System.out.println("R1 adjacent to R2 (false)? " + answer);
    }

}
