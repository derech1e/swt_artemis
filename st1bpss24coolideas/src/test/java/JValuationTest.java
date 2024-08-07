import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

 
public class JValuationTest extends JContentTestBase {
    private JValuation jValuation;

    @Override
    @BeforeEach
    public void setUp() {
        jValuation = new JValuation("title", "description");
        jContent = jValuation;
    }

    @Test
    public void jValuationTestConstructorNullArgument() {
        try {
            new JValuation(null, "description");
            Assertions.fail("JValuation.JValuation() should throw a NullPointerException if the title argument is null!");
        } catch (NullPointerException e) {
        }

        try {
            new JValuation("title", null);
            Assertions.fail(
                    "JValuation.JValuation() should throw a NullPointerException if the description argument is null!");
        } catch (NullPointerException e) {
        }

        try {
            new JValuation(null, null);
            Assertions.fail(
                    "JValuation.JValuation() should throw a NullPointerException if the title and the description argument are null!");
        } catch (NullPointerException e) {
        }
    }

    @Test
    public void jValuationTestConstructorInvalidArgument() {
        try {
            new JValuation("", "description");
            Assertions.fail("JValuation.JValuation() should throw an IllegalArgumentException if the title argument is empty!");
        } catch (IllegalArgumentException e) {
        }

        try {
            new JValuation("title", "");
            Assertions.fail("JValuation.JValuation() should throw an IllegalArgumentException if the description argument is empty!");
        } catch (IllegalArgumentException e) {
        }

        try {
            new JValuation("", "");
            Assertions.fail("JValuation.JValuation() should throw an IllegalArgumentException if the title and the description argument are empty!");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void jValuationTestToString() {
        Assertions.assertEquals("Valuation: title\ndescription", jValuation.toString(), "JValuation.toString() should return the correct String!");
    }
}
