package parking.parkingsystem;

import parking.parkinggarage.ParkingGarage;
import parking.vehicle.Vehicle;

/**
 * Created by sofia on 9/5/15.
 */

/**
 * Interface containing methods pertaining to parking system.
 */
public interface ParkingSystemInterface {

    boolean assignVehicleToGarage(Vehicle vehicle);
    boolean processDepartingVehicle(Vehicle vehicle);

    ParkingGarage getGarageForVehicle(Vehicle vehicle);

    String getCurrentStateReport(ParkingGarage parkingGarage);
    String getDailyRevenueReport(ParkingGarage parkingGarage);

    void checkAllGarageStates();
    void checkAllGarageRevenues();

}
