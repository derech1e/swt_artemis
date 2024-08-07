import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

  
public class DeliverableTest {
    private Deliverable deliverable;
    private LocalDate date;

    @BeforeEach
    public void setUp() {
        date = LocalDate.now();
        deliverable = new Deliverable("Deliverable1", "Details1", 2.2, 55, 2.5, date);
    }

    @Test
    public void deliverableTestConstructorNullArgument() {
        try {
            new Deliverable(null, "details", 1.5, 1, 1.0, LocalDate.now());
            fail(
                    "Deliverable.Deliverable() should throw a NullPointerException if the name argument is null!");
        } catch (NullPointerException ignored) {
        }
        try {
            new Deliverable("name", null, 1.5, 1, 1.0, LocalDate.now());
            fail("Deliverable.Deliverable() should throw a NullPointerException if the details argument is null!");
        } catch (NullPointerException ignored) {
        }
        try {
            new Deliverable("name", "details", 1.5, 1, 1.0, null);
            fail("Deliverable.Deliverable() should throw a NullPointerException if the date argument is null!");
        } catch (NullPointerException ignored) {
        }
    }

    @Test
    public void deliverableTestConstructorIllegalArgument() {
        try {
            new Deliverable("", "details", 1.5, 1, 1.0, LocalDate.now());
            fail("Deliverable.Deliverable() should throw an IllegalArgumentException if the name argument is empty!");
        } catch (IllegalArgumentException ignored) {
        }
        try {
            new Deliverable("name", "", 1.5, 1, 1.0, LocalDate.now());
            fail("Deliverable.Deliverable() should throw an IllegalArgumentException if the description argument is empty!");
        } catch (IllegalArgumentException ignored) {
        }
        try {
            new Deliverable("name", "details", -1.5, 1, 1.0, LocalDate.now());
            fail("Deliverable.Deliverable() should throw an IllegalArgumentException if the rate argument is negative!");
        } catch (IllegalArgumentException ignored) {
        }
        try {
            new Deliverable("name", "details", 1.5, -1, 1.0, LocalDate.now());
            fail("Deliverable.Deliverable() should throw an IllegalArgumentException if the materialCost argument is negative!");
        } catch (IllegalArgumentException ignored) {
        }
        try {
            new Deliverable("name", "details", 1.5, 1, -Double.MIN_VALUE, LocalDate.now());
            fail("Deliverable.Deliverable() should throw an IllegalArgumentException if the productionTime argument is negative!");
        } catch (IllegalArgumentException ignored) {
        }
        try {
            new Deliverable("name", "details", 1.5, 1, 0.0, LocalDate.now());
            fail("Deliverable.Deliverable() should throw an IllegalArgumentException if the productionTime argument is zero!");
        } catch (IllegalArgumentException ignored) {
        }
    }

    @Test
    public void deliverableTestGetCostEstimate() {
        assertEquals(61, deliverable.getCostEstimate(), 0.0001, "Deliverable.getCostEstimate() should return the correct value!");
    }

    @Test
    public void deliverableTestGetMaterialCost() {
        assertEquals(55, deliverable.getMaterialCost(), "Deliverable.getMaterialCost() should return the correct value!");
    }

    @Test
    public void deliverableTestGetTimeRequired() {
        assertEquals(2.5, deliverable.getTimeRequired(), 0.0001, "Deliverable.getTimeRequired() should return the correct value!");
    }

    @Test
    public void deliverableTestGetDate() {
        assertEquals(date, deliverable.getDate(), "Deliverable.getDate() should return the correct object!");
    }
}
