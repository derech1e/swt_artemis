import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

 
public class PayrollDBTest {
    @Test
    public void payrollDbTestInterface() {
        Assertions.assertTrue(PayrollDB.class.isInterface());
    }
}