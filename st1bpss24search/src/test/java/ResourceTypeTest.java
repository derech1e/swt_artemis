import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

 
public class ResourceTypeTest {
    private ResourceType rt;
    private KeywordCollector coll;

    @BeforeEach
    public void setUp() {
        coll = new PlainTextCollector();
        rt = new ResourceType("Portable Network Graphics", coll);

    }

    @Test
    public void resourceTypeTestConstructorNullArgument() {
        try {
            new ResourceType(null, coll);
            Assertions.fail(
                    "ResourceType.ResourceType() should throw a NullPointerException if the desc argument is null!");
        } catch (NullPointerException e) {
        }

        try {
            new ResourceType("Portable Network Graphics", null);
            Assertions.fail("ResourceType.ResourceType() should throw a NullPointerException if the keywordCollector argument is null!");
        } catch (NullPointerException e) {
        }
    }

    @Test
    public void resourceTypeTestConstructorIllegalArgument() {
        try {
            new ResourceType("", coll);
            Assertions.fail("ResourceType.ResourceType() should throw an IllegalArgumentException if the desc argument is empty!");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void resourceTypeTestGetDescription() {
        Assertions.assertEquals("Portable Network Graphics", rt.getDescription(),
                "ResourceType.getDescription() should return the correct value!");
    }

    @Test
    public void resourceTypeTestGetCollector() {
        Assertions.assertEquals(coll, rt.getCollector(), "ResourceType.getCollector() should return the correct object!");
    }
}
