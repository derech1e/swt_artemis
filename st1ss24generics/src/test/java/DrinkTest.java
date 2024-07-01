import java.lang.reflect.Modifier;

import drinks.Drink;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

 
public class DrinkTest {
    @Test
    public void testDrinkAbstract() {
        Assertions.assertTrue(Modifier.isAbstract(Drink.class.getModifiers()),
                "The class drinks.Drink should be an abstract class!");
        Assertions.assertTrue(!Drink.class.isInterface(), "The class drinks.Drink should be an abstract class, not an interface!");
    }
}
