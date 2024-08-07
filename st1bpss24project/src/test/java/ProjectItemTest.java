import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.Modifier;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

  
public class ProjectItemTest {
    private static class ProjectItemImpl extends ProjectItem {
        public ProjectItemImpl(String name, String details, double rate) {
            super(name, details, rate);
        }

        @Override
        public double getTimeRequired() {
            return 2.5;
        }

        @Override
        public long getMaterialCost() {
            return 13;
        }
    }

    private ProjectItemImpl p;

    @Test
    public void projectItemTestAbstract() {
        assertTrue(Modifier.isAbstract(ProjectItem.class.getModifiers()), "ProjectItem should be abstract!");
        try {
            assertTrue(Modifier.isAbstract(ProjectItem.class.getMethod("getTimeRequired").getModifiers()), "ProjectItem.getTimeRequired() should be abstract!");
        } catch (NoSuchMethodException e) {
            fail("ProjectItem should have a method named getTimeRequired without any parameters!");
        }
        try {
            assertTrue(Modifier.isAbstract(ProjectItem.class.getMethod("getMaterialCost").getModifiers()), "ProjectItem.getMaterialCost() should be abstract!");
        } catch (NoSuchMethodException e) {
            fail("ProjectItem should have a method named getMaterialCost without any parameters!");
        }
    }

    @BeforeEach
    public void setUp() {
        p = new ProjectItemImpl("name", "details", 2.25);
    }

    @Test
    public void projectItemTestConstructorNullArgument() {
        try {
            new ProjectItemImpl(null, "details", 1.0);
            fail("ProjectItem.ProjectItem() should throw a NullPointerException if the name argument is null!");
        } catch (NullPointerException e) {
        }
        try {
            new ProjectItemImpl("name", null, 1.0);
            fail(
                    "ProjectItem.ProjectItem() should throw a NullPointerException if the details argument is null!");
        } catch (NullPointerException e) {
        }
    }

    @Test
    public void projectItemTestConstructorIllegalArgument() {
        try {
            new ProjectItemImpl("", "details", 1.0);
            fail("ProjectItem.ProjectItem() should throw an IllegalArgumentException if the name argument is empty!");
        } catch (IllegalArgumentException e) {
        }
        try {
            new ProjectItemImpl("name", "", 1.0);
            fail("ProjectItem.ProjectItem() should throw an IllegalArgumentException if the description argument is empty!");
        } catch (IllegalArgumentException e) {
        }
        try {
            new ProjectItemImpl("name", "details", -Double.MIN_VALUE);
            fail("ProjectItem.ProjectItem() should throw an IllegalArgumentException if the rate argument is negative!");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void projectItemTestCostEstimate() {
        assertEquals(19, p.getCostEstimate(), "ProjectItem.getCostEstimate() should return the correct value!");
    }
}
