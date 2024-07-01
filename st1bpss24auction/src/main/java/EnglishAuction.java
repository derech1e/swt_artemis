public class EnglishAuction extends Auction {

    @Override
    public String generateItemString(Item item) {
        if(item.getAllBids().isEmpty())
            return String.format("%s\nNo bids placed", item.toString());
        return String.format("%s\nHighest bid: %s", item.toString(), item.getHighestBid());
    }
}