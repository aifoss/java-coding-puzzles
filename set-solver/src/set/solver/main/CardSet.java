package set.solver.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by sofia on 4/25/17.
 */

/**
 * Class to represent set.
 */
public class CardSet implements Comparable<CardSet> {

    private static final int SET_SIZE = 3;  // fixed set size regardless of the number of dimensions/values

    private List<Card> set;                 // list of cards constituting the set

    public CardSet(Card card1, Card card2, Card card3) {
        if (null == card1 || null == card2 || null == card3) throw new IllegalArgumentException("Null card(s)");
        if (!formValidSet(card1, card2, card3)) throw new IllegalArgumentException("Cards do not form a valid set");

        set = new ArrayList<>(SET_SIZE);
        set.add(card1);
        set.add(card2);
        set.add(card3);

        Collections.sort(set);
    }

    public List<Card> getSet() {
        return set;
    }

    /**
     * Checks if three cards constitute a valid set.
     *
     * @param c1 first card
     * @param c2 second card
     * @param c3 third card
     * @return true if the cards form a valid set; false otherwise
     */
    public static boolean formValidSet(Card c1, Card c2, Card c3) {
        if (c1.equals(c2) || c2.equals(c3) || c3.equals(c1)) throw new IllegalArgumentException("Identical cards in set");
        int numDimensions = c1.getNumDimensions();
        for (int i = 0; i < numDimensions; i++) {
            if (!haveSameValue(i, c1, c2, c3) && !haveAllDifferentValues(i, c1, c2, c3)) return false;
        }
        return true;
    }

    @Override
    public int compareTo(CardSet other) {
        for (int i = 0; i < SET_SIZE; i++) {
            int cmp = this.set.get(i).compareTo(other.set.get(i));
            if (cmp != 0) return cmp;
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (null == o) return false;
        if (o == this) return true;
        if (o.getClass() != this.getClass()) return false;
        CardSet other = (CardSet) o;
        return this.set.equals(other.set);
    }

    @Override
    public int hashCode() {
        return set.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < SET_SIZE; i++) {
            sb.append(set.get(i)).append(System.getProperty("line.separator"));
        }
        return sb.toString();
    }

    // auxiliary method to check if three cards have the same value for specified dimension
    private static boolean haveSameValue(int dimIdx, Card c1, Card c2, Card c3) {
        return  c1.hasSameDimValueAs(c2, dimIdx) &&
                c2.hasSameDimValueAs(c3, dimIdx) &&
                c3.hasSameDimValueAs(c1, dimIdx);
    }

    // auxiliary method to check if three cards have all different values for the specified dimension
    private static boolean haveAllDifferentValues(int dimIdx, Card c1, Card c2, Card c3) {
        return  !c1.hasSameDimValueAs(c2, dimIdx) &&
                !c2.hasSameDimValueAs(c3, dimIdx) &&
                !c3.hasSameDimValueAs(c1, dimIdx);
    }

}
