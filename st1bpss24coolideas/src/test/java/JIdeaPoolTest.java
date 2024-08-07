import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

 
public class JIdeaPoolTest {
    private JIdeaPool ideaPool;
    private final JIdea i1 = new JIdea("titleIdea1", "descriptionIdea1");
    private final JIdea i2 = new JIdea("titleIdea2", "descriptionIdea2");
    private final JIdea i3 = new JIdea("titleIdea3", "descriptionIdea3");
    private final JTopic t1 = new JTopic("titleTopic1", "descriptionTopic1", 1);
    private final JTopic t2 = new JTopic("titleTopic2", "descriptionTopic2", 2);
    private final JTopic t3 = new JTopic("titleTopic3", "descriptionTopic3", 3);

    @BeforeEach
    public void setUp() {
        ideaPool = new JIdeaPool();
    }

    @Test
    public void testAddNullArgument() {
        try {
            ideaPool.add(null);
            Assertions.fail("JIdeaPool.add(JTopic) should throw a NullPointerException if the argument is null!");
        } catch (NullPointerException e) {
        }

        try {
            ideaPool.add(null, t1);
            Assertions.fail(
                    "JIdeaPool.add(JIdea, JTopic) should throw a NullPointerException if the idea argument is null!");
        } catch (NullPointerException e) {
        }

        try {
            ideaPool.add(i1, null);
            Assertions.fail("JIdeaPool.add(JIdea, JTopic) should throw a NullPointerException if the topic argument is null!");
        } catch (NullPointerException e) {
        }

        try {
            ideaPool.add(null, null);
            Assertions.fail("JIdeaPool.add(JIdea, JTopic) should throw a NullPointerException if the both arguments are null!");
        } catch (NullPointerException e) {
        }
    }

    @Test
    public void testAddJTopic() {
        ideaPool.add(t1);
        Map<JTopic, Set<JIdea>> actPool = getPool();
        Assertions.assertTrue(actPool.get(t1).isEmpty(),
                "JIdeaPool.add(JTopic) should map a topic not contained in the pool to an empty Set!");
        ideaPool.add(i1, t1);
        ideaPool.add(t1);
        Assertions.assertTrue(actPool.get(t1)
                .contains(i1), "JIdeaPool.add(JTopic) should not remove any existing JIdea mappings in the pool!");
    }

    @Test
    public void testAddJIdeaJTopic() {
        Map<JTopic, Set<JIdea>> actPool = getPool();

        // Another JIdea object with a title equal to i1's title.
        // Only one of them can be referenced in the pool at the same time.
        JIdea ideaWithEqualTitle = new JIdea(i1.getTitle(), "idea titles are equal");

        ideaPool.add(i1, t1);
        Assertions.assertTrue(actPool.containsKey(t1), "JIdeaPool.add(JIdea, JTopic) should add the topic if the pool" +
                " did not contain it!");
        Assertions.assertTrue(actPool
                .get(t1).size() == 1 && actPool.get(t1).contains(i1), "JIdeaPool.add(JIdea, JTopic) should add the idea to the set of ideas of the given topic!");

        ideaPool.add(ideaWithEqualTitle, t1);
        List<JIdea> ideasInTopic = List.copyOf(actPool.get(t1));
        if (ideasInTopic.size() != 1 || ideasInTopic.get(0) != i1) {
            Assertions.fail(
                    "JIdeaPool.add(JIdea, JTopic) should not add an idea if the pool already contains another unidentical"
                            + " idea object with an equal title!");
        }

        ideaPool.add(ideaWithEqualTitle, t2);
        Assertions.assertFalse(actPool.containsKey(t2), "JIdeaPool.add(JIdea, JTopic) should not add the topic to the" +
                " pool if the idea is not added!");

        ideaPool.add(i3, t1);
        Assertions.assertTrue(actPool
                .get(t1).size() == 2 && actPool.get(t1).contains(i3), "JIdeaPool.add(JIdea, JTopic) should add the idea to the set of ideas of the given topic!");

        ideaPool.add(i1, t2);
        Assertions.assertTrue(actPool.containsKey(t2)
        && actPool.get(t2).size() == 1 && actPool.get(t2).contains(i1),
                "JIdeaPool.add(JIdea, JTopic) should add the idea to the set of ideas of the given topic even if"
                        + " an identical idea is already associated to another topic!");
    }

