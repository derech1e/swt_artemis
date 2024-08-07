import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.tum.in.test.api.StrictTimeout;
import de.tum.in.test.api.WhitelistClass;
import de.tum.in.test.api.jupiter.Public;

@Public
@StrictTimeout(1)
@WhitelistClass(JStateBaseTest.class)
public class StateOpenDraftTest extends JStateBaseTest {
    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();
        i.hold();
    }

    @Test
    public void stateOpenDraftTestDiscuss() {
        try {
            i.discuss(null);
            Assertions.fail("OpenState.discuss() should throw a NullPointerException if the argument is null!");
        } catch (NullPointerException e) {
        }
        try {
            i.discuss("");
            Assertions.fail("OpenState.discuss() should throw an IllegalArgumentException if the argument is empty!");
        } catch (IllegalArgumentException e) {
        }
        i.discuss("discussion");
        Assertions.assertEquals(getClassOpenDraft(), getState().getClass(),
                "OpenState.discuss() should not change the state!");
        Assertions.assertEquals("discussion\n", i.getCurrentDiscussion(),
                "OpenState.discuss() should add the new discussion text and a line break to the previous discussion!");
        i.discuss("second discussion");
        Assertions.assertEquals("discussion\nsecond discussion\n", i.getCurrentDiscussion(),
                "OpenState.discuss() should add the new discussion text and a line break to the previous texts!");
    }

    @Test
    public void stateOpenDraftTestEvaluate() {
        try {
            i.evaluate(null);
            Assertions.fail("OpenState.evaluate() should throw a NullPointerException if the argument is null!");
        } catch (NullPointerException e) {
        }
        JValuation v = new JValuation("title", "description");
        JValuation v2 = new JValuation("title2", "second valuation");
        i.evaluate(v);
        Assertions.assertEquals(getClassOpenDraft(), getState().getClass(),
                "OpenDraft.evaluate() should not change the state!");
        Assertions.assertEquals(v, i.getValuation(), "OpenState.evaluate() should replace the current valuation with the new one!");
        i.evaluate(v2);
        Assertions.assertEquals(v2, i.getValuation(),
                "OpenState.evaluate() should replace the current valuation with the new one!");
    }

    @Test
    public void stateOpenDraftTestHold() {
        i.hold();
        Assertions.assertEquals(getClassApprovedIdea(), getState()
                .getClass(), "OpenDraft.hold() should change the state to ApprovedIdea!");
    }

    @Test
    public void stateOpenDraftTestDecline() {
        i.decline();
        Assertions.assertEquals(getClassDeclinedIdea(), getState()
                .getClass(), "OpenDraft.decline() should change the state to DeclinedIdea!");
    }

    @Test
    public void stateOpenDraftTestIllegalStateExceptions() {
        try {
            i.release();
            Assertions.fail("OpenDraft.release() should throw an IllegalStateException if called!");
        } catch (IllegalStateException e) {
        }
    }
}
