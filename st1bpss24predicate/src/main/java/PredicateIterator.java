import java.util.Iterator;
import java.util.NoSuchElementException;

public class PredicateIterator<T> implements Iterator<T> {

    private Iterator<T> iter;
    private Predicate<T> predicate;
    private T next;
    private boolean findNextPredicateElement = true;

    public PredicateIterator(Iterator<T> iter, Predicate<T> predicate) {
        if (iter == null || predicate == null) throw new NullPointerException();
        this.iter = iter;
        this.predicate = predicate;
    }

    @Override
    public boolean hasNext() {
        while (iter.hasNext()) {
            T next = iter.next();
            if (predicate.test(next)) {
                this.next = next;
                findNextPredicateElement = false;
                return true;
            }
        }
        findNextPredicateElement = true;
        return false;
    }

    @Override
    public T next() {
        if (findNextPredicateElement && !hasNext())
            throw new NoSuchElementException();
        findNextPredicateElement = true;
        return this.next;
    }
}