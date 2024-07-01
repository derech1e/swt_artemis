import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

 
public class PositiveTest {

    private BankAccount account;
    private static final double DELTA = 0.001;

    @BeforeEach
    public void setUp() {
        account = new BankAccount("123", 50);
    }

    @Test
    public void positiveStateTestInheritance() {
        var positiveClass = Arrays.stream(BankAccount.class.getDeclaredClasses())
                .filter(cls -> cls.getSimpleName().equals("Positive"))
                .findFirst();

        if (positiveClass.isPresent()) {
            Assertions.assertEquals("AccountState", positiveClass.get().getSuperclass().getSimpleName(),
                    "Positive should be a subclass of AccountState!");
        } else {
            Assertions.fail("BankAccount should have inner class Positive!");
        }
    }

    @Test
    public void positiveStateTestToString() {
        Assertions.assertEquals("Positive", account.getState(),
                "BankAccount.getState() should return \"Positive\" if the state is Positive!");
    }

    @Test
    public void positiveStateTestPayIn() {
        Assertions.assertTrue(account.payIn(10), "Positive.payIn() should return true!");
        Assertions.assertEquals(10, account.getBalance(), DELTA, "Positive.payIn() should add the amount to the " +
        "balance of the account!");
        Assertions.assertEquals("Positive", account.getState(), "Positive.payIn() should not change the account's state!");
    }

    @Test
    public void positiveStateTestPayOffReturnFalse() {
        Assertions.assertFalse(account.payOff(60), "Positive.payOff() should return false " +
                "if the transaction exceeds the account's lineOfCredit!");
        Assertions.assertEquals(0, account.getBalance(), DELTA, "Positive.payOff() should not change the balance " +
                "if the transaction exceeds the account's lineOfCredit!");
        Assertions.assertEquals("Positive", account.getState(), "Positive.payOff() should not change the account's state " +
                "if the transaction exceeds the account's lineOfCredit!");
    }

    @Test
    public void positiveStateTestPayOffToFrozen() {
        Assertions.assertTrue(account.payOff(50), "Positive.payOff() should return true if the transaction can be processed!");
        Assertions.assertEquals(-50, account.getBalance(), DELTA,
                "Positive.payOff() should subtract the sum passed as argument " +
                        "from the balance of the account!");
        Assertions.assertEquals("Frozen", account.getState(),
                "Positive.payOff() should change the state to Frozen if the balance equals lineOfCredit!");
    }

    @Test
    public void positiveStateTestPayOffToNegative() {
        Assertions.assertTrue(account.payOff(40), "Positive.payOff() should return true if the transaction can be processed!");
        Assertions.assertEquals(-40, account.getBalance(), DELTA, "Positive.payOff() should subtract the amount from the balance of the account!");
        Assertions.assertEquals("Negative", account.getState(), "Positive.payOff() should change the state to Negative " +
                "if the amount is greater than the account's balance!");
    }

    @Test
    public void positiveStateTestPayOffToPositive() {
        account.payIn(50);
        Assertions.assertTrue(account.payOff(40), "Positive.payOff() should return true if the transaction can be processed!");
        Assertions.assertEquals(10, account.getBalance(), DELTA, "Positive.payOff() should subtract the amount from the balance of the account!");
        Assertions.assertEquals("Positive", account.getState(), "Positive.payOff() should not change the state " +
                "if the amount is less than the account's balance!");
    }

    @Test
    public void positiveStateTestPayInterest() {
        account.payInterest();
        Assertions.assertEquals(0.0, account.getBalance(), DELTA,
                "Positive.payInterest() should not change the balance if the balance is 0!");

        account = new BankAccount("1337", 10);
        account.payIn(20);
        account.payInterest();
        Assertions.assertEquals(20.2, account.getBalance(), DELTA, "Positive.payInterest() should increase the balance by 1 percent!");

    }

    @Test
    public void positiveStateTestPrintBalance() {
        var outStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outStream));
        account.printBalance();
        var outString = outStream.toString(StandardCharsets.UTF_8);
        Assertions.assertEquals("Balance is POSITIVE: +0.0.", outString.trim(), "Positive.printBalance() should print the balance as specified in the task description!");
        account.payIn(20);
        outStream.reset();
        account.printBalance();
        outString = outStream.toString(StandardCharsets.UTF_8);
        Assertions.assertEquals("Balance is POSITIVE: +20.0.", outString.trim(), "Positive.printBalance() should print the balance as specified in the task description!");
    }

}
