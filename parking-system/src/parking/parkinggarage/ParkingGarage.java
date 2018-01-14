package parking.parkinggarage;

import parking.parkingspot.ParkingSpot;
import parking.parkingspot.ParkingSpotState;
import parking.vehicle.Vehicle;
import parking.vehicle.VehicleType;

import java.util.*;

/**
 * Created by sofia on 9/5/15.
 */

/**
 * Class to represent parking garage, implementing methods in {@link ParkingGarageInterface}.
 */
public class ParkingGarage implements ParkingGarageInterface {

    private int id;
    private String name;

    private int totalCapacity;
    private int[] numParkingSpotsPerType;

    private int numOccupied;
    private int[] numOccupiedParkingSpotsPerType;

    private double totalDailyRevenue;
    private double[] dailyRevenuesPerType;

    private List<ParkingSpot> parkingSpots;

    Map<Integer,String> parkingSpotToVehicleMap;
    Map<String,Integer> vehicleToParkingSpotMap;

    public ParkingGarage(int id, String name, int numSpots, int[] numSpotsPerType) {
        this.id = id;
        this.name = name;

        totalCapacity = numSpots;
        parkingSpots = new ArrayList<>();
        numParkingSpotsPerType = numSpotsPerType;

        int numParkingSpotTypes = numSpotsPerType.length;
        int index = 0;
        for (int i = 0; i < numParkingSpotTypes; i++) {
            VehicleType type = VehicleType.getTypeByCode(i);
            for (int j = 0; j < numSpotsPerType[i]; j++) {
                parkingSpots.add(new ParkingSpot(index++, id, type));
            }
        }

        numOccupied = 0;
        numOccupiedParkingSpotsPerType = new int[numParkingSpotTypes];

        totalDailyRevenue = 0.0;
        dailyRevenuesPerType = new double[numParkingSpotTypes];

        parkingSpotToVehicleMap = new HashMap<>();
        vehicleToParkingSpotMap = new HashMap<>();
    }

    public int getId() { return id; }

    public String getName() {
        return name;
    }

    public int getTotalCapacity() {
        return totalCapacity;
    }

    public int getNumOccupied() {
        return numOccupied;
    }

    public double getTotalDailyRevenue() {
        return totalDailyRevenue;
    }

    public boolean isEmpty() {
        return numOccupied == 0;
    }

    public boolean isFull() {
        return numOccupied == totalCapacity;
    }

    @Override
    public ParkingSpot getParkingSpotById(int id) {
        return parkingSpots.get(id);
    }

    @Override
    public ParkingSpot findEmptyParkingSpot(VehicleType type) {
        int code = type.getCode();
        int numSpotsForType = numParkingSpotsPerType[code];
        int numOccupiedSpots = numOccupiedParkingSpotsPerType[code];
        int numEmptySpots = numSpotsForType - numOccupiedSpots;
        if (numEmptySpots == 0) return null;
        int start = (code == 0) ? 0 : numParkingSpotsPerType[code-1];
        int index = start + numOccupiedSpots;
        return parkingSpots.get(index);
    }

    @Override
    public void placeArrivingVehicle(Vehicle vehicle, ParkingSpot parkingSpot) {
        parkingSpot.setParkedVehicle(vehicle);
        parkingSpot.setState(ParkingSpotState.OCCUPIED);
        parkingSpot.setVehicleArrivalTimestamp(new Date());
        parkingSpotToVehicleMap.put(parkingSpot.getId(), vehicle.getOwner());
        vehicleToParkingSpotMap.put(vehicle.getOwner(), parkingSpot.getId());
        numOccupied++;
        numOccupiedParkingSpotsPerType[parkingSpot.getType().getCode()]++;
    }

    @Override
    public void processDepartingVehicle(Vehicle vehicle) {
        int parkingSpotId = vehicleToParkingSpotMap.get(vehicle.getOwner());
        ParkingSpot parkingSpot = getParkingSpotById(parkingSpotId);
        calculateParkingFee(parkingSpot);
        markParkingSpotAsEmpty(parkingSpot);
        parkingSpotToVehicleMap.remove(parkingSpot.getId());
        vehicleToParkingSpotMap.remove(vehicle.getOwner());
        numOccupied--;
        numOccupiedParkingSpotsPerType[parkingSpot.getType().getCode()]--;
    }

