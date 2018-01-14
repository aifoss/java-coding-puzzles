package particle.movement.test.unit;

import particle.movement.main.PositionType;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by sofia on 9/1/15.
 */
public class PositionTypeUnitTest {

    @Test
    public void testGetCodeEmpty() {
        char expected = '.';
        char actual = PositionType.EMPTY.getCode();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetCodeLeft() {
        char expected = 'L';
        char actual = PositionType.LEFT.getCode();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetCodeRight() {
        char expected = 'R';
        char actual = PositionType.RIGHT.getCode();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetCodeBoth() {
        char expected = 'B';
        char actual = PositionType.BOTH.getCode();
        Assert.assertEquals(expected, actual);
    }

}
