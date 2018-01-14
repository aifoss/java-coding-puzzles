package parking.parkinggarage;

import parking.parkingspot.ParkingSpot;
import parking.vehicle.Vehicle;
import parking.vehicle.VehicleType;

/**
 * Created by sofia on 9/5/15.
 */

/**
 * Interface containing methods pertaining to parking garage.
 */
public interface ParkingGarageInterface {

    ParkingSpot getParkingSpotById(int id);

    ParkingSpot findEmptyParkingSpot(VehicleType type);
    void placeArrivingVehicle(Vehicle vehicle, ParkingSpot parkingSpot);
    void processDepartingVehicle(Vehicle vehicle);

    int getNumOccupyingCars();
    int getNumOccupyingTrucks();

    String getVehicleForParkingSpot(ParkingSpot parkingSpot);
    ParkingSpot getParkingSpotForVehicle(String ownerName);

    double getDailyRevenueFromCars();
    double getDailyRevenueFromTrucks();

    String generateCurrentStateReport();
    String generateDailyRevenueReport();

}
