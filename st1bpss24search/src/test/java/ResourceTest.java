import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

 
public class ResourceTest {
    private Resource r;
    private ResourceType rt;

    @BeforeEach
    public void setUp() {
        rt = new ResourceType("Plain Text File", new PlainTextCollector());
        r = new Resource("text.txt", "/home/user/textfiles/", rt);
    }

    @Test
    public void resourceTestConstructorNullArgument() {
        try {
            new Resource(null, "/home/user/textfiles/", rt);
            Assertions.fail("Resource.Resource() should throw a NullPointerException if the name argument is null!");
        } catch (NullPointerException e) {
        }

        try {
            new Resource("text.txt", null, rt);
            Assertions.fail("Resource.Resource() should throw a NullPointerException if the path argument is null!");
        } catch (NullPointerException e) {
        }

        try {
            new Resource("text.txt", "/home/user/textfiles/", null);
            Assertions.fail("Resource.Resource() should throw a NullPointerException if the rt argument is null!");
        } catch (NullPointerException e) {
        }
    }

    @Test
    public void resourceTestConstructorIllegalArgument() {
        try {
            new Resource("", "/home/user/textfiles/", rt);
            Assertions.fail(
                    "Resource.Resource() should throw an IllegalArgumentException if the name argument is empty!");
        } catch (IllegalArgumentException e) {
        }

        try {
            new Resource("text.txt", "", rt);
            Assertions.fail("Resource.Resource() should throw an IllegalArgumentException if the path argument is empty!");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void resourceTestGetName() {
        Assertions.assertEquals("text.txt", r.getName(), "Resource.getName() should return the correct value!");
    }

    @Test
    public void resourceTestGetPath() {
        Assertions.assertEquals("/home/user/textfiles/", r.getPath(), "Resource.getPath() should return the correct value!");
    }

    @Test
    public void resourceTestGetType() {
        Assertions.assertEquals(rt, r.getType(), "Resource.getType() should return the correct object!");
    }
}
