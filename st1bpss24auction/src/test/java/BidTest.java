import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

 
public class BidTest {
    private Bid bid;
    private Person person;

    @BeforeEach
    public void setUp() {
        person = new Person("Max");
        bid = new Bid(person, 10);
    }

    @Test
    public void testBidConstructorNullArgument() {
        try {
            bid = new Bid(null, 1);
            Assertions.fail("Bid.Bid() should throw a NullPointerException if the person argument is null!");
        } catch (NullPointerException e) {
        }
    }

    @Test
    public void testBidConstructorIllegalArgument() {
        try {
            bid = new Bid(person, 0);
            Assertions.fail("Bid.Bid() should throw an IllegalArgumentException if the price argument is zero!");
        } catch (IllegalArgumentException e) {
        }

        try {
            bid = new Bid(person, -1);
            Assertions.fail("Bid.Bid() should throw an IllegalArgumentException if the price argument is negative!");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void testGetBidder() {
        Assertions.assertEquals(person, bid.getBidder(), "Bid.getBidder() should return the correct value!");
    }

    @Test
    public void testGetPrice() {
        Assertions.assertEquals(10, bid.getPrice(), "Bid.getPrice() should return the correct value!");
    }

    @Test
    public void testBidToString() {
        Assertions.assertEquals("10 EUR by Max", bid.toString(), "Bid.toString() should return the correct value!");
    }
}
