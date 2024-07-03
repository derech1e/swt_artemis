import java.util.*;

public abstract class Stock {

    private Map<Part, Integer> parts = new HashMap<>();
    private Set<StockObserver> observers = new HashSet<>();

    public int getCount(Part part) {
        if (part == null) throw new NullPointerException();
        if (!parts.containsKey(part)) return -1;
        return parts.get(part);
    }

    public boolean insert(Part part, int amount) {
        if (part == null) throw new NullPointerException();
        if (amount <= 0) throw new IllegalArgumentException();
        if (parts.containsKey(part)) {
            parts.replace(part, parts.get(part) + amount);
            notifyPartCountChanged(part);
            return true;
        }
        parts.put(part, amount);
        return true;
    }

    public boolean remove(Part part, int amount) {
        if (part == null) throw new NullPointerException();
        if (amount <= 0) throw new IllegalArgumentException();
        if (parts.containsKey(part)) {
            int currentAmount = parts.get(part);
            int computedAmount = currentAmount - amount;
            if (computedAmount >= 0) {
                parts.replace(part, computedAmount);
                notifyPartCountChanged(part);
                return true;
            }
            return false;
        }
        return false;
    }

    public void addObserver(StockObserver observer) {
        if (observer == null) throw new NullPointerException();
        observers.add(observer);
    }

    private void notifyPartCountChanged(Part part) {
        for(StockObserver observer : observers)
            observer.onPartCountChanged(part, parts.get(part));
    }

}