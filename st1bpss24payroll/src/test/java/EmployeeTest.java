import java.lang.reflect.Modifier;
import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

 
public class EmployeeTest {
    private static class EmployeeImpl extends Employee {
        EmployeeImpl(String id) {
            super(id);
        }

        @Override
        public boolean isPayday(int dayOfMonth) {
            return false;
        }

        @Override
        public double calculatePay() throws UnpayableEmployeeException {
            return 0;
        }

        @Override
        public double calculateDeductions() {
            return 0;
        }
    }

    @Test
    public void testAbstract() {
        Assertions.assertTrue(Modifier.isAbstract(Employee.class.getModifiers()), "Employee should be abstract!");
        try {
            Assertions.assertTrue(Modifier.isAbstract(Employee.class.getDeclaredMethod("isPayday", int.class).getModifiers()), "Employee.isPayday() should be abstract!");
        } catch (NoSuchMethodException exception) {
            Assertions.fail("Employee should have a method named isPayday with one parameter of type int!");
        }
        try {
            Assertions.assertTrue(Modifier.isAbstract(Employee.class.getDeclaredMethod("calculatePay").getModifiers()), "Employee.calculatePay() should be abstract!");
        } catch (NoSuchMethodException exception) {
            Assertions.fail("Employee should have a method named calculatePay without any parameters!");
        }
        try {
            Assertions.assertTrue(Modifier.isAbstract(Employee.class.getDeclaredMethod("calculateDeductions").getModifiers()), "Employee.calculateDeductions() should be abstract!");
        } catch (NoSuchMethodException exception) {
            Assertions.fail("Employee should have a method named calculateDeductions without any parameters!");
        }
    }

    @Test
    public void testConstructorNullArgument() {
        try {
            new EmployeeImpl(null);
            Assertions.fail("Employee.Employee() should throw a NullPointerException if the argument is null!");
        } catch (NullPointerException exception) {
            // NullPointerException correctly thrown
        }

        try {
            new Appointee(null, 1, 1, 1);
            Assertions.fail("Appointee.Appointee() should throw a NullPointerException if the id argument is null!");
        } catch (NullPointerException exception) {
            // NullPointerException correctly thrown
        }

        try {
            new Volunteer(null);
            Assertions.fail("Volunteer.Volunteer() should throw a NullPointerException if the argument is null!");
        } catch (NullPointerException exception) {
            // NullPointerException correctly thrown
        }
    }

    @Test
    public void testConstructorIllegalArgument() {
        try {
            new EmployeeImpl("");
            Assertions.fail("Employee.Employee() should throw an IllegalArgumentException if the argument is empty!");
        } catch (IllegalArgumentException exception) {
            // IllegalArgumentException correctly thrown
        }

        try {
            new Appointee("", 1, 1, 1);
            Assertions.fail("Appointee.Appointee() should throw an IllegalArgumentException if the id argument is empty!");
        } catch (IllegalArgumentException exception) {
            // IllegalArgumentException correctly thrown
        }

        try {
            new Volunteer("");
            Assertions.fail("Volunteer.Volunteer() should throw an IllegalArgumentException if the argument is empty!");
        } catch (IllegalArgumentException exception) {
            // IllegalArgumentException correctly thrown
        }
    }

    @Test
    public void testGetId() {
        Employee e = new EmployeeImpl("Martin");
        Assertions.assertEquals("Martin", e.getId(), "Employee.getId() should return the correct value!");

        e = new Appointee("Eric", 1, 1, 1);
        Assertions.assertEquals("Eric", e.getId(), "Appointee.getId() should return the correct value!");

        e = new Volunteer("Michael");
        Assertions.assertEquals("Michael", e.getId(), "Volunteer.getId() should return the correct value!");
    }

    @Test
    public void testCalculatePayThrowsDeclaration() {
        try {
            Assertions.assertTrue(Arrays.asList(Employee.class.getDeclaredMethod("calculatePay").getExceptionTypes())
                    .contains(UnpayableEmployeeException.class),
                    "Employee.calculatePay() should declare to throw UnpayableEmployeeException!");
        } catch (NoSuchMethodException exception) {
            Assertions.fail("Employee should have a method named calculatePay without any parameters!");
        }
    }
}
