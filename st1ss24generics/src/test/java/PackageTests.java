import static org.junit.jupiter.api.Assertions.assertEquals;

import drinks.*;
import org.junit.jupiter.api.Test;

 
class PackageTests {
    @Test
    void testDrinkPackage() {
        String expectedPackage = "drinks";
        assertEquals(expectedPackage, Bottle.class.getPackage().getName(), "The class drinks.Bottle should be in the package" +
                " drinks");
        assertEquals(expectedPackage, Drink.class.getPackage().getName(), "The class drinks.Drink should be in the package " +
                "drinks");
        assertEquals(expectedPackage, Beer.class.getPackage().getName(), "The class drinks.Beer should be in the package " +
                "drinks");
        assertEquals(expectedPackage, Wine.class.getPackage().getName(), "The class drinks.Wine should be in the package " +
                "drinks");
        assertEquals(expectedPackage, WhiteWine.class.getPackage().getName(), "The class drinks.WhiteWine should be in the " +
                "package drinks");
        assertEquals(expectedPackage, RedWine.class.getPackage().getName(), "The class drinks.RedWine should be in the " +
                "package drinks");
    }
}
