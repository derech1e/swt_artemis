import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

 
class PredicateTest {
    @Test
    void shouldBeAnInterface() {
        Assertions.assertTrue(Predicate.class.isInterface(), "Predicate<T> should be an interface!");
    }
}