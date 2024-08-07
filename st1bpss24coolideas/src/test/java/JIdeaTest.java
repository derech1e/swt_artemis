import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

 
public class JIdeaTest extends JContentTestBase {
    private JIdea jIdea;
    private JAttachment a = new JAttachment("titleA", "descriptionA", new File("/dev/null"));
    private JAttachment b = new JAttachment("titleB", "descriptionB", new File("/dev/null"));
    private JAttachment c = new JAttachment("titleC", "descriptionC", new File("/dev/null"));
    private List<JAttachment> expAttachments = Arrays.asList(new JAttachment[] { a, b, c });

    @Override
    @BeforeEach
    public void setUp() {
        jIdea = new JIdea("title", "description");
        jContent = jIdea;
    }

    @Test
    public void jIdeaTestConstructorNullArgument() {
        try {
            new JIdea(null, "description");
            Assertions.fail("JIdea.JIdea() should throw a NullPointerException if the title argument is null!");
        } catch (NullPointerException e) {
        }

        try {
            new JIdea("title", null);
            Assertions.fail("JIdea.JIdea() should throw a NullPointerException if the description argument is null!");
        } catch (NullPointerException e) {
        }

        try {
            new JIdea(null, null);
            Assertions.fail(
                    "JIdea.JIdea() should throw a NullPointerException if the title and the description argument are null!");
        } catch (NullPointerException e) {
        }
    }

    @Test
    public void jIdeaTestConstructorIllegalArgument() {
        try {
            new JIdea("", "description");
            Assertions.fail("JIdea.JIdea() should throw an IllegalArgumentException if the title argument is empty!");
        } catch (IllegalArgumentException e) {
        }

        try {
            new JIdea("title", "");
            Assertions.fail("JIdea.JIdea() should throw an IllegalArgumentException if the description argument is empty!");
        } catch (IllegalArgumentException e) {
        }

        try {
            new JIdea("", "");
            Assertions.fail("JIdea.JIdea() should throw an IllegalArgumentException if the title and the description argument are empty!");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void jIdeaTestIsDeclined() {
        Assertions.assertFalse(jIdea.isDeclined(), "JIdea.isDeclined() should return false if the state is not DeclinedIdea!");
        jIdea.hold();
        Assertions.assertFalse(jIdea.isDeclined(), "JIdea.isDeclined() should return false if the state is not DeclinedIdea!");
        jIdea.hold();
        Assertions.assertFalse(jIdea.isDeclined(), "JIdea.isDeclined() should return false if the state is not DeclinedIdea!");
        jIdea.release();
        Assertions.assertFalse(jIdea.isDeclined(), "JIdea.isDeclined() should return false if the state is not DeclinedIdea!");
        jIdea = new JIdea("title", "description");
        jIdea.decline();
        Assertions.assertTrue(jIdea.isDeclined(), "JIdea.isDeclined() should return true if the state is DeclinedIdea!");

    }

    @Test
    public void jIdeaTestIsReleased() {
        Assertions.assertFalse(jIdea.isReleased(), "JIdea.isReleased() should return false if the state is not ReleasedIdea!");
        jIdea.hold();
        Assertions.assertFalse(jIdea.isReleased(), "JIdea.isReleased() should return false if the state is not ReleasedIdea!");
        jIdea.hold();
        Assertions.assertFalse(jIdea.isReleased(), "JIdea.isReleased() should return false if the state is not ReleasedIdea!");
        jIdea.release();
        Assertions.assertTrue(jIdea.isReleased(), "JIdea.isReleased() should return true if the state is ReleasedIdea!");
        jIdea = new JIdea("title", "description");
        jIdea.decline();
        Assertions.assertFalse(jIdea.isReleased(), "JIdea.isReleased() should return false if the state is not ReleasedIdea!");

    }

    @Test
    public void jIdeaTestAddAttachmentNullArgument() {
        try {
            jIdea.addAttachment(null);
            Assertions.fail("JIdea.addAttachment() should throw a NullPointerException if the argument is null!");
        } catch (NullPointerException e) {
        }
    }

    @Test
    public void jIdeaTestGetAttachments() {
        jIdea.addAttachment(a);
        jIdea.addAttachment(b);
        jIdea.addAttachment(c);
        List<JAttachment> actualAttachments = jIdea.getAttachments();

        for (JAttachment attachment : expAttachments) {
            Assertions.assertTrue(expAttachments.contains(attachment),
                    "JIdea.getAttachments() should return every attachment of a JIdea!");
        }
        Assertions.assertEquals(actualAttachments.size(), expAttachments.size(),
                "JIdea.getAttachments() should return nothing more than the JIdea's attachments!");
    }

    @Test
    public void jIdeaTestRemoveAttachmentNullArgument() {
        try {
            jIdea.removeAttachment(null);
            Assertions.fail("JIdea.removeAttachment() should throw a NullPointerException if the argument is null!");
        } catch (NullPointerException e) {
        }
    }

    @Test
    public void jIdeaTestRemoveAttachment() {
        Assertions.assertFalse(jIdea.removeAttachment(a), "JIdea.removeAttachment() should return false if the JIdea did not contain the attachment!");

        jIdea.addAttachment(a);
        jIdea.addAttachment(b);
        jIdea.addAttachment(c);

        for (JAttachment attachment : expAttachments) {
            Assertions.assertTrue(jIdea.removeAttachment(attachment), "JIdea.removeAttachment() should return true if the JIdea did contain the attachment!");
            Assertions.assertFalse(jIdea.getAttachments()
                    .contains(attachment), "JIdea.removeAttachment() should actually delete the attachment!");
        }
    }

    @Test
    public void jIdeaTestToString() {
        String expResult = "Idea: title\ndescription";
        Assertions.assertEquals(expResult, jIdea.toString(), "JIdea.toString() does not work correctly.");
    }
}
