import java.util.*;

public class Event implements Comparable<Event> {

    private String title;
    private EventCategory category;

    public Event(String title, EventCategory category) {
        if(title == null || category == null) throw new NullPointerException();
        if(title.isEmpty()) throw new IllegalArgumentException();
        this.title = title;
        this.category = category;
    }

    public String getTitle() {
        return this.title;
    }

    public EventCategory getCategory() {
        return this.category;
    }

    @Override
    public int compareTo(Event event) {
        if(event == null) throw new NullPointerException();
        int result = this.getTitle().compareTo(event.getTitle());
        if(result == 0) {
            return this.getCategory().compareTo(event.getCategory());
        }
        return result;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getTitle(), this.getCategory());
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null || !(obj instanceof Event)) return false;
        Event other = (Event) obj;
        if(other.getTitle().equals(this.getTitle())) {
            return other.getCategory().equals(this.getCategory());
        }
        return false;
    }
}