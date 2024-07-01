import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

 
public class BankAccountTest {

    private BankAccount account;
    private static final double DELTA = 0.01;

    @BeforeEach
    public void setUp() {
        account = new BankAccount("123", 50);
    }

    @Test
    public void testFieldBalance() {
        try {
            Field balanceField = BankAccount.class.getDeclaredField("balance");
            Assertions.assertTrue(Modifier.isPrivate(balanceField.getModifiers()), "Field balance should be private!");
            Assertions.assertEquals(double.class, balanceField.getType(), "Field balance should be of type double!");
        } catch (NoSuchFieldException e) {
            Assertions.fail("BankAccount should have a field 'balance'!");
        }
    }

    @Test
    public void testFieldLineOfCredit() {
        try {
            Field lineOfCreditField = BankAccount.class.getDeclaredField("lineOfCredit");
            Assertions.assertTrue(Modifier.isPrivate(lineOfCreditField.getModifiers()),
                    "Field lineOfCredit should be private!");
            Assertions.assertEquals(double.class, lineOfCreditField.getType(), "Field lineOfCredit should be of type double!");
        } catch (NoSuchFieldException e) {
            Assertions.fail("BankAccount should have a field 'lineOfCredit'!");
        }
    }

    @Test
    public void testFieldAccountNumber() {
        try {
            Field accountNumberField = BankAccount.class.getDeclaredField("accountNumber");
            Assertions.assertTrue(Modifier.isPrivate(accountNumberField.getModifiers()),
                    "Field accountNumber should be private!");
            Assertions.assertEquals(String.class, accountNumberField.getType(), "Field accountNumber should be of type String!");
        } catch (NoSuchFieldException e) {
            Assertions.fail("BankAccount should have a field 'accountNumber'!");
        }
    }

    @Test
    public void testFieldState() {
        try {
            Field stateField = BankAccount.class.getDeclaredField("state");
            Assertions.assertTrue(Modifier.isPrivate(stateField.getModifiers()), "Field 'state' should be private!");
            var innerClasses = BankAccount.class.getDeclaredClasses();
            var stateClass = Arrays.stream(innerClasses)
                    .filter(cls -> cls.getSimpleName().equals("AccountState"))
                    .findFirst();
            Assertions.assertTrue(stateClass.isPresent(), "BankAccount should have an inner class AccountState!");
            Assertions.assertEquals(stateField.getType(), stateClass.get(), "Field 'accountNumber' should be of type AccountState!");
        } catch (NoSuchFieldException e) {
            Assertions.fail("BankAccount should have a field 'state'!");
        }
    }

    @Test
    public void testConstructorNullArgument() {
        try {
            new BankAccount(null, 50);
            Assertions.fail("Constructor BankAccount() should throw a NullPointerException if" +
                    " the accountNumber is null!");
        } catch (NullPointerException ignored) {
        }
    }

    @Test
    public void testConstructorIllegalArgument() {
        try {
            new BankAccount("", 50);
            Assertions.fail("Constructor BankAccount() should throw an IllegalArgumentException" +
                    " if the accountNumber is empty!");

            new BankAccount("", -1);
            Assertions.fail("Constructor BankAccount() should throw an IllegalArgumentException" +
                    " if the lineOfCredit is negative!");
        } catch (IllegalArgumentException ignored) {
        }
    }

    @Test
    public void testConstructor() {
        Assertions.assertEquals(0, account.getBalance(), DELTA, "Constructor BankAccount should initialize the " +
                "balance" +
                "with 0!");
        Assertions.assertEquals("Positive", account.getState(), "Constructor BankAccount() should set initial state to Positive!");
        Assertions.assertEquals("123", account.getAccountNumber(), "Constructor BankAccount() should set the field " +
                        "'accountNumber' to the String passed as argument!");
        try {
            Field lineOfCreditField = BankAccount.class.getDeclaredField("lineOfCredit");
            lineOfCreditField.setAccessible(true);
            double lineOfCredit = (double) lineOfCreditField.get(account);
            Assertions.assertEquals(50, lineOfCredit, DELTA, "Constructor BankAccount() should set the field " +
                            "'lineOfCredit' to the double passed as argument!");
        } catch (NoSuchFieldException | IllegalAccessException e) {
            Assertions.fail("BankAccount should have a field 'lineOfCredit'!");
        }
    }

    @Test
    public void testPayInIllegalArgument() {
        try {
            account.payIn(-1);
            Assertions.fail("BankAccount.payIn() should throw an IllegalArgumentException " +
                    "if called with a negative amount!");
        } catch (IllegalArgumentException ignored) {
        }

        try {
            account.payIn(0);
            Assertions.fail("BankAccount.payIn() should throw an IllegalArgumentException " +
                    "if called with the an non positive amount!");
        } catch (IllegalArgumentException ignored) {
        }
    }

    @Test
    public void testPayOffIllegalArgument() {
        try {
            account.payOff(-1);
            Assertions.fail("BankAccount.payOff() should throw an IllegalArgumentException " +
                    "if called with a negative amount!");
        } catch (IllegalArgumentException ignored) {
        }

        try {
            account.payOff(0);
            Assertions.fail("BankAccount.payOff() should throw an IllegalArgumentException " +
                    "if called with the an non positive amount!");
        } catch (IllegalArgumentException ignored) {
        }
    }

    @Test
    public void testClose() {
        var state = account.getState();
        account.payIn(50);
        Assertions.assertFalse(account.close(), "BankAccount.close() should return false if the balance is not 0!");
        Assertions.assertEquals(state, account.getState(),
                "An unsuccessful BankAccount.close() call should not change the account state!");
        account.payOff(50);
        Assertions.assertTrue(account.close(), "BankAccount.close() should return true if the balance is 0!");
        Assertions.assertEquals("Closed", account.getState(), "A successful BankAccount.close() call should set the state to Closed!");
    }

}
