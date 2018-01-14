package parking.vehicle;

/**
 * Created by sofia on 9/5/15.
 */

/**
 * Abstract class to represent vehicle.
 */
public abstract class Vehicle {

    private VehicleType type;
    private String owner;

    public Vehicle(VehicleType type, String owner) {
        this.type = type;
        this.owner = owner;
    }

    public VehicleType getType() {
        return type;
    }

    public String getOwner() {
        return owner;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[[[Vehicle]]]").append(System.getProperty("line.separator"));
        sb.append("Type: ").append(getType()).append(System.getProperty("line.separator"));
        sb.append("Owner: ").append(getOwner()).append(System.getProperty("line.separator"));
        return sb.toString();
    }

}
