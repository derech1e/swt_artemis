import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

 
public class MaterialTest {
    private static class MaterialDummy extends Material {
        MaterialDummy(String name, double price) {
            super(name, price);
        }

        @Override
        public int getMaterialRequirements(Surface surface) {
            return 13;
        }
    }

    private Material material;

    @BeforeEach
    public void setUp() {
        material = new MaterialDummy("matName", 1234.56);
    }

    @Test
    public void materialTestConstructorNullArgument() {
        try {
            new MaterialDummy(null, 0);
            Assertions.fail(
                    "The constructor of Material should throw a NullPointerException if the name argument is null!");
        } catch (NullPointerException ignored) {
        }
    }

    @Test
    public void materialTestConstructorEmptyString() {
        try {
            new MaterialDummy("", 0);
            Assertions.fail("The constructor of Material should throw an IllegalArgumentException if the name argument is an empty String!");
        } catch (IllegalArgumentException ignored) {
        }
    }

    @Test
    public void materialTestConstructorNegativeArgument() {
        try {
            new MaterialDummy("test", -10.0);
            Assertions.fail("The constructor of Material should throw an IllegalArgumentException if the cost argument is negative!");
        } catch (IllegalArgumentException ignored) {
        }
    }

    @Test
    public void materialTestGetName() {
        Assertions.assertEquals("matName", material.getName(), "Material.getName() should return the correct value!");
    }

    @Test
    public void materialTestGetPricePerUnit() {
        Assertions.assertEquals(1234.56, material.getPricePerUnit(), 0.0001, "Material.getPricePerUnit() should return the correct value!");
    }

    @Test
    public void materialTestGetPriceOfASurfaceNullArgument() {
        try {
            material.getPriceOfASurface(null);
            Assertions.fail("Material.getPriceOfASurface() should throw a NullPointerException if the argument is null!");
        } catch (NullPointerException ignored) {
        }
    }

    @Test
    public void materialTestGetPriceOfASurface() {
        Assertions.assertEquals(16049.28, material.getPriceOfASurface(new Surface(13, 3)), 0.0001,
                "Material.getPriceOfASurface() should return the correct value!");
    }
}
