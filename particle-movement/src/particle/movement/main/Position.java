package particle.movement.main;

/**
 * Created by sofia on 9/1/15.
 */

/**
 * Class to represent each position in a linear chamber.
 */
public class Position {

    private PositionType type;

    public Position(PositionType type) {
        this.type = type;
    }

    public PositionType getType() {
        return type;
    }

    @Override
    public String toString() {
        return (PositionType.EMPTY == type) ? "." : "X";
    }

}
