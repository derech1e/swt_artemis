import java.util.*;

public class VehicleGenerator {
    private Random randomGenerator;

    public VehicleGenerator() {
        randomGenerator = new Random();
    }

    public Vehicle createVehicle() {
        switch(randomGenerator.nextInt(3)) {
            case 0:
                return new Bicycle();
            case 1:
                return new Bus();
            default:
                return new Car();
        }
    }

}