import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

 
public class EndsWithTest {
    private Predicate<String> endsWithJava;
    private Predicate<String> endsWithUML;
    private Predicate<String> empty;

    @BeforeEach
    public void setUp() {
        endsWithJava = new EndsWith("Java");
        endsWithUML = new EndsWith("UML");
        empty = new EndsWith("");
    }

    @Test
    public void endsWithConstructorRejectsNullSuffix() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new EndsWith(null));
    }

    @Test
    public void endsWithNullValuesNeverSatisfy() {
        String message = "null values should never satisfy the predicate!";
        Assertions.assertFalse(empty.test(null), message);
        Assertions.assertFalse(endsWithJava.test(null), message);
    }

    @Test
    public void endsWithEndsWithEmptyStringShouldAlwaysMatch() {
        String message = "EndsWith(<empty string>) should be satisfied by any string!";
        Assertions.assertTrue(empty.test(""), message);
        Assertions.assertTrue(empty.test("x"), message);
        Assertions.assertTrue(empty.test("some string"), message);
    }

    @Test
    public void endsWithEmptyStringCannotEndWithNonEmptyString() {
        String message = "EndsWith(<non-empty string>) cannot be satisfied by an empty string!";
        Assertions.assertFalse(endsWithUML.test(""), message);
        Assertions.assertFalse(endsWithJava.test(""), message);
    }

    @Test
    public void endsWithSameStringShouldSatisfy() {
        String message = "EndsWith(<some string>) should always be satisfied by the same string!";
        Assertions.assertTrue(endsWithJava.test("Java"), message);
        Assertions.assertTrue(endsWithUML.test("UML"), message);
    }

    @Test
    public void endsWithComparisonShouldBeCaseSensitive() {
        String message = "EndsWith(<some string>) should compare strings in a case-sensitive manner!";
        Assertions.assertFalse(endsWithJava.test("JAVA"), message);
        Assertions.assertFalse(endsWithUML.test("uml"), message);
    }

    @Test
    public void endsWithCharsBeforeSuffixShouldBeIgnored() {
        String message = "EndsWith(<some string>) should be satisfied by all strings that end with <some string>!";
        Assertions.assertTrue(endsWithJava.test("UML and Java"), message);
        Assertions.assertTrue(endsWithUML.test("Java and UML"), message);
    }

    @Test
    public void endsWithSuffixShouldMatchAtLastPosition() {
        String message =
                "EndsWith(<some string>) should only be satisfied if <some string> appears at the strings's very end!";
        Assertions.assertFalse(endsWithJava.test("Java "), message);
        Assertions.assertFalse(endsWithUML.test("UML "), message);
    }
}