    @Test
    public void testRemoveNullArgument() {
        try {
            ideaPool.remove((JTopic) null);
            Assertions.fail("JIdeaPool.remove(JTopic) should throw a NullPointerException if the argument is null!");
        } catch (NullPointerException e) {
        }

        try {
            ideaPool.remove((JIdea) null);
            Assertions.fail("JIdeaPool.remove(JIdea) should throw a NullPointerException if the argument is null!");
        } catch (NullPointerException e) {
        }
    }

    @Test
    public void testRemoveJTopic() {
        ideaPool.add(t1);
        ideaPool.add(t3);
        Assertions.assertTrue(ideaPool.remove(t1), "JIdeaPool.remove(JTopic) should return true if the given topic exists in the pool and was removed!");
        Assertions.assertTrue(ideaPool.remove(t3), "JIdeaPool.remove(JTopic) should return true if the given topic exists in the pool and was removed!");
        Assertions.assertFalse(ideaPool.remove(t1), "JIdeaPool.remove(JTopic) should return false if the given topic does not exist in the pool!");
        Assertions.assertFalse(ideaPool.remove(t2), "JIdeaPool.remove(JTopic) should return false if the given topic does not exist in the pool!");
        Assertions.assertFalse(ideaPool.remove(t3), "JIdeaPool.remove(JTopic) should return false if the given topic does not exist in the pool!");
    }

    @Test
    public void testRemoveJIdea() {
        ideaPool.add(i1, t1);
        ideaPool.add(i1, t2);
        ideaPool.add(i3, t2);
        Assertions.assertTrue(ideaPool.remove(i1),
                "JIdeaPool.remove(JIdea) should return true if the given idea exists in the pool and was removed!");
        Assertions.assertNull(ideaPool.getIdea(i1.getTitle()), "JIdeaPool.remove(JIdea) should remove the idea from every topic!");
        Assertions.assertTrue(ideaPool.remove(i3),
                "JIdeaPool.remove(JIdea) should return true if the given idea exists in the pool and was removed!");
        Assertions.assertFalse(ideaPool.remove(i1),
                "JIdeaPool.remove(JIdea) should return false if the given idea does not exist in the pool!");
        Assertions.assertFalse(ideaPool.remove(i2), "JIdeaPool.remove(JIdea) should return false if the given idea does not exist in the pool!");
        Assertions.assertFalse(ideaPool.remove(i3), "JIdeaPool.remove(JIdea) should return false if the given idea does not exist in the pool!");
    }

    @Test
    public void testRemoveDeclined() {
        ideaPool.add(i1, t1);
        ideaPool.add(i2, t1);
        ideaPool.add(i3, t2);
        ideaPool.add(i1, t2);

        i1.decline();
        ideaPool.removeDeclined();
        Assertions.assertNull(ideaPool.getIdea(i1.getTitle()), "JIdeaPool.removeDeclined() should remove all declined ideas!");
        i2.decline();
        i3.decline();
        ideaPool.removeDeclined();
        Assertions.assertNull(ideaPool.getIdea(i2.getTitle()), "JIdeaPool.removeDeclined() should remove all declined ideas!");
        Assertions.assertNull(ideaPool.getIdea(i3.getTitle()), "JIdeaPool.removeDeclined() should remove all declined ideas!");
    }

