import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

 
public class PayrollDispositionImplTest {
    private PayrollDispositionImpl disposition;
    private Employee newEmployee;
    private Map<Employee, Double> payments;

    @BeforeEach
    public void setUp() {
        disposition = new PayrollDispositionImpl();
        newEmployee = new Appointee("a0", 21, 197, 37);
        payments = new HashMap<>();

        Employee e1 = new Appointee("a1", 21, 205, 13);
        Employee e2 = new Appointee("a2", 21, 120, 25);
        Employee e3 = new Appointee("a3", 21, 163, 11);
        Employee e4 = new Appointee("a4", 21, 87, 10);
        Employee e5 = new Appointee("a5", 21, 111, 31);

        payments.put(e1, 1599.0);
        payments.put(e2, 1800.0);
        payments.put(e3, 1075.8);
        payments.put(e4, 522.0);
        payments.put(e5, 2064.6);
    }

    private void depositPayments() {
        for (Entry<Employee, Double> e : payments.entrySet()) {
            disposition.sendPayment(e.getKey(), e.getValue());
        }
    }

    @Test
    public void testSendPaymentNullArgument() {
        try {
            disposition.sendPayment(null, 5);
            Assertions.fail(
                    "PayrollDispositionImpl.sendPayment() should throw a NullPointerException if the empl argument is null!");
        } catch (NullPointerException exception) {
            // NullPointerException correctly thrown
        }
    }

    @Test
    public void testSendPaymentIllegalArgument() {
        try {
            disposition.sendPayment(newEmployee, -1);
            Assertions.fail(
                    "PayrollDispositionImpl.sendPayment() should throw an IllegalArgumentException if the payment argument is negative!");
        } catch (IllegalArgumentException exception) {
            // IllegalArgumentException correctly thrown
        }

        try {
            disposition.sendPayment(newEmployee, 0);
            Assertions.fail("PayrollDispositionImpl.sendPayment() should throw an IllegalArgumentException if the payment argument is zero!");
        } catch (IllegalArgumentException exception) {
            // IllegalArgumentException correctly thrown
        }
    }

    @Test
    public void testGetTotal() {
        Assertions.assertEquals(0, disposition.getTotal(), 0,
                "PayrollDispositionImpl.getTotal() should return the correct value");

        depositPayments();

        Assertions.assertEquals(7061.4, disposition.getTotal(), 0.0001, "PayrollDispositionImpl.getTotal() should return the correct value");
        disposition.sendPayment(newEmployee, 4373.4);
        Assertions.assertEquals(11434.8, disposition.getTotal(), 0.0001, "PayrollDispositionImpl.getTotal() should return the correct value");
    }

    @Test
    public void testGetAverage() {
        Assertions.assertEquals(0, disposition.getAverage(), 0, "PayrollDispositionImpl.getAverage() should return " +
                "the correct value");

        depositPayments();

        Assertions.assertEquals(1412.28, disposition.getAverage(), 0.0001,
                "PayrollDispositionImpl.getAverage() should return the correct value");
        disposition.sendPayment(newEmployee, 4373.4);
        Assertions.assertEquals(1905.8, disposition.getAverage(), 0.0001,
                "PayrollDispositionImpl.getAverage() should return the correct value");
    }

    @Test
    public void testGetPayments() {
        Assertions.assertTrue(disposition.getPayments().isEmpty(),
                "PayrollDispositionImpl.getPayments() should return an empty Map if not payments have been sent!");

        depositPayments();

        for (Entry<Employee, Double> e : payments.entrySet()) {
            Assertions.assertEquals(disposition.getPayments().get(e.getKey()), e.getValue(), 0,
                    "PayrollDispositionImpl.getPayments() should return all sent Employee-payment pairs!");
        }
        Assertions.assertEquals(disposition.getPayments().size(), payments.size(), "PayrollDispositionImpl.getPayments() should only return sent Employee-payment pairs!");
    }
}
