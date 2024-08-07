import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Task extends ProjectItem {

    private Set<ProjectItem> projectItems = new HashSet<>();

    public Task(String name, String details, double rate) {
        super(name, details, rate);
    }

    @Override
    public double getTimeRequired() {
        double result = 0D;
        for (ProjectItem projectItem : projectItems) {
            result += projectItem.getTimeRequired();
        }
        return result;
    }

    @Override
    public long getMaterialCost() {
        long result = 0L;
        for (ProjectItem projectItem : projectItems) {
            result += projectItem.getMaterialCost();
        }
        return result;
    }

    public void addProjectItem(ProjectItem pi) {
        if (pi == null) throw new NullPointerException();
        projectItems.add(pi);
    }

    public void removeProjectItem(ProjectItem pi) {
        if (pi == null) throw new NullPointerException();
        projectItems.remove(pi);
    }

    public List<Deliverable> allDeliverables() {
        List<Deliverable> deliverables = new ArrayList<>();
        for (ProjectItem item : projectItems) {
            if (item instanceof Deliverable) {
                deliverables.add((Deliverable) item);
                continue;
            }
            Task task = (Task) item;
            List<Deliverable> deliverableList = task.allDeliverables();
            deliverables.addAll(deliverableList);
        }
        return deliverables;
    }
}