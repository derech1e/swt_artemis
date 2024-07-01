public class Bid {
    private final long price;
    private final Person bidder;

    public Bid(Person bidder, long price) {
        if(bidder == null)
            throw new NullPointerException("bidder is null");
        if(price <= 0)
            throw new IllegalArgumentException("price must be greater than zero");
        this.bidder = bidder;
        this.price = price;
    }

    public Person getBidder() {
        return this.bidder;
    }

    public long getPrice() {
        return this.price;
    }

    @Override
    public String toString() {
        return String.format("%s EUR by %s", this.price, this.bidder.getName());
    }


}