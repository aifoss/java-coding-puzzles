package particle.movement.main;

/**
 * Created by sofia on 4/25/17.
 */

/**
 * Enum to represent position types.
 */
public enum PositionType {

    EMPTY('.'),
    LEFT('L'),
    RIGHT('R'),
    BOTH('B');

    private char code;

    PositionType(char code) {
        this.code = code;
    }

    public char getCode() {
        return code;
    }

}
