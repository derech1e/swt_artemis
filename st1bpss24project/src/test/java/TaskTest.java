import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

  
public class TaskTest {
    private Task mainTask, t1, t2, t3, emptyTask;
    private Deliverable d1, d2, d3;

    @BeforeEach
    public void setUp() {
        emptyTask = new Task("Empty", "Empty", 1);
        mainTask = new Task("MainTask", "MainDetails", 1.23456);

        t1 = new Task("Subtask1", "Subdetails1", 2.34567);
        t2 = new Task("Subtask2", "Subdetails2", 3.0);
        t3 = new Task("Subtask3", "Subdetails3", 1.0);

        d1 = new Deliverable("Deliverable1", "Deliverable1", 1.25, 1999, 2.33, LocalDate.now());
        d2 = new Deliverable("Deliverable2", "Deliverable2", 1.0, 401, 1.0, LocalDate.now());
        d3 = new Deliverable("Deliverable3", "Deliverable3", 1.0, 2, 5.5, LocalDate.now());

        t1.addProjectItem(d1);
        t1.addProjectItem(d2);

        t2.addProjectItem(d2);
        t2.addProjectItem(d3);

        t3.addProjectItem(d3);
        t3.addProjectItem(d1);
        t3.addProjectItem(t2);

        mainTask.addProjectItem(t1);
        mainTask.addProjectItem(t2);
        mainTask.addProjectItem(t3);
        mainTask.addProjectItem(d1);
    }

    @Test
    public void taskTestConstructorNullArgument() {
        try {
            new Task(null, "details", 1.0);
            fail("Task.Task() should throw a NullPointerException if the name argument is null!");
        } catch (NullPointerException ignored) {
        }
        try {
            new Task("name", null, 1.0);
            fail("Task.Task() should throw a NullPointerException if the details argument is null!");
        } catch (NullPointerException ignored) {
        }
    }

    @Test
    public void taskTestConstructorIllegalArgument() {
        try {
            new Task("", "details", 1.0);
            fail("Task.Task() should throw an IllegalArgumentException if the name argument is empty!");
        } catch (IllegalArgumentException ignored) {
        }
        try {
            new Task("name", "", 1.0);
            fail("Task.Task() should throw an IllegalArgumentException if the details argument is empty!");
        } catch (IllegalArgumentException ignored) {
        }
        try {
            new Task("name", "details", -Double.MIN_VALUE);
            fail("Task.Task() should throw an IllegalArgumentException if the rate argument is negative!");
        } catch (IllegalArgumentException ignored) {
        }
    }

    @Test
    public void taskTestEmptyTask() {
        assertEquals(0, emptyTask.getCostEstimate(),
                "Task.getCostEstimate() should return 0 if no ProjectItem has been added!");
        assertEquals(0, emptyTask.getMaterialCost(),
                "Task.getMaterialCost() should return 0 if no ProjectItem has been added!");
        assertEquals(0, emptyTask.getTimeRequired(), 0.0001,
                "Task.getTimeRequired() should return 0 if no ProjectItem has been added!");
        assertTrue(emptyTask
                .allDeliverables().isEmpty(), "Task.allDeliverables() should return an empty list if no ProjectItem has been added!");
    }

    @Test
    public void taskTestGetCostEstimate() {
        assertEquals(2408, t1.getCostEstimate(),
                "Task.getCostEstimate() should return the costs of all Deliverables!");
        assertEquals(423, t2.getCostEstimate(),
                "Task.getCostEstimate() should return the costs of all Deliverables!");
        assertEquals(2418, t3.getCostEstimate(),
                "Task.getCostEstimate() should return the costs of all Deliverables and subtasks!");
        assertEquals(7239, mainTask.getCostEstimate(), "Task.getCostEstimate() should return the " +
                "correct value!");
    }

    @Test
    public void taskTestGetMaterialCost() {
        assertEquals(2400, t1.getMaterialCost(),
                "Task.getMaterialCost() should return the costs of all Deliverables!");
        assertEquals(403, t2.getMaterialCost(),
                "Task.getMaterialCost() should return the costs of all Deliverables!");
        assertEquals(2404, t3.getMaterialCost(), "Task.getMaterialCost() should return the costs of all Deliverables and subtasks!");
        assertEquals(7206, mainTask.getMaterialCost(), "Task.getMaterialCost() should return the correct value!");
    }

    @Test
    public void taskTestGetTimeRequired() {
        assertEquals(3.33, t1.getTimeRequired(), 0,
                "Task.getTimeRequired() should return the time of all Deliverables!");
        assertEquals(6.5, t2.getTimeRequired(), 0.0001,
                "Task.getTimeRequired() should return the time of all Deliverables!");
        assertEquals(14.33, t3.getTimeRequired(), 0.0001, "Task.getTimeRequired() should return the time of all Deliverables and subtasks!");
        assertEquals(26.49, mainTask.getTimeRequired(), 0.0001, "Task.getTimeRequired() should return the correct value!");
    }

    @Test
    public void taskTestAddProjectItemNullArgument() {
        try {
            mainTask.addProjectItem(null);
            fail("Task.addProjectItem() should throw a NullPointerException if the argument is null!");
        } catch (NullPointerException ignored) {
        }
    }

    @Test
    public void taskTestAddProjectItem() {
        emptyTask.addProjectItem(d1);
        assertThat(emptyTask.allDeliverables()) //
                .as("Task.addProjectItem() should actually add the ProjectItem!") //
                .containsExactly(d1);
        emptyTask.addProjectItem(t3);
        assertThat(emptyTask.allDeliverables()) //
                .as("Task.addProjectItem() should actually add the ProjectItem!") //
                .containsExactlyInAnyOrder(d1, d1, d2, d3, d3);
    }

    @Test
    public void taskTestRemoveProjectItemNullArgument() {
        try {
            mainTask.removeProjectItem(null);
            fail("Task.removeProjectItem() should throw a NullPointerException if the argument is null!");
        } catch (NullPointerException ignored) {
        }
    }

    @Test
    public void taskTestRemoveProjectItem() {
        t1.removeProjectItem(d1);
        assertFalse(t1.allDeliverables().contains(d1), "Task.removeProjectItem() should actually remove the item!");
    }

    @Test
    public void taskTestAllDeliverables() {
        var message = "Task.allDeliverables() should return a list containing all Deliverables";
        assertThat(t1.allDeliverables()) //
                .as(message).containsExactlyInAnyOrder(d1, d2);
        assertThat(t2.allDeliverables()) //
                .as(message).containsExactlyInAnyOrder(d2, d3);
        assertThat(t3.allDeliverables()) //
                .as(message).containsExactlyInAnyOrder(d1, d2, d3, d3);
        assertThat(mainTask.allDeliverables()) //
                .as(message).containsExactlyInAnyOrder(d1, d1, d1, d2, d2, d2, d3, d3, d3);
    }
}
