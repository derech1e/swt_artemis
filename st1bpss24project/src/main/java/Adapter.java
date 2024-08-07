import java.util.ArrayList;
import java.util.List;

public class Adapter implements IProject {

    private String name;
    private String description;
    private double rate;
    private Project project;

    public Adapter(String name, String description, double rate) {
        if (name == null || description == null) throw new NullPointerException();
        if (name.isEmpty() || description.isEmpty() || rate < 0) throw new IllegalArgumentException();
        this.name = name;
        this.description = description;
        this.rate = rate;
        project = new Project(name, description, rate); // Also possible with Task implementation
    }


    @Override
    public void setTask(Task newTask) {
        if (newTask == null) throw new NullPointerException();
        project.setTask(newTask);
    }

    @Override
    public double getDuration() {
        return project.getDuration();
    }

    @Override
    public long getTotalCost() {
        return project.getTotalCost();
    }

    @Override
    public List<Deliverable> getDeliverables() {
        List<Deliverable> deliverables = new ArrayList<Deliverable>();
        for (List<Deliverable> item : project.allDeliverables().values()) {
            deliverables.addAll(item);
        }
        return deliverables;
    }
}