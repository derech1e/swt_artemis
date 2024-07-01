import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

 
public class NegativeTest {

    private BankAccount account, account2;
    private static final double DELTA = 0.001;

    @BeforeEach
    public void setUp() {
        account = new BankAccount("123", 50);
        account.payOff(30);

        account2 = new BankAccount("1337", 10);
        account2.payOff(5);
    }

    @Test
    public void negativeStateTestInheritance() {
        var negativeClass = Arrays.stream(BankAccount.class.getDeclaredClasses())
                .filter(cls -> cls.getSimpleName().equals("Negative"))
                .findFirst();

        if (negativeClass.isPresent()) {
            Assertions.assertEquals("AccountState", negativeClass.get().getSuperclass().getSimpleName(),
                    "Negative should be a subclass of AccountState!");
        } else {
            Assertions.fail("BankAccount should have inner class Negative!");
        }
    }

    @Test
    public void negativeStateTestNegativeToString() {
        Assertions.assertEquals("Negative", account.getState(),
                "BankAccount.getState() should return \"Negative\", if the state is Negative!");
    }

    @Test
    public void negativeStateTestPayInToNegative() {
        Assertions.assertTrue(account.payIn(10), "Negative.payIn() should return true!");
        Assertions.assertEquals(-20, account.getBalance(), DELTA, "Negative.payIn() should add the amount to the " +
        "balance of the account!");
        Assertions.assertEquals("Negative", account.getState(),
                "Negative.payIn() should not change the account's state if" +
                        "the balance is still less than 0!");
    }

    @Test
    public void negativeStateTestPayInToPositive() {
        Assertions.assertTrue(account.payIn(30), "Negative.payIn() should return true!");
        Assertions.assertEquals(0, account.getBalance(), DELTA, "Negative.payIn() should add the amount to the balance of the account!");
        Assertions.assertEquals("Positive", account.getState(),
                "Negative.payIn() should change the account's state to Positive " +
                        "if the balance is 0!");

        account.payOff(30);
        Assertions.assertTrue(account.payIn(40), "Negative.payIn() should return true!");
        Assertions.assertEquals(10, account.getBalance(), DELTA, "Negative.payIn() should add the amount to the balance of the account!");
        Assertions.assertEquals("Positive", account.getState(), "Negative.payIn() should change the account's state to Positive " +
                "if the balance is greater than 0!");
    }

    @Test
    public void negativeStateTestPayOffReturnFalse() {
        Assertions.assertFalse(account.payOff(60), "Negative.payOff() should return false " +
                "if the transaction exceeded the account's lineOfCredit!");
        Assertions.assertEquals(-30, account.getBalance(), DELTA, "Negative.payOff() should not change the balance " +
                "if the transaction exceeded the account's lineOfCredit!");
        Assertions.assertEquals("Negative", account.getState(), "Negative.payOff() should not change the account's state " +
                "if the transaction exceeded the account's lineOfCredit!");
    }

    @Test
    public void negativeStateTestPayOffToFrozen() {
        Assertions.assertTrue(account.payOff(20), "Negative.payOff() should return true if the transaction can be processed!");
        Assertions.assertEquals(-50, account.getBalance(), DELTA, "Negative.payOff() should subtract the amount from the balance of the account!");
        Assertions.assertEquals("Frozen", account.getState(), "Negative.payOff() should change the state to Frozen if" +
        " the balance equals lineOfCredit!");
    }

    @Test
    public void negativeStateTestPayOffToNegative() {
        Assertions.assertTrue(account.payOff(10),
                "Negative.payOff() should return true if the transaction can be processed!");
        Assertions.assertEquals(-40, account.getBalance(), DELTA,
                "Negative.payOff() should subtract the amount from the balance of the account!");
        Assertions.assertEquals("Negative", account.getState(),
                "Negative.payOff() should not change the state if the transaction can be processed!");
    }

    @Test
    public void negativeStateTestPayInterestToNegative() {
        account.payInterest();
        Assertions.assertEquals(-30.9, account.getBalance(), DELTA, "Negative.payInterest() should increase the debts by 3 percent!");
        Assertions.assertEquals("Negative", account.getState(), "Negative.payInterest() should not change the state " +
                "unless the balance falls below negative lineOfCredit!");

        account2.payInterest();
        Assertions.assertEquals(-5.15, account2.getBalance(), DELTA,
                "Negative.payInterest() should increase the debts by 3 percent!");
        Assertions.assertEquals("Negative", account.getState(), "Negative.payInterest() should not change the state " +
                        "unless the balance falls below negative lineOfCredit!");

    }

    @Test
    public void negativeStateTestPayInterestToFrozen() {
        account.payOff(19);
        account.payInterest();
        //balance: -50.47
        Assertions.assertEquals("Frozen", account.getState(), "Negative.payInterest() should change the state to " +
                "Frozen if the balance falls below negative lineOfCredit!");
    }

    @Test
    public void negativeStateTestPrintBalance() {
        var outStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outStream));
        account.printBalance();
        var outString = outStream.toString(StandardCharsets.UTF_8);
        Assertions.assertEquals("Balance is NEGATIVE: -30.0.", outString.trim(), "Negative.printBalance() should print the balance as specified in the task description!");
    }

}
