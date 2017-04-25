package set.solver.test.functional;

import set.solver.main.Card;
import set.solver.main.SetGame;

import java.util.List;

/**
 * Created by sofia on 4/25/17.
 */
public class SetGameFunctionalTest {

    public static void main(String[] args) {
        SetGame setGame = new SetGame(4, 5);
        List<Card> deck = setGame.getDeck();

        System.out.println("Deck Size = " + deck.size());
        System.out.println();

        System.out.println("Deck: ");
        System.out.println();

        setGame.printDeck();

        List<Card> deal = setGame.deal(20);

        System.out.println("Deal: ");
        System.out.println();

        for (int i = 0; i < 20; i++) {
            System.out.println((i+1) + ": " + deal.get(i));
        }

        System.out.println();
    }

}
