import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

 
public class ISalePricingTest {
    @Test
    public void testInterface() {
        Assertions.assertTrue(ISalePricing.class.isInterface());
    }
}