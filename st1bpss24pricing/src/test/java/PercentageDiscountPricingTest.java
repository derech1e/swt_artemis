import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

 
public class PercentageDiscountPricingTest {
    private Sale sale;
    private ISalePricing myTestPricing;

    @BeforeEach
    public void setUp() {
        myTestPricing = new PercentageDiscountPricing(10.0);
        sale = new Sale(20000, myTestPricing);
    }

    @Test
    public void percentageDiscountingPricingTestConstructorIllegalArgument() {
        try {
            new PercentageDiscountPricing(-Double.MIN_VALUE);
            Assertions.fail(
                    "PercentageDiscountPricing.PercentageDiscountPricing() should throw an IllegalArgumentException if the argument is negative!");
        } catch (IllegalArgumentException e) {
        }

        try {
            new PercentageDiscountPricing(100.000000001);
            Assertions.fail(
                    "PercentageDiscountPricing.PercentageDiscountPricing() should throw an IllegalArgumentException if the argument is greater than 100!");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void percentageDiscountingPricingTestGetTotalNullArgument() {
        try {
            myTestPricing.getTotal(null);
            Assertions.fail("PercentageDiscountPricing.getTotal() should throw a NullPointerEception if the argument is null!");
        } catch (NullPointerException e) {
        }
    }

    @Test
    public void percentageDiscountingPricingTestGetTotal() {
        Assertions.assertEquals(18000, myTestPricing.getTotal(sale), "PercentageDiscountPricing.getTotal() should return the correct total price!");

        myTestPricing = new PercentageDiscountPricing(3.33);
        Assertions.assertEquals(19334, myTestPricing.getTotal(sale), "PercentageDiscountPricing.getTotal() should return the correct total price!");
    }
}
