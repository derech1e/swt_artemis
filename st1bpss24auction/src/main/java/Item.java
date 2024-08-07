import java.util.ArrayList;
import java.util.List;

public class Item implements InterfaceExample {
    private String name;
    private String description;
    private long minPrice;

    private List<Bid> allBids = new ArrayList<>();
    private Bid highestBid;

    public Item(String name, String description, long minPrice) {
        if (name == null || description == null)
            throw new NullPointerException();
        if (name.isEmpty() || description.isEmpty() || minPrice <= 0)
            throw new IllegalArgumentException();

        this.name = name;
        this.description = description;
        this.minPrice = minPrice;
    }

    public void addBid(Person bidder, long price) {
        Bid bid = new Bid(bidder, price);

        if (price < minPrice) return;

        if (this.getHighestBid() == null) {
            this.highestBid = bid;
            this.allBids.add(bid);
        } else if (this.getHighestBid().getPrice() < price) {
            this.highestBid = bid;
            this.allBids.add(bid);
        }
    }

    public List<Bid> getAllBids() {
        return this.allBids;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public Bid getHighestBid() {
        // TODO: Update highestBid on addBid
        return this.highestBid;
    }

    @Override
    public String toString() {
        return String.format("%s: %s (minimum bidding price: %s EUR)", this.name, this.description, this.minPrice);
    }

    @Override
    public void method() {

    }
}