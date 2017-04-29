package rectangle.test.unit;

import org.junit.Assert;
import org.junit.Test;
import rectangle.main.Point;

/**
 * Created by sofia on 4/28/17.
 */
public class PointUnitTest {

    @Test
    public void testVerticallyAlignedPositiveCase() {
        Point p1 = new Point(0,1);
        Point p2 = new Point(0,5);
        boolean answer = Point.verticallyAligned(p1, p2);
        Assert.assertTrue(answer);
    }

    @Test
    public void testVerticallyAlignedNegativeCase() {
        Point p1 = new Point(0,1);
        Point p2 = new Point(1,5);
        boolean answer = Point.verticallyAligned(p1, p2);
        Assert.assertFalse(answer);
    }

    @Test
    public void testHorizontallyAlignedPositiveCase() {
        Point p1 = new Point(1,0);
        Point p2 = new Point(5,0);
        boolean answer = Point.horizontallyAligned(p1, p2);
        Assert.assertTrue(answer);
    }

    @Test
    public void testHorizontallyAlignedNegativeCase() {
        Point p1 = new Point(1,0);
        Point p2 = new Point(5,1);
        boolean answer = Point.horizontallyAligned(p1, p2);
        Assert.assertFalse(answer);
    }

}
