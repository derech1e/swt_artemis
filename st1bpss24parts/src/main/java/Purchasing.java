public class Purchasing implements StockObserver {

    private ReceivingStock receivingStock;

    public Purchasing(ReceivingStock receivingStock) {
        if(receivingStock == null) throw new NullPointerException();
        this.receivingStock = receivingStock;
    }

    public void buy(Part part, int count) {
        if(part == null) throw new NullPointerException();
        if(count <= 0) throw new IllegalArgumentException();
        receivingStock.insert(part, count);
    }

    public ReceivingStock getStock() {
        return this.receivingStock;
    }

    @Override
    public void onPartCountChanged(Part part, int count) {
        if(part == null) throw new NullPointerException();
        if(count <= 0) throw new IllegalArgumentException();

        // getCount also works here
        if(count < getStock().getMinStockItems())
            buy(part, getStock().getMaxStockItems() - count);
    }
}