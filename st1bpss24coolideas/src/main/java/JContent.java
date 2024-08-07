import java.util.HashSet;
import java.util.Set;

public abstract class JContent {
    private String title;
    private String description;

    private Set<ContentObserver> observers = new HashSet<ContentObserver>();

    public JContent(String title, String description) {
        if (title == null || description == null) throw new NullPointerException();
        if (title.isEmpty() || description.isEmpty()) throw new IllegalArgumentException();
        this.title = title;
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        if (description == null) throw new NullPointerException();
        if (description.isEmpty()) throw new IllegalArgumentException();
        this.description = description;
        for (ContentObserver observer : observers) {
            observer.update(this);
        }
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        if (title == null) throw new NullPointerException();
        if (title.isEmpty()) throw new IllegalArgumentException();
        this.title = title;
        for (ContentObserver observer : observers) {
            observer.update(this);
        }
    }

    public void addObserver(ContentObserver observer) {
        if (observer == null) throw new NullPointerException();
        this.observers.add(observer);
        observer.update(this);
    }

    public void removeObserver(ContentObserver observer) {
        if (observer == null) throw new NullPointerException();
        this.observers.remove(observer);
        observer.update(this);
    }

    public int countObservers() {
        return this.observers.size();
    }

    @Override
    public abstract String toString();
}