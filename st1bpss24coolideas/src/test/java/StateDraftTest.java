import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

 
public class StateDraftTest extends JStateBaseTest {
    @Test
    public void stateDraftTestHold() {
        i.hold();
        Assertions.assertEquals(getClassOpenDraft(), getState().getClass(),
                "Draft.hold() should change the state to OpenDraft!");
    }

    @Test
    public void stateDraftTestDecline() {
        i.decline();
        Assertions.assertEquals(getClassDeclinedIdea(), getState()
                .getClass(), "Draft.decline() should change the state to DeclinedIdea!");
    }

    @Test
    public void stateDraftTestIllegalStateExceptions() {
        try {
            i.discuss("Test");
            Assertions.fail("Draft.discuss() should throw an IllegalStateException if called!");
        } catch (IllegalStateException e) {
        }

        try {
            i.evaluate(new JValuation("title", "description"));
            Assertions.fail("Draft.evaluate() should throw an IllegalStateException if called!");
        } catch (IllegalStateException e) {
        }

        try {
            i.release();
            Assertions.fail("Draft.release() should throw an IllegalStateException if called!");
        } catch (IllegalStateException e) {
        }
    }
}
