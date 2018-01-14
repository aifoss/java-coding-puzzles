package parking.vehicle;

/**
 * Created by sofia on 9/5/15.
 */

/**
 * Enum representing different {@link Vehicle} types and associated codes and parking rates.
 */
public enum VehicleType {

    CAR(0, 5.00),
    TRUCK(1, 7.50);

    private int code;
    private double rate;

    public static VehicleType getTypeByCode(int code) {
        for (VehicleType type : values()) {
            if (type.code == code) return type;
        }
        return null;
    }

    VehicleType(int code, double rate) {
        this.code = code;
        this.rate = rate;
    }

    public int getCode() {
        return code;
    }

    public double getRate() {
        return rate;
    }

}
