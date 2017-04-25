package set.solver.test.unit;

import org.junit.Assert;
import org.junit.Test;
import set.solver.main.Card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by sofia on 4/25/17.
 */
public class CardUnitTest {

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorExceptionalCase1() throws Exception {
        new Card(null, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorExceptionalCase2() throws Exception {
        new Card(new ArrayList<>(), 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorExceptionalCase3() throws Exception {
        List<Integer> dimVals = new ArrayList<>();
        dimVals.add(0);
        dimVals.add(1);
        dimVals.add(2);
        dimVals.add(2);
        new Card(dimVals, -5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorExceptionalCase4() throws Exception {
        List<Integer> dimVals = new ArrayList<>();
        dimVals.add(0);
        dimVals.add(1);
        dimVals.add(2);
        dimVals.add(2);
        new Card(dimVals, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorExceptionalCase5() throws Exception {
        List<Integer> dimVals = new ArrayList<>();
        dimVals.add(0);
        dimVals.add(1);
        dimVals.add(2);
        dimVals.add(3);
        int numVals = 3;
        new Card(dimVals, numVals);
    }

    @Test
    public void testConstructorNormalCase() throws Exception {
        List<Integer> dimVals = new ArrayList<>();
        dimVals.add(0);
        dimVals.add(1);
        dimVals.add(2);
        dimVals.add(2);
        int numVals = 4;
        new Card(dimVals, numVals);
    }

    @Test
    public void testGetNumDimensions() {
        List<Integer> dimVals = new ArrayList<>();
        dimVals.add(0);
        dimVals.add(1);
        dimVals.add(2);
        dimVals.add(2);
        int numVals = 3;
        Card card = new Card(dimVals, numVals);
        int expected = 4;
        int actual = card.getNumDimensions();
        Assert.assertTrue(expected == actual);
    }

    @Test
    public void testGeNumDimValues() {
        List<Integer> dimVals = new ArrayList<>();
        dimVals.add(0);
        dimVals.add(1);
        dimVals.add(2);
        dimVals.add(2);
        int numVals = 3;
        Card card = new Card(dimVals, numVals);
        int expected = 3;
        int actual = card.getNumDimValues();
        Assert.assertTrue(expected == actual);
    }

    @Test
    public void testGetDimensions() {
        List<Integer> dimVals = new ArrayList<>();
        dimVals.add(0);
        dimVals.add(0);
        dimVals.add(1);
        dimVals.add(1);
        dimVals.add(2);
        int numVals = 3;
        Card card = new Card(dimVals, numVals);
        int[] expected = new int[dimVals.size()];
        for (int i = 0; i < dimVals.size(); i++) {
            expected[i] = dimVals.get(i);
        }
        int[] actual = card.getDimensions();
        Assert.assertTrue(Arrays.equals(expected, actual));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testHaveSameDimValueExceptionalCase1() throws Exception {
        Card.haveSameDimValue(null, null, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testHaveSameDimValueExceptionalCase2() throws Exception {
        int numVals = 3;
        List<Integer> dimVals = new ArrayList<>();
        dimVals.add(0);
        dimVals.add(0);
        dimVals.add(1);
        dimVals.add(1);
        dimVals.add(2);
        Card card = new Card(dimVals, numVals);
        Card.haveSameDimValue(null, card, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testHaveSameDimValueExceptionalCase3() throws Exception {
        int numVals = 3;
        List<Integer> dimVals = new ArrayList<>();
        dimVals.add(0);
        dimVals.add(0);
        dimVals.add(1);
        dimVals.add(1);
        dimVals.add(2);
        Card card = new Card(dimVals, numVals);
        Card.haveSameDimValue(card, null, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testHaveSameDimValueExceptionCase4() throws Exception {
        int numVals = 3;
        List<Integer> dimVals1 = new ArrayList<>();
        dimVals1.add(0);
        dimVals1.add(0);
        dimVals1.add(1);
        dimVals1.add(1);
        dimVals1.add(2);
        Card card1 = new Card(dimVals1, numVals);
        List<Integer> dimVals2 = new ArrayList<>();
        dimVals2.add(0);
        dimVals2.add(1);
        dimVals2.add(2);
        dimVals2.add(0);
        dimVals2.add(1);
        Card card2 = new Card(dimVals2, numVals);
        Card.haveSameDimValue(card1, card2, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testHaveSameDimValueExceptionalCase5() throws Exception {
        int numVals = 3;
        List<Integer> dimVals1 = new ArrayList<>();
        dimVals1.add(0);
        dimVals1.add(0);
        dimVals1.add(1);
        dimVals1.add(1);
        dimVals1.add(2);
        Card card1 = new Card(dimVals1, numVals);
        List<Integer> dimVals2 = new ArrayList<>();
        dimVals2.add(0);
        dimVals2.add(1);
        dimVals2.add(2);
        dimVals2.add(0);
        Card card2 = new Card(dimVals2, numVals);
        int dimIdx = 0;
        Card.haveSameDimValue(card1, card2, dimIdx);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testHaveSameDimValueExceptionalCase6() throws Exception {
        int numVals = 3;
        List<Integer> dimVals1 = new ArrayList<>();
        dimVals1.add(0);
        dimVals1.add(0);
        dimVals1.add(1);
        dimVals1.add(1);
        dimVals1.add(2);
        Card card1 = new Card(dimVals1, numVals);
        numVals = 4;
        List<Integer> dimVals2 = new ArrayList<>();
        dimVals2.add(0);
        dimVals2.add(1);
        dimVals2.add(2);
        dimVals2.add(0);
        dimVals2.add(1);
        Card card2 = new Card(dimVals2, numVals);
        int dimIdx = 0;
        Card.haveSameDimValue(card1, card2, dimIdx);
    }

    @Test
    public void testHaveSameDimValueNormalPositiveCase() {
        int numVals = 3;
        List<Integer> dimVals1 = new ArrayList<>();
        dimVals1.add(0);
        dimVals1.add(0);
        dimVals1.add(1);
        dimVals1.add(1);
        dimVals1.add(2);
        Card card1 = new Card(dimVals1, numVals);
        List<Integer> dimVals2 = new ArrayList<>();
        dimVals2.add(0);
        dimVals2.add(1);
        dimVals2.add(2);
        dimVals2.add(0);
        dimVals2.add(1);
        Card card2 = new Card(dimVals2, numVals);
        int dimIdx = 0;
        boolean result = Card.haveSameDimValue(card1, card2, dimIdx);
        Assert.assertTrue(result);
    }

    @Test
    public void testHaveSameDimValueNormalNegativeCase() {
        int numVals = 3;
        List<Integer> dimVals1 = new ArrayList<>();
        dimVals1.add(0);
        dimVals1.add(0);
        dimVals1.add(1);
        dimVals1.add(1);
        dimVals1.add(2);
        Card card1 = new Card(dimVals1, numVals);
        List<Integer> dimVals2 = new ArrayList<>();
        dimVals2.add(0);
        dimVals2.add(0);
        dimVals2.add(1);
        dimVals2.add(1);
        dimVals2.add(1);
        Card card2 = new Card(dimVals2, numVals);
        int dimIdx = 4;
        boolean result = Card.haveSameDimValue(card1, card2, dimIdx);
        Assert.assertFalse(result);
    }

    @Test
    public void testCompareToAscendingOrderCase() {
        int numVals = 4;
        List<Integer> dimVals1 = new ArrayList<>();
        dimVals1.add(0);
        dimVals1.add(1);
        dimVals1.add(2);
        dimVals1.add(3);
        dimVals1.add(0);
        Card card1 = new Card(dimVals1, numVals);
        List<Integer> dimVals2 = new ArrayList<>();
        dimVals2.add(0);
        dimVals2.add(1);
        dimVals2.add(2);
        dimVals2.add(3);
        dimVals2.add(1);
        Card card2 = new Card(dimVals2, numVals);
        int expected = -1;
        int actual = card1.compareTo(card2);
        Assert.assertTrue(expected == actual);
    }

    @Test
    public void testCompareToEqualOrderCase() {
        int numVals = 4;
        List<Integer> dimVals1 = new ArrayList<>();
        dimVals1.add(0);
        dimVals1.add(1);
        dimVals1.add(2);
        dimVals1.add(3);
        dimVals1.add(0);
        Card card1 = new Card(dimVals1, numVals);
        List<Integer> dimVals2 = new ArrayList<>();
        dimVals2.add(0);
        dimVals2.add(1);
        dimVals2.add(2);
        dimVals2.add(3);
        dimVals2.add(0);
        Card card2 = new Card(dimVals2, numVals);
        int expected = 0;
        int actual = card1.compareTo(card2);
        Assert.assertTrue(expected == actual);
    }

    @Test
    public void testCompareToDescendingOrderCase() {
        int numVals = 4;
        List<Integer> dimVals1 = new ArrayList<>();
        dimVals1.add(0);
        dimVals1.add(1);
        dimVals1.add(2);
        dimVals1.add(3);
        dimVals1.add(1);
        Card card1 = new Card(dimVals1, numVals);
        List<Integer> dimVals2 = new ArrayList<>();
        dimVals2.add(0);
        dimVals2.add(1);
        dimVals2.add(2);
        dimVals2.add(3);
        dimVals2.add(0);
        Card card2 = new Card(dimVals2, numVals);
        int expected = 1;
        int actual = card1.compareTo(card2);
        Assert.assertTrue(expected == actual);
    }

    @Test
    public void testEqualsNegativeCase1() {
        int numVals = 4;
        List<Integer> dimVals = new ArrayList<>();
        dimVals.add(0);
        dimVals.add(1);
        dimVals.add(2);
        dimVals.add(3);
        dimVals.add(1);
        Card card1 = new Card(dimVals, numVals);
        boolean result = card1.equals(null);
        Assert.assertFalse(result);
    }

    @Test
    public void testEqualsNegativeCase2() {
        int numVals = 4;
        List<Integer> dimVals = new ArrayList<>();
        dimVals.add(0);
        dimVals.add(1);
        dimVals.add(2);
        dimVals.add(3);
        dimVals.add(1);
        Card card1 = new Card(dimVals, numVals);
        boolean result = card1.equals(new Object());
        Assert.assertFalse(result);
    }

    @Test
    public void testEqualsPositiveCase1() {
        int numVals = 4;
        List<Integer> dimVals = new ArrayList<>();
        dimVals.add(0);
        dimVals.add(1);
        dimVals.add(2);
        dimVals.add(3);
        dimVals.add(0);
        Card card1 = new Card(dimVals, numVals);
        Card card2 = card1;
        boolean result = card1.equals(card2);
        Assert.assertTrue(result);
    }

    @Test
    public void testEqualsPositiveCase2() {
        int numVals = 4;
        List<Integer> dimVals1 = new ArrayList<>();
        dimVals1.add(0);
        dimVals1.add(1);
        dimVals1.add(2);
        dimVals1.add(3);
        dimVals1.add(1);
        Card card1 = new Card(dimVals1, numVals);
        List<Integer> dimVals2 = new ArrayList<>();
        dimVals2.add(0);
        dimVals2.add(1);
        dimVals2.add(2);
        dimVals2.add(3);
        dimVals2.add(1);
        Card card2 = new Card(dimVals2, numVals);
        boolean result = card1.equals(card2);
        Assert.assertTrue(result);
    }

    @Test
    public void testHashCodeEqualCase() {
        int numVals = 4;
        List<Integer> dimVals1 = new ArrayList<>();
        dimVals1.add(0);
        dimVals1.add(1);
        dimVals1.add(2);
        dimVals1.add(3);
        dimVals1.add(1);
        Card card1 = new Card(dimVals1, numVals);
        List<Integer> dimVals2 = new ArrayList<>();
        dimVals2.add(0);
        dimVals2.add(1);
        dimVals2.add(2);
        dimVals2.add(3);
        dimVals2.add(1);
        Card card2 = new Card(dimVals2, numVals);
        int hashCode1 = card1.hashCode();
        int hashCode2 = card2.hashCode();
        Assert.assertTrue(hashCode1 == hashCode2);
    }

    @Test
    public void testHashCodeNonEqualCase() {
        int numVals = 4;
        List<Integer> dimVals1 = new ArrayList<>();
        dimVals1.add(0);
        dimVals1.add(1);
        dimVals1.add(2);
        dimVals1.add(3);
        dimVals1.add(0);
        Card card1 = new Card(dimVals1, numVals);
        List<Integer> dimVals2 = new ArrayList<>();
        dimVals2.add(0);
        dimVals2.add(1);
        dimVals2.add(2);
        dimVals2.add(3);
        dimVals2.add(1);
        Card card2 = new Card(dimVals2, numVals);
        int hashCode1 = card1.hashCode();
        int hashCode2 = card2.hashCode();
        Assert.assertFalse(hashCode1 == hashCode2);
    }

}
