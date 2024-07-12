import java.lang.reflect.Modifier;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

 
public class RenovationObjectTest {
    @Test
    public void testAbstract() {
        Assertions.assertTrue(Modifier.isAbstract(RenovationObject.class.getModifiers()),
                "RenovationObject should be an abstract class!");
        Assertions.assertFalse(RenovationObject.class.isInterface(),
                "RenovationObject should be an abstract class, not an interface!");
    }

    @Test
    public void testGetPriceAbstract() {
        try {
            Assertions.assertTrue(Modifier.isAbstract(RenovationObject.class.getDeclaredMethod("getPrice").getModifiers()), "RenovationObject.getPrice() should be abstract!");
        } catch (NoSuchMethodException e) {
            Assertions.fail("RenovationObject should have a method named getPrice with no parameters!");
        }
    }

    @Test
    public void testAddMaterialRequirementsAbstract() {
        try {
            Assertions.assertTrue(Modifier.isAbstract(RenovationObject.class.getDeclaredMethod("addMaterialRequirements", Map.class)
                    .getModifiers()), "RenovationObject.addMaterialRequirements() should be abstract!");
        } catch (NoSuchMethodException e) {
            Assertions.fail("RenovationObject should have a method named addMaterialRequirements with a parameter of type Map!");
        }
    }
}