    @Test
    public void testRemoveReleased() {
        ideaPool.add(i1, t1);
        ideaPool.add(i2, t1);
        ideaPool.add(i3, t2);
        ideaPool.add(i1, t2);

        i1.hold();
        i1.hold();
        i1.release();
        ideaPool.removeReleased();
        Assertions.assertNull(ideaPool.getIdea(i1.getTitle()), "JIdeaPool.removeDeclined() should remove all declined" +
                " ideas!");
        i2.hold();
        i2.hold();
        i2.release();
        i3.hold();
        i3.hold();
        i3.release();
        ideaPool.removeReleased();
        Assertions.assertNull(ideaPool.getIdea(i2.getTitle()), "JIdeaPool.removeDeclined() should remove all declined ideas!");
        Assertions.assertNull(ideaPool.getIdea(i3.getTitle()), "JIdeaPool.removeDeclined() should remove all declined ideas!");
    }

    @Test
    public void testGetIdeaIllegalArgument() {
        try {
            ideaPool.getIdea(null);
            Assertions.fail("JIdeaPool.getIdea() should throw a NullPointerException if the argument is null!");
        } catch (NullPointerException e) {
        }

        try {
            ideaPool.getIdea("");
            Assertions.fail("JIdeaPool.getIdea() should throw an IllegalArgumentException if the argument is empty!");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void testGetIdea() {
        ideaPool.add(i1, t1);
        ideaPool.add(i1, t2);
        ideaPool.add(i3, t2);

        Assertions.assertEquals(i1, ideaPool.getIdea("titleIdea1"),
                "JIdeaPool.getIdea() should return the idea with the given title!");
        Assertions.assertEquals(i3, ideaPool.getIdea("titleIdea3"), "JIdeaPool.getIdea() should return the idea with the given title!");
        Assertions.assertNull(ideaPool.getIdea("notInThePool"),
                "JIdeaPool.getIdea() should return null if the pool does not contain any idea with the given title!");
    }

    @Test
    public void testNumberOfTopics() {
        ideaPool.add(t1);
        Assertions.assertEquals(1, ideaPool.numberOfTopics(), "JIdeaPool.numberOfTopics() should return the right " +
                "number of topics in the pool!");
        ideaPool.add(t2);
        Assertions.assertEquals(2, ideaPool.numberOfTopics(),
                "JIdeaPool.numberOfTopics() should return the right number of topics in the pool!");
        ideaPool.add(t3);
        Assertions.assertEquals(3, ideaPool.numberOfTopics(),
                "JIdeaPool.numberOfTopics() should return the right number of topics in the pool!");
    }

    @Test
    public void testNumberOfIdeas() {
        ideaPool.add(i1, t1);
        Assertions.assertEquals(1, ideaPool.numberOfIdeas(), "JIdeaPool.numberOfIdeas() should return the right number of ideas in the pool!");
        ideaPool.add(i2, t1);
        Assertions.assertEquals(2, ideaPool.numberOfIdeas(), "JIdeaPool.numberOfIdeas() should return the right number of ideas in the pool!");
        ideaPool.add(i2, t2);
        Assertions.assertEquals(2, ideaPool.numberOfIdeas(), "JIdeaPool.numberOfIdeas() should count each individual idea only once!");
        ideaPool.add(i3, t2);
        Assertions.assertEquals(3, ideaPool.numberOfIdeas(), "JIdeaPool.numberOfIdeas() should return the right number of ideas in the pool!");
    }

    @SuppressWarnings("unchecked")
    private Map<JTopic, Set<JIdea>> getPool() {
        // Get attribute pool, set the mode to accessible and return the content
        try {
            Field myField = ideaPool.getClass().getDeclaredField("pool");
            myField.setAccessible(true);
            Object pool = myField.get(ideaPool);
            if (!(pool instanceof Map<?, ?>)) {
                Assertions.fail("JIdeaPool.pool should be a Map<JTopic, Set<JIdea>>!");
            }
            return (Map<JTopic, Set<JIdea>>) pool;
        } catch (NoSuchFieldException e) {
            throw new AssertionError("JIdeaPool should have an attribute named pool!");
        } catch (IllegalArgumentException e) {
            Assertions.fail("An unexpected error occurred!");
        } catch (IllegalAccessException e) {
            Assertions.fail("An unexpected error occurred!");
        }
        throw new RuntimeException("An unexpected error occurred!");
    }
}
