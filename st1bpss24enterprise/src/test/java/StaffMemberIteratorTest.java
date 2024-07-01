import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

 
public class StaffMemberIteratorTest {
    @Test
    public void testConstructorRejectsNullArgument() {
        try {
            new StaffMemberIterator(null);
            Assertions.fail(
                    "StaffMemberIterator.StaffMemberIterator() should throw a NullPointerException if the argument is null!");
        } catch (NullPointerException e) {
        }
    }

    @Test
    public void testIteratorOverEmptySet() {
        Iterator<StaffMember> iter = new StaffMemberIterator(new HashSet<>());

        Assertions.assertFalse(iter.hasNext(), "StaffMemberIterator.hasNext() should return false if there is no next element!");
        try {
            iter.next();
            Assertions.fail("StaffMemberIterator.next() should throw a NoSuchElementException if there is no next element!");
        } catch (NoSuchElementException e) {
        }
    }

    @Test
    public void testIteratorReturnsElementsInProperOrder() {
        StaffMember teamLeader = new StaffMember("Mike", "Supervisor");
        SortedSet<StaffMember> expectedMembers = new TreeSet<>(TeamTest.createTeamHierarchy(teamLeader));
        expectedMembers.remove(teamLeader);

        Iterator<StaffMember> expectedIter = expectedMembers.iterator();
        Iterator<StaffMember> actualIter = new StaffMemberIterator(teamLeader.getDirectSubordinates());

        while (expectedIter.hasNext()) {
            Assertions.assertTrue(actualIter.hasNext(),
                    "StaffMemberIterator.hasNext() should return true if there is a next available element!");
            Assertions.assertEquals(expectedIter.next(), actualIter.next(),
                    "StaffMemberIterator.next() should return the correct next element if there is one available!");
        }

        Assertions.assertFalse(actualIter.hasNext(),
                "StaffMemberIterator.hasNext() should return false if there is no next element!");
        try {
            actualIter.next();
            Assertions.fail("StaffMemberIterator.next() should throw a NoSuchElementException if there is no next element!");
        } catch (NoSuchElementException e) {
        }
    }
}
