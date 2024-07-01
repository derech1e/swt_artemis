import java.util.NoSuchElementException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

 
public class TextFileIteratorTest {
    private TextFileIterator tfi;

    @BeforeEach
    public void setUp() {
        tfi = new TextFileIterator(new Resource("text.txt", "/home/user/textfiles/", new ResourceType(
                "Plain Text File", new PlainTextCollector())));
    }

    @Test
    public void textFileIteratorTestConstructorNullArgument() {
        try {
            new TextFileIterator(null);
            Assertions.fail(
                    "TextFileIterator.TextFileIterator() should throw a NullPointerException if the argument is null!");
        } catch (NullPointerException e) {
        }
    }

    @Test
    public void textFileIteratorTestIterator() {
        String[] expected = "We wish you good luck in this exam We hope you are well prepared".split(" ");
        for (int i = 0; i < expected.length; i++) {
            Assertions.assertTrue(tfi.hasNext(),
                    "TextFileIterator.hasNext() should return true if there is another word in the text!");
            Assertions.assertEquals(expected[i], tfi.next(),
                    "TextFileIterator.next() should return the correct next word!");
        }
        Assertions.assertFalse(tfi.hasNext(), "TextFileIterator.hasNext() should return false if there is no next word!");
        try {
            tfi.next();
            Assertions.fail("TextFileIterator.next() should throw a NoSuchElementException if there is no next word!");
        } catch (NoSuchElementException e) {
        }
    }

    @Test
    public void textFileIteratorTestRemove() {
        try {
            tfi.remove();
            Assertions.fail("TextFileIterator.remove() should throw an UnsupportedOperationException upon being called!");
        } catch (UnsupportedOperationException e) {
        }
    }
}
