package parking.parkingspot;

import parking.vehicle.Vehicle;
import parking.vehicle.VehicleType;

import java.util.Date;

/**
 * Created by sofia on 9/5/15.
 */

/**
 * Class to represent parking spot in a garage.
 */
public class ParkingSpot {

    private int id;
    private int garageId;

    private VehicleType type;
    private ParkingSpotState state;

    private Vehicle parkedVehicle;

    private Date vehicleArrivalTimestamp;
    private Date vehicleDepartureTimestamp;

    public ParkingSpot(int id, int garageId, VehicleType type) {
        this.id = id;
        this.garageId = garageId;
        this.type = type;
        this.state = ParkingSpotState.EMPTY;
    }

    public int getId() { return id; }

    public int getGarageId() { return garageId; }

    public VehicleType getType() {
        return type;
    }

    public ParkingSpotState getState() {
        return state;
    }

    public Vehicle getParkedVehicle() {
        return parkedVehicle;
    }

    public Date getVehicleArrivalTimestamp() {
        return vehicleArrivalTimestamp;
    }

    public Date getVehicleDepartureTimestamp() {
        return vehicleDepartureTimestamp;
    }

    public void setType(VehicleType type) { this.type = type; }

    public void setState(ParkingSpotState state) {
        this.state = state;
    }

    public void setParkedVehicle(Vehicle parkedVehicle) {
        this.parkedVehicle = parkedVehicle;
    }

    public void setVehicleArrivalTimestamp(Date vehicleArrivalTimestamp) {
        this.vehicleArrivalTimestamp = vehicleArrivalTimestamp;
    }

    public void setVehicleDepartureTimestamp(Date vehicleDepartureTimestamp) {
        this.vehicleDepartureTimestamp = vehicleDepartureTimestamp;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[[[Parking Spot]]]").append(System.getProperty("line.separator"));
        sb.append("ID: ").append(getId()).append(System.getProperty("line.separator"));
        sb.append("Garage ID: ").append(getGarageId()).append(System.getProperty("line.separator"));
        sb.append("Type: ").append(getType()).append(System.getProperty("line.separator"));
        sb.append("State: ").append(getState()).append(System.getProperty("line.separator"));
        if (ParkingSpotState.OCCUPIED == getState()) {
            sb.append("Occupying Vehicle Owner: ").append(getParkedVehicle().getOwner()).append(System.getProperty("line.separator"));
            sb.append("Vehicle Arrival Time: ").append(getVehicleArrivalTimestamp().toString()).append(System.getProperty("line.separator"));
        }
        sb.append(System.getProperty("line.separator"));
        return sb.toString();
    }

}
