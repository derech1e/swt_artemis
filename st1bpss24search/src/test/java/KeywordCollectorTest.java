import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

 
public class KeywordCollectorTest {
    private KeywordCollector coll;

    @Test
    public void testInterface() {
        Assertions.assertTrue(KeywordCollector.class.isInterface());
    }

    @Test
    public void testDefaultCollectorGetKeywordsNullArgument() {
        try {
            new DefaultCollector().getKeywords(null);
            Assertions.fail(
                    "DefaultCollector.getKeywords() should throw a NullPointerException if the res argument is null!");
        } catch (NullPointerException e) {
        }
    }

    @Test
    public void testDefaultCollectorGetKeywords() {
        coll = new DefaultCollector();
        Set<String> keywords = coll.getKeywords(new Resource("name", "path", new ResourceType("desc", coll)));
        Assertions.assertTrue(keywords.size() == 1 && keywords.contains("name"),
                "DefaultCollector.getKeywords() should return a set containing only the name of the specified resource!");

        keywords = coll.getKeywords(new Resource("name 2", "path", new ResourceType("desc", coll)));
        Assertions.assertTrue(keywords.size() == 1 && keywords.contains("name 2"), "DefaultCollector.getKeywords() should return a set containing only the name of the specified resource" +
                "and should not store the previous ones!");
    }

    @Test
    public void testPlainTextCollectorGetKeywordsNullArgument() {
        try {
            new PlainTextCollector().getKeywords(null);
            Assertions.fail("PlainTextCollector.getKeywords() should throw a NullPointerException if the res argument is null!");
        } catch (NullPointerException e) {
        }
    }

    @Test
    public void testPlainTextCollectorGetKeywords() {
        coll = new PlainTextCollector();
        Set<String> keywords = coll.getKeywords(new Resource("name", "path", new ResourceType("desc", coll)));
        Set<String> expected = new HashSet<String>(
                Arrays.asList("are exam good hope in luck prepared this We well wish you".split(" ")));
        Assertions.assertEquals(expected, keywords,
                "PlainTextCollector.getKeywords() should return a set containing every word within the String given in the task description!");
    }

}
