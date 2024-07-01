import java.lang.reflect.Modifier;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

 
public class AbstractEnterpriseUnitTest {
    private static class AbstractEnterpriseUnitImpl extends AbstractEnterpriseUnit {
        public AbstractEnterpriseUnitImpl(String name) {
            super(name);
        }
    }

    @Test
    public void abstractEnterpriseUnitAbstract() {
        Assertions.assertTrue(Modifier.isAbstract(AbstractEnterpriseUnit.class.getModifiers()),
                "AbstractEnterpriseUnit should be abstract!");
    }

    @Test
    public void abstractEnterpriseUnitConstructorNullArgument() {
        try {
            new AbstractEnterpriseUnitImpl(null);
            Assertions.fail(
                    "AbstractEnterpriseUnit.AbstractEnterpriseUnit() should throw a NullPointerException if the argument is null!");
        } catch (NullPointerException e) {
        }

        try {
            new Holding(null);
            Assertions.fail("Holding.Holding() should throw a NullPointerException if the argument is null!");
        } catch (NullPointerException e) {
        }

        try {
            new Company(null);
            Assertions.fail("Company.Company() should throw a NullPointerException if the argument is null!");
        } catch (NullPointerException e) {
        }

        try {
            new Division(null);
            Assertions.fail("Division.Division() should throw a NullPointerException if the argument is null!");
        } catch (NullPointerException e) {
        }

        try {
            new Team(null, new StaffMember("Mike", "Supervisor"));
            Assertions.fail("Team.Team() should throw a NullPointerException if the name argument is null!");
        } catch (NullPointerException e) {
        }

        try {
            new Team("A-Team", null);
            Assertions.fail("Team.Team() should throw a NullPointerException if the teamLeader argument is null!");
        } catch (NullPointerException e) {
        }
    }

    @Test
    public void abstractEnterpriseUnitConstructorIllegalArgument() {
        try {
            new AbstractEnterpriseUnitImpl("");
            Assertions.fail("AbstractEnterpriseUnit.AbstractEnterpriseUnit() should throw an IllegalArgumentException if the argument is empty!");
        } catch (IllegalArgumentException e) {
        }

        try {
            new Holding("");
            Assertions.fail("Holding.Holding() should throw an IllegalArgumentException if the argument is empty!");
        } catch (IllegalArgumentException e) {
        }

        try {
            new Company("");
            Assertions.fail("Company.Company() should throw an IllegalArgumentException if the argument is empty!");
        } catch (IllegalArgumentException e) {
        }

        try {
            new Division("");
            Assertions.fail("Division.Division() should throw an IllegalArgumentException if the argument is empty!");
        } catch (IllegalArgumentException e) {
        }

        try {
            new Team("", new StaffMember("Mike", "Supervisor"));
            Assertions.fail("Team.Team() should throw an IllegalArgumentException if the name argument is empty!");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void abstractEnterpriseUnitGetName() {
        EnterpriseNode node = new AbstractEnterpriseUnitImpl("Abstract Enterprise Unit");
        Assertions.assertTrue(node.getName().equals("Abstract Enterprise Unit"), "AbstractEnterpriseUnit.getName() should return the correct value!");

        node = new Holding("AEU Holding");
        Assertions.assertTrue(node.getName().equals("AEU Holding"), "Holding.getName() should return the correct value!");

        node = new Company("AEU Company");
        Assertions.assertTrue(node.getName().equals("AEU Company"), "Company.getName() should return the correct value!");

        node = new Division("AEU Division");
        Assertions.assertTrue(node.getName().equals("AEU Division"), "Division.getName() should return the correct value!");

        node = new Team("AEU Team", new StaffMember("Member 1", "Executive 1"));
        Assertions.assertTrue(node.getName().equals("AEU Team"), "Team.getName() should return the correct value!");
    }
}
