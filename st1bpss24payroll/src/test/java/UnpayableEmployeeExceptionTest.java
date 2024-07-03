import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

 
public class UnpayableEmployeeExceptionTest {
    @Test
    public void testSuperclass() {
        Assertions.assertEquals(Exception.class, UnpayableEmployeeException.class.getSuperclass(),
                "UnpayableEmployeeException should be a subclass of java.lang.Exception!");
    }

    @Test
    public void testConstructor() {
        try {
            throw new UnpayableEmployeeException("Test message");
        } catch (UnpayableEmployeeException exception) {
            Assertions.assertEquals("Test message", exception.getMessage(),
                    "A thrown UnpayableEmployeeException should contain the message specified in the constructor!");
        }
    }
}
