import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

 
public class StateReleasedIdeaTest extends JStateBaseTest {
    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();
        i.hold();
        i.hold();
        i.release();
    }

    @Test
    public void testStateReleasedIdeaIllegalStateExceptions() {
        try {
            i.hold();
            Assertions.fail("ReleasedIdea.hold() should throw an IllegalStateException if called!");
        } catch (IllegalStateException e) {
        }

        try {
            i.decline();
            Assertions.fail("ReleasedIdea.decline() should throw an IllegalStateException if called!");
        } catch (IllegalStateException e) {
        }

        try {
            i.discuss("Test");
            Assertions.fail("ReleasedIdea.discuss() should throw an IllegalStateException if called!");
        } catch (IllegalStateException e) {
        }

        try {
            i.evaluate(new JValuation("title", "description"));
            Assertions.fail("ReleasedIdea.evaluate() should throw an IllegalStateException if called!");
        } catch (IllegalStateException e) {
        }

        try {
            i.release();
            Assertions.fail("ReleasedIdea.release() should throw an IllegalStateException if called!");
        } catch (IllegalStateException e) {
        }
    }
}
