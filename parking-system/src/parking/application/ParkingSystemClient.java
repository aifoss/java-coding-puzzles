package parking.application;

import parking.parkinggarage.ParkingGarage;
import parking.parkingsystem.ParkingSystem;
import parking.vehicle.Vehicle;
import parking.vehicle.VehicleFactory;
import parking.vehicle.VehicleType;

/**
 * Created by sofia on 9/5/15.
 */

/**
 * Client program to demonstrate the use of parking system.
 */
public class ParkingSystemClient {

    public static void main(String[] args) {
        ParkingSystem parkingSystem = new ParkingSystem();

        int numSpots1 = 2;
        int[] numSpotsPerType1 = new int[2];
        numSpotsPerType1[0] = 1;
        numSpotsPerType1[1] = 1;

        ParkingGarage garage1 = new ParkingGarage(0, "Alpha", numSpots1, numSpotsPerType1);

        int numSpots2 = 3;
        int[] numSpotsPerType2 = new int[2];
        numSpotsPerType2[0] = 1;
        numSpotsPerType2[1] = 2;

        ParkingGarage garage2 = new ParkingGarage(1, "Bravo", numSpots2, numSpotsPerType2);

        int numSpots3 = 2;
        int[] numSpotsPerType3 = new int[2];
        numSpotsPerType3[0] = 2;
        numSpotsPerType3[1] = 0;

        ParkingGarage garage3 = new ParkingGarage(2, "Charlie", numSpots3, numSpotsPerType3);

        parkingSystem.addGarage(garage1);
        parkingSystem.addGarage(garage2);
        parkingSystem.addGarage(garage3);

        System.out.println(parkingSystem);

        VehicleFactory vehicleFactory = VehicleFactory.getInstance();

        Vehicle car1 = vehicleFactory.createVehicle(VehicleType.CAR, "John");
        Vehicle car2 = vehicleFactory.createVehicle(VehicleType.CAR, "James");
        Vehicle car3 = vehicleFactory.createVehicle(VehicleType.CAR, "Thomas");
        Vehicle car4 = vehicleFactory.createVehicle(VehicleType.CAR, "David");
        Vehicle car5 = vehicleFactory.createVehicle(VehicleType.CAR, "Peter");

        Vehicle truck1 = vehicleFactory.createVehicle(VehicleType.TRUCK, "Tom");
        Vehicle truck2 = vehicleFactory.createVehicle(VehicleType.TRUCK, "Paul");
        Vehicle truck3 = vehicleFactory.createVehicle(VehicleType.TRUCK, "Scott");
        Vehicle truck4 = vehicleFactory.createVehicle(VehicleType.TRUCK, "Henry");

        boolean canPark;

        System.out.println(car1);
        canPark = parkingSystem.assignVehicleToGarage(car1);
        if (!canPark) printNoParkingSpotMessage(car1);

        System.out.println(truck1);
        canPark = parkingSystem.assignVehicleToGarage(truck1);
        if (!canPark) printNoParkingSpotMessage(truck1);

        System.out.println(car2);
        canPark = parkingSystem.assignVehicleToGarage(car2);
        if (!canPark) printNoParkingSpotMessage(car2);

        parkingSystem.checkAllGarageStates();

        System.out.println(truck2);
        canPark = parkingSystem.assignVehicleToGarage(truck2);
        if (!canPark) printNoParkingSpotMessage(truck2);

        System.out.println(car3);
        canPark = parkingSystem.assignVehicleToGarage(car3);
        if (!canPark) printNoParkingSpotMessage(car3);

        System.out.println(truck3);
        canPark = parkingSystem.assignVehicleToGarage(truck3);
        if (!canPark) printNoParkingSpotMessage(truck3);

        parkingSystem.checkAllGarageStates();

        System.out.println(car4);
        canPark = parkingSystem.assignVehicleToGarage(car4);
        if (!canPark) printNoParkingSpotMessage(car4);

        System.out.println(truck4);
        canPark = parkingSystem.assignVehicleToGarage(truck4);
        if (!canPark) printNoParkingSpotMessage(truck4);

        System.out.println(car5);
        canPark = parkingSystem.assignVehicleToGarage(car5);
        if (!canPark) printNoParkingSpotMessage(car5);

        parkingSystem.checkAllGarageStates();

        parkingSystem.processDepartingVehicle(car1);
        parkingSystem.processDepartingVehicle(car3);
        parkingSystem.processDepartingVehicle(truck2);

        parkingSystem.checkAllGarageStates();

        parkingSystem.processDepartingVehicle(truck3);
        parkingSystem.processDepartingVehicle(car4);
        parkingSystem.processDepartingVehicle(car2);
        parkingSystem.processDepartingVehicle(truck1);

        parkingSystem.checkAllGarageStates();

        parkingSystem.checkAllGarageRevenues();
    }

    private static void printNoParkingSpotMessage(Vehicle vehicle) {
        System.out.println("No more available spot for vehicle type " + vehicle.getType());
        System.out.println();
    }

}
