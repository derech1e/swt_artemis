
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

 
class EmptyListPredicateIteratorTest {
    private final List<String> emptyList = List.of();
    private Iterator<String> emptyListIterator;

    @BeforeEach
    public void setUp() {
        emptyListIterator = new PredicateIterator<String>(emptyList.iterator(), new HasLength(2));
    }

    @Test
    void iterHasNextShouldReturnFalse() {
        assertFalse(emptyListIterator.hasNext());
    }

    @Test
    void iterNextShouldThrowNoSuchElementException() {
        Assertions.assertThrows(NoSuchElementException.class, () -> emptyListIterator.next());
    }
}
