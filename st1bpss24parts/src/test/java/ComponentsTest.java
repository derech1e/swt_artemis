import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

 
public class ComponentsTest {
    @Test
    public void componentTestConstructorNullArgument() {
        try {
            new Components(null, "name");
            Assertions.fail("Components.Components() should throw a NullPointerException if the id argument is null!");
        } catch (NullPointerException e) {
        }

        try {
            new Components("id", null);
            Assertions.fail("Components.Components() should throw a NullPointerException if the name argument is null!");
        } catch (NullPointerException e) {
        }

        try {
            new Components(null, null);
            Assertions.fail(
                    "Components.Components() should throw a NullPointerException if the id and the name argument are null!");
        } catch (NullPointerException e) {
        }
    }

    @Test
    public void componentTestConstructorIllegalArgument() {
        try {
            new Components("", "name");
            Assertions.fail("Components.Components() should throw an IllegalArgumentException if the id argument is empty!");
        } catch (IllegalArgumentException e) {
        }

        try {
            new Components("id", "");
            Assertions.fail("Components.Components() should throw an IllegalArgumentException if the name argument is empty!");
        } catch (IllegalArgumentException e) {
        }

        try {
            new Components("", "");
            Assertions.fail("Components.Components() should throw an IllegalArgumentException if the id and the name argument are empty!");
        } catch (IllegalArgumentException e) {
        }
    }
}