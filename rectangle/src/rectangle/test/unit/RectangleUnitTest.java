package rectangle.test.unit;

import org.junit.Assert;
import org.junit.Test;
import rectangle.main.Point;
import rectangle.main.Rectangle;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by sofia on 4/28/17.
 */
public class RectangleUnitTest {

    @Test
    public void testIntersectPositiveCase() {
        Rectangle r1 = new Rectangle(0, 5, 5, 0);
        Rectangle r2 = new Rectangle(3, 7, 3, -3);
        boolean answer = Rectangle.intersect(r1, r2);
        Assert.assertTrue(answer);
    }

    @Test
    public void testIntersectNegativeCase() {
        Rectangle r1 = new Rectangle(0, 4, 0, 5);
        Rectangle r2 = new Rectangle(5, 10, 0, 5);
        boolean answer = Rectangle.intersect(r1, r2);
        Assert.assertFalse(answer);
    }

    @Test
    public void testIntersectsPositiveCase1() {
        Rectangle r1 = new Rectangle(3, 7, 3, -3);
        Rectangle r2 = new Rectangle(0, 5, 5, 0);
        boolean answer = Rectangle.intersect(r1, r2);
        Assert.assertTrue(answer);
    }

    @Test
    public void testIntersectsPositiveCase2() {
        Rectangle r1 = new Rectangle(0, 7, 5, 0);
        Rectangle r2 = new Rectangle(3, 10, 3, -2);
        boolean answer = Rectangle.intersect(r1, r2);
        Assert.assertTrue(answer);
    }

    @Test
    public void testIntersectsNegativeCase() {
        Rectangle r1 = new Rectangle(0, 4, 0, 5);
        Rectangle r2 = new Rectangle(5, 10, 0, 5);
        boolean answer = Rectangle.intersect(r1, r2);
        Assert.assertFalse(answer);
    }

    @Test
    public void testContainsPositiveCase() {
        Rectangle r1 = new Rectangle(0, 10, 10, 0);
        Rectangle r2 = new Rectangle(3, 7, 7, 3);
        boolean answer = r1.contains(r2);
        Assert.assertTrue(answer);
    }

    @Test
    public void testContainsNegativeCase1() {
        Rectangle r1 = new Rectangle(0, 10, 10, 0);
        Rectangle r2 = new Rectangle(12, 15, 3, 7);
        boolean answer = r1.contains(r2);
        Assert.assertFalse(answer);
    }

    @Test
    public void testContainsNegativeCase2() {
        Rectangle r1 = new Rectangle(0, 10, 10, 0);
        Rectangle r2 = new Rectangle(7, 15, 7, 3);
        boolean answer = r1.contains(r2);
        Assert.assertFalse(answer);
    }

    @Test
    public void testAdjacentPositiveCase1() {
        Rectangle r1 = new Rectangle(0, 10, 10, 0);
        Rectangle r2 = new Rectangle(10, 15, 7, 3);
        boolean answer = Rectangle.adjacent(r1, r2);
        Assert.assertTrue(answer);
    }

    @Test
    public void testAdjacentPositiveCase2() {
        Rectangle r1 = new Rectangle(0, 10, 10, 0);
        Rectangle r2 = new Rectangle(10, 15, 10, 0);
        boolean answer = Rectangle.adjacent(r1, r2);
        Assert.assertTrue(answer);
    }

    @Test
    public void testAdjacentNegativeCase() {
        Rectangle r1 = new Rectangle(3, 10, 5, 0);
        Rectangle r2 = new Rectangle(12, 15, 7, 3);
        boolean answer = Rectangle.adjacent(r1, r2);
        Assert.assertFalse(answer);
    }

    @Test
    public void testAdjacentToPositiveCase() {
        Rectangle r1 = new Rectangle(0, 10, 5, 0);
        Rectangle r2 = new Rectangle(2, 7, 0, -5);
        boolean answer = r1.adjacentTo(r2);
        Assert.assertTrue(answer);
    }

    @Test
    public void testAdjacentToNegativeCase() {
        Rectangle r1 = new Rectangle(0, 10, 5, 0);
        Rectangle r2 = new Rectangle(2, 7, 3, 1);
        boolean answer = r1.adjacentTo(r2);
        Assert.assertFalse(answer);
    }

    @Test
    public void testGetIntersectingRectangleNonNullCase1() {
        Rectangle r1 = new Rectangle(3, 7, 3, -3);
        Rectangle r2 = new Rectangle(0, 5, 5, 0);
        Rectangle expected = new Rectangle(3, 5, 3, 0);
        Rectangle actual = Rectangle.getIntersectingRectangle(r1, r2);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetIntersectingRectangleNonNullCase2() {
        Rectangle r1 = new Rectangle(3, 7, 3, -3);
        Rectangle r2 = new Rectangle(0, 5, 5, 0);
        Rectangle expected = new Rectangle(3, 5, 3, 0);
        Rectangle actual = r1.getIntersectingRectangle(r2);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetIntersectingRectangleNullCase() {
        Rectangle r1 = new Rectangle(0, 4, 0, 5);
        Rectangle r2 = new Rectangle(5, 10, 0, 5);
        Rectangle actual = r1.getIntersectingRectangle(r2);
        Assert.assertNull(actual);
    }

    @Test
    public void testGetPointsOfIntersectionNonEmptyCase1() {
        Rectangle r1 = new Rectangle(0, 5, 5, 0);
        Rectangle r2 = new Rectangle(3, 7, 3, -3);
        Set<Point> expected = new HashSet<>();
        expected.add(new Point(3,0));
        expected.add(new Point(5,3));
        Set<Point> actual = Rectangle.getPointsOfIntersection(r1, r2);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetPointsOfIntersectionNonEmptyCase2() {
        Rectangle r1 = new Rectangle(3, 7, 3, -3);
        Rectangle r2 = new Rectangle(0, 5, 5, 0);
        Set<Point> expected = new HashSet<>();
        expected.add(new Point(3,0));
        expected.add(new Point(5,3));
        Set<Point> actual = r1.getPointsOfIntersection(r2);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetPointsOfIntersectionEmptyCase() {
        Rectangle r1 = new Rectangle(2, 5, 5, 0);
        Rectangle r2 = new Rectangle(7, 10, 5, -5);
        Set<Point> expected = new HashSet<>();
        Set<Point> actual = r1.getPointsOfIntersection(r2);
        Assert.assertEquals(expected, actual);
    }

}
