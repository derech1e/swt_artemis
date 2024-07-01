import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

 
public class ItemTest {
    private Bid bid;
    private Item item;
    private Person person1, person2, personNotHighestPrice;

    @BeforeEach
    public void setUp() {
        item = new Item("Blueprint", "A blueprint for building a touch screen", 5);
        person1 = new Person("Max");
        person2 = new Person("Moritz");
        personNotHighestPrice = new Person("Klaus");
    }

    @Test
    public void testItemConstructorNullArgument() {
        try {
            item = new Item(null, "description", 5);
            Assertions.fail("Item.Item() should throw a NullPointerException if the name argument is null!");
        } catch (NullPointerException e) {
        }
        try {
            item = new Item("name", null, 5);
            Assertions.fail("Item.Item() should throw a NullPointerException if the description argument is null!");
        } catch (NullPointerException e) {
        }
    }

    @Test
    public void testConstructorEmptyStringArgument() {
        try {
            item = new Item("", "description", 5);
            Assertions.fail("Item.Item() should throw an IllegalArgumentException if the name argument is empty!");
        } catch (IllegalArgumentException e) {
        }

        try {
            item = new Item("name", "", 5);
            Assertions.fail("Item.Item() should throw an IllegalArgumentException if the description argument is empty!");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void testItemConstructorIllegalArgument() {
        try {
            item = new Item("name", "description", 0);
            Assertions.fail("Item.Item() should throw an IllegalArgumentException if the minPrice argument is zero!");
        } catch (IllegalArgumentException e) {
        }

        try {
            item = new Item("name", "description", -1);
            Assertions.fail("Item.Item() should throw an IllegalArgumentException if the minPrice argument is negative!");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void testItemAddBidNullArgument() {
        try {
            item.addBid(null, 5);
            Assertions.fail("Item.addBid() should throw a NullPointerException if the person argument is null!");
        } catch (NullPointerException e) {
        }
    }

    @Test
    public void testItemAddBidIllegalArgument() {
        try {
            item.addBid(person1, 0);
            Assertions.fail("Item.addBid() should throw an IllegalArgumentException if the price argument is zero!");
        } catch (IllegalArgumentException e) {
        }

        try {
            item.addBid(person1, -1);
            Assertions.fail("Item.addBid() should throw an IllegalArgumentException if the price argument is negative!");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void testAddBid() {
        item.addBid(person1, 4);
        Assertions.assertEquals(0, item.getAllBids().size(),
                "Item.addBid() shouldn't create a new bid if its price is below the minPrice!");
        Assertions.assertNull(item.getHighestBid(), "Item.addBid() should not set a highest bid, when its price is below the minPrice!");

        item.addBid(person1, 5);
        Assertions.assertEquals(1, item.getAllBids().size(), "Item.addBid() should create a new bid!");
        bid = item.getHighestBid();
        Assertions.assertTrue(bid.getBidder() == person1 && bid.getPrice() == 5,
                "Item.addBid() should set bid as highest bid, when bid's price is higher than minPrice!");

        item.addBid(person2, 6);
        Assertions.assertEquals(2, item.getAllBids().size(), "Item.addBid() should create a new bid!");
        bid = item.getHighestBid();
        Assertions.assertTrue(bid.getBidder() == person2 && bid.getPrice() == 6,
                "Item.addBid() should set bid as highest bid, when bid's price is higher than current highest bid!");

        item.addBid(personNotHighestPrice, 1);
        Assertions.assertEquals(2, item.getAllBids().size(),
                "Item.addBid() shouldn't create a new bid, if the price isn't higher than current highest bid!");
        bid = item.getHighestBid();
        Assertions.assertTrue(bid.getBidder() == person2 && bid.getPrice() == 6,
                "Item.addBid() shouldn't change the highest bid, when bid's price isn't higher than current highest bid!");

        item.addBid(personNotHighestPrice, 6);
        Assertions.assertEquals(2, item.getAllBids().size(), "Item.addBid() shouldn't create a new bid, if the price " +
                "isn't higher than current highest bid!");
        bid = item.getHighestBid();
        Assertions.assertTrue(bid.getBidder() == person2 && bid.getPrice() == 6,
                "Item.addBid() shouldn't change the highest bid, when bid's price isn't higher than current highest bid!");

        List<Bid> allBids = item.getAllBids();
        Assertions.assertEquals(2, allBids.size(),
                "Item.getAllBids() should return a list containing all bids which were the highestBid!");
    }

    @Test
    public void testItemGetName() {
        Assertions.assertEquals("Blueprint", item.getName(), "Item.getName() should return the correct value!");
    }

    @Test
    public void testGetDescription() {
        Assertions.assertEquals("A blueprint for building a touch screen", item.getDescription(), "Item.getDescription() should return the correct value!");
    }

    @Test
    public void testItemToString() {
        Assertions.assertEquals("Blueprint: A blueprint for building a touch screen (minimum bidding price: 5 EUR)", item.toString(), "Item.toString() should return the correct value!");
    }
}
