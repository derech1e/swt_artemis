import java.lang.reflect.Modifier;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

 
public class VehicleTest {
    @Test
    public void testVehicleAbstract() {
        Assertions.assertTrue(Modifier.isAbstract(Vehicle.class.getModifiers()), "The class Vehicle should be abstract!");
        Assertions.assertTrue(!Vehicle.class.isInterface(),
                "The class Vehicle should be an abstract class, not an interface!");
        try {
            Assertions.assertTrue(Modifier.isAbstract(Vehicle.class.getMethod("getLength").getModifiers()),
                    "Vehicle.getLength() should be abstract!");
        } catch (NoSuchMethodException e) {
            Assertions.fail("The class Vehicle should have a public method getLength()!");
        }
    }

    @Test
    public void testBicycle() {
        Assertions.assertEquals(1.5, new Bicycle().getLength(), 0, "Bicycle.getLength() should return the correct value!");
    }

    @Test
    public void testBus() {
        Assertions.assertEquals(18.0, new Bus().getLength(), 0, "Bus.getLength() should return the correct value!");
    }

    @Test
    public void testCar() {
        Assertions.assertEquals(6.0, new Car().getLength(), 0, "Car.getLength() should return the correct value!");
    }
}
