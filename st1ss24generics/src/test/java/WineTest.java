import drinks.Wine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

 
public class WineTest {
    @Test
    public void testWineOverrideToString() {
        try {
            Wine.class.getDeclaredMethod("toString");
        } catch(NoSuchMethodException e) {
            Assertions.fail("The class drinks.Wine should override toString()!");
        }
    }

    @Test
    public void wineTestGetRegion() {
        Wine wine = new Wine("Pfalz") {
            // anonymous subclass for testing purposes
        };
        Assertions.assertEquals("Pfalz", wine.getRegion(), "drinks.Wine.getRegion() should return the correct region!");
    }
}
