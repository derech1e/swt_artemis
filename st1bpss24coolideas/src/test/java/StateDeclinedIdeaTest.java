import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

 
public class StateDeclinedIdeaTest extends JStateBaseTest {
    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();
        i.decline();
    }

    @Test
    public void stateDeclinedIdeaTestIllegalStateExceptions() {
        try {
            i.hold();
            Assertions.fail("DeclinedIdea.hold() should throw an IllegalStateException if called!");
        } catch (IllegalStateException e) {
        }

        try {
            i.decline();
            Assertions.fail("DeclinedIdea.decline() should throw an IllegalStateException if called!");
        } catch (IllegalStateException e) {
        }

        try {
            i.discuss("Test");
            Assertions.fail("DeclinedIdea.discuss() should throw an IllegalStateException if called!");
        } catch (IllegalStateException e) {
        }

        try {
            i.evaluate(new JValuation("title", "description"));
            Assertions.fail("DeclinedIdea.evaluate() should throw an IllegalStateException if called!");
        } catch (IllegalStateException e) {
        }

        try {
            i.release();
            Assertions.fail("DeclinedIdea.release() should throw an IllegalStateException if called!");
        } catch (IllegalStateException e) {
        }

    }
}
