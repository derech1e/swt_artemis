import java.util.*;

public class Team extends AbstractEnterpriseUnit {

    private StaffMember teamLeader;

    public Team(String name, StaffMember teamLeader) {
        super(name);
        if(teamLeader == null) throw new NullPointerException();
        this.teamLeader = teamLeader;
    }

    public StaffMember getTeamLeader() {
        return this.teamLeader;
    }

    public SortedSet<StaffMember> getTeamMembers() {
        SortedSet<StaffMember> members = teamLeader.getDirectSubordinates();
        members.add(getTeamLeader());
        return members;
    }

}