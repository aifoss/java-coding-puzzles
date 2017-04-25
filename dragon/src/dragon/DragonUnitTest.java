package dragon;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by sofia on 4/25/17.
 */
public class DragonUnitTest {

    @Test
    public void testSolveDragon_PositiveCase_1() {
        int[] a = { 5, 6, 0, 4, 2, 4, 1, 0, 0, 4 };
        int n = a.length;
        String actual = Dragon.solveDragon(a, n);
        String expected = "0, 5, 9, out";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testSolveDragon_PositiveCase_2() {
        int[] a = { 1, 1, 1, 1, 1 };
        int n = a.length;
        String actual = Dragon.solveDragon(a, n);
        String expected = "0, 1, 2, 3, 4, out";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testSolveDragon_PositiveCase_3() {
        int[] a = { 3, 3, 0, 0, 2, 1 };
        int n = a.length;
        String actual = Dragon.solveDragon(a, n);
        String expected = "0, 1, 4, out";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testSolveDragon_NegativeCase_1() {
        int[] a = { 0, 1, 2, 3, 4, 5 };
        int n = a.length;
        String actual = Dragon.solveDragon(a, n);
        String expected = Dragon.FAILURE;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testSolveDragon_NegativeCase_2() {
        int[] a = { 3, 0, 0, 0, 5, 7 };
        int n = a.length;
        String actual = Dragon.solveDragon(a, n);
        String expected = Dragon.FAILURE;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testSolveDragon_NegativeCase_3() {

        int[] a = { 3, 2, 1, 0 };
        int n = a.length;
        String actual = Dragon.solveDragon(a, n);
        String expected = Dragon.FAILURE;
        Assert.assertEquals(expected, actual);
    }

}
