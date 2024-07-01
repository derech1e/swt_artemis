import java.lang.Comparable;
import java.util.*;

public class StaffMember implements EnterpriseNode, Comparable<StaffMember> {

    private String name;
    private String job;
    private SortedSet<StaffMember> directSubordinates = new TreeSet<>();

    public StaffMember(String name, String job) {
        if(name == null || job == null) throw new NullPointerException();
        if(name.isEmpty() || job.isEmpty()) throw new IllegalArgumentException();
        this.name = name;
        this.job = job;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public String getJob() {
        return this.job;
    }

    public boolean addDirectSubordinate(StaffMember subordinate) {
        if(subordinate == null) throw new NullPointerException();
        if(directSubordinates.contains(subordinate)) return false;
        directSubordinates.add(subordinate);
        return true;
    }

    public boolean removeDirectSubordinate(StaffMember subordinate) {
        if(subordinate == null) throw new NullPointerException();
        if(!directSubordinates.contains(subordinate)) return false;
        directSubordinates.remove(subordinate);
        return true;
    }

    public SortedSet<StaffMember> getDirectSubordinates() {
        if(directSubordinates.isEmpty()) return new TreeSet<>();

        SortedSet<StaffMember> allSubordinates = new TreeSet<>();
        for (StaffMember subordinate : directSubordinates) {
            allSubordinates.add(subordinate);
            allSubordinates.addAll(subordinate.getDirectSubordinates());
        }
        return allSubordinates;
    }

    @Override
    public String toString() {
        return this.getName();
    }

    @Override
    public int compareTo(StaffMember object) {
        return this.getName().compareTo(object.getName());
    }
}