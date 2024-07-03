import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

 
public class StockObserverTest {
    @Test
    public void testInterface(){
        Assertions.assertTrue(StockObserver.class.isInterface());
    }
}