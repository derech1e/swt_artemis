import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public abstract class Auction {
    private boolean closed;

    private List<Item> allItems = new ArrayList<>();
    private List<Person> bidders = new ArrayList<>();

    public Auction() {
    }

    public void addBid(Item bidItem, String nameOfBidder, long price) {
        if (bidItem == null || nameOfBidder == null)
            throw new NullPointerException();

        if (price <= 0)
            throw new IllegalArgumentException();

        if (this.closed)
            throw new IllegalStateException();

        if (!allItems.stream().map(Item::getName).toList().contains(bidItem.getName()))
            throw new NoSuchElementException();

        Person bidder = new Person(nameOfBidder);

        if (!this.bidders.contains(bidder)) {
            this.bidders.add(bidder);
        }

        if (bidItem.getHighestBid() == null) {
            bidItem.addBid(bidder, price);
            return;
        }

        if (bidItem.getHighestBid().getPrice() < price)
            bidItem.addBid(bidder, price);
    }

    public String closeAuction() {
        if (this.closed)
            throw new IllegalStateException();

        this.closed = true;
        return this.generateItemListString();
    }

    public String generateAllBidsString(Item item) {
        if(item == null)
            throw new NullPointerException();
        
        String result = "All bids:";
        for (Bid bid : item.getAllBids()) {
            result += "\n" + bid.toString();
        }
        return result;
    }

    public String generateItemListString() {
        String result = "";
        for (Item item : allItems) {
            result += generateItemString(item) + "\n";
        }
        return result;
    }

    public void registerItem(Item item) {
        if (item == null)
            throw new NullPointerException();

        if (this.closed)
            throw new IllegalStateException();
        for (Item item1 : allItems) {
            if (item1.getName() == item.getName())
                throw new IllegalArgumentException();
        }
        this.allItems.add(item);
    }

    public abstract String generateItemString(Item item);

    public List<Item> getAllItems() {
        return this.allItems;
    }

}