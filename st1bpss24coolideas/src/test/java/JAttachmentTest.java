import java.io.File;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JAttachmentTest extends JContentTestBase {
    private JAttachment jAttachment;

    @Override
    @BeforeEach
    public void setUp() {
        jAttachment = new JAttachment("title", "description", new File("/dev/null"));
        jContent = jAttachment;
    }

    @Test
    public void jAttachmentTestConstructorNullArgument() {
        try {
            new JAttachment("title", "description", null);
            Assertions.fail(
                    "JAttachment.JAttachment() should throw a NullPointerException if the File argument is null!");
        } catch (NullPointerException e) {
        }
    }

    @Test
    public void jAttachmentTestConstructorIllegalArgument() {
        File file = new File("/dev/null");

        try {
            new JAttachment("", "description", file);
            Assertions.fail("JAttachment.JAttachment() should throw an IllegalArgumentException if the title argument is empty!");
        } catch (IllegalArgumentException e) {
        }

        try{
            new JAttachment("", "", file);
            Assertions.fail("JAttachment.JAttachment() should throw an IllegalArgumentException if the description argument is empty!");
        } catch (IllegalArgumentException e) {
        }

        try {
            new JAttachment("", "", file);
            Assertions.fail(
                    "JAttachment.JAttachment() should throw an IllegalArgumentException if the title and the description argument are empty!");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void jAttachmentTestGetFile() {
        Assertions.assertEquals(new File("/dev/null"), jAttachment.getFile(), "JAttachment.getFile() does not work correctly!");
    }

    @Test
    public void jAttachmentTestSetFileNullArgument() {
        try {
            jAttachment.setFile(null);
            Assertions.fail("JAttachment.setFile() should throw a NullPointerException if the argument is null!");
        } catch (NullPointerException e) {
        }
    }

    @Test
    public void jAttachmentTestSetFile() {
        File expResult = new File("/etc/X11");
        jAttachment.setFile(expResult);
        Assertions.assertEquals(expResult, jAttachment.getFile(), "JAttachment.setFile() should set the file correctly!");
    }

    @Test
    public void jAttachmentTestToString() {
        Assertions.assertEquals("Attachment: title\ndescription", jAttachment.toString(), "JAttachment.toString() should return the correct String!");
    }
}
