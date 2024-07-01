import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

 
public class EventCatalogTest {
    @Test
    public void testInterface() {
        Assertions.assertTrue(EventCatalog.class.isInterface());
    }
}