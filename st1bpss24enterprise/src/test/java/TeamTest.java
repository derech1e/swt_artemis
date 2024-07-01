import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

 
public class TeamTest {
    private Team team;
    private StaffMember teamLeader;

    @BeforeEach
    public void setUp() {
        teamLeader = new StaffMember("Thomas", "Executive");
        team = new Team("Team", teamLeader);
    }

    @Test
    public void testGetTeamLeader() {
        Assertions.assertEquals(teamLeader, team.getTeamLeader(),
                "Team.getTeamLeader() should return the correct value!");
    }

    @Test
    public void testGetTeamMembersWithoutTeam() {
        SortedSet<StaffMember> actual = team.getTeamMembers();
        Assertions.assertEquals(Set.of(teamLeader), actual,
                "Team.getTeamMembers() should return a sorted set of all of the Team's members!");
    }

    @Test
    public void testGetTeamMembersWithTeam() {
        SortedSet<StaffMember> expected = createTeamHierarchy(teamLeader);
        SortedSet<StaffMember> actual = team.getTeamMembers();
        Assertions.assertEquals(expected, actual, "Team.getTeamMembers() should return a sorted set of all of the Team's members!");
    }

    public static SortedSet<StaffMember> createTeamHierarchy(StaffMember teamLeader) {
        StaffMember a1, a2, a3, a11, a12, a121, a122, a123, a13, a31, a32, a33;
        a1 = new StaffMember("Allie", "J1");
        a2 = new StaffMember("Joseph", "J2");
        a3 = new StaffMember("Lilith", "J3");
        a11 = new StaffMember("Annabel", "J11");
        a12 = new StaffMember("Camellia", "J12");
        a13 = new StaffMember("Hutch", "J13");
        a31 = new StaffMember("Norris", "J31");
        a32 = new StaffMember("Sofia", "J32");
        a33 = new StaffMember("Valentine", "J33");
        a121 = new StaffMember("Gerard", "J121");
        a122 = new StaffMember("Graham", "J122");
        a123 = new StaffMember("Henry", "J123");

        teamLeader.addDirectSubordinate(a2);
        teamLeader.addDirectSubordinate(a3);
        teamLeader.addDirectSubordinate(a1);
        a1.addDirectSubordinate(a11);
        a1.addDirectSubordinate(a13);
        a1.addDirectSubordinate(a12);
        a12.addDirectSubordinate(a121);
        a12.addDirectSubordinate(a123);
        a12.addDirectSubordinate(a122);
        a3.addDirectSubordinate(a33);
        a3.addDirectSubordinate(a31);
        a3.addDirectSubordinate(a32);

        return new TreeSet<>(Set.of(a1, a11, a12, a121, a122, a123, a13, a2, a3, a31, a32, teamLeader, a33));
    }
}
