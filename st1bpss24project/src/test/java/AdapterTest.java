import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

  
public class AdapterTest {
    private Adapter adapter;
    private List<Deliverable> expDeliverables;

    @BeforeEach
    public void setUp() {
        Task t1, t2;
        Deliverable d1, d2, d3, d4;

        d1 = new Deliverable("checkTask", "Request task-list", 1.0, 10, 30.0, LocalDate.of(2008, 2, 10));
        d2 = new Deliverable("checkAvailability", "Check availability of goods", 1.0, 0, 100.0, LocalDate.of(
                2008, 2, 20));
        d3 = new Deliverable("checkAccount", "Check customers account", 1.0, 30, 30.0, LocalDate.of(2008, 3,
                2));
        d4 = new Deliverable("confirmOrder", "Deliver goods", 2.0, 1000, 100.0, LocalDate.of(2008, 3, 23));

        t2 = new Task("confirmTask", "Check order", 1.0);
        t2.addProjectItem(d2);
        t2.addProjectItem(d3);

        t1 = new Task("shipOrdering", "Ship goods", 1.0);
        t1.addProjectItem(d1);
        t1.addProjectItem(t2);
        t1.addProjectItem(d4);

        adapter = new Adapter("Test adapter", "This adapter is created to adapterTest the Adapter class.", 3.0);
        adapter.setTask(t1);

        expDeliverables = new ArrayList<Deliverable>();
        expDeliverables.add(d1);
        expDeliverables.add(d2);
        expDeliverables.add(d3);
        expDeliverables.add(d4);
    }

    @Test
    public void adapterTestImplementsIProject() {
        if (!IProject.class.isAssignableFrom(Adapter.class)) {
            fail("Adapter should implement the interface IProject!");
        }
    }

    @Test
    public void adapterTestConstructorNullArgument() {
        try {
            new Adapter(null, "details", 5.0);
            fail("Adapter.Adapter() should throw a NullPointerException if the name argument is null!");
        } catch (NullPointerException ignored) {
        }

        try {
            new Adapter("name", null, 5.0);
            fail("Adapter.Adapter() should throw a NullPointerException if the details argument is null!");
        } catch (NullPointerException ignored) {
        }
    }

    @Test
    public void adapterTestConstructorIllegalArgument() {
        try {
            new Adapter("", "details", 5.0);
            fail("Adapter.Adapter() should throw an IllegalArgumentException if the name argument is empty!");
        } catch (IllegalArgumentException ignored) {
        }

        try {
            new Adapter("name", "", 5.0);
            fail("Adapter.Adapter() should throw an IllegalArgumentException if the details argument is empty!");
        } catch (IllegalArgumentException ignored) {
        }

        try {
            new Adapter("name", "details", -Double.MIN_VALUE);
            fail("Adapter.Adapter() should throw an IllegalArgumentException if the rate argument is negative!");
        } catch (IllegalArgumentException ignored) {
        }
    }

    @Test
    public void adapterTestSetTaskNullArgument() {
        try {
            adapter.setTask(null);
            fail("Adapter.setTask() should throw a NullPointerException if the argument is null!");
        } catch (NullPointerException ignored) {
        }
    }

    @Test
    public void adapterTestGetDuration() {
        assertEquals(260.0, adapter.getDuration(), 0.0001, "Adapter.getDuration() should return the correct value!");
    }

    @Test
    public void adapterTestGetTotalCost() {
        assertEquals(1300, adapter.getTotalCost(), "Adapter.getTotalCosts() should return the correct value!");
    }

    @Test
    public void adapterTestAllDeliverables() {
        List<Deliverable> actual = adapter.getDeliverables();
        assertThat(actual) //
                .as("Adapter.allDeliverables() should return all Deliverables!") //
                .containsExactlyInAnyOrderElementsOf(expDeliverables);
    }
}
