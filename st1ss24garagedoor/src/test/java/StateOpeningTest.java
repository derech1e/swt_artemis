import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

  
public class StateOpeningTest extends DoorStateBaseTest {
    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();
        garageDoor.openDoor();
    }

    @Test
    public void stateOpeningTestCloseDoor() {
        garageDoor.closeDoor();
        Assertions.assertEquals("Closing", getCurrentStateName(),
                "Opening.closeDoor() should change the state to Closing!");
    }

    @Test
    public void stateOpeningTestStopper() {
        garageDoor.stopper();
        Assertions.assertEquals("Open", getCurrentStateName(), "Opening.stopper() should change the state to Open!");
    }

    @Test
    public void stateOpeningTestIllegalStateExceptions() {
        try {
            garageDoor.openDoor();
            Assertions.fail("Opening.openDoor() should throw an IllegalStateException if called!");
        } catch (IllegalStateException e) {
        }
    }
}
