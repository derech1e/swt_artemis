import java.lang.reflect.Modifier;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

 
public class FactoryTest {
    private Factory fac;
    private ReceivingStock rStock;
    private Purchasing purch;

    @BeforeEach
    public void setUp() {
        rStock = new ReceivingStock(3, 10);
        purch = new Purchasing(rStock);
        fac = new Factory(purch, rStock);
    }

    @Test
    public void testConstructorNullArgument() {
        try {
            new Factory(null, rStock);
            Assertions.fail("Factory.Factory() should throw a NullPointerException if the purchasing argument is null!");
        } catch (NullPointerException e) {
        }
        try {
            new Factory(purch, null);
            Assertions.fail(
                    "Factory.Factory() should throw a NullPointerException if the receivingStock argument is null!");
        } catch (NullPointerException e) {
        }
    }

    @Test
    public void testGetPurchasing() {
        Assertions.assertEquals(purch, fac.getPurchasing(), "Factory.getPurchasing() should return the correct object!");
    }

    @Test
    public void testGetReceivingStock() {
        Assertions.assertEquals(rStock, fac.getReceivingStock(), "Factory.getReceivingStock() should return the correct object!");
    }

    @Test
    public void testCreatePartStatic() throws NoSuchMethodException {
        Assertions.assertTrue(Modifier.isStatic(Factory.class.getMethod("createPart",
                PartType.class, String.class, String.class).getModifiers()), "Factory.createPart() should be static!");
    }

    @Test
    public void testCreatePartNullArgument() {
        try {
            Factory.createPart(null, "id", "name");
            Assertions.fail("Factory.createPart() should throw an NullPointerException if the partType argument is null!");
        } catch (NullPointerException e) {
        }

        try {
            Factory.createPart(PartType.COMPONENTS, null, "name");
            Assertions.fail("Factory.createPart() should throw a NullPointerException if the id argument is null!");
        } catch (NullPointerException e) {
        }

        try {
            Factory.createPart(PartType.COMPONENTS, "id", null);
            Assertions.fail("Factory.createPart() should throw a NullPointerException if the name argument is null!");
        } catch (NullPointerException e) {
        }
    }

    @Test
    public void testCreatePartIllegalArgument() {
        try {
            Factory.createPart(PartType.COMPONENTS, "", "name");
            Assertions.fail("Factory.createPart() should throw an IllegalArgumentException if the id argument is empty!");
        } catch (IllegalArgumentException e) {
        }

        try {
            Factory.createPart(PartType.COMPONENTS, "id", "");
            Assertions.fail("Factory.createPart() should throw an IllegalArgumentException if the name argument is empty!");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void testCreatePart() {
        Part p = Factory.createPart(PartType.COMPONENTS, "0", "some components");
        Assertions.assertSame(Components.class, p.getClass(),
                "Factory.createPart() should return the kind of object specified by the partType argument!");
        Assertions.assertEquals("0", p.getId(), "Factory.createPart() should return a Part with the specified id!");
        Assertions.assertEquals("some components", p.getName(), "Factory.createPart() should return a Part with the specified name!");

        p = Factory.createPart(PartType.SINGLE_COMPONENT, "1", "a single component");
        Assertions.assertSame(SingleComponent.class, p.getClass(),
                "Factory.createPart() should return the kind of object specified by the partType argument!");
        Assertions.assertEquals("1", p.getId(), "Factory.createPart() should return a Part with the specified id!");
        Assertions.assertEquals("a single component", p.getName(), "Factory.createPart() should return a Part with the specified name!");

        p = Factory.createPart(PartType.RESOURCE, "2", "a resource");
        Assertions.assertSame(Resource.class, p.getClass(),
                "Factory.createPart() should return the kind of object specified by the partType argument!");
        Assertions.assertEquals("2", p.getId(), "Factory.createPart() should return a Part with the specified id!");
        Assertions.assertEquals("a resource", p.getName(), "Factory.createPart() should return a Part with the specified name!");
    }
}