    private double calculateParkingFee(ParkingSpot parkingSpot) {
        Date currentTimestamp = new Date();
        parkingSpot.setVehicleDepartureTimestamp(currentTimestamp);
        Date departureTime = parkingSpot.getVehicleDepartureTimestamp();
        Date arrivalTime = parkingSpot.getVehicleArrivalTimestamp();
        long diff = departureTime.getTime() - arrivalTime.getTime();
        // int diffHours = (int) diff / (60 * 60 * 1000) % 24;

        // for demo use in the sample client program
        Random random = new Random();
        int diffHours = random.nextInt(5);

        double hourlyRate = parkingSpot.getType().getRate();
        double currentCharge = hourlyRate * diffHours;
        totalDailyRevenue += currentCharge;
        dailyRevenuesPerType[parkingSpot.getType().getCode()] += currentCharge;
        return currentCharge;
    }

    private void markParkingSpotAsEmpty(ParkingSpot parkingSpot) {
        parkingSpot.setState(ParkingSpotState.EMPTY);
        parkingSpot.setParkedVehicle(null);
        parkingSpot.setVehicleArrivalTimestamp(null);
        parkingSpot.setVehicleDepartureTimestamp(null);
    }

    @Override
    public int getNumOccupyingCars() {
        int index = VehicleType.CAR.getCode();
        return numOccupiedParkingSpotsPerType[index];
    }

    @Override
    public int getNumOccupyingTrucks() {
        int index = VehicleType.TRUCK.getCode();
        return numOccupiedParkingSpotsPerType[index];
    }

    @Override
    public String getVehicleForParkingSpot(ParkingSpot parkingSpot) {
        return parkingSpotToVehicleMap.get(parkingSpot.getId());
    }

    @Override
    public ParkingSpot getParkingSpotForVehicle(String ownerName) {
        int id = vehicleToParkingSpotMap.get(ownerName);
        return parkingSpots.get(id);
    }

    @Override
    public double getDailyRevenueFromCars() {
        int index = VehicleType.CAR.getCode();
        return dailyRevenuesPerType[index];
    }

    @Override
    public double getDailyRevenueFromTrucks() {
        int index = VehicleType.TRUCK.getCode();
        return dailyRevenuesPerType[index];
    }

    @Override
    public String generateCurrentStateReport() {
        StringBuilder report = new StringBuilder();
        report.append("Total number of vehicles = ").append(getNumOccupied()).append(System.getProperty("line.separator"));
        int codeForCar = VehicleType.CAR.getCode();
        report.append("Number of cars = ").append(numOccupiedParkingSpotsPerType[codeForCar]).append(System.getProperty("line.separator"));
        int codeForTruck = VehicleType.TRUCK.getCode();
        report.append("Number of trucks = ").append(numOccupiedParkingSpotsPerType[codeForTruck]).append(System.getProperty("line.separator"));
        int numAvailable = totalCapacity - numOccupied;
        report.append("Total number of available spots = ").append(numAvailable).append(System.getProperty("line.separator"));
        int numAvailableForCars = numParkingSpotsPerType[codeForCar] - numOccupiedParkingSpotsPerType[codeForCar];
        report.append("Number of available spots for cars = " + numAvailableForCars).append(System.getProperty("line.separator"));
        int numAvailableForTrucks = numParkingSpotsPerType[codeForTruck] - numOccupiedParkingSpotsPerType[codeForTruck];
        report.append("Number of available spots for trucks = " + numAvailableForTrucks).append(System.getProperty("line.separator"));
        return report.toString();
    }

    @Override
    public String generateDailyRevenueReport() {
        StringBuilder report = new StringBuilder();
        report.append("Total daily revenue = ").append(getTotalDailyRevenue()).append(" dollars").append(System.getProperty("line.separator"));
        int code = VehicleType.CAR.getCode();
        report.append("Revenue from cars = ").append(dailyRevenuesPerType[code]).append(" dollars").append(System.getProperty("line.separator"));
        code = VehicleType.TRUCK.getCode();
        report.append("Revenue from trucks = ").append(dailyRevenuesPerType[code]).append(" dollars").append(System.getProperty("line.separator"));
        return report.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[[[Garage]]]").append(System.getProperty("line.separator"));
        sb.append("ID: ").append(getId()).append(System.getProperty("line.separator"));
        sb.append("Name: ").append(getName()).append(System.getProperty("line.separator"));
        sb.append("Capacity: ").append(getTotalCapacity()).append(System.getProperty("line.separator"));
        sb.append("Occupied: ").append(getNumOccupied()).append(System.getProperty("line.separator"));
        sb.append(System.getProperty("line.separator"));
        return sb.toString();
    }

}
