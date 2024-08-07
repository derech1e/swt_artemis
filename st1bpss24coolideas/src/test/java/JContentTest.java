import java.lang.reflect.Modifier;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

 
public class JContentTest extends JContentTestBase {
    @Test
    public void jContentTestAbstract() {
        Assertions.assertTrue(Modifier.isAbstract(JContent.class.getModifiers()), "JContent should be abstract!");
    }

    @Test
    public void jContentTestConstructorIllegalArgument() {
        try {
            new JContentImpl(null, "description");
            Assertions.fail("JContent.JContent() should throw a NullPointerException if the title argument is null!");
        } catch (NullPointerException e) {
        }

        try {
            new JContentImpl("title", null);
            Assertions.fail(
                    "JContent.JContent() should throw a NullPointerException if the description argument is null!");
        } catch (NullPointerException e) {
        }

        try {
            new JContentImpl(null, null);
            Assertions.fail("JContent.JContent() should throw a NullPointerException if the arguments are null!");
        } catch (NullPointerException e) {
        }

        try {
            new JContentImpl("", "description");
            Assertions.fail("JContent.JContent() should throw an IllegalArgumentException if the title argument is empty!");
        } catch (IllegalArgumentException e) {
        }

        try {
            new JContentImpl("title", "");
            Assertions.fail("JContent.JContent() should throw an IllegalArgumentException if the description argument is empty!");
        } catch (IllegalArgumentException e) {
        }

        try {
            new JContentImpl("", "");
            Assertions.fail("JContent.JContent() should throw an IllegalArgumentException if the arguments are empty!");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void jContentTestGetTitle() {
        Assertions.assertEquals("title", jContent.getTitle(), "JContent.getTitle() should return the correct title!");
    }

    @Test
    public void jContentTestSetTitleIllegalArgument() {
        try {
            jContent.setTitle(null);
            Assertions.fail("JContent.setTitle() should throw a NullPointerException if the argument is null!");
        } catch (NullPointerException e) {
        }

        try {
            jContent.setTitle("");
            Assertions.fail("JContent.setTitle() should throw an IllegalArgumentException if the argument is empty!");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void jContentTestSetTitle() {
        jContent.setTitle("newTitle");
        Assertions.assertEquals("newTitle", jContent.getTitle(), "JContent.setTitle() should set the title correctly!");
    }

    @Test
    public void jContentTestGetDescription() {
        Assertions.assertEquals("description", jContent.getDescription(),
                "JContent.getDescription() should return the correct description!");
    }

    @Test
    public void jContentTestSetDescriptionIllegalArgument() {
        try {
            jContent.setDescription(null);
            Assertions.fail("JContent.setDescription() should throw a NullPointerException if the argument is null!");
        } catch (NullPointerException e) {
        }

        try {
            jContent.setDescription("");
            Assertions.fail("JContent.setDescription() should throw an IllegalArgumentException if the argument is empty!");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void jContentTestSetDescription() {
        jContent.setDescription("newDescription");
        Assertions.assertEquals("newDescription", jContent.getDescription(), "JContent.setDescription() should set the description correctly!");
    }
}
