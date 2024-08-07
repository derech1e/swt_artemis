import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

 
public class StartsWithTest {
    private Predicate<String> startsWithJava;
    private Predicate<String> startsWithUML;
    private Predicate<String> empty;

    @BeforeEach
    public void setUp() {
        startsWithJava = new StartsWith("Java");
        startsWithUML = new StartsWith("UML");
        empty = new StartsWith("");
    }

    @Test
    public void startsWithConstructorRejectsNullPrefix() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new StartsWith(null));
    }

    @Test
    public void startsWithNullValuesNeverSatisfy() {
        String message = "null values should never satisfy the predicate!";
        Assertions.assertFalse(empty.test(null), message);
        Assertions.assertFalse(startsWithJava.test(null), message);
    }

    @Test
    public void startsWithEmptyStringShouldAlwaysMatch() {
        String message = "StartsWith(<empty string>) should be satisfied by any string!";
        Assertions.assertTrue(empty.test(""), message);
        Assertions.assertTrue(empty.test("x"), message);
        Assertions.assertTrue(empty.test("some string"), message);
    }

    @Test
    public void startsWithEmptyStringCannotStartWithNonEmptyString() {
        String message = "StartsWith(<non-empty string>) cannot be satisfied by an empty string!";
        Assertions.assertFalse(startsWithUML.test(""), message);
        Assertions.assertFalse(startsWithJava.test(""), message);
    }

    @Test
    public void startsWithSameStringShouldSatisfy() {
        String message = "StartsWith(<some string>) should always be satisfied by the same string!";
        Assertions.assertTrue(startsWithJava.test("Java"), message);
        Assertions.assertTrue(startsWithUML.test("UML"), message);
    }

    @Test
    public void startsWithComparisonShouldBeCaseSensitive() {
        String message = "StartsWith(<some string>) should compare strings in a case-sensitive manner!";
        Assertions.assertFalse(startsWithJava.test("JAVA"), message);
        Assertions.assertFalse(startsWithUML.test("uml"), message);
    }

    @Test
    public void startsWithCharsAfterPrefixShouldBeIgnored() {
        String message = "StartsWith(<some string>) should be satisfied by all strings that begin with <some string>!";
        Assertions.assertTrue(startsWithJava.test("Java and UML"), message);
        Assertions.assertTrue(startsWithUML.test("UML and Java"), message);
    }

    @Test
    public void startsWithPrefixShouldMatchAtPositionZero() {
        String message =
                "StartsWith(<some string>) should only be satisfied if <some string> appears at the strings's very beginning!";
        Assertions.assertFalse(startsWithJava.test(" Java"), message);
        Assertions.assertFalse(startsWithUML.test(" UML"), message);
    }
}
