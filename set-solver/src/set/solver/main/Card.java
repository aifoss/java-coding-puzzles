package set.solver.main;

import java.util.List;

/**
 * Created by sofia on 4/25/17.
 */

/**
 * Class to represent card.
 */
public class Card implements Comparable<Card> {

    private int numDimensions;  // number of dimensions
    private int numDimValues;   // number of values for each dimension

    private int[] dimensions;   // array of dimension values

    public Card(List<Integer> dimVals, int numVals) {
        if (null == dimVals || dimVals.isEmpty()) throw new IllegalArgumentException("Null/empty dimension values");
        if (numVals <= 0) throw new IllegalArgumentException("Invalid number of dimension values");

        numDimensions = dimVals.size();
        numDimValues = numVals;
        dimensions = new int[numDimensions];

        for (int i = 0; i < numDimensions; i++) {
            int val = dimVals.get(i);
            if (val >= numDimValues) throw new IllegalArgumentException("Invalid dimension value");
            dimensions[i] = val;
        }
    }

    public int getNumDimensions() {
        return numDimensions;
    }

    public int getNumDimValues() {
        return numDimValues;
    }

    public int[] getDimensions() {
        return dimensions;
    }

    /**
     * Checks if two cards have the same value for the specified dimension.
     *
     * @param card1 first card
     * @param card2 second card
     * @param dimIdx index for the array of dimension values
     * @return true if two cards have the same dimension value; false otherwise
     */
    public static boolean haveSameDimValue(Card card1, Card card2, int dimIdx) {
        if (null == card1 || null == card2) throw new IllegalArgumentException("Null card(s)");
        if (card1.numDimensions != card2.numDimensions) throw new IllegalArgumentException("Card dimension size mismatch");
        if (card1.numDimValues != card2.numDimValues) throw new IllegalArgumentException("Card value size mismatch");
        if (dimIdx < 0) throw new IllegalArgumentException("Invalid array index for dimension");

        return card1.hasSameDimValueAs(card2, dimIdx);
    }

    /**
     * Checks if this card has the same value for the specified dimension as another card.
     *
     * @param other other card
     * @param dimIdx index for the array of dimension values
     * @return true if this card has the same dimension value as the other card; false otherwise
     */
    public boolean hasSameDimValueAs(Card other, int dimIdx) {
        if (null == other) throw new IllegalArgumentException("Null card input");
        if (this.numDimensions != other.numDimensions) throw new IllegalArgumentException("Card dimension size mismatch");
        if (this.numDimValues != other.numDimValues) throw new IllegalArgumentException("Card value size mismatch");
        if (dimIdx < 0) throw new IllegalArgumentException("Invalid array index for dimension");

        return this.dimensions[dimIdx] == other.dimensions[dimIdx];
    }

    @Override
    public int compareTo(Card other) {
        int cmp;
        for (int i = 0; i < numDimensions; i++) {
            cmp = ((Integer) this.dimensions[i]).compareTo(other.dimensions[i]);
            if (cmp != 0) return cmp;
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (null == o) return false;
        if (o == this) return true;
        if (o.getClass() != this.getClass()) return false;
        Card other = (Card) o;
        for (int i = 0; i < numDimensions; i++) {
            if (this.dimensions[i] != other.dimensions[i]) return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 17;
        for (int i = 0; i < numDimensions; i++) {
            hash = hash * 31 + (dimensions[i] * i);
        }
        return hash;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append("Dim 1: ").append(dimensions[0]);
        for (int i = 0; i < numDimensions; i++) {
            sb.append(", ").append("Dim ").append(i+1).append(": ").append(dimensions[i]);
        }
        sb.append("]");
        return sb.toString();
    }

}
