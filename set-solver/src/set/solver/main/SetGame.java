package set.solver.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by sofia on 4/25/17.
 */

/**
 * Class to represent set game.
 */
public class SetGame {

    private int numDimsPerCard;     // number of dimensions per each card
    private int numValsPerDim;      // number of values per each dimension
    private int deckSize;           // number of cards in deck

    private List<Card> deck;        // deck of cards

    public SetGame(int n, int m) {
        if (n <= 0) throw new IllegalArgumentException("Invalid value for number of card dimensions");
        if (m <= 0) throw new IllegalArgumentException("Invalid value for number of dimension values");

        numDimsPerCard = n;
        numValsPerDim = m;
        deckSize = (int) Math.pow(numValsPerDim, numDimsPerCard);
        deck = new ArrayList<>(deckSize);

        populateDeck();
    }

    public int getNumDimsPerCard() {
        return numDimsPerCard;
    }

    public int getNumValsPerDim() {
        return numValsPerDim;
    }

    public int getDeckSize() {
        return deckSize;
    }

    public List<Card> getDeck() {
        return deck;
    }

    /**
     * Returns a deal consisting of the specified number of cards.
     *
     * @param n number of cards to be included in the deal
     * @return list of cards containing the deal
     */
    public List<Card> deal(int n) {
        List<Card> deal = new ArrayList<>();
        List<Integer> indices = new ArrayList<>();
        Random random = new Random();
        int i = 0;
        while (i < n) {
            int idx = random.nextInt(deckSize);
            if (!indices.contains(idx)) {
                deal.add(deck.get(idx));
                indices.add(idx);
                i++;
            }
        }
        Collections.sort(deal);
        return deal;
    }

    public void printDeck() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < deckSize; i++) {
            sb.append(i+1).append(": ").append(deck.get(i)).append(System.getProperty("line.separator"));
        }
        System.out.println(sb);
    }

    // auxiliary method to populate card deck
    private void populateDeck() {
        Random random = new Random();
        int count = 0;
        while (count < deckSize) {
            List<Integer> dimVals = new ArrayList<>();
            for (int i = 0; i < numDimsPerCard; i++) {
                dimVals.add(random.nextInt(numValsPerDim));
            }
            Card card = new Card(dimVals, numValsPerDim);
            if (!deck.contains(card)) {
                deck.add(card);
                count++;
            }
        }
        Collections.sort(deck);
    }

}
