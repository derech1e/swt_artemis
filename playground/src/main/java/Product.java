public class Product extends Item {

    private double price;

    public Product(String name, double price) {
        super(name);
        this.price = price;
    }

    @Override
    public double getPrice() {
        return this.price;
    }
}
