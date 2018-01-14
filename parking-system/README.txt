Package/Directory Structure of Parking System Design:

parking
------- vehicle
	   	------- VehicleType (enum)
	   	------- Vehicle (abstract class)
	   	------- Car
	   	------- Truck
	   	------- VehicleFactory
------- parkingspot
	   	------- ParkingSpotState (enum)
	   	------- ParkingSpot
------- parkinggarage
	   	------- ParkingGarageInterface (interface)
	   	------- ParkingGarage
------- parkingsystem
	   	------- ParkingSystemInterface (interface)
	   	------- ParkingSystem
------- application
	   	------- ParkingSystemClient (client program to demonstrate use of parking system)
