import org.junit.jupiter.api.BeforeEach;

public abstract class AuctionTestBase {
    protected Auction auction;
    protected Item socks, book, clock;

    @BeforeEach
    public void setUp() {
        auction = new AuctionImpl();
        socks = new Item("Socks", "Ancient socks", 1);
        book = new Item("Book", "Ancient book", 5000);
        clock = new Item("Clock", "Ancient clock", 1000);
    }

    private static class AuctionImpl extends Auction {
        @Override
        public String generateItemString(Item item) {
            return "";
        }
    }
}
