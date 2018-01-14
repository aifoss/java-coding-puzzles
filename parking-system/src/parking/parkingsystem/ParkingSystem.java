package parking.parkingsystem;

import parking.parkinggarage.ParkingGarage;
import parking.parkingspot.ParkingSpot;
import parking.vehicle.Vehicle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sofia on 9/5/15.
 */

/**
 * Class to represent parking system, implementing methods in {@link ParkingSystemInterface}.
 */
public class ParkingSystem implements ParkingSystemInterface {

    private int numGarages = 0;
    private List<ParkingGarage> garages = new ArrayList<>();
    private Map<String,Integer> vehicleToGarageMap = new HashMap<>();

    public ParkingSystem() {}

    public void addGarage(ParkingGarage garage) {
        garages.add(garage);
        numGarages++;
    }

    public int getNumGarages() {
        return numGarages;
    }

    public List<ParkingGarage> getGarages() {
        return garages;
    }

    public boolean isEmpty() {
        for (ParkingGarage garage : garages) {
            if (!garage.isEmpty()) return false;
        }
        return true;
    }

    public boolean isFull() {
        for (ParkingGarage garage : garages) {
            if (!garage.isFull()) return false;
        }
        return true;
    }

    @Override
    public boolean assignVehicleToGarage(Vehicle vehicle) {
        for (ParkingGarage garage : getGarages()) {
            ParkingSpot availableSpot = garage.findEmptyParkingSpot(vehicle.getType());

            if (null != availableSpot) {
                garage.placeArrivingVehicle(vehicle, availableSpot);
                vehicleToGarageMap.put(vehicle.getOwner(), garage.getId());

                StringBuilder sb = new StringBuilder();
                sb.append(vehicle.getType()).append(" owned by ").append(vehicle.getOwner()).append(" placed");
                sb.append(" in Garage ").append(garage.getName());
                sb.append(" at Spot ").append(availableSpot.getId());
                System.out.println(sb);
                System.out.println();

                System.out.println(availableSpot);

                return true;
            }
        }

        System.out.println("No available spot to park vehicle for " + vehicle.getOwner());
        System.out.println();

        return false;
    }

    @Override
    public boolean processDepartingVehicle(Vehicle vehicle) {
        int garageId = vehicleToGarageMap.get(vehicle.getOwner());
        ParkingGarage garage = garages.get(garageId);

        if (null == garage) {
            System.out.println("No garage associated with vehicle for " + vehicle.getOwner());
            System.out.println();
            return false;
        }

        garage.processDepartingVehicle(vehicle);

        StringBuilder sb = new StringBuilder();
        sb.append(vehicle.getType()).append(" owned by ").append(vehicle.getOwner()).append(" removed");
        sb.append(" from Garage ").append(garage.getName());
        System.out.println(sb);
        System.out.println();

        return true;
    }

    @Override
    public ParkingGarage getGarageForVehicle(Vehicle vehicle) {
        int garageId = vehicleToGarageMap.get(vehicle.getOwner());
        return garages.get(garageId);
    }

    @Override
    public String getCurrentStateReport(ParkingGarage garage) {
        return garage.generateCurrentStateReport();
    }

    @Override
    public String getDailyRevenueReport(ParkingGarage garage) {
        return garage.generateDailyRevenueReport();
    }

    @Override
    public void checkAllGarageStates() {
        System.out.println();
        System.out.println("[[[Current State of Each Garage]]]");
        System.out.println();
        for (ParkingGarage garage : getGarages()) {
            System.out.println("Garage " + garage.getName() + " (" + garage.getId() + "):");
            String report = getCurrentStateReport(garage);
            System.out.println(report);
        }
        System.out.println();
    }

    @Override
    public void checkAllGarageRevenues() {
        System.out.println();
        System.out.println("[[[Revenues from Each Garage]]]");
        System.out.println();
        for (ParkingGarage garage : getGarages()) {
            System.out.println("Garage " + garage.getName() + " (" + garage.getId() + "):");
            String report = getDailyRevenueReport(garage);
            System.out.println(report);
        }
        System.out.println();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[[[Parking System]]]").append(System.getProperty("line.separator"));
        sb.append(System.getProperty("line.separator"));
        sb.append("Number of garages: ").append(getNumGarages()).append(System.getProperty("line.separator"));
        sb.append("Garages:").append(System.getProperty("line.separator"));
        sb.append(System.getProperty("line.separator"));
        for (ParkingGarage parkingGarage : getGarages()) {
            sb.append(parkingGarage.toString());
        }
        return sb.toString();
    }

}
