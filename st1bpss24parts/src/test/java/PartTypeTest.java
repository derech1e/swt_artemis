import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

 
public class PartTypeTest {
    public enum ExpPartType {
        COMPONENTS, SINGLE_COMPONENT, RESOURCE;
    }

    @Test
    public void testValues() {
        Assertions.assertEquals(ExpPartType.values().length, PartType.values().length,
                "The enumeration PartType should have the right number of values!");
        for (ExpPartType e : ExpPartType.values()) {
            try {
                Assertions.assertEquals(e.ordinal(), PartType
                .valueOf(e.name()).ordinal(), "PartType." + e.name()
                        + " should be at the correct position within the enumeration PartType!");
            } catch (Exception ex) {
                Assertions.fail("The enumeration PartType should possess the value " + e.name() + "!");
            }
        }
    }
}