public class AbsoluteDiscountPricing implements ISalePricing {

    private long discount;
    private long threshold;

    public AbsoluteDiscountPricing(long discount, long threshold) {
        if(discount <= 0 || threshold <= 0) throw new IllegalArgumentException();
        this.discount = discount;
        this.threshold = threshold;
    }


    @Override
    public long getTotal(Sale sale) {
        if(sale == null) throw new NullPointerException();
        long preDiscount = sale.getPreDiscountTotal();

        if(preDiscount <= threshold)
            return preDiscount;

        return Math.max(preDiscount - discount, threshold);
    }
}