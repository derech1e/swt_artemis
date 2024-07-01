import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

 
public class FrozenTest {

    private BankAccount account;
    private static final double DELTA = 0.001;

    @BeforeEach
    public void setUp() {
        account = new BankAccount("123", 50);
        account.payOff(50);
    }

    @Test
    public void frozenStateTestInheritance() {
        var frozenClass = Arrays.stream(BankAccount.class.getDeclaredClasses())
                .filter(cls -> cls.getSimpleName().equals("Frozen"))
                .findFirst();

        if (frozenClass.isPresent()) {
            Assertions.assertEquals("AccountState", frozenClass.get().getSuperclass().getSimpleName(),
                    "Frozen should be a subclass of AccountState!");
        } else {
            Assertions.fail("BankAccount should have inner class Frozen!");
        }
    }

    @Test
    public void frozenStateTestGetState() {
        Assertions.assertEquals("Frozen", account.getState(),
                "BankAccount.getState() should return \"Frozen\", if the state is Frozen!");
    }

    @Test
    public void frozenStateTestPayInToNegative() {
        Assertions.assertTrue(account.payIn(30), "Frozen.payIn() should return true!");
        Assertions.assertEquals(-20, account.getBalance(), DELTA, "Frozen.payIn() should add the sum passed as argument " +
                "to the balance of the account!");
        Assertions.assertEquals("Negative", account.getState(), "Frozen.payIn() should change the state to Negative, " +
                        "if the sum passed as argument is lower than the account's lineOfCredit!");
    }

    @Test
    public void frozenStateTestPayInToPositive() {
        Assertions.assertTrue(account.payIn(50), "Frozen.payIn() should return true!");
        Assertions.assertEquals(0, account.getBalance(), DELTA, "Frozen.payIn() should add the sum passed as argument " +
                "to the balance of the account!");
        Assertions.assertEquals("Positive", account.getState(), "Frozen.payIn() should change the state to Positive, " +
                        "if the sum passed as argument is equal to the account's lineOfCredit!");

        account.payOff(50);
        Assertions.assertTrue(account.payIn(60), "Frozen.payIn() should return true!");
        Assertions.assertEquals(10, account.getBalance(), DELTA, "Frozen.payIn() should add the sum passed as argument " +
                "to the balance of the account!");
        Assertions.assertEquals("Positive", account.getState(), "Frozen.payIn() should change the state to Positive, " +
                        "if the sum passed as argument is greater than the account's lineOfCredit!");
    }

    @Test
    public void frozenStateTestPayOff() {
        Assertions.assertFalse(account.payOff(10), "Frozen.payOff() should throw return false!");
        Assertions.assertEquals(-50, account.getBalance(), DELTA,
                "Frozen.payOff() should not change the account's balance!");
        Assertions.assertEquals("Frozen", account.getState(), "Frozen.payOff() should not change the account's state!");
    }

    @Test
    public void frozenStateTestPayInterest() {
        account.payInterest();
        Assertions.assertEquals(-52.5, account.getBalance(), DELTA, "Frozen.payInterest() should increase the debts by 5 percent!");

        account = new BankAccount("1337", 10);
        account.payOff(10);
        account.payInterest();
        Assertions.assertEquals(-10.5, account.getBalance(), DELTA, "Frozen.payInterest() should increase the debts by 5 percent!");

    }

    @Test
    public void frozenStateTestPrintBalance() {
        var outStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outStream));
        account.printBalance();
        var outString = outStream.toString(StandardCharsets.UTF_8);
        Assertions.assertEquals("Balance is NEGATIVE: -50.0. You need to pay in money.", outString.trim(), "Frozen.printBalance() should print the balance as specified in the task description!");
    }
}
