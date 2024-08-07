import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

  
public class ProjectTest {
    private Project project;
    private Task t1;
    private Map<LocalDate, List<Deliverable>> expDeliverablesTask1;
    private Map<LocalDate, List<Deliverable>> expDeliverablesTask2;

    @BeforeEach
    public void setUp() {
        expDeliverablesTask1 = new HashMap<>();
        expDeliverablesTask2 = new HashMap<>();

        var date1 = LocalDate.of(2008, 2, 10);
        var d1 = new Deliverable("checkTask", "Request instruction list", 1.0, 10, 30.0, date1);
        var d2 = new Deliverable("checkAvailability", "Check availability of the goods", 1.0, 100, 5.0, date1);
        expDeliverablesTask1.put(date1, List.of(d2));
        expDeliverablesTask2.put(date1, List.of(d1, d2));

        var date2 = LocalDate.of(2008, 3, 2);
        var d3 = new Deliverable("checkAccount", "Check customers account", 1.0, 30, 30.0, date2);
        expDeliverablesTask1.put(date2, List.of(d3));
        expDeliverablesTask2.put(date2, List.of(d3));

        var date3 = LocalDate.of(2008, 3, 23);
        var d4 = new Deliverable("confirmOrder", "Deliver items", 2.0, 100, 1000.0, date3);
        expDeliverablesTask2.put(date3, List.of(d4));

        t1 = new Task("confirmTask", "Check order", 1);
        t1.addProjectItem(d2);
        t1.addProjectItem(d3);

        var t2 = new Task("shipOrdering", "Ship items", 1);
        t2.addProjectItem(d1);
        t2.addProjectItem(t1);
        t2.addProjectItem(d4);

        project = new Project("shipOrdering", "Ship items", 1);
        project.setTask(t2);
    }

    @Test
    public void projectTestConstructorNullArgument() {
        try {
            new Project(null, "description", 1.0);
            fail("Project.Project() should throw a NullPointerException if the name argument is null!");
        } catch (NullPointerException ignored) {
        }

        try {
            new Project("name", null, 1.0);
            fail("Project.Project() should throw a NullPointerException if the description argument is null!");
        } catch (NullPointerException ignored) {
        }
    }

    @Test
    public void projectTestConstructorIllegalArgument() {
        try {
            new Project("", "description", 1.0);
            fail("Project.Project() should throw an IllegalArgumentException if the name argument is null!");
        } catch (IllegalArgumentException ignored) {
        }

        try {
            new Project("name", "", 1.0);
            fail(
                    "Project.Project() should throw an IllegalArgumentException if the description argument is null!");
        } catch (IllegalArgumentException ignored) {
        }

        try {
            new Project("name", "description", -0.1);
            fail("Project.Project() should throw an IllegalArgumentException if the rate argument is negative!");
        } catch (IllegalArgumentException ignored) {
        }
    }

    @Test
    public void projectTestGetName() {
        assertEquals("shipOrdering", project.getName(), "Project.getName() should return the correct value!");
    }

    @Test
    public void projectTestGetDescription() {
        assertEquals("Ship items", project.getDescription(), "Project.getDescription() should return the correct value!");
    }

    @Test
    public void projectTestSetTaskNullArgument() {
        try {
            project.setTask(null);
            fail("Project.setTask() should throw a NullPointerException if the argument is null!");
        } catch (NullPointerException ignored) {
        }
    }

    @Test
    public void projectTestSetTask() {
        project.setTask(t1);
        assertEquals(35.0, project.getDuration(), 0.0001, "Project.setTask() should set the main task " +
                "correctly!");
        assertEquals(165, project.getTotalCost(), "Project.setTask() should set the main task correctly!");
        assertEquals(expDeliverablesTask1, project.allDeliverables(), "Project.setTask() should set the main task correctly!");
    }

    @Test
    public void projectTestGetDuration() {
        assertEquals(1065.0, project.getDuration(), 0.0001, "Project.getDuration() should return the correct value!");
    }

    @Test
    public void projectTestGetTotalCost() {
        assertEquals(1305, project.getTotalCost(), "Project.getTotalCosts() should return the correct value!");
    }

    @Test
    public void projectTestAllDeliverables() {
        Map<LocalDate, List<Deliverable>> actual = project.allDeliverables();
        assertThat(actual) //
                .as("Project.allDeliverables() should return a map which contains the dates of "
                        + "all Deliverables as keys.") //
                .containsOnlyKeys(expDeliverablesTask2.keySet());
        for (var expectedEntry : expDeliverablesTask2.entrySet()) {
            var actualDeliverables = actual.get(expectedEntry.getKey());
            assertThat(actualDeliverables) //
                    .as("In the map returned by Project.allDeliverables(), the list of Deliverables "
                            + "mapped to key %s is not as expected.",
                            expectedEntry.getKey()) //
                    .containsExactlyInAnyOrderElementsOf(expectedEntry.getValue());
        }
    }
}
