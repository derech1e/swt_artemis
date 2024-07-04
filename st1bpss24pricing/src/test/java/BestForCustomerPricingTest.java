import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

 
public class BestForCustomerPricingTest {
    private Sale testSale;
    private ISalePricing p1, p2, p3, p4, p5;

    @BeforeEach
    public void setUp() {
        p1 = new PercentageDiscountPricing(10.0); // 9000
        p2 = new PercentageDiscountPricing(5.0); // 9500
        p3 = new AbsoluteDiscountPricing(500, 5000); // 9500
        p4 = new AbsoluteDiscountPricing(3000, 9000); // 9000
        p5 = new AbsoluteDiscountPricing(1900, 8000); // 8100

        testSale = new Sale(10000, p1);
    }

    @Test
    public void bestForCustomerPricingTestConstructorNullArgument() {
        try {
            new BestForCustomerPricing(null);
            Assertions.fail(
                    "BestForCustomerPricing.BestForCustomerPricing() should throw a NullPointerException if the argument is null!");
        } catch (NullPointerException e) {
        }
    }

    @Test
    public void bestForCustomerPricingTestAddNullArgument() {
        try {
            new BestForCustomerPricing(p1).add(null);
            Assertions.fail("BestForCustomerPricing.add() should throw a NullPointerException if the argument is null!");
        } catch(NullPointerException e) {
        }
    }

    @Test
    public void bestForCustomerPricingTestAdd() {
        List<ISalePricing> solution = new LinkedList<ISalePricing>();
        solution.add(p1);
        ComplexPricing cPricing = new BestForCustomerPricing(p1);
        Assertions.assertEquals(solution, cPricing.getPricings(),
                "BestForCustomerPricing.BestForCustomerPricing() should add the argument to the pricings!");
        cPricing.add(p2);
        solution.add(p2);
        Assertions.assertEquals(solution, cPricing.getPricings(), "BestForCustomerPricing.add() should add the argument to the pricings!");
        cPricing.add(p3);
        solution.add(p3);
        Assertions.assertEquals(solution, cPricing.getPricings(), "BestForCustomerPricing.add() should add the argument to the pricings!");
    }

    @Test
    public void bestForCustomerPricingTestGetTotalNullArgument() {
        try {
            BestForCustomerPricing p = new BestForCustomerPricing(p1);
            p.getTotal(null);
            Assertions.fail("BestForCustomerPricing.getTotal() should throw a NullPointerEception if the argument is null!");
        } catch (NullPointerException e) {
        }
    }

    @Test
    public void bestForCustomerPricingTestGetTotal() {
        BestForCustomerPricing p = new BestForCustomerPricing(p1);
        p.add(p2);
        p.add(p3);
        p.add(p4);
        p.add(p5);
        testSale.setPricing(p);
        Assertions.assertEquals(8100, testSale.getTotal(), "BestForCustomerPricing.getTotal() should return the correct total price!");
    }
}