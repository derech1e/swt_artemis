import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

 
public class PayrollTest {
    private static class PayrollDBImpl implements PayrollDB {
        private List<Employee> db;

        PayrollDBImpl(List<Employee> employees) {
            db = employees;
        }

        @Override
        public List<Employee> getEmployeeList() {
            return db;
        }
    }

    @Test
    public void payrollTestConstructorDisposition() {
        try {
            new Payroll(null, 1);
            Assertions.fail("Payroll.Payroll() should throw a NullPointerException if the disposition argument is null!");
        } catch (NullPointerException exception) {
            // NullPointerException correctly thrown
        }
    }

    @Test
    public void payrollTestConstructorInvalidPayday() {
        try {
            new Payroll(new PayrollDispositionImpl(), -1);
            Assertions.fail(
                    "Payroll.Payroll() should throw an IllegalArgumentException if the payday argument is invalid!");
        } catch (IllegalArgumentException exception) {
            // IllegalArgumentException correctly thrown
        }
        try {
            new Payroll(new PayrollDispositionImpl(), 0);
            Assertions.fail("Payroll.Payroll() should throw an IllegalArgumentException if the payday argument is invalid!");
        } catch (IllegalArgumentException exception) {
            // IllegalArgumentException correctly thrown
        }
        try {
            new Payroll(new PayrollDispositionImpl(), 31);
            Assertions.fail("Payroll.Payroll() should throw an IllegalArgumentException if the payday argument is invalid!");
        } catch (IllegalArgumentException exception) {
            // IllegalArgumentException correctly thrown
        }
    }

    @Test
    public void payrollTestConstructorValidPayday() {
        try {
            for (int i = 1; i <= 30; i++) {
                new Payroll(new PayrollDispositionImpl(), i);
            }
        } catch (IllegalArgumentException exception) {
            Assertions.fail("Payroll.Payroll() should not throw an IllegalArgumentException if the payday argument is valid!");
        }
    }

    @Test
    public void payrollTestDoPayrollNullArgument() {
        try {
            new Payroll(new PayrollDispositionImpl(), 1).doPayroll(null);
            Assertions.fail("Payroll.doPayroll() should throw a NullPointerException if the argument is null!");
        } catch (NullPointerException exception) {
            // NullPointerException correctly thrown
        }
    }

    @Test
    public void payrollTestDoPayroll() {
        PayrollDispositionImpl disposition = new PayrollDispositionImpl();
        List<Employee> employeeList;
        Map<Employee, Double> payments;

        Employee e1 = new Appointee("a1", 21, 205, 13);
        Employee e2 = new Volunteer("v1");
        Employee e3 = new Appointee("a2", 15, 120, 25);
        Employee e4 = new Appointee("a3", 21, 163, 11);
        Employee e5 = new Volunteer("v2");
        Employee e6 = new Appointee("a4", 18, 200, 10);
        Employee e7 = new Appointee("a5", 21, 200, 10);
        employeeList = Arrays.asList(e1, e2, e3, e4, e5, e6, e7);

        new Payroll(disposition, 21).doPayroll(new PayrollDBImpl(employeeList));
        payments = disposition.getPayments();

        Assertions.assertEquals(1599, payments.get(e1), 0.001,
                "Payroll.doPayroll() should send a payment for every Employee to be paid!");
        Assertions.assertEquals(1075.8, payments.get(e4), 0.001, "Payroll.doPayroll() should send a payment for every" +
                " Employee to be paid!");
        Assertions.assertEquals(1200, payments.get(e7), 0.001,
                "Payroll.doPayroll() should send a payment for every Employee to be paid!");
        Assertions.assertEquals(3, payments.size(), "Payroll.doPayroll() should only send payments for Employees to be paid!");
    }
}
