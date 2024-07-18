import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

  
public class StateClosedTest extends DoorStateBaseTest {
    @Test
    public void stateClosedTestOpenDoor() {
        garageDoor.openDoor();
        Assertions.assertEquals("Opening", getCurrentStateName(), "Closed.openDoor() should change the state to Opening!");
    }

    @Test
    public void stateClosedTestIllegalStateExceptions() {
        try {
            garageDoor.stopper();
            Assertions.fail("Closed.stopper() should throw an IllegalStateException if called!");
        } catch (IllegalStateException e) {
        }

        try {
            garageDoor.closeDoor();
            Assertions.fail("Closed.closeDoor() should throw an IllegalStateException if called!");
        } catch (IllegalStateException e) {
        }
    }
}
