public class AllPayAuction extends Auction {

    @Override
    public String generateItemString(Item item) {
        if(item.getAllBids().isEmpty())
            return String.format("%s\nNo bids placed", item.toString());

        String result = item.toString() + "\n";
        result += "Highest bid: " + item.getHighestBid().toString() + "\n";
        result += generateAllBidsString(item);
        return result;
    }
}