import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

 
public class PersonTest {
    private Person person;

    @BeforeEach
    public void setUp() {
        person = new Person("Max");
    }

    @Test
    public void testPersonConstructorNullArgument() {
        try {
            person = new Person(null);
            Assertions.fail("Person.Person() should throw a NullPointerException if the argument is null!");
        } catch (NullPointerException e) {
        }
    }

    @Test
    public void testPersonConstructorIllegalArgument() {
        try {
            person = new Person("");
            Assertions.fail("Person.Person() should throw an IllegalArgumentException if the argument is empty!");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void testPersonGetName() {
        Assertions.assertEquals("Max", person.getName(), "Person.getName() should return the correct value!");
    }

    @Test
    public void testPersonToString() {
        Assertions.assertEquals("Max", person.toString(), "Person.toString() should return the correct value!");
    }
}
