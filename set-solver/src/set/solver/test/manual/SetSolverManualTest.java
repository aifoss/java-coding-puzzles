package set.solver.test.manual;

import set.solver.main.Card;
import set.solver.main.CardSet;
import set.solver.main.SetGame;
import set.solver.main.SetSolver;

import java.util.List;
import java.util.Random;

/**
 * Created by sofia on 4/25/17.
 */
public class SetSolverManualTest {

    public static void main(String[] args) {
        if (args.length != 4) {
            System.err.println("Usage: java SetSolverManualTest <numDimensions> <numValues> <numTrials> <maxDealSize>");
            System.exit(1);
        }

        int numDimensions = Integer.parseInt(args[0]);
        int numValues = Integer.parseInt(args[1]);
        int numTrials = Integer.parseInt(args[2]);
        int maxDealSize = Integer.parseInt(args[3]);

        SetSolver solver = new SetSolver();
        SetGame game = new SetGame(numDimensions, numValues);

        for (int i = 0; i < numTrials; i++) {
            Random random = new Random();
            int dealSize = 1 + random.nextInt(maxDealSize);
            List<Card> deal = game.deal(dealSize);
            List<CardSet> solution = solver.solve(deal);

            System.out.println("Trial " + (i+1) + ":");
            System.out.println("Deal size = " + dealSize);
            System.out.println("Solution size = " + solution.size());
            System.out.println();

            System.out.println("Solution:");
            System.out.println();

            if (solution.isEmpty()) {
                System.out.println("No solution exists");
                System.out.println();
                continue;
            }

            for (int j = 0; j < solution.size(); j++) {
                System.out.println("Set " + (j+1) + ":");
                System.out.println(solution.get(j));
            }
        }
    }

}
