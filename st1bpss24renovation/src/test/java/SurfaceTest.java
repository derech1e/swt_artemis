import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

 
public class SurfaceTest {
    private Surface testSurface;
    private Material testFlooring, testPaint;

    @BeforeEach
    public void setUp() {
        testSurface = new Surface(2.0, 10.0);
        testPaint = new Paint("Wall paint white", 50.0, 2, 10.0);
        testFlooring = new Flooring("PVC red", 10.0, 2.0);
    }

    @Test
    public void surfaceTestConstructorNonpositiveArgument() {
        try {
            new Surface(-2.0, 10.0);
            Assertions.fail(
                    "Surface.Surface() should throw an IllegalArgumentException if the length argument is negative!");
        } catch (IllegalArgumentException ignored) {
        }

        try {
            new Surface(0.0, 10.0);
            Assertions.fail("Surface.Surface() should throw an IllegalArgumentException if the length argument is zero!");
        } catch (IllegalArgumentException ignored) {
        }

        try {
            new Surface(2.0, -10.0);
            Assertions.fail("Surface.Surface() should throw an IllegalArgumentException if the width argument is negative!");
        } catch (IllegalArgumentException ignored) {
        }

        try {
            new Surface(2.0, 0.0);
            Assertions.fail("Surface.Surface() should throw an IllegalArgumentException if the width argument is zero!");
        } catch (IllegalArgumentException ignored) {
        }
    }

    @Test
    public void surfaceTestSetMaterialNullArgument() {
        try {
            testSurface.setMaterial(null);
            Assertions.fail("Surface.setMaterial() should throw a NullPointerException if the argument is null!");
        } catch (NullPointerException ignored) {
        }
    }

    @Test
    public void surfaceTestSetMaterial() {
        testSurface.setMaterial(testFlooring);
    }

    @Test
    public void surfaceTestGetArea() {
        Assertions.assertEquals(20.0, testSurface.getArea(), 0.0, "Surface.getArea() should return the correct value!");
    }

    @Test
    public void surfaceTestGetPrice() {
        testSurface.setMaterial(testPaint);
        Assertions.assertEquals(400.0, testSurface.getPrice(), 0.0, "Surface.getPrice() should return the correct value!");
        testSurface.setMaterial(testFlooring);
        Assertions.assertEquals(100.0, testSurface.getPrice(), 0.0, "Surface.getPrice() should return the correct value!");
    }

    @Test
    public void surfaceTestAddMaterialRequirementsNullArgument() {
        try {
            testSurface.addMaterialRequirements(null);
            Assertions.fail("Surface.addMaterialRequirements() should throw a NullPointerException if the argument is null!");
        } catch (NullPointerException ignored) {
        }

        testSurface.setMaterial(testPaint);
        try{
            testSurface.addMaterialRequirements(Collections.singletonMap(null, 1));
            Assertions.fail("Surface.addMaterialRequirements() should throw a NullPointerException if a key of the argument is null!");
        } catch (NullPointerException ignored) {
        }
        try{
            testSurface.addMaterialRequirements(Collections.singletonMap("key", null));
            Assertions.fail("Surface.addMaterialRequirements() should throw a NullPointerException if a value of the argument is null!");
        } catch (NullPointerException ignored) {
        }
    }

    @Test
    public void surfaceTestSelectedMaterialNull() {
        try {
            testSurface.addMaterialRequirements(new TreeMap<String, Integer>());
            Assertions.fail("Surface.addMaterialRequirements() should throw a NullPointerException if Surface.selectedMaterial is null!");
        } catch (NullPointerException ignored) {
        }
    }

    @Test
    public void surfaceTestAddMaterialRequirementsEmpty() {
        Map<String, Integer> expectedPaint = new TreeMap<String, Integer>();
        expectedPaint.put("Wall paint white", 8);
        Map<String, Integer> expectedFlooring = new TreeMap<String, Integer>();
        expectedFlooring.put("PVC red", 10);

        testSurface.setMaterial(testPaint);
        Map<String, Integer> actualMap = testSurface.addMaterialRequirements(new TreeMap<String, Integer>());
        Assertions.assertEquals(expectedPaint, actualMap, "Surface.addMaterialRequirements() should return a Map containing the correct entries!");
        testSurface.setMaterial(testFlooring);
        actualMap = testSurface.addMaterialRequirements(new TreeMap<String, Integer>());
        Assertions.assertEquals(expectedFlooring, actualMap,
                "Surface.addMaterialRequirements() should return a Map containing the correct entries!");
    }

    @Test
    public void surfaceTestAddMaterialRequirementsFilled() {
        Map<String, Integer> expectedPaint = new TreeMap<String, Integer>();
        expectedPaint.put("Wall paint white", 11);
        Map<String, Integer> expectedFlooring = new TreeMap<String, Integer>();
        expectedFlooring.put("PVC red", 14);

        Map<String, Integer> materialRequirements1 = new TreeMap<String, Integer>();
        materialRequirements1.put("Wall paint white", 3);
        Map<String, Integer> materialRequirements2 = new TreeMap<String, Integer>();
        materialRequirements2.put("PVC red", 4);

        testSurface.setMaterial(testPaint);
        Map<String, Integer> actualMap = testSurface.addMaterialRequirements(materialRequirements1);
        Assertions.assertEquals(expectedPaint, actualMap, "Surface.addMaterialRequirements() should return a Map containing the correct entries!");
        testSurface.setMaterial(testFlooring);
        actualMap = testSurface.addMaterialRequirements(materialRequirements2);
        Assertions.assertEquals(expectedFlooring, actualMap, "Surface.addMaterialRequirements() should return a Map containing the correct entries!");
    }

    @Test
    public void surfaceTestDontChangeArgumentAddMaterialRequirements() {
        testSurface.setMaterial(new Paint("Paint", 1, 1, 1));
        Map<String, Integer> materialRequirements = Collections.singletonMap("Wood", 4);
        try{
            testSurface.addMaterialRequirements(materialRequirements);
        } catch (UnsupportedOperationException e) {
            Assertions.fail("Surface.addMaterialRequirements() shouldn't change the given Map. Instead, a new Map should be used!");
        }
    }
}
