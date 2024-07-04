import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

 
public class AbsoluteDiscountPricingTest {
    private Sale sale;
    private ISalePricing myTestPricing;

    @BeforeEach
    public void setUp() {
        myTestPricing = new AbsoluteDiscountPricing(100, 1000);
        sale = new Sale(20000, myTestPricing);
    }

    @Test
    public void absoluteDiscountPricingTestConstructorIllegalArgument() {
        try {
            new AbsoluteDiscountPricing(-1, 0);
            Assertions.fail(
                    "AbsoluteDiscountPricing.AbsoluteDiscountPricing() should throw an IllegalArgumentException if the discount argument is negative!");
        } catch (IllegalArgumentException e) {
        }

        try {
            new AbsoluteDiscountPricing(0, -1);
            Assertions.fail(
                    "AbsoluteDiscountPricing.AbsoluteDiscountPricing() should throw an IllegalArgumentException if the threshold argument is negative!");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void absoluteDiscountPricingTestGetTotalNullArgument() {
        try {
            myTestPricing.getTotal(null);
            Assertions.fail("AbsoluteDiscountPricing.getTotal() should throw a NullPointerEception if the argument is null!");
        } catch (NullPointerException e) {
        }
    }

    @Test
    public void absoluteDiscountPricingTestGetTotal() {
        Assertions.assertEquals(19900, myTestPricing.getTotal(sale), "AbsoluteDiscountPricing.getTotal() should return the correct total price!");

        myTestPricing = new AbsoluteDiscountPricing(2000, 10000);
        Assertions.assertEquals(18000, myTestPricing.getTotal(sale), "AbsoluteDiscountPricing.getTotal() should return the correct total price!");
    }

    @Test
    public void absoluteDiscountPricingTestGetTotalWhenCatalogPriceIsBelowThreshold() {
        myTestPricing = new AbsoluteDiscountPricing(2000, 25000);
        Assertions.assertEquals(20000, myTestPricing.getTotal(sale), "AbsoluteDiscountPricing.getTotal() should take care of a given threshold!");
    }

    @Test
    public void absoluteDiscountPricingTestGetTotalWhenCatalogPriceIsAboveThreshold() {
        myTestPricing = new AbsoluteDiscountPricing(2000, 19000);
        Assertions.assertEquals(19000, myTestPricing.getTotal(sale), "AbsoluteDiscountPricing.getTotal() should take care of a given threshold!");
    }
}
