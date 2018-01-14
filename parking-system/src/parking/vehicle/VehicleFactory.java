package parking.vehicle;

/**
 * Created by sofia on 9/5/15.
 */

/**
 * Factory class to handle creation of {@link Vehicle} objects.
 */
public class VehicleFactory {

    private static VehicleFactory instance = null;

    private VehicleFactory() {}

    public static VehicleFactory getInstance() {
        if (null == instance) {
            instance = new VehicleFactory();
        }
        return instance;
    }

    public Vehicle createVehicle(VehicleType type, String owner) {
        switch(type) {
            case CAR: return new Car(type, owner);
            case TRUCK: return new Truck(type, owner);
        }
        return null;
    }

}
