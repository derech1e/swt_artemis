public class ReceivingStock extends Stock {

    private int minStockItems;
    private int maxStockItems;

    public ReceivingStock(int minStockItems, int maxStockItems) {
        if(minStockItems < 0 || maxStockItems <= minStockItems) throw new IllegalArgumentException();
        this.minStockItems = minStockItems;
        this.maxStockItems = maxStockItems;
    }

    public int getMinStockItems() {
        return this.minStockItems;
    }

    public int getMaxStockItems() {
        return this.maxStockItems;
    }

    @Override
    public boolean insert(Part part, int amount) {
        if(part == null) throw new IllegalArgumentException();
        if(amount <= 0) throw new IllegalArgumentException();
        if(amount > maxStockItems) return false;
        return super.insert(part, amount);
    }

    @Override
    public boolean remove(Part part, int amount) {
        if(part == null) throw new IllegalArgumentException();
        if(amount <= 0) throw new IllegalArgumentException();
        return super.remove(part, amount);
    }

}