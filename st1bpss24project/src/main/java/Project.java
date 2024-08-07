import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Project {
    private String name;
    private String description;
    private Task mainTask;

    public Project(String name, String description, double rate) {
        if (name == null || description == null) throw new NullPointerException();
        if (name.isEmpty() || description.isEmpty() || rate < 0) throw new IllegalArgumentException();
        this.name = name;
        this.description = description;
        this.mainTask = new Task(name, description, rate);
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setTask(Task newTask) {
        if (newTask == null) throw new NullPointerException();
        this.mainTask = newTask;
    }

    public double getDuration() {
        return mainTask.getTimeRequired();
    }

    public long getTotalCost() {
        return mainTask.getCostEstimate();
    }

    public Map<LocalDate, List<Deliverable>> allDeliverables() {
        Map<LocalDate, List<Deliverable>> map = new HashMap<>();

        for (Deliverable item : mainTask.allDeliverables()) {
            if (map.containsKey(item.getDate())) {
                map.get(item.getDate()).add(item);
            } else {
                List<Deliverable> list = new ArrayList<>();
                list.add(item);
                map.put(item.getDate(), list);
            }
        }

        return map;
    }
}