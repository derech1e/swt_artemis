import drinks.Beer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

 
public class BeerTest {
    @Test
    public void testGetBrewery() {
        Beer beer = new Beer("Freiberger");
        Assertions.assertEquals("Freiberger", beer.getBrewery(), "drinks.Beer.getBrewery() should return the correct brewery!");
    }

    @Test
    public void testBeerOverridesToString() {
        try {
            Beer.class.getDeclaredMethod("toString");
        } catch(NoSuchMethodException e) {
            Assertions.fail("The class drinks.Beer should override toString()!");
        }
    }
}