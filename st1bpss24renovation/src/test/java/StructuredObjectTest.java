import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

 
public class StructuredObjectTest {
    private StructuredObject structuredObject;

    @BeforeEach
    public void setUp() {
        structuredObject = new StructuredObject();
    }

    @Test
    public void structuredObjectTestPartsType() {
        try {
            Assertions.assertEquals(Set.class, structuredObject.getClass()
                    .getDeclaredField("parts").getType(), "StructuredObject.parts should be a Set!");
        } catch (NoSuchFieldException nsfe) {
            Assertions.fail("The class StructuredObject should have an attribute named parts!");
        }
    }

    @Test
    public void structuredObjectTestAddNullArgument() {
        try {
            structuredObject.add(null);
            Assertions.fail("StructuredObject.add() should throw a NullPointerException if the argument is null!");
        } catch (NullPointerException ignored) {
        }
    }

    @Test
    public void structuredObjectTestAddMaterialRequirementsNullArgument() {
        try {
            structuredObject.addMaterialRequirements(null);
            Assertions.fail(
                    "StructuredObject.addMaterialRequirements() should throw a NullPointerException if the argument is null!");
        } catch (NullPointerException ignored) {
        }
        try {
            structuredObject.addMaterialRequirements(Collections.singletonMap(null, 1));
            Assertions.fail(
                    "StructuredObject.addMaterialRequirements() should throw a NullPointerException if a key of the argument is null!");
        } catch (NullPointerException ignored) {
        }
        try {
            structuredObject.addMaterialRequirements(Collections.singletonMap("key", null));
            Assertions.fail("StructuredObject.addMaterialRequirements() should throw a NullPointerException if a value of the argument is null!");
        } catch (NullPointerException ignored) {
        }
    }

    @Test
    public void structuredObjectTestFloorSurface() {
        Surface surface = new Surface(2.0, 3.0);
        Material material = new Flooring("PVC red", 5.0, 2.0);
        surface.setMaterial(material);
        structuredObject.add(surface);

        Map<String, Integer> expectedMaterials = new TreeMap<String, Integer>();
        expectedMaterials.put("PVC red", 3);

        Map<String, Integer> actualMaterials = new TreeMap<String, Integer>();
        actualMaterials = structuredObject.addMaterialRequirements(actualMaterials);

        Assertions.assertEquals(expectedMaterials, actualMaterials,
                "StructuredObject.addMaterialRequirements() should return a Map containing the correct entries!");
        Assertions.assertEquals(15.0, structuredObject.getPrice(), 0.0, "StructuredObject.getPrice() should return the correct value!");
    }

    @Test
    public void structuredObjectTestSurface() {
        StructuredObject structuredObject2 = new StructuredObject();
        Surface surface1 = new Surface(2.0, 3.0);
        Surface surface2 = new Surface(3.0, 4.0);

        Material material1 = new Flooring("PVC red", 10.0, 2.0);
        Material material2 = new Paint("Wall paint green", 50.0, 2, 10.0);

        surface1.setMaterial(material1);
        surface2.setMaterial(material2);

        structuredObject.add(surface1);
        structuredObject.add(structuredObject2);

        structuredObject2.add(surface2);
        structuredObject2.add(surface2);

        Map<String, Integer> expectedMaterials = new TreeMap<String, Integer>();
        expectedMaterials.put("PVC red", 3);
        expectedMaterials.put("Wall paint green", 5);

        Map<String, Integer> actualMaterials = new TreeMap<String, Integer>();
        actualMaterials = structuredObject.addMaterialRequirements(actualMaterials);

        Assertions.assertEquals(expectedMaterials, actualMaterials, "RenovationObject.addMaterialRequirements() " +
                "should return a Map containing the correct entries");
        Assertions.assertEquals(280.0, structuredObject.getPrice(), 0.0, "RenovationObject.getPrice() should return the correct value!");
    }

    @Test
    public void structuredObjectTestDontChangeArgumentAddMaterialRequirements() {
        structuredObject.add(new StructuredObject());
        Map<String, Integer> mat = Collections.singletonMap("Wood", 4);
        try{
            structuredObject.addMaterialRequirements(mat);
        } catch (UnsupportedOperationException e) {
            Assertions.fail("StructuredObject.addMaterialRequirements() shouldn't change the given Map. Instead, a new Map should be used!");
        }
    }
}
