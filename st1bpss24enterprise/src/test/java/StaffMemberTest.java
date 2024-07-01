import java.util.Set;
import java.util.TreeSet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

 
public class StaffMemberTest {
    private StaffMember mike;
    private StaffMember bob;
    private Set<StaffMember> staffMembers;

    @BeforeEach
    public void setUp() {
        mike = new StaffMember("Mike", "Supervisor");
        bob = new StaffMember("Bob", "Executive 1");
        staffMembers = new TreeSet<>();
        staffMembers.add(bob);
        staffMembers.add(new StaffMember("Member 2", "Executive 2"));
        staffMembers.add(new StaffMember("Member 3", "Executive 3"));
        staffMembers.add(new StaffMember("Member 4", "Executive 4"));
    }

    @Test
    public void staffMemberTestConstructorNullArgument() {
        try {
            new StaffMember(null, "Supervisor");
            Assertions.fail(
                    "StaffMember.StaffMember() should throw a NullPointerException if the name argument is null!");
        } catch (NullPointerException e) {
        }

        try {
            new StaffMember("Mike", null);
            Assertions.fail("StaffMember.StaffMember() should throw a NullPointerException if the job argument is null!");
        } catch (NullPointerException e) {
        }
    }

    @Test
    public void staffMemberTestConstructorIllegalArgument() {
        try {
            new StaffMember("", "Supervisor");
            Assertions.fail("StaffMember.StaffMember() should throw an IllegalArgumentException if the name argument is empty!");
        } catch (IllegalArgumentException e) {
        }

        try {
            new StaffMember("Mike", "");
            Assertions.fail("StaffMember.StaffMember() should throw an IllegalArgumentException if the job argument is empty!");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void staffMemberTestGetName() {
        Assertions.assertEquals("Mike", mike.getName(), "StaffMember.getName() should return the correct value!");
    }

    @Test
    public void staffMemberTestGetJob() {
        Assertions.assertEquals("Supervisor", mike.getJob(), "StaffMember.getJob() should return the correct value!");
    }

    @Test
    public void staffMemberTestCompareTo() {
        Assertions.assertTrue(mike.compareTo(new StaffMember("Marc", "CEO")) > 0,
                "StaffMember.compareTo() should return a positive integer if the given value is smaller!");
        Assertions.assertEquals(0, mike.compareTo(new StaffMember("Mike", "Chief Supervisor")),
                "StaffMember.compareTo() should return the integer zero if the given value is equal!");
        Assertions.assertTrue(mike.compareTo(new StaffMember("Monique", "CFO")) < 0, "StaffMember.compareTo() should return a negative integer if the given value is greater!");
    }

    @Test
    public void staffMemberTestAddDirectSubordinateNullArgument() {
        try {
            mike.addDirectSubordinate(null);
            Assertions.fail("StaffMember.addDirectSubordinate() should throw a NullPointerException if the argument is null!");
        } catch (NullPointerException e) {
        }
    }

    @Test
    public void staffMemberTestAddDirectSubordinate() {
        for (StaffMember subordinate : staffMembers) {
            Assertions.assertTrue(mike.addDirectSubordinate(subordinate), "StaffMember.addDirectSubordinate() should " +
                    "return true if the member is to be added!");
            Assertions.assertTrue(mike.getDirectSubordinates().contains(subordinate),
                    "StaffMember.addDirectSubordinate() should add the member if it was not a direct subordinate previously!");

            int sizeBefore = mike.getDirectSubordinates().size();
            Assertions.assertFalse(mike.addDirectSubordinate(subordinate),
                    "StaffMember.addDirectSubordinate() should return false if the member is not to be added!");
            Assertions.assertEquals(sizeBefore, mike.getDirectSubordinates().size(), "StaffMember.addDirectSubordinate() should not add the member if it is a direct subordinate!");
        }
    }

    @Test
    public void staffMemberTestRemoveDirectSubordinateNullArgument() {
        try {
            mike.removeDirectSubordinate(null);
            Assertions.fail("StaffMember.removeDirectSubordinate() should throw a NullPointerException if the argument is null!");
        } catch (NullPointerException e) {
        }
    }

    @Test
    public void staffMemberTestRemoveDirectSubordinate() {
        for (StaffMember subordinate : staffMembers) {
            mike.addDirectSubordinate(subordinate);
        }

        for (StaffMember subordinate : staffMembers) {
            Assertions.assertTrue(mike.removeDirectSubordinate(subordinate),
                    "StaffMember.removeDirectSubordinate() should return true if the member was a direct subordinate!");
            Assertions.assertFalse(mike.getDirectSubordinates().contains(subordinate), "StaffMember.removeDirectSubordinate() should remove the member if it is a direct subordinate!");
            Assertions.assertFalse(mike.removeDirectSubordinate(subordinate), "StaffMember.removeDirectSubordinate() should return false if the member is not a direct subordinate!");
        }
    }

    @Test
    public void staffMemberTestRemoveIndirectSubordinate() {
        for (StaffMember subordinate : staffMembers) {
            mike.addDirectSubordinate(subordinate);
        }

        StaffMember newMember = new StaffMember("New Guy", "New Stuff");
        bob.addDirectSubordinate(newMember);

        Assertions.assertFalse(mike.removeDirectSubordinate(newMember), "StaffMember.removeDirectSubordinate() should return false if the member is not a direct subordinate!");
        Assertions.assertTrue(bob.getDirectSubordinates().contains(newMember),
                "StaffMember.removeDirectSubordinate() should not remove an indirect subordinate!");
    }

    @Test
    public void staffMemberTestGetDirectSubordinatesInitiallyEmpty() {
        Set<StaffMember> subordinates = mike.getDirectSubordinates();
        Assertions.assertTrue(subordinates.isEmpty(),
                "StaffMember.getDirectSubordinates() should return an empty set if no direct subordinates have been added!");
    }

    @Test
    public void staffMemberTestToString() {
        Assertions.assertEquals("Mike", mike.toString(), "StaffMember.toString() should return the correct value!");
    }
}
