import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

 
public class HasLengthTest {
    @Test
    public void hasLengthConstructorRejectsNegativeLength() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new HasLength(-1));
    }

    @Test
    public void hasLengthNullValuesNeverSatisfy() {
        String message = "null values should never satisfy the predicate!";
        Assertions.assertFalse(new HasLength(0).test(null), message);
        Assertions.assertFalse(new HasLength(9).test(null), message);
    }

    @Test
    public void hasLengthMatchEmptyStrings() {
        Predicate<String> lengthOfZero = new HasLength(0);
        String message = "HasLength.test(…) should only match strings of the given length (0)!";
        Assertions.assertTrue(lengthOfZero.test(""), message);
        Assertions.assertTrue(lengthOfZero.test(new String("")), message);
        Assertions.assertFalse(lengthOfZero.test("x"), message);
    }

    @Test
    public void hasLengthMatchNonEmptyStrings() {
        Predicate<String> lengthOfFive = new HasLength(5);
        String message = "HasLength.test(…) should only match strings of the given length (5)!";
        Assertions.assertTrue(lengthOfFive.test("12345"), message);
        Assertions.assertFalse(lengthOfFive.test("1234"), message);
        Assertions.assertFalse(lengthOfFive.test("123456"), message);
    }
}
