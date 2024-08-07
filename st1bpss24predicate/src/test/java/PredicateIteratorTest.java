import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

 
public class PredicateIteratorTest {
    private final List<String> values = List.of(//
            "Java and UML", "UML and Java", "Java 11", "UML 2.0", "Effective Java");
    private Iterator<String> valuesEndingWithJava;
    private Iterator<String> valuesBeginningWithJava;
    private Iterator<String> noValues;

    @BeforeEach
    public void setUp() {
        valuesEndingWithJava = new PredicateIterator<>(values.iterator(), new EndsWith("Java"));
        valuesBeginningWithJava = new PredicateIterator<>(values.iterator(), new StartsWith("Java"));
        noValues = new PredicateIterator<>(values.iterator(), new StartsWith("Doesn't match"));
    }

    @Test
    public void providesValuesEndingWithJava() {
        Assertions.assertTrue(valuesEndingWithJava.hasNext());
        Assertions.assertEquals("UML and Java", valuesEndingWithJava.next());
        Assertions.assertTrue(valuesEndingWithJava.hasNext());
        Assertions.assertEquals("Effective Java", valuesEndingWithJava.next());
    }

    @Test
    public void providesValuesBeginningWithJava() {
        Assertions.assertTrue(valuesBeginningWithJava.hasNext());
        Assertions.assertEquals("Java and UML", valuesBeginningWithJava.next());
        Assertions.assertTrue(valuesBeginningWithJava.hasNext());
        Assertions.assertEquals("Java 11", valuesBeginningWithJava.next());
    }

    @Test
    public void hasNextReturnsFalseAfterLastElement1() {
        valuesEndingWithJava.next();
        valuesEndingWithJava.next();
        Assertions.assertFalse(valuesEndingWithJava.hasNext());
    }

    @Test
    public void hasNextReturnsFalseAfterLastElement2() {
        valuesBeginningWithJava.next();
        valuesBeginningWithJava.next();
        Assertions.assertFalse(valuesBeginningWithJava.hasNext());
    }

    @Test
    public void nextThrowsExceptionAfterLastElement1() {
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            valuesEndingWithJava.next();
            valuesEndingWithJava.next();
            valuesEndingWithJava.next();  // should fail
        });
    }

    @Test
    public void nextThrowsExceptionAfterLastElement2() {
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            valuesBeginningWithJava.next();
            valuesBeginningWithJava.next();
            valuesBeginningWithJava.next(); //should fail
        });
    }

    @Test
    public void hasNextReturnsFalseIfNothingSatisfies() {
        Assertions.assertFalse(noValues.hasNext());
    }

    @Test
    public void nextThrowsExceptionIfNothingSatisfies() {
        Assertions.assertThrows(NoSuchElementException.class, () -> noValues.next());
    }
}
