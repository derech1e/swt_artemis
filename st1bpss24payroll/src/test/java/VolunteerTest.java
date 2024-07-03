import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

 
public class VolunteerTest {
    private Volunteer volunteer;

    @BeforeEach
    public void setUp() {
        volunteer = new Volunteer("Martin");
    }

    @Test
    public void volunteerTestIsPaydayIllegalArgument() {
        try {
            volunteer.isPayday(-1);
            Assertions.fail("Volunteer.isPayday() should throw an IllegalArgumentException for values lower than 1!");
        } catch (IllegalArgumentException exception) {
            // IllegalArgumentException correctly thrown
        }

        try {
            volunteer.isPayday(0);
            Assertions.fail("Volunteer.isPayday() should throw an IllegalArgumentException for values lower than 1!");
        } catch (IllegalArgumentException exception) {
            // IllegalArgumentException correctly thrown
        }

        try {
            volunteer.isPayday(31);
            Assertions.fail("Volunteer.isPayday() should throw an IllegalArgumentException for values greater than 30!");
        } catch (IllegalArgumentException exception) {
            // IllegalArgumentException correctly thrown
        }
    }

    @Test
    public void volunteerTestIsPayday() {
        for (int i = 1; i <= 30; i++) {
            Assertions.assertFalse(volunteer.isPayday(i), "Volunteer.isPayday() should return the correct value!");
        }
    }

    @Test
    public void volunteerTestCalculatePay() {
        try {
            volunteer.calculatePay();
            Assertions.fail("Volunteer.calculatePay() should throw an UnpayableEmployeeException!");
        } catch (UnpayableEmployeeException exception) {
            //UnpayableEmployeeException correctly thrown
        }
    }

    @Test
    public void volunteerTestCalculateDeductions() {
        Assertions.assertEquals(0, volunteer.calculateDeductions(), Double.MIN_VALUE,
                "Volunteer.calculateDeductions() should return the correct value!");
    }
}
