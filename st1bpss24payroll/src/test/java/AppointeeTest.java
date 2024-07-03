import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

 
public class AppointeeTest {
    private Employee appointee1, appointee2;

    @BeforeEach
    public void setUp() {
        appointee1 = new Appointee("Martin", 5, 165, 21.5);
        appointee2 = new Appointee("Eric", 29, 83, 19.87);
    }

    @Test
    public void appointeeTestConstructorInvalidPayday() {
        try {
            new Appointee("Martin", -1, 1, 1);
            Assertions.fail(
                    "Appointee.Appointee() should throw an IllegalArgumentException if the payday argument is invalid!");
        } catch (IllegalArgumentException exception) {
            // IllegalArgumentException correctly thrown
        }
        try {
            new Appointee("Martin", 0, 1, 1);
            Assertions.fail("Appointee.Appointee() should throw an IllegalArgumentException if the payday argument is invalid!");
        } catch (IllegalArgumentException exception) {
            // IllegalArgumentException correctly thrown
        }
        try {
            new Appointee("Martin", 31, 1, 1);
            Assertions.fail("Appointee.Appointee() should throw an IllegalArgumentException if the payday argument is invalid!");
        } catch (IllegalArgumentException exception) {
            // IllegalArgumentException correctly thrown
        }
    }

    @Test
    public void appointeeTestConstructorValidPayday() {
        try {
            for (int i = 1; i <= 30; i++) {
                new Appointee("Martin", i, 1, 1);
            }
        } catch (IllegalArgumentException exception) {
            Assertions.fail("Appointee.Appointee() should not throw an IllegalArgumentException if the payday argument is valid!");
        }
    }

    @Test
    public void appointeeTestConstructorHoursPerMonth() {
        try {
            new Appointee("Martin", 1, 0, 1);
            Assertions.fail("Appointee.Appointee() should throw an IllegalArgumentException if the hoursPerMonth argument is invalid!");
        } catch (IllegalArgumentException exception) {
            // IllegalArgumentException correctly thrown
        }

        try {
            new Appointee("Martin", 1, -1, 1);
            Assertions.fail("Appointee.Appointee() should throw an IllegalArgumentException if the hoursPerMonth argument is invalid!");
        } catch (IllegalArgumentException exception) {
            // IllegalArgumentException correctly thrown
        }
    }

    @Test
    public void appointeeTestConstructorPayPerHour() {
        try {
            new Appointee("Martin", 1, 1, 0);
            Assertions.fail("Appointee.Appointee() should throw an IllegalArgumentException if the payPerHour argument is invalid!");
        } catch (IllegalArgumentException exception) {
            // IllegalArgumentException correctly thrown
        }

        try {
            new Appointee("Martin", 1, 1, -1);
            Assertions.fail("Appointee.Appointee() should throw an IllegalArgumentException if the payPerHour argument is invalid!");
        } catch (IllegalArgumentException exception) {
            // IllegalArgumentException correctly thrown
        }

        try {
            new Appointee("Martin", 1, 1, Double.MIN_VALUE);
        } catch (IllegalArgumentException exception) {
            Assertions.fail("Appointee.Appointee() should not throw an IllegalArgumentException if the payPerHour argument is valid!");
        }
    }

    @Test
    public void appointeeTestIsPaydayIllegalArgument() {
        try {
            appointee1.isPayday(-1);
            Assertions.fail("Appointee.isPayday() should throw an IllegalArgumentException for values lower than 1!");
        } catch (IllegalArgumentException exception) {
            // IllegalArgumentException correctly thrown
        }

        try {
            appointee1.isPayday(0);
            Assertions.fail("Appointee.isPayday() should throw an IllegalArgumentException for values lower than 1!");
        } catch (IllegalArgumentException exception) {
            // IllegalArgumentException correctly thrown
        }

        try {
            appointee1.isPayday(31);
            Assertions.fail("Appointee.isPayday() should throw an IllegalArgumentException for values greater than 30!");
        } catch (IllegalArgumentException exception) {
            // IllegalArgumentException correctly thrown
        }
    }

    @Test
    public void appointeeTestIsPayday() {
        for (int i = 1; i <= 30; i++) {
            if (i == 5) {
                Assertions.assertTrue(appointee1.isPayday(i), "Appointee.isPayday() should return the correct value!");
            } else {
                Assertions.assertFalse(appointee1.isPayday(i), "Appointee.isPayday() should return the correct value!");
            }
            if (i == 29) {
                Assertions.assertTrue(appointee2.isPayday(i), "Appointee.isPayday() should return the correct value!");
            } else {
                Assertions.assertFalse(appointee2.isPayday(i), "Appointee.isPayday() should return the correct value!");
            }
        }
    }

    @Test
    public void appointeeTestCalculatePay() {
        try {
            Assertions.assertEquals(3547.5, appointee1.calculatePay(), 0.0001,
                    "Appointee.calculatePay() should return the correct value!");
            Assertions.assertEquals(1649.21, appointee2.calculatePay(), 0.0001, "Appointee.calculatePay() should return the correct value!");
        } catch (UnpayableEmployeeException exception) {
            Assertions.fail("Appointee.calculatePay() should never throw an UnpayableEmployeeException!");
        }
    }

    @Test
    public void appointeeTestCalculateDeductions() {
        Assertions.assertEquals(1419, appointee1.calculateDeductions(), 0.0001, "Appointee.calculateDeductions() " +
                "should return the correct value!");
        Assertions.assertEquals(659.684, appointee2.calculateDeductions(), 0.0001, "Appointee.calculateDeductions() should return the correct value!");
    }
}
