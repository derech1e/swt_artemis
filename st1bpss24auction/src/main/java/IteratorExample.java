import java.util.Iterator;
import java.util.NoSuchElementException;

public class IteratorExample<T> implements Iterator<T> {

    private int index = 0;

    // CTR

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public T next() {
        if(!hasNext()) throw new NoSuchElementException();
        while (index < /* Some number outside the iterator */ 0) {
            index++;
        }
        return null; // Some more logic
    }
}

