package drinks;

public class Bottle<T extends Drink> {

    private T content;

    public boolean isEmpty() {
        return content == null;
    }

    public void fill(T content) {
        if (content == null || this.content != null)
            throw new IllegalStateException("Content is null");
        this.content = content;
    }

    public T empty() {
        if (content == null)
            throw new IllegalStateException("Content is null");
        T drink = content;
        this.content = null;
        return drink;
    }

}
