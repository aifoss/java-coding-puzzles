package set.solver.test.unit;

import org.junit.Assert;
import org.junit.Test;
import set.solver.main.SetGame;

/**
 * Created by sofia on 8/23/15.
 */
public class SetGameUnitTest {

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorExceptionalCase1() throws Exception {
        new SetGame(0, 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorExceptionalCase2() throws Exception {
        new SetGame(5, -5);
    }

    @Test
    public void testGetNumDimsPerCard() {
        SetGame setGame = new SetGame(5, 4);
        int expected = 5;
        int actual = setGame.getNumDimsPerCard();
        Assert.assertTrue(expected == actual);
    }

    @Test
    public void testGetNumValsPerDim() {
        SetGame setGame = new SetGame(5, 4);
        int expected = 4;
        int actual = setGame.getNumValsPerDim();
        Assert.assertTrue(expected == actual);
    }

    @Test
    public void testGetDeckSize() {
        SetGame setGame = new SetGame(5, 4);
        int expected = (int) Math.pow(4, 5);
        int actual = setGame.getDeckSize();
        Assert.assertTrue(expected == actual);
    }

}
