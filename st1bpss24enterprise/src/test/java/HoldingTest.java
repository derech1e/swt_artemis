import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

 
public class HoldingTest {
    private Holding holding;

    @BeforeEach
    public void setUp() {
        holding = new Holding("Holding");
    }

    @Test
    public void holdingTestHoldingConsistsOnlyOfCompanies() {
        Holding childHolding = new Holding("Holding 2");
        Company childCompany = new Company("Company");
        Division childDivision = new Division("Division");
        Team childTeam = new Team("Team", new StaffMember("Thomas", "Executive"));

        try {
            holding.add(null);
            Assertions.fail("Holding.add() should throw a NullPointerException if the argument is null!");
        } catch (NullPointerException e) {
        }

        try {
            holding.add(childHolding);
            Assertions.fail("Holding.add() should throw an IllegalArgumentException if the argument is a Holding!");
        } catch(IllegalArgumentException e) {
        }

        Assertions.assertTrue(holding.add(childCompany), "Holding.add() should return true if a new Company was added!");
        Assertions.assertFalse(holding.add(childCompany),
                "Holding.add() should return false if the given Company was already added in the past!");

        try {
            holding.add(childDivision);
            Assertions.fail("Holding.add() should throw an IllegalArgumentException if the argument is a Division!");
        } catch (IllegalArgumentException e) {
        }

        try {
            holding.add(childTeam);
            Assertions.fail("Holding.add() should throw an IllegalArgumentException if the argument is a Team!");
        } catch (IllegalArgumentException e) {
        }
    }
}