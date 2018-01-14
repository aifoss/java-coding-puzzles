package set.solver.test.unit;

import org.junit.Assert;
import org.junit.Test;
import set.solver.main.Card;
import set.solver.main.CardSet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sofia on 8/23/15.
 */
public class CardSetUnitTest {

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorExpcetionalCase1() throws Exception {
        int numVals = 3;
        List<Integer> dimVals1 = new ArrayList<>();
        dimVals1.add(0);
        dimVals1.add(0);
        dimVals1.add(1);
        dimVals1.add(1);
        Card card1 = new Card(dimVals1, numVals);
        List<Integer> dimVals2 = new ArrayList<>();
        dimVals2.add(1);
        dimVals2.add(1);
        dimVals2.add(2);
        dimVals2.add(2);
        Card card2 = new Card(dimVals2, numVals);
        new CardSet(null, card1, card2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorExpcetionalCase2() throws Exception {
        int numVals = 3;
        List<Integer> dimVals1 = new ArrayList<>();
        dimVals1.add(0);
        dimVals1.add(0);
        dimVals1.add(1);
        dimVals1.add(1);
        Card card1 = new Card(dimVals1, numVals);
        List<Integer> dimVals2 = new ArrayList<>();
        dimVals2.add(1);
        dimVals2.add(1);
        dimVals2.add(2);
        dimVals2.add(2);
        Card card2 = new Card(dimVals2, numVals);
        new CardSet(card1, null, card2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorExpcetionalCase3() throws Exception {
        int numVals = 3;
        List<Integer> dimVals1 = new ArrayList<>();
        dimVals1.add(0);
        dimVals1.add(0);
        dimVals1.add(1);
        dimVals1.add(1);
        Card card1 = new Card(dimVals1, numVals);
        List<Integer> dimVals2 = new ArrayList<>();
        dimVals2.add(1);
        dimVals2.add(1);
        dimVals2.add(2);
        dimVals2.add(2);
        Card card2 = new Card(dimVals2, numVals);
        new CardSet(card1, card2, null);
    }

    @Test
    public void testConstructorNormalCase() {
        int numVals = 3;
        List<Integer> dimVals1 = new ArrayList<>();
        dimVals1.add(0);
        dimVals1.add(0);
        dimVals1.add(1);
        dimVals1.add(1);
        Card card1 = new Card(dimVals1, numVals);
        List<Integer> dimVals2 = new ArrayList<>();
        dimVals2.add(1);
        dimVals2.add(1);
        dimVals2.add(2);
        dimVals2.add(2);
        Card card2 = new Card(dimVals2, numVals);
        List<Integer> dimVals3 = new ArrayList<>();
        dimVals3.add(2);
        dimVals3.add(2);
        dimVals3.add(0);
        dimVals3.add(0);
        Card card3 = new Card(dimVals3, numVals);
        new CardSet(card1, card2, card3);
    }

    @Test
    public void testGetSet() {
        int numVals = 3;
        List<Integer> dimVals1 = new ArrayList<>();
        dimVals1.add(2);
        dimVals1.add(2);
        dimVals1.add(0);
        dimVals1.add(0);
        Card card1 = new Card(dimVals1, numVals);
        List<Integer> dimVals2 = new ArrayList<>();
        dimVals2.add(1);
        dimVals2.add(1);
        dimVals2.add(2);
        dimVals2.add(2);
        Card card2 = new Card(dimVals2, numVals);
        List<Integer> dimVals3 = new ArrayList<>();
        dimVals3.add(0);
        dimVals3.add(0);
        dimVals3.add(1);
        dimVals3.add(1);
        Card card3 = new Card(dimVals3, numVals);
        CardSet cardSet = new CardSet(card1, card2, card3);
        List<Card> expected = new ArrayList<>();
        expected.add(card3);
        expected.add(card2);
        expected.add(card1);
        List<Card> actual = cardSet.getSet();
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFormValidSetExceptionalCase1() throws Exception {
        int numVals = 3;
        List<Integer> dimVals1 = new ArrayList<>();
        dimVals1.add(0);
        dimVals1.add(0);
        dimVals1.add(1);
        dimVals1.add(1);
        Card card1 = new Card(dimVals1, numVals);
        List<Integer> dimVals2 = new ArrayList<>();
        dimVals2.add(1);
        dimVals2.add(1);
        dimVals2.add(2);
        dimVals2.add(2);
        Card card2 = new Card(dimVals2, numVals);
        List<Integer> dimVals3 = new ArrayList<>();
        dimVals3.add(2);
        dimVals3.add(2);
        dimVals3.add(0);
        dimVals3.add(0);
        dimVals3.add(1);
        Card card3 = new Card(dimVals3, numVals);
        CardSet.formValidSet(card1, card2, card3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFormValidSetExceptionalCase2() throws Exception {
        int numVals1 = 3;
        List<Integer> dimVals1 = new ArrayList<>();
        dimVals1.add(0);
        dimVals1.add(0);
        dimVals1.add(1);
        dimVals1.add(1);
        Card card1 = new Card(dimVals1, numVals1);
        int numVals2 = 3;
        List<Integer> dimVals2 = new ArrayList<>();
        dimVals2.add(1);
        dimVals2.add(1);
        dimVals2.add(2);
        dimVals2.add(2);
        Card card2 = new Card(dimVals2, numVals2);
        int numVals3 = 4;
        List<Integer> dimVals3 = new ArrayList<>();
        dimVals3.add(2);
        dimVals3.add(2);
        dimVals3.add(3);
        dimVals3.add(3);
        Card card3 = new Card(dimVals3, numVals3);
        CardSet.formValidSet(card1, card2, card3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFormValidSetExceptionalCase3() throws Exception {
        int numVals1 = 3;
        List<Integer> dimVals1 = new ArrayList<>();
        dimVals1.add(0);
        dimVals1.add(0);
        dimVals1.add(1);
        dimVals1.add(1);
        Card card1 = new Card(dimVals1, numVals1);
        int numVals2 = 3;
        List<Integer> dimVals2 = new ArrayList<>();
        dimVals2.add(1);
        dimVals2.add(1);
        dimVals2.add(2);
        dimVals2.add(2);
        Card card2 = new Card(dimVals2, numVals2);
        int numVals3 = 3;
        List<Integer> dimVals3 = new ArrayList<>();
        dimVals3.add(1);
        dimVals3.add(1);
        dimVals3.add(2);
        dimVals3.add(2);
        Card card3 = new Card(dimVals3, numVals3);
        CardSet.formValidSet(card1, card2, card3);
    }

    @Test
    public void testFormValidSetNormalPositiveCase1() {
        int numVals = 3;
        List<Integer> dimVals1 = new ArrayList<>();
        dimVals1.add(0);
        dimVals1.add(1);
        dimVals1.add(2);
        dimVals1.add(0);
        Card card1 = new Card(dimVals1, numVals);
        List<Integer> dimVals2 = new ArrayList<>();
        dimVals2.add(0);
        dimVals2.add(1);
        dimVals2.add(2);
        dimVals2.add(1);
        Card card2 = new Card(dimVals2, numVals);
        List<Integer> dimVals3 = new ArrayList<>();
        dimVals3.add(0);
        dimVals3.add(1);
        dimVals3.add(2);
        dimVals3.add(2);
        Card card3 = new Card(dimVals3, numVals);
        boolean result = CardSet.formValidSet(card1, card2, card3);
        Assert.assertTrue(result);
    }

    @Test
    public void testFormValidSetNormalPositiveCase2() {
        int numVals = 3;
        List<Integer> dimVals1 = new ArrayList<>();
        dimVals1.add(0);
        dimVals1.add(1);
        dimVals1.add(2);
        dimVals1.add(0);
        Card card1 = new Card(dimVals1, numVals);
        List<Integer> dimVals2 = new ArrayList<>();
        dimVals2.add(1);
        dimVals2.add(2);
        dimVals2.add(0);
        dimVals2.add(1);
        Card card2 = new Card(dimVals2, numVals);
        List<Integer> dimVals3 = new ArrayList<>();
        dimVals3.add(2);
        dimVals3.add(0);
        dimVals3.add(1);
        dimVals3.add(2);
        Card card3 = new Card(dimVals3, numVals);
        boolean result = CardSet.formValidSet(card1, card2, card3);
        Assert.assertTrue(result);
    }

    @Test
    public void testFormValidSetNormalNegativeCase() {
        int numVals = 3;
        List<Integer> dimVals1 = new ArrayList<>();
        dimVals1.add(0);
        dimVals1.add(0);
        dimVals1.add(2);
        dimVals1.add(0);
        Card card1 = new Card(dimVals1, numVals);
        List<Integer> dimVals2 = new ArrayList<>();
        dimVals2.add(0);
        dimVals2.add(1);
        dimVals2.add(2);
        dimVals2.add(0);
        Card card2 = new Card(dimVals2, numVals);
        List<Integer> dimVals3 = new ArrayList<>();
        dimVals3.add(0);
        dimVals3.add(2);
        dimVals3.add(2);
        dimVals3.add(1);
        Card card3 = new Card(dimVals3, numVals);
        boolean result = CardSet.formValidSet(card1, card2, card3);
        Assert.assertFalse(result);
    }

    @Test
    public void testCompareToAscendingOrderCase() {
        int numVals = 3;
        List<Integer> dimVals1 = new ArrayList<>();
        dimVals1.add(0);
        dimVals1.add(0);
        dimVals1.add(0);
        dimVals1.add(0);
        Card card1 = new Card(dimVals1, numVals);
        List<Integer> dimVals2 = new ArrayList<>();
        dimVals2.add(0);
        dimVals2.add(0);
        dimVals2.add(0);
        dimVals2.add(1);
        Card card2 = new Card(dimVals2, numVals);
        List<Integer> dimVals3 = new ArrayList<>();
        dimVals3.add(0);
        dimVals3.add(0);
        dimVals3.add(0);
        dimVals3.add(2);
        Card card3 = new Card(dimVals3, numVals);
        CardSet cardSet1 = new CardSet(card1, card2, card3);
        List<Integer> dimVals4 = new ArrayList<>();
        dimVals4.add(0);
        dimVals4.add(0);
        dimVals4.add(0);
        dimVals4.add(0);
        Card card4 = new Card(dimVals4, numVals);
        List<Integer> dimVals5 = new ArrayList<>();
        dimVals5.add(1);
        dimVals5.add(1);
        dimVals5.add(1);
        dimVals5.add(1);
        Card card5 = new Card(dimVals5, numVals);
        List<Integer> dimVals6 = new ArrayList<>();
        dimVals6.add(2);
        dimVals6.add(2);
        dimVals6.add(2);
        dimVals6.add(2);
        Card card6 = new Card(dimVals6, numVals);
        CardSet cardSet2 = new CardSet(card4, card5, card6);
        int expected = -1;
        int actual = cardSet1.compareTo(cardSet2);
        Assert.assertTrue(expected == actual);
    }

    @Test
    public void testCompareToEqualOrderCase() {
        int numVals = 3;
        List<Integer> dimVals1 = new ArrayList<>();
        dimVals1.add(0);
        dimVals1.add(1);
        dimVals1.add(2);
        dimVals1.add(0);
        Card card1 = new Card(dimVals1, numVals);
        List<Integer> dimVals2 = new ArrayList<>();
        dimVals2.add(0);
        dimVals2.add(1);
        dimVals2.add(2);
        dimVals2.add(1);
        Card card2 = new Card(dimVals2, numVals);
        List<Integer> dimVals3 = new ArrayList<>();
        dimVals3.add(0);
        dimVals3.add(1);
        dimVals3.add(2);
        dimVals3.add(2);
        Card card3 = new Card(dimVals3, numVals);
        CardSet cardSet1 = new CardSet(card1, card2, card3);
        CardSet cardSet2 = new CardSet(card1, card2, card3);
        int expected = 0;
        int actual = cardSet1.compareTo(cardSet2);
        Assert.assertTrue(expected == actual);
    }

    @Test
    public void testCompareToDescendingOrderCase() {
        int numVals = 3;
        List<Integer> dimVals1 = new ArrayList<>();
        dimVals1.add(0);
        dimVals1.add(0);
        dimVals1.add(0);
        dimVals1.add(0);
        Card card1 = new Card(dimVals1, numVals);
        List<Integer> dimVals2 = new ArrayList<>();
        dimVals2.add(1);
        dimVals2.add(1);
        dimVals2.add(1);
        dimVals2.add(1);
        Card card2 = new Card(dimVals2, numVals);
        List<Integer> dimVals3 = new ArrayList<>();
        dimVals3.add(2);
        dimVals3.add(2);
        dimVals3.add(2);
        dimVals3.add(2);
        Card card3 = new Card(dimVals3, numVals);
        CardSet cardSet1 = new CardSet(card1, card2, card3);
        List<Integer> dimVals4 = new ArrayList<>();
        dimVals4.add(0);
        dimVals4.add(0);
        dimVals4.add(0);
        dimVals4.add(0);
        Card card4 = new Card(dimVals4, numVals);
        List<Integer> dimVals5 = new ArrayList<>();
        dimVals5.add(0);
        dimVals5.add(0);
        dimVals5.add(0);
        dimVals5.add(1);
        Card card5 = new Card(dimVals5, numVals);
        List<Integer> dimVals6 = new ArrayList<>();
        dimVals6.add(0);
        dimVals6.add(0);
        dimVals6.add(0);
        dimVals6.add(2);
        Card card6 = new Card(dimVals6, numVals);
        CardSet cardSet2 = new CardSet(card4, card5, card6);
        int expected = 1;
        int actual = cardSet1.compareTo(cardSet2);
        Assert.assertTrue(expected == actual);
    }

    @Test
    public void testEqualsNegativeCase1() {
        int numVals = 3;
        List<Integer> dimVals1 = new ArrayList<>();
        dimVals1.add(0);
        dimVals1.add(0);
        dimVals1.add(1);
        dimVals1.add(1);
        Card card1 = new Card(dimVals1, numVals);
        List<Integer> dimVals2 = new ArrayList<>();
        dimVals2.add(1);
        dimVals2.add(1);
        dimVals2.add(2);
        dimVals2.add(2);
        Card card2 = new Card(dimVals2, numVals);
        List<Integer> dimVals3 = new ArrayList<>();
        dimVals3.add(2);
        dimVals3.add(2);
        dimVals3.add(0);
        dimVals3.add(0);
        Card card3 = new Card(dimVals3, numVals);
        CardSet cardSet1 = new CardSet(card1, card2, card3);
        boolean result = cardSet1.equals(null);
        Assert.assertFalse(result);
    }

    @Test
    public void testEqualsNegativeCase2() {
        int numVals = 3;
        List<Integer> dimVals1 = new ArrayList<>();
        dimVals1.add(0);
        dimVals1.add(0);
        dimVals1.add(1);
        dimVals1.add(1);
        Card card1 = new Card(dimVals1, numVals);
        List<Integer> dimVals2 = new ArrayList<>();
        dimVals2.add(1);
        dimVals2.add(1);
        dimVals2.add(2);
        dimVals2.add(2);
        Card card2 = new Card(dimVals2, numVals);
        List<Integer> dimVals3 = new ArrayList<>();
        dimVals3.add(2);
        dimVals3.add(2);
        dimVals3.add(0);
        dimVals3.add(0);
        Card card3 = new Card(dimVals3, numVals);
        CardSet cardSet1 = new CardSet(card1, card2, card3);
        boolean result = cardSet1.equals(new Object());
        Assert.assertFalse(result);
    }

    @Test
    public void testEqualsPositiveCase1() {
        int numVals = 3;
        List<Integer> dimVals1 = new ArrayList<>();
        dimVals1.add(0);
        dimVals1.add(0);
        dimVals1.add(1);
        dimVals1.add(1);
        Card card1 = new Card(dimVals1, numVals);
        List<Integer> dimVals2 = new ArrayList<>();
        dimVals2.add(1);
        dimVals2.add(1);
        dimVals2.add(2);
        dimVals2.add(2);
        Card card2 = new Card(dimVals2, numVals);
        List<Integer> dimVals3 = new ArrayList<>();
        dimVals3.add(2);
        dimVals3.add(2);
        dimVals3.add(0);
        dimVals3.add(0);
        Card card3 = new Card(dimVals3, numVals);
        CardSet cardSet1 = new CardSet(card1, card2, card3);
        CardSet cardSet2 = cardSet1;
        boolean result = cardSet1.equals(cardSet2);
        Assert.assertTrue(result);
    }

    @Test
    public void testEqualsPositiveCase2() {
        int numVals = 3;
        List<Integer> dimVals1 = new ArrayList<>();
        dimVals1.add(0);
        dimVals1.add(0);
        dimVals1.add(1);
        dimVals1.add(1);
        Card card1 = new Card(dimVals1, numVals);
        List<Integer> dimVals2 = new ArrayList<>();
        dimVals2.add(1);
        dimVals2.add(1);
        dimVals2.add(2);
        dimVals2.add(2);
        Card card2 = new Card(dimVals2, numVals);
        List<Integer> dimVals3 = new ArrayList<>();
        dimVals3.add(2);
        dimVals3.add(2);
        dimVals3.add(0);
        dimVals3.add(0);
        Card card3 = new Card(dimVals3, numVals);
        CardSet cardSet1 = new CardSet(card1, card2, card3);
        CardSet cardSet2 = new CardSet(card1, card2, card3);
        boolean result = cardSet1.equals(cardSet2);
        Assert.assertTrue(result);
    }

    @Test
    public void testHashCodeEqualCase() {
        int numVals = 3;
        List<Integer> dimVals1 = new ArrayList<>();
        dimVals1.add(0);
        dimVals1.add(0);
        dimVals1.add(1);
        dimVals1.add(1);
        Card card1 = new Card(dimVals1, numVals);
        List<Integer> dimVals2 = new ArrayList<>();
        dimVals2.add(1);
        dimVals2.add(1);
        dimVals2.add(2);
        dimVals2.add(2);
        Card card2 = new Card(dimVals2, numVals);
        List<Integer> dimVals3 = new ArrayList<>();
        dimVals3.add(2);
        dimVals3.add(2);
        dimVals3.add(0);
        dimVals3.add(0);
        Card card3 = new Card(dimVals3, numVals);
        CardSet cardSet1 = new CardSet(card1, card2, card3);
        List<Integer> dimVals4 = new ArrayList<>();
        dimVals4.add(0);
        dimVals4.add(0);
        dimVals4.add(1);
        dimVals4.add(1);
        Card card4 = new Card(dimVals4, numVals);
        List<Integer> dimVals5 = new ArrayList<>();
        dimVals5.add(1);
        dimVals5.add(1);
        dimVals5.add(2);
        dimVals5.add(2);
        Card card5 = new Card(dimVals5, numVals);
        List<Integer> dimVals6 = new ArrayList<>();
        dimVals6.add(2);
        dimVals6.add(2);
        dimVals6.add(0);
        dimVals6.add(0);
        Card card6 = new Card(dimVals6, numVals);
        CardSet cardSet2 = new CardSet(card4, card5, card6);
        int hashCode1 = cardSet1.hashCode();
        int hashCode2 = cardSet2.hashCode();
        Assert.assertTrue(hashCode1 == hashCode2);
    }

    @Test
    public void testHashCodeNonEqualCase() {
        int numVals = 3;
        List<Integer> dimVals1 = new ArrayList<>();
        dimVals1.add(0);
        dimVals1.add(0);
        dimVals1.add(1);
        dimVals1.add(1);
        Card card1 = new Card(dimVals1, numVals);
        List<Integer> dimVals2 = new ArrayList<>();
        dimVals2.add(1);
        dimVals2.add(1);
        dimVals2.add(2);
        dimVals2.add(2);
        Card card2 = new Card(dimVals2, numVals);
        List<Integer> dimVals3 = new ArrayList<>();
        dimVals3.add(2);
        dimVals3.add(2);
        dimVals3.add(0);
        dimVals3.add(0);
        Card card3 = new Card(dimVals3, numVals);
        CardSet cardSet1 = new CardSet(card1, card2, card3);
        List<Integer> dimVals4 = new ArrayList<>();
        dimVals4.add(0);
        dimVals4.add(1);
        dimVals4.add(2);
        dimVals4.add(0);
        Card card4 = new Card(dimVals4, numVals);
        List<Integer> dimVals5 = new ArrayList<>();
        dimVals5.add(0);
        dimVals5.add(1);
        dimVals5.add(2);
        dimVals5.add(1);
        Card card5 = new Card(dimVals5, numVals);
        List<Integer> dimVals6 = new ArrayList<>();
        dimVals6.add(0);
        dimVals6.add(1);
        dimVals6.add(2);
        dimVals6.add(2);
        Card card6 = new Card(dimVals6, numVals);
        CardSet cardSet2 = new CardSet(card4, card5, card6);
        int hashCode1 = cardSet1.hashCode();
        int hashCode2 = cardSet2.hashCode();
        Assert.assertFalse(hashCode1 == hashCode2);
    }

}
