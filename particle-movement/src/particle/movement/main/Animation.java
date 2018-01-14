package particle.movement.main;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sofia on 9/1/15.
 */

/**
 * Class that implements the method to animate the particle movements.
 */
public class Animation {

    /**
     * Method to animate particle movements in a linear chamber.
     *
     * @param speed unit of particle movement (= number of positions moved)
     * @param init initial state of the chamber with particle types/positions indicated
     * @return array of strings representing the particle movement sequence until the chamber becomes empty
     */
    public String[] animate(int speed, String init) {
        List<String> result = new ArrayList<>();
        int size = init.length();

        Position[] state = convertInputStringToPositions(init, size);
        String output = convertPositionsToOutputString(state);
        result.add(output);

        while (!allEmpty(state, size)) {
            state = applyMovement(state, size, speed);
            output = convertPositionsToOutputString(state);
            result.add(output);
        }

        return result.toArray(new String[result.size()]);
    }

    // auxiliary method to apply particle movements to each given state of chamber
    private Position[] applyMovement(Position[] prev, int size, int speed) {
        Position[] next = new Position[size];

        for (int i = 0; i < size; i++) {
            PositionType type = prev[i].getType();

            switch(type) {
                case LEFT:
                    setNextPositionType(next, i-speed, size, PositionType.LEFT);
                    break;
                case RIGHT:
                    setNextPositionType(next, i+speed, size, PositionType.RIGHT);
                    break;
                case BOTH:
                    setNextPositionType(next, i-speed, size, PositionType.LEFT);
                    setNextPositionType(next, i+speed, size, PositionType.RIGHT);
                    break;
            }
        }

        for (int i = 0; i < size; i++) {
            if (null == next[i]) {
                next[i] = new Position(PositionType.EMPTY);
            }
        }

        return next;
    }

    // auxiliary method to set the type of each position in the subsequent state of chamber
    private void setNextPositionType(Position[] next, int index, int size, PositionType type) {
        if (withinRange(index, size)) {
            if (null == next[index]) {
                next[index] = new Position(type);
            } else if (next[index].getType() == type) {
                // no change required
            } else {
                next[index] = new Position(PositionType.BOTH);
            }
        }
    }

    // auxiliary method to validate index range
    private boolean withinRange(int index, int size) {
        return index >= 0 && index < size;
    }

    // auxiliary method to convert input string to array of positions
    private Position[] convertInputStringToPositions(String init, int size) {
        Position[] result = new Position[size];
        for (int i = 0; i < size; i++) {
            char c = init.charAt(i);
            if (c == PositionType.EMPTY.getCode()) {
                result[i] = new Position(PositionType.EMPTY);
            } else if (c == PositionType.LEFT.getCode()) {
                result[i] = new Position(PositionType.LEFT);
            } else if (c == PositionType.RIGHT.getCode()) {
                result[i] = new Position(PositionType.RIGHT);
            }
        }
        return result;
    }

    // auxiliary method to convert array of positions to output string
    private String convertPositionsToOutputString(Position[] positions) {
        StringBuilder result = new StringBuilder();
        for (Position pos : positions) {
            result.append(pos.toString());
        }
        return result.toString();
    }

    // auxiliary method to check if all positions in the chamber are empty
    private boolean allEmpty(Position[] state, int size) {
        for (int i = 0; i < size; i++) {
            if (PositionType.EMPTY != state[i].getType()) {
                return false;
            }
        }
        return true;
    }

}
