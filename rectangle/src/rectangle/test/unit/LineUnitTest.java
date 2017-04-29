package rectangle.test.unit;

import org.junit.Assert;
import org.junit.Test;
import rectangle.main.Line;
import rectangle.main.Point;

/**
 * Created by sofia on 4/28/17.
 */
public class LineUnitTest {

    @Test
    public void testIsVerticalPositiveCase() {
        Line line = new Line(new Point(0,1), new Point(0,5));
        boolean answer = line.isVertical();
        Assert.assertTrue(answer);
    }

    @Test
    public void testIsVerticalNegativeCase() {
        Line line = new Line(new Point(1,0), new Point(5,0));
        boolean answer = line.isVertical();
        Assert.assertFalse(answer);
    }

    @Test
    public void testIsHorizontalPositiveCase() {
        Line line = new Line(new Point(1,0), new Point(5,0));
        boolean answer = line.isHorizontal();
        Assert.assertTrue(answer);
    }

    @Test
    public void testIsHorizontalNegativeCase() {
        Line line = new Line(new Point(0,1), new Point(0,5));
        boolean answer = line.isHorizontal();
        Assert.assertFalse(answer);
    }

    @Test
    public void testOverlapsPositiveCase() {
        Line line1 = new Line(new Point(3,0), new Point(7,0));
        Line line2 = new Line(new Point(0,0), new Point(10,0));
        boolean answer = line1.overlaps(line2);
        Assert.assertTrue(answer);
    }

    @Test
    public void testOverlapsNegativeCase() {
        Line line1 = new Line(new Point(0,0), new Point(0,5));
        Line line2 = new Line(new Point(1,6), new Point(1,10));
        boolean answer = line1.overlaps(line2);
        Assert.assertFalse(answer);
    }

    @Test
    public void testContainsPositiveCase() {
        Line line1 = new Line(new Point(0,0), new Point(10,0));
        Line line2 = new Line(new Point(3,0), new Point(7,0));
        boolean answer = line1.contains(line2);
        Assert.assertTrue(answer);
    }

    @Test
    public void testContainsNegativeCase() {
        Line line1 = new Line(new Point(0,0), new Point(10,0));
        Line line2 = new Line(new Point(3,0), new Point(7,0));
        boolean answer = line2.contains(line1);
        Assert.assertFalse(answer);
    }

    @Test
    public void testContainsPointPositiveCase() {
        Line line = new Line(new Point(0,0), new Point(0,10));
        Point point = new Point(0,5);
        boolean answer = line.containsPoint(point);
        Assert.assertTrue(answer);
    }

    @Test
    public void testContainsPointNegativeCase() {
        Line line = new Line(new Point(0,0), new Point(0,10));
        Point point = new Point(5,0);
        boolean answer = line.containsPoint(point);
        Assert.assertFalse(answer);
    }

    @Test
    public void testCutsPositiveCase1() {
        Line line1 = new Line(new Point(0,0), new Point(10,0));
        Line line2 = new Line(new Point(5,-5), new Point(5,5));
        boolean answer = line1.cuts(line2);
        Assert.assertTrue(answer);
    }

    @Test
    public void testCutsPositiveCase2() {
        Line line1 = new Line(new Point(0,0), new Point(10,0));
        Line line2 = new Line(new Point(5,-5), new Point(5,5));
        boolean answer = line2.cuts(line1);
        Assert.assertTrue(answer);
    }

    @Test
    public void testCutsNegativeCase() {
        Line line1 = new Line(new Point(0,0), new Point(10,0));
        Line line2 = new Line(new Point(0,2), new Point(10,2));
        boolean answer = line1.cuts(line2);
        Assert.assertFalse(answer);
    }

    @Test
    public void testGetCutPointNonNullCase() {
        Line line1 = new Line(new Point(0,0), new Point(10,0));
        Line line2 = new Line(new Point(5,-5), new Point(5,5));
        Point expected = new Point(5,0);
        Point actual = line1.getCutPoint(line2);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetCutPointNullCase() {
        Line line1 = new Line(new Point(0,0), new Point(10,0));
        Line line2 = new Line(new Point(0,5), new Point(10,5));
        Point actual = line1.getCutPoint(line2);
        Assert.assertNull(actual);
    }

}
