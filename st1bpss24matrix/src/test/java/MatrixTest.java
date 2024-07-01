import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

 
public class MatrixTest {
    @Test
    public void testInterface() {
        Assertions.assertTrue(Matrix.class.isInterface());
    }
}