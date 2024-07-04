public class BestForCustomerPricing extends ComplexPricing {

    public BestForCustomerPricing(ISalePricing pricing) {
        super(pricing);
    }

    public long getTotal(Sale sale) {
        if(sale == null) throw new NullPointerException();
        long lowestPrice = this.getPricings().get(0).getTotal(sale);
        for(ISalePricing pricing : this.getPricings()) {
            long newPrice = pricing.getTotal(sale);
            if(newPrice < lowestPrice) {
                lowestPrice = newPrice;
            }
        }
        return lowestPrice;
    }

}