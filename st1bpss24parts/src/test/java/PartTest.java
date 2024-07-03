import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

 
public class PartTest {
    private static class PartDummy extends Part {
        public PartDummy(String id, String name) {
            super(id, name);
        }
    }

    private Part p1, p2, p3, p4;

    @BeforeEach
    public void setUp() {
        p1 = new PartDummy("id1", "name1");
        p2 = new Components("id2", "name2");
        p3 = new SingleComponent("id3", "name3");
        p4 = new Resource("id4", "name4");
    }

    @Test
    public void partTestConstructorNullArgument() {
        try {
            new PartDummy(null, "name");
            Assertions.fail("Part.Part() should throw a NullPointerException if the id argument is null!");
        } catch (NullPointerException e) {
        }

        try {
            new PartDummy("id", null);
            Assertions.fail("Part.Part() should throw a NullPointerException if the name argument is null!");
        } catch (NullPointerException e) {
        }
    }

    @Test
    public void partTestConstructorIllegalArgument() {
        try {
            new PartDummy("", "name");
            Assertions.fail("Part.Part() should throw an IllegalArgumentException if the id argument is empty!");
        } catch (IllegalArgumentException e) {
        }

        try {
            new PartDummy("id", "");
            Assertions.fail("Part.Part() should throw an IllegalArgumentException if the name argument is empty!");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void partTestGetId() {
        Assertions.assertEquals("id1", p1.getId(), "Part.getId() should return the correct value!");
        Assertions.assertEquals("id2", p2.getId(), "Components.getId() should return the correct value!");
        Assertions.assertEquals("id3", p3.getId(), "SingleComponent.getId() should return the correct value!");
        Assertions.assertEquals("id4", p4.getId(), "Resource.getId() should return the correct value!");
    }

    @Test
    public void partTestGetName() {
        Assertions.assertEquals("name1", p1.getName(), "Part.getName() should return the correct value!");
        Assertions.assertEquals("name2", p2.getName(), "Components.getName() should return the correct value!");
        Assertions.assertEquals("name3", p3.getName(), "SingleComponent.getName() should return the correct value!");
        Assertions.assertEquals("name4", p4.getName(), "Resource.getName() should return the correct value!");
    }
}
