import java.lang.reflect.Field;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;

 
public class DoorStateBaseTest {
    protected GarageDoor garageDoor;

    @BeforeEach
    public void setUp() {
        garageDoor = new GarageDoor();
    }

    protected String getCurrentStateName() {
        try {
            Field myField = GarageDoor.class.getDeclaredField("currentState");
            myField.setAccessible(true);
            Object currentState = myField.get(garageDoor);
            return currentState.getClass().getSimpleName();
        } catch (NoSuchFieldException e) {
            throw new AssertionError("GarageDoor should have an attribute named currentState!");
        } catch (IllegalAccessException e) {
            throw new AssertionError("An unexpected error occurred!");
        }
    }
}
