import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

 
public class EnterpriseNodeTest {
    @Test
    public void enterpriseNodeTestInterface() {
        Assertions.assertTrue(EnterpriseNode.class.isInterface());
    }
}