import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

 
public class CompanyTest {
    private Company company;

    @BeforeEach
    public void setUp() {
        company = new Company("Company");
    }

    @Test
    public void companyTestHoldingConsistsOnlyOfCompanies() {
        Holding childHolding = new Holding("Holding");
        Company childCompany = new Company("Company 2");
        Division childDivision = new Division("Division");
        Team childTeam = new Team("Team", new StaffMember("Thomas", "Executive"));

        try {
            company.add(null);
            Assertions.fail("Company.add() should throw a NullPointerException if the argument is null!");
        } catch (NullPointerException e) {
        }

        try {
            company.add(childHolding);
            Assertions.fail("Company.add() should throw an IllegalArgumentException if the argument is a Holding!");
        } catch(IllegalArgumentException e) {
        }

        try {
            company.add(childCompany);
            Assertions.fail("Company.add() should throw an IllegalArgumentException if the argument is a Company!");
        } catch (IllegalArgumentException e) {
        }

        Assertions.assertTrue(company.add(childDivision), "Company.add() should return true if a new Division was added!");
        Assertions.assertFalse(company.add(childDivision),
                "Company.add() should return false if the given Division was already added in the past!");


        try {
            company.add(childTeam);
            Assertions.fail("Company.add() should throw an IllegalArgumentException if the argument is a Team!");
        } catch (IllegalArgumentException e) {
        }
    }
}