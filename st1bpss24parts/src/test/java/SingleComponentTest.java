import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

 
public class SingleComponentTest {
    @Test
    public void singleComponentTestConstructorNullArgument() {
        try {
            new SingleComponent(null, "name");
            Assertions.fail(
                    "SingleComponent.SingleComponent() should throw a NullPointerException if the id argument is null!");
        } catch (NullPointerException e) {
        }

        try {
            new SingleComponent("id", null);
            Assertions.fail("SingleComponent.SingleComponent() should throw a NullPointerException if the name argument is null!");
        } catch (NullPointerException e) {
        }

        try {
            new SingleComponent(null, null);
            Assertions.fail(
                    "SingleComponent.SingleComponent() should throw a NullPointerException if the id and the name argument are null!");
        } catch (NullPointerException e) {
        }
    }

    @Test
    public void singleComponentTestConstructorIllegalArgument() {
        try {
            new SingleComponent("", "name");
            Assertions.fail("SingleComponent.SingleComponent() should throw an IllegalArgumentException if the id argument is empty!");
        } catch (IllegalArgumentException e) {
        }

        try {
            new SingleComponent("id", "");
            Assertions.fail("SingleComponent.SingleComponent() should throw an IllegalArgumentException if the name argument is empty!");
        } catch (IllegalArgumentException e) {
        }

        try {
            new SingleComponent("", "");
            Assertions.fail("SingleComponent.SingleComponent() should throw an IllegalArgumentException if the id and the name argument are empty!");
        } catch (IllegalArgumentException e) {
        }
    }
}