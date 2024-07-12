import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

 
public class PaintTest {
    private Paint paint;
    private Surface[] surfaces = new Surface[6];
    private int[] results = new int[6];

    @BeforeEach
    public void setUp() {
        paint = new Paint("Wall paint white", 50.0, 2, 10.0);
        surfaces[0] = new Surface(5.0, 2.0);
        surfaces[0].setMaterial(paint);
        surfaces[1] = new Surface(5.0, 2.01);
        surfaces[1].setMaterial(paint);
        surfaces[2] = new Surface(5.0, 2.02);
        surfaces[2].setMaterial(paint);
        surfaces[3] = new Surface(1.25, 2);
        surfaces[3].setMaterial(paint);
        surfaces[4] = new Surface(1.275, 2);
        surfaces[4].setMaterial(paint);
        surfaces[5] = new Surface(5.0, 1.52);
        surfaces[5].setMaterial(paint);

        results[0] = 4; // 2.00 l
        results[1] = 4; // 2.01 l
        results[2] = 5; // 2.02 l
        results[3] = 1; // 0.50 l
        results[4] = 1; // 0.51 l
        results[5] = 4; // 1.52 l
    }

    @Test
    public void paintTestConstructorNullArgument() {
        try {
            new Paint(null, 10.0, 1, 10.0);
            Assertions.fail("Paint.Paint() should throw a NullPointerException if the name argument is null!");
        } catch (NullPointerException ignored) {
        }
    }

    @Test
    public void paintTestConstructorIllegalArgument() {
        try {
            new Paint("", 10.0, 1, 10.0);
            Assertions.fail("Paint.Paint() should throw an IllegalArgumentException if the name argument is empty!");
        } catch (IllegalArgumentException ignored) {
        }
        
        try {
            new Paint("name", -10.0, 1, 10.0);
            Assertions.fail("Paint.Paint() should throw an IllegalArgumentException if the price argument is negative!");
        } catch (IllegalArgumentException ignored) {
        }
    }

    @Test
    public void paintTestConstructorNonpositiveArgument() {
        try {
            new Paint("name", 50.0, 0, 10.0);
            Assertions.fail("Paint.Paint() should throw an IllegalArgumentException if the noOfCoats argument is zero!");
        } catch (IllegalArgumentException ignored) {
        }

        try {
            new Paint("name", 50.0, -2, 10.0);
            Assertions.fail(
                    "Paint.Paint() should throw an IllegalArgumentException if the noOfCoats argument is negative!");
        } catch (IllegalArgumentException ignored) {
        }

        try {
            new Paint("name", 50.0, 1, 0.0);
            Assertions.fail("Paint.Paint() should throw an IllegalArgumentException if the noOfSqMPerLiter argument is zero!");
        } catch (IllegalArgumentException ignored) {
        }

        try {
            new Paint("name", 50.0, 1, -10.0);
            Assertions.fail("Paint.Paint() should throw an IllegalArgumentException if the noOfSqMPerLiter argument is negative!");
        } catch (IllegalArgumentException ignored) {
        }
    }

    @Test
    public void paintTestGetNumberOfCoats() {
        Assertions.assertEquals(2, paint.getNumberOfCoats(), "Paint.getNumberOfCoats() should return the correct value!");
    }

    @Test
    public void paintTestGetSquareMetersPerLiter() {
        Assertions.assertEquals(10.0, paint.getSquareMetersPerLiter(), 0.0, "Paint.getSquareMetersPerLiter() should return the correct value!");
    }

    @Test
    public void paintTestGetMaterialRequirementsNullArgument() {
        try {
            paint.getMaterialRequirements(null);
            Assertions.fail("Paint.getMaterialRequirements() should throw a NullPointerException if the argument is null!");
        } catch (NullPointerException ignored) {
        }
    }

    @Test
    public void paintTestGetMaterialRequirements() {
        Assertions.assertEquals(results[0], paint.getMaterialRequirements(surfaces[0]),
                "Paint.getMaterialRequirements() should calculate the required materials using the given formula!");
        Assertions.assertEquals(results[1], paint.getMaterialRequirements(surfaces[1]),
                "Paint.getMaterialRequirements() should round down if the tolerance is less than 0.02!");
        Assertions.assertEquals(results[2], paint.getMaterialRequirements(surfaces[2]),
                "Paint.getMaterialRequirements() should round up if the tolerance is higher than or equal to 0.02!");
        Assertions.assertEquals(results[3], paint.getMaterialRequirements(surfaces[3]),
                "Paint.getMaterialRequirements() should round down if the tolerance is less than 0.02!");
        Assertions.assertEquals(results[4], paint.getMaterialRequirements(surfaces[4]),
                "Paint.getMaterialRequirements() should round down if the tolerance is less than 0.02!");
        Assertions.assertEquals(results[5], paint.getMaterialRequirements(surfaces[5]), "Paint.getMaterialRequirements() should round up if the tolerance is higher than or equal to 0.02!");
    }
}
