import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

 
public class BestForStorePricingTest {
    private Sale sale;
    private ISalePricing p1, p2, p3, p4, p5;

    @BeforeEach
    public void setUp() {
        p1 = new PercentageDiscountPricing(10.0); // 9000
        p2 = new PercentageDiscountPricing(5.0); // 9500
        p3 = new AbsoluteDiscountPricing(500, 5000); // 9500
        p4 = new AbsoluteDiscountPricing(3000, 9000); // 9000
        p5 = new AbsoluteDiscountPricing(1900, 8000); // 8100

        sale = new Sale(10000, p1);
    }

    @Test
    public void bestForStorePricingTestConstructorNullArgument() {
        try {
            new BestForStorePricing(null);
            Assertions.fail(
                    "BestForStorePricing.BestForStorePricing() should throw a NullPointerException if the argument is null!");
        } catch (NullPointerException e) {
        }
    }

    @Test
    public void bestForStorePricingTestAddNullArgument() {
        try {
            new BestForStorePricing(p1).add(null);
            Assertions.fail("BestForStorePricing.add() should throw a NullPointerException if the argument is null!");
        } catch(NullPointerException e) {
        }
    }

    @Test
    public void bestForStorePricingTestAdd() {
        List<ISalePricing> solution = new LinkedList<ISalePricing>();
        solution.add(p1);
        ComplexPricing cPricing = new BestForStorePricing(p1);
        Assertions.assertEquals(solution, cPricing.getPricings(),
                "BestForStorePricing.BestForStorePricing() should add the argument to the pricings!");
        cPricing.add(p2);
        solution.add(p2);
        Assertions.assertEquals(solution, cPricing.getPricings(), "BestForStorePricing.add() should add the argument to the pricings!");
        cPricing.add(p3);
        solution.add(p3);
        Assertions.assertEquals(solution, cPricing.getPricings(), "BestForStorePricing.add() should add the argument to the pricings!");
    }

    @Test
    public void bestForStorePricingTestGetTotalNullArgument() {
        try {
            BestForStorePricing p = new BestForStorePricing(p1);
            p.getTotal(null);
            Assertions.fail("BestForStorePricing.getTotal() should throw a NullPointerEception if the argument is null!");
        } catch (NullPointerException e) {
        }
    }

    @Test
    public void bestForStorePricingTestGetTotal() {
        BestForStorePricing p = new BestForStorePricing(p1);
        p.add(p2);
        p.add(p3);
        p.add(p4);
        p.add(p5);
        sale.setPricing(p);
        Assertions.assertEquals(9500, sale.getTotal(), "BestForStorePricing.getTotal() should return the correct total price!");
    }
}