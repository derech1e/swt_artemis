import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

  
public class StateClosingTest extends DoorStateBaseTest {
    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();
        garageDoor.openDoor();
        garageDoor.closeDoor();
    }

    @Test
    public void stateClosingTestOpenDoor() {
        garageDoor.openDoor();
        Assertions.assertEquals("Opening", getCurrentStateName(), "Closing.openDoor() should change the state to Opening!");
    }

    @Test
    public void stateClosingTestStopper() {
        garageDoor.stopper();
        Assertions.assertEquals("Closed", getCurrentStateName(), "Closing.stopper() should change the state to Closed!");
    }

    @Test
    public void stateClosingTestIllegalStateExceptions() {
        try {
            garageDoor.closeDoor();
            Assertions.fail("Closed.closeDoor() should throw an IllegalStateException if called!");
        } catch (IllegalStateException e) {
        }
    }
}
