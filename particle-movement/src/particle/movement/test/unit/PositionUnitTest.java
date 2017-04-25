package particle.movement.test.unit;

import particle.movement.main.Position;
import particle.movement.main.PositionType;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by sofia on 4/25/17.
 */
public class PositionUnitTest {

    @Test
    public void testGetTypeEmpty() {
        Position position = new Position(PositionType.EMPTY);
        PositionType expected = PositionType.EMPTY;
        PositionType actual = position.getType();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetTypeLeft() {
        Position position = new Position(PositionType.LEFT);
        PositionType expected = PositionType.LEFT;
        PositionType actual = position.getType();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetTypeRight() {
        Position position = new Position(PositionType.RIGHT);
        PositionType expected = PositionType.RIGHT;
        PositionType actual = position.getType();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetTypeBoth() {
        Position position = new Position(PositionType.BOTH);
        PositionType expected = PositionType.BOTH;
        PositionType actual = position.getType();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testToStringEmpty() {
        Position position = new Position(PositionType.EMPTY);
        String expected = ".";
        String actual = position.toString();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testToStringLeft() {
        Position position = new Position(PositionType.LEFT);
        String expected = "X";
        String actual = position.toString();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testToStringRight() {
        Position position = new Position(PositionType.RIGHT);
        String expected = "X";
        String actual = position.toString();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testToStringBoth() {
        Position position = new Position(PositionType.BOTH);
        String expected = "X";
        String actual = position.toString();
        Assert.assertEquals(expected, actual);
    }

}
