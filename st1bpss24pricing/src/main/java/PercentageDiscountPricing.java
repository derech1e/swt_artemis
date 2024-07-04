public class PercentageDiscountPricing implements ISalePricing {

    private double percentage;

    public PercentageDiscountPricing(double percentage) {
        if(percentage < 0 || percentage > 100) throw new IllegalArgumentException();
        this.percentage = percentage;
    }

    @Override
    public long getTotal(Sale sale) {
        if(sale == null) throw new NullPointerException();
        long preDiscount = sale.getPreDiscountTotal();
        return (long) (preDiscount - (preDiscount / 100.0 * percentage));
    }
}