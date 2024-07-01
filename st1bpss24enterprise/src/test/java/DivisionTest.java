import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

 
public class DivisionTest {
    private Division division;

    @BeforeEach
    public void setUp() {
        division = new Division("Division");
    }

    @Test
    public void testDivisionConsistsOnlyOfTeams() {
        Holding childHolding = new Holding("Holding");
        Company childCompany = new Company("Company");
        Division childDivision = new Division("Division 2");
        Team childTeam = new Team("Team", new StaffMember("Thomas", "Executive"));

        try {
            division.add(null);
            Assertions.fail("Division.add() should throw a NullPointerException if the argument is null!");
        } catch (NullPointerException e) {
        }

        try {
            division.add(childHolding);
            Assertions.fail("Division.add() should throw an IllegalArgumentException if the argument is a Holding!");
        } catch(IllegalArgumentException e) {
        }

        try {
            division.add(childCompany);
            Assertions.fail("Division.add() should throw an IllegalArgumentException if the argument is a Company!");
        } catch (IllegalArgumentException e) {
        }

        try {
            division.add(childDivision);
            Assertions.fail("Division.add() should throw an IllegalArgumentException if the argument is a Division!");
        } catch (IllegalArgumentException e) {
        }

        Assertions.assertTrue(division.add(childTeam), "Division.add() should return true if a new Team was added!");
        Assertions.assertFalse(division.add(childTeam),
                "Division.add() should return false if the given Team was already added in the past!");
    }
}