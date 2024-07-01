import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

 
class AuctionTest extends AuctionTestBase {
    @Test
    void testAuctionRegisterItem() {
        Assertions.assertEquals(0, auction.getAllItems().size(), "In the beginning, Auction.allItems should be empty!");
        auction.registerItem(new Item("Book", "Ancient book", 5000));
        Assertions.assertEquals(1, auction.getAllItems().size(),
                "Auction.registerItem() should actually add an item to allItems!");
        Assertions.assertEquals("Book", auction.getAllItems().get(0).getName(),
                "Auction.registerItem() shouldn't change the order of the items!");
        auction.registerItem(new Item("CD", "Ancient cd", 5));
        Assertions.assertEquals(2, auction.getAllItems().size(), "Auction.registerItem() should actually add an item " +
                "to allItems!");
        Assertions.assertEquals("CD", auction.getAllItems().get(1).getName(), "Auction.registerItem() shouldn't change the order of the items!");
    }

    @Test
    void testGenerateAllBidsStringNoBids() {
        for (Item item : List.of(socks, book, clock)) {
            auction.registerItem(item);
        }
        List<Item> items = auction.getAllItems();
        Assertions.assertNotNull(items, "Auction.getAllItems() must not return null");
        for (Item item : items) {
            String allBidsString = auction.generateAllBidsString(item);
            Assertions.assertNotNull(allBidsString, "Auction.generateAllBidsString() must not return null");
            Assertions.assertEquals("All bids:", allBidsString.trim(), "Auction.generateAllBidsString() returns incorrect string when there are no bids");
        }
    }

    @Test
    void testGenerateAllBidsStringsValidStrings() {
        for (Item item : List.of(socks, book, clock)) {
            auction.registerItem(item);
        }
        auction.addBid(socks, "Max", 1);
        auction.addBid(book, "Moritz", 7000);
        auction.addBid(clock, "Max", 1000);
        auction.addBid(clock, "Moritz", 2000);

        // Strings (instead of Items) are used as keys, because equals()
        // and/or hashCode() in submitted Item class might be bogus
        Map<String, Item> expectedItemStrings = Map.of(
                "All bids:\n1 EUR by Max", socks,
                "All bids:\n7000 EUR by Moritz", book,
                "All bids:\n1000 EUR by Max\n2000 EUR by Moritz", clock
        );

        for (Map.Entry<String, Item> entry : expectedItemStrings.entrySet()) {
            String allBidsString = auction.generateAllBidsString(entry.getValue());
            Assertions.assertNotNull(allBidsString, "Auction.generateAllBidsString() must not return null");
            Assertions.assertEquals(entry.getKey(), allBidsString.trim(), "Auction.generateAllBidsString() should return the correct string");
        }
    }

    @Test
    void testCloseAuction() {
        auction.registerItem(clock);
        Assertions.assertEquals(auction.generateItemListString(), auction.closeAuction(),
                "Auction.closeAuction() should call generateItemListString()");
        try {
            auction.closeAuction();
            Assertions.fail("Auction.closeAuction() should throw an IllegalStateException if the auction is already closed!");
        } catch (IllegalStateException e) {
        }
        try {
            auction.addBid(clock, "bidder", 2);
            Assertions.fail("Auction.addBid() should throw an IllegalStateException if the auction is already closed!");
        } catch (IllegalStateException e) {
        }
        try {
            auction.registerItem(new Item("name", "description", 2));
            Assertions.fail("Auction.registerItem() should throw an IllegalStateException if the auction is already closed!");
        } catch (IllegalStateException e) {
        }
    }

    @Test
    void testAuctionAddBidNullArgument() {
        auction.registerItem(clock);
        try {
            auction.addBid(null, "Max", 5);
            Assertions.fail("Auction.addBid() should throw a NullPointerException if the itemName argument is null!");
        } catch (NullPointerException e) {
        }
        try {
            auction.addBid(clock, null, 5);
            Assertions.fail("Auction.addBid() should throw auction NullPointerException if the nameOfBidder argument is null!");
        } catch (NullPointerException e) {
        }
    }

    @Test
    void testAuctionAddBidIllegalArgument() {
        auction.registerItem(clock);

        try {
            auction.addBid(clock, "", 5);
            Assertions.fail("Auction.addBid() should throw an IllegalArgumentException if the nameOfBidder argument is empty!");
        } catch (IllegalArgumentException e) {
        }

        try {
            auction.addBid(clock, "Max", 0);
            Assertions.fail("Auction.addBid() should throw an IllegalArgumentException if the price argument is zero!");
        } catch (IllegalArgumentException e) {
        }

        try {
            auction.addBid(clock, "Max", -1);
            Assertions.fail("Auction.addBid() should throw an IllegalArgumentException if the price argument is negative!");
        } catch (IllegalArgumentException e) {
        }

        Item unregisteredItem = new Item("New", "an Item that is not registered in the auction", 1);

        try {
            auction.addBid(unregisteredItem, "Max", 5);
            Assertions.fail("Auction.addBid() should throw a NoSuchElementException if no item in the auction has the given name!");
        } catch (NoSuchElementException e) {
        }
    }

    @Test
    void registerItemIllegalArgument() {
        auction.registerItem(new Item("duplicate", "description", 1));

        try {
            auction.registerItem(new Item("duplicate", "description", 1));
            Assertions.fail(
                    "Auction.registerItem() should throw an IllegalArgumentException if the item has the same name as an Auction's item!");
        } catch (IllegalArgumentException e) {
        }

    }

    @Test
    void testRegisterItemNullArgument() {
        try {
            auction.registerItem(null);
            Assertions.fail("Auction.registerItem() should throw a NullPointerException if the item is null!");
        } catch (NullPointerException e) {
        }
    }

    @Test
    void testGenerateAllBidsStringNullArgument() {
        try {
            auction.generateAllBidsString(null);
            Assertions.fail("Auction.generateAllBidsString() should throw a NullPointerException if the argument is null!");
        } catch (NullPointerException e) {
        }
    }

    @Test
    void testGetAllItems() {
        for (Item item : List.of(book, clock)) {
            auction.registerItem(item);
        }
        List<Item> expected = List.of(book, clock);
        List<Item> actual = auction.getAllItems();
        Assertions.assertEquals(expected, actual, "Auction.getAllItems() should return a List of all registered Items!");
    }
}
