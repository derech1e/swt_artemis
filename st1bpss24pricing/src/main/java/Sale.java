
public class Sale {
    private long preDiscountTotal;
    private ISalePricing pricing;

    public Sale(long preDiscountTotal, ISalePricing pricing) {
        if(preDiscountTotal < 0) throw new IllegalArgumentException();
        if(pricing == null) throw new NullPointerException();
        this.pricing = pricing;
        this.preDiscountTotal = preDiscountTotal;
    }

    public long getPreDiscountTotal() {
        return preDiscountTotal;
    }

    public void setPricing(ISalePricing pricing) {
        if(pricing == null) throw new NullPointerException();
        this.pricing = pricing;
    }

    public long getTotal() {
        return pricing.getTotal(this);
    }

    public static ISalePricing createPricing(DiscountType discountType, double percentage, long discount, long threshold) {
        if(discountType == null) throw new NullPointerException();
        if(percentage < 0 || percentage > 100 || discount < 0 || threshold < 0 || threshold < discount) throw new IllegalArgumentException();
        if (discountType == DiscountType.ABSOLUTEDISCOUNT)
            return new AbsoluteDiscountPricing(discount, threshold);

        return new PercentageDiscountPricing(percentage);
    }
}