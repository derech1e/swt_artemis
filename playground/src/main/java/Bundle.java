import java.util.HashSet;
import java.util.Set;

public class Bundle extends Item {

    private Set<Item> bundleItems = new HashSet<>();

    public Bundle(String name) {
        super(name);
    }

    public void addItem(Item item) {
        bundleItems.add(item);
    }

    @Override
    public double getPrice() {
        double price = 0D;
        for (Item item : bundleItems)
            price += item.getPrice();

        return price;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
