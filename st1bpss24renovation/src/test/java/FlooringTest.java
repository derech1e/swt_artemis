import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

 
public class FlooringTest {
    private Flooring flooringUnderTest;
    private Surface[] surfaces;
    private int[] results;

    @BeforeEach
    public void setUp() {
        surfaces = new Surface[3];
        results = new int[3];

        flooringUnderTest = new Flooring("PVC red", 10.0, 2.0);
        surfaces[0] = new Surface(5.0, 2.0);
        surfaces[0].setMaterial(flooringUnderTest);
        surfaces[1] = new Surface(5.0, 2.808);
        surfaces[1].setMaterial(flooringUnderTest);
        surfaces[2] = new Surface(5.0, 2.804);
        surfaces[2].setMaterial(flooringUnderTest);

        results[0] = 5; // 5.00
        results[1] = 8; // 7.02
        results[2] = 7; // 7.01
    }

    @Test
    public void flooringTestConstructorNullArgument() {
        try {
            new Flooring(null, 10.0, 10.0);
            Assertions.fail("Flooring.Flooring() should throw a NullPointerException if the name argument is null!");
        } catch (NullPointerException ignored) {
        }
    }

    @Test
    public void flooringTestConstructorIllegalArgument() {
        try {
            new Flooring("", 10.0, 10.0);
            Assertions.fail(
                    "Flooring.Flooring() should throw an IllegalArgumentException if the name argument is empty!");
        } catch (IllegalArgumentException ignored) {
        }

        try {
            new Flooring("name", -10.0, 10.0);
            Assertions.fail("Flooring.Flooring() should throw an IllegalArgumentException if the price argument is negative!");
        } catch (IllegalArgumentException ignored) {
        }
    }


    @Test
    public void flooringTestConstructorNonpositiveArgument() {
        try {
            new Flooring("name", 10.0, 0.0);
            Assertions.fail("The constructor of Flooring should throw an IllegalArgumentException if the width argument is zero!");
        } catch (IllegalArgumentException ignored) {
        }

        try {
            new Flooring("name", 10.0, -2.0);
            Assertions.fail("The constructor of Flooring should throw an IllegalArgumentException if the width argument is negative!");
        } catch (IllegalArgumentException ignored) {
        }
    }

    @Test
    public void flooringTestGetWidth() {
        Assertions.assertEquals(2.0, flooringUnderTest.getWidth(), 0.0, "Flooring.getWidth() should return the correct value!");
    }

    @Test
    public void flooringTestGetMaterialRequirementsNullArgument() {
        try {
            flooringUnderTest.getMaterialRequirements(null);
            Assertions.fail("Flooring.getMaterialRequirements() should throw a NullPointerException if the argument is null!");
        } catch (NullPointerException ignored) {
        }
    }

    @Test
    public void flooringTestGetMaterialRequirements() {
        Assertions.assertEquals(results[0], flooringUnderTest.getMaterialRequirements(surfaces[0]),
                "Flooring.getMaterialRequirements() should calculate the required materials using the given formula!");
        Assertions.assertEquals(results[1], flooringUnderTest.getMaterialRequirements(surfaces[1]),
                "Flooring.getMaterialRequirements() should round up if the tolerance is higher than or equal to 0.02!");
        Assertions.assertEquals(results[2], flooringUnderTest.getMaterialRequirements(surfaces[2]),
                "Flooring.getMaterialRequirements() should round down if the tolerance is less than 0.02!");
    }
}
