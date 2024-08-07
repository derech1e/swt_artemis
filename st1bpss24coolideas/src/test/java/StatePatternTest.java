import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

 
public class StatePatternTest extends JStateBaseTest {
    private String methodDiscuss = "discuss";
    private String methodEvaluate = "evaluate";
    private String methodHold = "hold";
    private String methodRelease = "release";
    private String methodDecline = "decline";

    private List<String> expectedStateClassNames = Arrays.asList(
            "DeclinedIdea",
            "ReleasedIdea",
            "ApprovedIdea",
            "OpenDraft",
            "Draft",
            "JState"
    );

    @Test
    public void testClassesAreSubClassesOfJState() {
        List<Class<?>> expectedSubclasses = new ArrayList<>();
        for (Class<?> actualClass : JIdea.class.getDeclaredClasses()) {
            if (!actualClass.getSimpleName().equals("JState") &&
                    expectedStateClassNames.contains(actualClass.getSimpleName())) {
                expectedSubclasses.add(actualClass);
            }
        }

        for (Class<?> expectedSubclass : expectedSubclasses) {
            Assertions.assertEquals("JState", expectedSubclass.getSuperclass().getSimpleName(), expectedSubclass.getSimpleName() + " must have the superclass JState");
        }
    }

    @Test
    public void testStateClassesExist() {
        List<String> actualStateClassNames = new ArrayList<>();
        for (Class<?> actualClass : JIdea.class.getDeclaredClasses()) {
            actualStateClassNames.add(actualClass.getSimpleName());
        }

        for (String stateClassName : expectedStateClassNames) {
            Assertions.assertTrue(actualStateClassNames.contains(stateClassName), "JIdea should have an inner class named " + stateClassName + "!");
        }
    }

    @Test
    public void testJIdeaClasses() {
        Assertions.assertTrue(searchForMethod("JState", methodDiscuss, String.class), "JState should have a method named " + methodDiscuss + "!");
        Assertions.assertTrue(searchForMethod("JState", methodEvaluate, JValuation.class), "JState should have a method named " + methodEvaluate + "!");
        Assertions.assertTrue(searchForMethod("JState", methodHold), "JState should have a method named " + methodHold + "!");
        Assertions.assertTrue(searchForMethod("JState", methodRelease), "JState should have a method named " + methodRelease + "!");
        Assertions.assertTrue(searchForMethod("JState", methodDecline), "JState should have a method named " + methodDecline + "!");
    }

    private boolean searchForMethod(String innerClassName, String methodName, Class<?>... methodArguments) {
        for (Class<?> c : JIdea.class.getDeclaredClasses()) {
            if (c.getSimpleName().equals(innerClassName)) {
                try {
                    c.getMethod(methodName, methodArguments);
                    return true;
                } catch (NoSuchMethodException ex) {
                    return false;
                }
            }
        }
        return false;
    }
}
