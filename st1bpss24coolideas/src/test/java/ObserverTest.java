import static java.lang.reflect.Modifier.isInterface;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UncheckedIOException;
import java.io.UnsupportedEncodingException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// Checking-code for io-streams is from:
// https://stackoverflow.com/questions/1119385/junit-test-for-system-out-println

 
public class ObserverTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private PrintStream backOut, backErr;

    private JMember m1, m2;
    private JTopic t1, t2;


    @BeforeEach
    public void setUp() {
        backOut = System.out;
        backErr = System.err;

        try {
            System.setOut(new PrintStream(outContent, false, "UTF-8"));
            System.setErr(new PrintStream(errContent, false, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new UncheckedIOException("UTF-8 is not supported.", e);
        }

        m1 = new JMember();
        m2 = new JMember();
        t1 = new JTopic("title1", "description1", 1);
        t2 = new JTopic("title2", "description2", 2);
    }

    @AfterEach
    public void cleanUpStreams() {
        System.setOut(backOut);
        System.setErr(backErr);
    }

    // A method comparing the String streamOutput with the actual streams
    private void testStreams(String message, String streamOutput) {
        try {
            Assertions.assertEquals(streamOutput + System.lineSeparator(), outContent.toString("UTF-8"), message);
            Assertions.assertEquals("", errContent.toString("UTF-8"),
                    "Your program should not print into the error stream (System.err)!");
        } catch (UnsupportedEncodingException e) {
            throw new UncheckedIOException("UTF-8 is not supported.", e);
        }
        outContent.reset();
        errContent.reset();
    }

    @Test
    public void testStructure() {
        Assertions.assertTrue(isInterface(ContentObserver.class.getModifiers()), "ContentObserver should be an " +
                "interface!");
        Assertions.assertTrue(ContentObserver.class.isAssignableFrom(JMember.class), "JMember should implement ContentObserver!");
    }

    @Test
    public void testSubscribe() {
        m1.subscribe(t1);
        Assertions.assertTrue(m1
                .getSubscribedTopics().contains(t1), "JMember.subscribe() should add the topic to the member's subscribed topics!");
        Assertions.assertEquals(1, t1.countObservers(), "JMember.subscribe() should add the JMember as an Observer of" +
                " the topic!");

        m1.subscribe(t2);
        Assertions.assertTrue(m1
                .getSubscribedTopics().contains(t2), "JMember.subscribe() should add the topic to the member's subscribed topics!");
        Assertions.assertEquals(1, t2.countObservers(), "JMember.subscribe() should add the JMember as an Observer of the topic!");

        m2.subscribe(t2);
        Assertions.assertTrue(m2
                .getSubscribedTopics().contains(t2), "JMember.subscribe() should add the topic to the member's subscribed topics!");
        Assertions.assertEquals(2, t2.countObservers(), "JMember.subscribe() should add the JMember as an Observer of the topic!");

        m1.subscribe(t1);
        Assertions.assertEquals(1, t1.countObservers(), "JMember.subscribe() should not add the JMember as an " +
                "Observer of a topic more than once!");

        m1.subscribe(t2);
        Assertions.assertEquals(2, t2.countObservers(), "JMember.subscribe() should not add the JMember as an Observer of a topic more than once!");
    }

    @Test
    public void testUnsubscribe() {
        m1.subscribe(t1);
        m1.subscribe(t2);
        m2.subscribe(t2);

        m1.unsubscribe(t2);
        Assertions.assertFalse(m1
                .getSubscribedTopics().contains(t2), "JMember.unsubscribe() should remove the topic from the member's subscribed topics!");
        Assertions.assertEquals(1, t2.countObservers(), "JMember.unsubscribe() should remove the JMember as an Observer of the topic!");

        m1.unsubscribe(t1);
        Assertions.assertFalse(m1
                .getSubscribedTopics().contains(t1), "JMember.unsubscribe() should add the topic to the member's subscribed topics!");
        Assertions.assertEquals(0, t1.countObservers(), "JMember.unsubscribe() should add the JMember as an Observer of the topic!");

        m2.unsubscribe(t1);
        Assertions.assertTrue(m2
                .getSubscribedTopics().contains(t2), "JMember.unsubscribe() should only remove topics subscribed by the member!");
        Assertions.assertEquals(1, t2.countObservers(), "JMember.unsubscribe() should add the JMember as an Observer of the topic!");

        m2.unsubscribe(t2);
        Assertions.assertFalse(m2
                .getSubscribedTopics().contains(t2), "JMember.unsubscribe() should add the topic to the member's subscribed topics!");
        Assertions.assertEquals(0, t2.countObservers(), "JMember.unsubscribe() should add the JMember as an Observer of the topic!");
    }

    @Test
    public void testNotifications() {
        m1.subscribe(t1);
        m1.subscribe(t2);
        m2.subscribe(t2);

        t1.setTitle("newTitle1");
        testStreams("JMember.update() should print the correct log message when a topic is updated!", "The topic "
                + t1.getId() + " has been updated!");
        t1.setDescription("newDescription1");
        testStreams("JMember.update() should print the correct log message when a topic is updated!", "The topic "
                + t1.getId() + " has been updated!");

        t2.setTitle("newTitle2");
        testStreams("JMember.update() should print the correct log message when a topic is updated!", "The topic "
                + t2.getId() + " has been updated!" + System.lineSeparator() + "The topic " + t2.getId()
                + " has been updated!");
        t2.setDescription("newDescription2");
        testStreams("JMember.update() should print the correct log message when a topic is updated!", "The topic "
                + t2.getId() + " has been updated!" + System.lineSeparator() + "The topic " + t2.getId()
                + " has been updated!");
    }
}
