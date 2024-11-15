import java.lang.reflect.Field;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;

@Disabled
public abstract class JStateBaseTest {
    protected JIdea i;

    public static Class<?> getClassDraft() {
        return searchForInnerClass("Draft");
    }

    public static Class<?> getClassOpenDraft() {
        return searchForInnerClass("OpenDraft");
    }

    public static Class<?> getClassApprovedIdea() {
        return searchForInnerClass("ApprovedIdea");
    }

    public static Class<?> getClassReleasedIdea() {
        return searchForInnerClass("ReleasedIdea");
    }

    public static Class<?> getClassDeclinedIdea() {
        return searchForInnerClass("DeclinedIdea");
    }

    @BeforeEach
    public void setUp() {
        i = new JIdea("title", "description");
    }

    /* Return the Content of the Attribute "JIdea.state" */
    protected Object getState() {
        /* Get attribute state, set the mode to accessible and return the content */
        try {
            Field myField = JIdea.class.getDeclaredField("state");
            myField.setAccessible(true);
            return myField.get(i);
        } catch (NoSuchFieldException e) {
            Assertions.fail("JIdea should have an attribute named state!");
        } catch (IllegalArgumentException | IllegalAccessException e) {
            Assertions.fail("An unexpected error occurred!");
        }
        throw new AssertionError("An unexpected error occurred!");
    }

    private static Class<?> searchForInnerClass(String name) {
        for (Class<?> clazz : JIdea.class.getDeclaredClasses()) {
            if (clazz.getSimpleName().equals(name)) {
                if ("JState".equals(clazz.getSimpleName()) || "JState".equals(clazz.getSuperclass().getSimpleName())) {
                    return clazz;
                }
            }
        }
        Assertions.fail("Class " + name + " not found");
        return null;
    }
}
