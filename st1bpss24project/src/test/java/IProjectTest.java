import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

  
public class IProjectTest {
    @Test
    public void testIProjectIsInterface() {
        assertTrue(IProject.class.isInterface());
    }
}