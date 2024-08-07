import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

 
public class JTopicTest extends JContentTestBase {
    private JTopic jTopic;

    @Override
    @BeforeEach
    public void setUp() {
        jTopic = new JTopic("title", "description", 1);
        jContent = jTopic;
    }

    @Test
    public void jTopicTestConstructorNullArgument() {
        try {
            new JTopic(null, "description", 0);
            Assertions.fail("JTopic.JTopic() should throw a NullPointerException if the title argument is null!");
        } catch (NullPointerException e) {
        }

        try {
            new JTopic("title", null, 0);
            Assertions.fail("JTopic.JTopic() should throw a NullPointerException if the description argument is null!");
        } catch (NullPointerException e) {
        }

        try {
            new JTopic(null, null, 0);
            Assertions.fail(
                    "JTopic.JTopic() should throw a NullPointerException if the title and the description argument are null!");
        } catch (NullPointerException e) {
        }
    }

    @Test
    public void jTopicTestConstructorInvalidArgument() {
        try {
            new JTopic("", "description", 0);
            Assertions.fail("JTopic.JTopic() should throw an IllegalArgumentException if the title argument is empty!");
        } catch (IllegalArgumentException e) {
        }

        try {
            new JTopic("title", "", 0);
            Assertions.fail("JTopic.JTopic() should throw an IllegalArgumentException if the description argument is empty!");
        } catch (IllegalArgumentException e) {
        }

        try {
            new JTopic("", "", 0);
            Assertions.fail("JTopic.JTopic() should throw an IllegalArgumentException if the title and the description argument are empty!");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void jTopicTestGetId() {
        Assertions.assertEquals(jTopic.getId(), 1, "JTopic.getId() should return the correct ID!");
    }

    @Test
    public void jTopicTestToString() {
        Assertions.assertEquals("Topic: title\ndescription", jTopic.toString(), "JTopic.toString() should return the correct String!");
    }
}
