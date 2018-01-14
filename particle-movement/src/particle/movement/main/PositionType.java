package particle.movement.main;

/**
 * Created by sofia on 9/1/15.
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
