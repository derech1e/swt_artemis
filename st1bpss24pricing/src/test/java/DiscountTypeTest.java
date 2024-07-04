import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

 
public class DiscountTypeTest {
    public enum ExpDiscountType {
        PERCENTAGEDISCOUNT, ABSOLUTEDISCOUNT;
    }

    @Test
    public void testValues() {
        Assertions.assertEquals(ExpDiscountType.values().length, DiscountType.values().length,
                "The enumeration DiscountType should have the right number of values!");
        for (ExpDiscountType e : ExpDiscountType.values()) {
            try {
                Assertions.assertEquals(e.ordinal(), DiscountType.valueOf(e.name()).ordinal(), "DiscountType." + e.name()
                        + " should be at the correct position within the enumeration DiscountType!");
            } catch (Exception ex) {
                Assertions.fail("The enumeration DiscountType should possess the value " + e.name() + "!");
            }
        }
    }
}