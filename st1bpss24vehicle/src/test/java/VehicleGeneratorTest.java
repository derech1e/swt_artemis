import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

 
public class VehicleGeneratorTest {
    @Test
    public void testVehicleGenerator() {
        VehicleGenerator generator = new VehicleGenerator();
        Set<Class<?>> generatedVehicleTypes = new HashSet<>();

        /*
         * This loop assumes that 100 random vehicles are enough to get each vehicle type at least once. (The
         * probability that one of three vehicle types does not appear should be low enough.)
         */
        for (int i = 0; i < 100; i++) {
            generatedVehicleTypes.add(generator.createVehicle().getClass());
        }

        Assertions.assertTrue(generatedVehicleTypes.size() == 3,
                "VehicleGenerator.createVehicle() should generate vehicles randomly!");
    }
}
