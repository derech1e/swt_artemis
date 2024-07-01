import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class AllPayAuctionTest extends AuctionTestBase {
    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();
        auction = new AllPayAuction();
    }

    @Test
    public void testAllPayAuctionSuperclass() {
        Assertions.assertEquals(Auction.class, AllPayAuction.class.getSuperclass(),
                "AllPayAuction should be a subclass of Auction!");
    }

    @Test
    public void testAllPayAuctionGenerateItemStringNullArgument() {
        try {
            auction.generateItemString(null);
            Assertions.fail("AllPayAuction.generateItemString() should throw a NullPointerException if the argument is null!");
        } catch (NullPointerException e) {
        }
    }

    @Test
    public void testAllPayAuctionGenerateItemListString() {
        for (Item item : List.of(socks, book, clock)) {
            auction.registerItem(item);
        }

        String expectedItemListStringBeforeBidding = "Socks: Ancient socks (minimum bidding price: 1 EUR)\nNo bids placed\n"
                + "Book: Ancient book (minimum bidding price: 5000 EUR)\nNo bids placed\n"
                + "Clock: Ancient clock (minimum bidding price: 1000 EUR)\nNo bids placed";

        Assertions.assertEquals(expectedItemListStringBeforeBidding, auction.generateItemListString().trim(),
                "Auction.generateItemListString() should return the correct String!");

        auction.addBid(socks, "Max", 1);
        auction.addBid(book, "Moritz", 7000);
        auction.addBid(clock, "Max", 1000);
        auction.addBid(clock, "Moritz", 2000);

        String expectedItemListStringAfterBidding = "Socks: Ancient socks (minimum bidding price: 1 EUR)\nHighest bid: 1 EUR by Max\n"
                + "All bids:\n1 EUR by Max\n"
                + "Book: Ancient book (minimum bidding price: 5000 EUR)\nHighest bid: 7000 EUR by Moritz\n"
                + "All bids:\n7000 EUR by Moritz\n"
                + "Clock: Ancient clock (minimum bidding price: 1000 EUR)\nHighest bid: 2000 EUR by Moritz\n"
                + "All bids:\n1000 EUR by Max\n2000 EUR by Moritz";

        Assertions.assertEquals(expectedItemListStringAfterBidding, auction.generateItemListString().trim(),
                "Auction.generateItemListString() should return the correct String!");
    }

    @Test
    public void testAllPayGenerateItemString() {
        for (Item item : List.of(socks, book, clock)) {
            auction.registerItem(item);
        }

        Map<String, Item> expectedItemStringsBeforeBidding = Map.of(
                "Socks: Ancient socks (minimum bidding price: 1 EUR)\nNo bids placed", socks,
                "Book: Ancient book (minimum bidding price: 5000 EUR)\nNo bids placed", book,
                "Clock: Ancient clock (minimum bidding price: 1000 EUR)\nNo bids placed", clock
        );

        for (Entry<String, Item> entry : expectedItemStringsBeforeBidding.entrySet()) {
            String expectedString = entry.getKey();
            Item item = entry.getValue();
            Assertions.assertEquals(expectedString, auction.generateItemString(item).trim(), "AllPayAuction.generateItemString() should return the correct String!");
        }

        auction.addBid(socks, "Max", 1);
        auction.addBid(book, "Moritz", 7000);
        auction.addBid(clock, "Max", 1000);
        auction.addBid(clock, "Moritz", 2000);

        Map<String, Item> expectedItemStringsAfterBidding = Map.of(
                "Socks: Ancient socks (minimum bidding price: 1 EUR)\nHighest bid: 1 EUR by Max\n"
                        + "All bids:\n1 EUR by Max", socks,
                "Book: Ancient book (minimum bidding price: 5000 EUR)\nHighest bid: 7000 EUR by Moritz\n"
                        + "All bids:\n7000 EUR by Moritz", book,
                "Clock: Ancient clock (minimum bidding price: 1000 EUR)\nHighest bid: 2000 EUR by Moritz\n"
                        + "All bids:\n1000 EUR by Max\n2000 EUR by Moritz", clock
        );

        for (Entry<String, Item> entry : expectedItemStringsAfterBidding.entrySet()) {
            String expectedString = entry.getKey();
            Item item = entry.getValue();
            Assertions.assertEquals(expectedString, auction.generateItemString(item).trim(), "AllPayAuction.generateItemString() should return the correct String!");
        }
    }
}
