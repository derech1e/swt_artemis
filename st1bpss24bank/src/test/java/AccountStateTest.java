import java.lang.reflect.Modifier;
import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

 
public class AccountStateTest {

    @Test
    public void testAccountStateAbstract() {
        var stateClass = Arrays.stream(BankAccount.class.getDeclaredClasses())
                .filter(cls -> cls.getSimpleName().equals("AccountState"))
                .findFirst();

        if (stateClass.isPresent()) {
            Assertions.assertTrue(Modifier.isAbstract(stateClass.get().getModifiers()),
                    "Inner class AccountState should be abstract!");
        } else {
            Assertions.fail("BankAccount should have inner class AccountState!");
        }
    }
}
