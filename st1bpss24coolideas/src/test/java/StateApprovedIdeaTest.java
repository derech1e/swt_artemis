import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.tum.in.test.api.StrictTimeout;
import de.tum.in.test.api.WhitelistClass;
import de.tum.in.test.api.jupiter.Public;

@Public
@StrictTimeout(1)
@WhitelistClass(JStateBaseTest.class)
public class StateApprovedIdeaTest extends JStateBaseTest {
    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();
        i.hold();
        i.hold();
    }

    @Test
    public void stateApprovedIdeaTestRelease() {
        i.release();
        Assertions.assertEquals(getClassReleasedIdea(), getState()
                .getClass(), "ApprovedIdea.release() should change the state to ReleasedIdea!");
    }

    @Test
    public void stateApprovedIdeaTestIllegalStateExceptions() {
        try {
            i.hold();
            Assertions.fail("ApprovedIdea.hold() should throw an IllegalStateException if called!");
        } catch (IllegalStateException e) {
        }

        try {
            i.decline();
            Assertions.fail("ApprovedIdea.decline() should throw an IllegalStateException if called!");
        } catch (IllegalStateException e) {
        }

        try {
            i.discuss("Test");
            Assertions.fail("ApprovedIdea.discuss() should throw an IllegalStateException if called!");
        } catch (IllegalStateException e) {
        }

        try {
            i.evaluate(new JValuation("title", "description"));
            Assertions.fail("ApprovedIdea.evaluate() should throw an IllegalStateException if called!");
        } catch (IllegalStateException e) {
        }
    }
}
