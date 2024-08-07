public abstract class Item implements Comparable<Item> {
    protected String name;

    public Item(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Item o) {
        return 0;
    }

    public abstract double getPrice();

    @Override
    public String toString() {
        return super.toString();
    }
}