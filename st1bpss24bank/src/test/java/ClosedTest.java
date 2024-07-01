import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

 
public class ClosedTest {

    private BankAccount account;
    private static final double DELTA = 0.001;

    @BeforeEach
    public void setUp() {
        account = new BankAccount("12345", 10);
        account.close();
    }

    @Test
    public void closedStateTestInheritance() {
        var frozenClass = Arrays.stream(BankAccount.class.getDeclaredClasses())
                .filter(cls -> cls.getSimpleName().equals("Closed"))
                .findFirst();

        if (frozenClass.isPresent()) {
            Assertions.assertEquals("AccountState", frozenClass.get().getSuperclass().getSimpleName(),
                    "Closed should be a subclass of AccountState!");
        } else {
            Assertions.fail("BankAccount should have an inner class Closed!");
        }
    }

    @Test
    public void closedStateTestToString() {
        Assertions.assertEquals("Closed", account.getState(), "BankAccount.getState() should return \"Closed\" if the state is Closed!");
    }

    @Test
    public void closedStateTestPayIn() {
        Assertions.assertFalse(account.payIn(10), "Closed.payIn() should return false!");
        Assertions.assertEquals(0.0, account.getBalance(), DELTA, "Closed.payIn() should not change the account's " +
        "balance!");
        Assertions.assertEquals("Closed", account.getState(), "Closed.payIn() should not change the account's state!");
    }

    @Test
    public void closedStateTestPayOff() {
        Assertions.assertFalse(account.payOff(10), "Closed.payOff() should return false!");
        Assertions.assertEquals(0.0, account.getBalance(), DELTA, "Closed.payOff() should not change the account's balance!");
        Assertions.assertEquals("Closed", account.getState(), "Closed.payOff() should not change the account's state!");
    }

    @Test
    public void closedStateTestPayInterest() {
        try {
            account.payInterest();
            Assertions.fail("Closed.payInterest() should throw an IllegalStateException!");
        } catch (IllegalStateException ignored) {
        }
        Assertions.assertEquals(0.0, account.getBalance(), DELTA, "Closed.payInterest() should not change the account's balance!");
        Assertions.assertEquals("Closed", account.getState(), "Closed.payInterest() should not change the account's state!");

    }

    @Test
    public void closedStateTestPrintBalance() {
        var outStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outStream));
        account.printBalance();
        var outString = outStream.toString(StandardCharsets.UTF_8);
        Assertions.assertEquals("This account is CLOSED. The balance is 0.", outString.trim(), "Closed.printBalance() should print the balance as specified in the task description!");
    }
}
