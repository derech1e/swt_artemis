import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

 
public class ResourceTest {
    @Test
    public void resourceTestConstructorNullArgument() {
        try {
            new Resource(null, "name");
            Assertions.fail("Resource.Resource() should throw a NullPointerException if the id argument is null!");
        } catch (NullPointerException e) {
        }

        try {
            new Resource("id", null);
            Assertions.fail("Resource.Resource() should throw a NullPointerException if the name argument is null!");
        } catch (NullPointerException e) {
        }

        try {
            new Resource(null, null);
            Assertions.fail(
                    "Resource.Resource() should throw a NullPointerException if the id and the name argument are null!");
        } catch (NullPointerException e) {
        }
    }

    @Test
    public void resourceTestConstructorIllegalArgument() {
        try {
            new Resource("", "name");
            Assertions.fail("Resource.Resource() should throw an IllegalArgumentException if the id argument is empty!");
        } catch (IllegalArgumentException e) {
        }

        try {
            new Resource("id", "");
            Assertions.fail("Resource.Resource() should throw an IllegalArgumentException if the name argument is empty!");
        } catch (IllegalArgumentException e) {
        }

        try {
            new Resource("", "");
            Assertions.fail("Resource.Resource() should throw an IllegalArgumentException if the id and the name argument are empty!");
        } catch (IllegalArgumentException e) {
        }
    }
}