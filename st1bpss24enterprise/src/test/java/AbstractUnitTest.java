import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

 
public class AbstractUnitTest {
    private static class AbstractUnitImpl extends AbstractUnit {
        public AbstractUnitImpl(String name) {
            super(name);
        }
    }

    private AbstractUnit unit;
    private Set<AbstractUnit> childNodes;
    private AbstractUnit subUnit;

    @BeforeEach
    public void setUp() {
        unit = new AbstractUnitImpl("Abstract Unit");
        subUnit = new AbstractUnitImpl("AU1");
        childNodes = new HashSet<>();
        childNodes.add(subUnit);
        childNodes.add(new AbstractUnitImpl("AU2"));
        childNodes.add(new AbstractUnitImpl("AU3"));
        childNodes.add(new AbstractUnitImpl("AU4"));
        childNodes.add(new AbstractUnitImpl("AU5"));
    }

    @Test
    public void abstractUnitTestAbstract() {
        Assertions.assertTrue(Modifier.isAbstract(AbstractUnit.class.getModifiers()), "AbstractUnit should be abstract!");
    }

    @Test
    public void abstractUnitTestConstructorNullArgument() {
        try {
            new AbstractUnitImpl(null);
            Assertions.fail("AbstractUnit.AbstractUnit() should throw a NullPointerException if the argument is null!");
        } catch (NullPointerException e) {
        }
    }

    @Test
    public void abstractUnitTestConstructorIllegalArgument() {
        try {
            new AbstractUnitImpl("");
            Assertions.fail(
                    "AbstractUnit.AbstractUnit() should throw an IllegalArgumentException if the argument is empty!");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void abstractUnitTestGetName() {
        Assertions.assertEquals("Abstract Unit", unit.getName(), "AbstractUnit.getName() should return the correct value!");
    }

    @Test
    public void abstractUnitTestAddNullArgument() {
        try {
            unit.add(null);
            Assertions.fail("AbstractUnit.add() should throw a NullPointerException if the argument is null!");
        } catch (NullPointerException e) {
        }
    }

    @Test
    public void abstractUnitTestAdd() {
        for (AbstractUnit childNode : childNodes) {
            Assertions.assertTrue(unit.add(childNode), "AbstractUnit.add() should return true if the unit was added!");
            Assertions.assertTrue(unit.getChildNodes().contains(childNode),
                    "AbstractUnit.add() should add the unit if it was not a direct child node previously!");

            int sizeBefore = unit.getChildNodes().size();
            Assertions.assertFalse(unit.add(childNode), "AbstractUnit.add() should return false if the unit was not added!");
            Assertions.assertEquals(sizeBefore, unit.getChildNodes().size(), "AbstractUnit.add() should not add duplicates of the unit!");
        }
    }

    @Test
    public void abstractUnitTestRemoveNullArgument() {
        try {
            unit.remove(null);
            Assertions.fail("AbstractUnit.remove() should throw a NullPointerException if the argument is null!");
        } catch (NullPointerException e) {
        }
    }

    @Test
    public void abstractUnitTestRemove() {
        for (AbstractUnit childNode : childNodes) {
            unit.add(childNode);
        }

        for (AbstractUnit childNode : childNodes) {
            Assertions.assertTrue(unit.remove(childNode), "AbstractUnit.remove() should return true if the unit was a child node!");
            Assertions.assertFalse(unit.getChildNodes().contains(childNode), "AbstractUnit.remove() should remove the" +
                    " unit if it is a child node!");
            Assertions.assertFalse(unit.remove(childNode), "AbstractUnit.remove() should return false if the unit is not a child node!");
        }
    }

    @Test
    public void abstractUnitTestRemoveIndirectChildNode() {
        for (AbstractUnit childNode : childNodes) {
            unit.add(childNode);
        }

        AbstractUnit newUnit = new Holding("H1");
        subUnit.add(newUnit);

        Assertions.assertFalse(unit.remove(newUnit),
                "AbstractUnit.remove() should return false if the unit is not a direct child node!");
        Assertions.assertTrue(subUnit.getChildNodes().contains(newUnit), "AbstractUnit.remove() should not remove an indirect child node!");
    }

    @Test
    public void abstractUnitTestGetChildNodesInitiallyEmpty() {
        Set<AbstractEnterpriseUnit> childNodes = unit.getChildNodes();
        Assertions.assertTrue(childNodes.isEmpty(), "AbstractUnit.getChildNodes() should return an empty set if no child nodes have been added!");
    }
}
