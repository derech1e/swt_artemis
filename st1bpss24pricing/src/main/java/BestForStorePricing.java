public class BestForStorePricing extends ComplexPricing {

    public BestForStorePricing(ISalePricing pricing) {
        super(pricing);
    }

    public long getTotal(Sale sale) {
        if(sale == null) throw new NullPointerException();
        long highestPrice = this.getPricings().get(0).getTotal(sale);
        for(ISalePricing pricing : this.getPricings()) {
            long newPrice = pricing.getTotal(sale);
            if(newPrice > highestPrice) {
                highestPrice = newPrice;
            }
        }
        return highestPrice;
    }
}