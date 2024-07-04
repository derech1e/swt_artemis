import java.lang.reflect.Modifier;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

 
public class SaleTest {
    private Sale sale1, sale2;
    private PercentageDiscountPricing pricing1 = new PercentageDiscountPricing(10.0);
    private AbsoluteDiscountPricing pricing2 = new AbsoluteDiscountPricing(500, 1000);
    private static long preDiscountTotal1 = 10000;
    private static long preDiscountTotal2 = 2000;

    @BeforeEach
    public void setUp() {
        sale1 = new Sale(preDiscountTotal1, pricing1);
        sale2 = new Sale(preDiscountTotal2, pricing2);
    }

    @Test
    public void saleTestConstructorIllegalArgument() {
        try {
            new Sale(-1, pricing1);
            Assertions.fail(
                    "Sale.Sale() should throw an IllegalArgumentException if the preDiscountTotal argument is negative!");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void saleTestConstructorNullArgument() {
        try {
            new Sale(preDiscountTotal1, null);
            Assertions.fail("Sale.Sale() should throw a NullPointerException if the pricing argument is null!");
        } catch (NullPointerException e) {
        }
    }

    @Test
    public void saleTestGetPreDiscountTotal() {
        Assertions.assertEquals(preDiscountTotal1, sale1.getPreDiscountTotal(),
                "Sale.getPreDiscountTotal() should return the correct value!");
        Assertions.assertEquals(preDiscountTotal2, sale2.getPreDiscountTotal(), "Sale.getPreDiscountTotal() should return the correct value!");
    }

    @Test
    public void saleTestSetPricingNullArgument() {
        try {
            sale1.setPricing(null);
            Assertions.fail("Sale.setPricing() should throw a NullPointerException if the argument is null!");
        } catch (NullPointerException e) {
        }
    }

    @Test
    public void saleTestSetPricing() {
        sale2.setPricing(pricing1);
        Assertions.assertEquals(1800, sale2.getTotal(), "Sale.setPricing() should set the pricing correctly!");
    }

    @Test
    public void saleTestGetTotal() {
        Assertions.assertEquals(9000, sale1.getTotal(), "Sale.getTotal() should return the correct total cost for a " +
                "PercentageDiscountPricing!");
        Assertions.assertEquals(1500, sale2.getTotal(), "Sale.getTotal() should return the correct total cost for an AbsoluteDiscountPricing!");
    }

    @Test
    public void saleTestCreatePricingStatic() throws NoSuchMethodException {
        Assertions.assertTrue(Modifier.isStatic(Sale.class.getMethod("createPricing", DiscountType.class, double.class, long.class,
                long.class).getModifiers()), "Sale.createPricing() should be static!");
    }

    @Test
    public void saleTestCreatePricingNullArgument() {
        try {
            Sale.createPricing(null, 0, 0, 0);
            Assertions.fail(
                    "Sale.createPricing() should throw a NullPointerException if the discountType argument is null!");
        } catch (NullPointerException e) {
        }
    }

    @Test
    public void saleTestCreatePricing() {
        ISalePricing e1 = Sale.createPricing(DiscountType.PERCENTAGEDISCOUNT, 10.0, 0, 0);
        Assertions.assertSame(PercentageDiscountPricing.class, e1.getClass(), "Sale.createPricing() should return the kind of object specified by the discountType argument!");
        e1 = Sale.createPricing(DiscountType.ABSOLUTEDISCOUNT, 0.0, 999, 1000);
        Assertions.assertSame(AbsoluteDiscountPricing.class, e1.getClass(), "Sale.createPricing() should return the kind of object specified by the discountType argument!");
    }
}
