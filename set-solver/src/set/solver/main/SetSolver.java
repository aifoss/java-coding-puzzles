package set.solver.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by sofia on 4/25/17.
 */
public class SetSolver {

    /**
     * Returns all possible sets of cards, given the deal of cards.
     *
     * @param deal deal of cards drawn from the card deck
     * @return list of card sets representing solutions
     */
    public List<CardSet> solve(List<Card> deal) {
        List<CardSet> result = new ArrayList<>();
        Collections.sort(deal);

        int n = deal.size();
        for (int i = 0; i <= n-3; i++) {
            for (int j = i+1; j <= n-2; j++) {
                for (int k = j+1; k <= n-1; k++) {

                    Card card1 = deal.get(i);
                    Card card2 = deal.get(j);
                    Card card3 = deal.get(k);

                    if (CardSet.formValidSet(card1, card2, card3)) {
                        CardSet aSet = new CardSet(card1, card2, card3);
                        result.add(aSet);
                    }
                }
            }
        }

        return result;
    }

}
