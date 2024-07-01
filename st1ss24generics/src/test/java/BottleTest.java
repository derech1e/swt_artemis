import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import drinks.Beer;
import drinks.Bottle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

 
class BottleTest {
    private Beer beer;
    private Bottle<Beer> bottle;

    @BeforeEach
    public void setUp() {
        beer = new Beer("Hasseröder");
        bottle = new Bottle<Beer>();
    }

    @Test
    void testIsEmpty() {
        assertTrue(bottle.isEmpty(), "drinks.Bottle.isEmpty() should return true if the drinks.Bottle is empty!");
        bottle.fill(beer);
        assertFalse(bottle.isEmpty(), "drinks.Bottle.isEmpty() should return false if the drinks.Bottle is not empty!");
        bottle.empty();
        assertTrue(bottle.isEmpty(), "drinks.Bottle.isEmpty() should return true if the drinks.Bottle has been emptied!");
    }

    @Test
    void testFill() {
        bottle.fill(beer);
        assertEquals(beer, bottle.empty());

        // test whether an illegal state exception is thrown when fill is called upon a already filled bottle
        try {
            bottle.fill(beer);
            bottle.fill(beer);
            fail(
                    "drinks.Bottle.fill() should throw an IllegalStateException if the drinks.Bottle is in an invalid state for the" +
                            " operation!");
        } catch (IllegalStateException e) {
        }
    }



    @Test
    void testEmpty() {
        bottle.fill(beer);
        assertEquals(beer, bottle.empty(), "drinks.Bottle.empty() should return the same drinks.Drink which was put into the " +
                "drinks.Bottle!");

        // test whether an illegal state exception is thrown when empty is called upon a already emptied bottle
        try {
            bottle.empty();
            fail("drinks.Bottle.empty() should throw an IllegalStateException if the drinks.Bottle is in an invalid state for the " +
                    "operation!");
        } catch (IllegalStateException e) {
        }
    }
}
