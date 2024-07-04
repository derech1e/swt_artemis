import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

 
public class ComplexPricingTest {
    @Test
    public void complexPricingTestAbstract() {
        Assertions.assertTrue(Modifier.isAbstract(ComplexPricing.class.getModifiers()),
                "The class ComplexPricing should be abstract!");
    }

    @Test
    public void complexPricingTestOnlyOneConstructor() {
        Constructor<?>[] constructors = ComplexPricing.class.getConstructors();
        Assertions.assertEquals(1, constructors.length, "ComplexPricing should have only one public constructor!");
        Class<?>[] params = constructors[0].getParameterTypes();
        Assertions.assertEquals(1, params.length, "ComplexPricing's constructor should have exactly one parameter!");
        Assertions.assertEquals(ISalePricing.class, params[0], "ComprexPricing's parameter should be of type ISalePricing!");
    }

    @Test
    public void complexPricingTestConstructorNullArgument() {
        try {
            new ComplexPricingImpl(null);
            Assertions.fail("The ComplexPricing constructor should throw a NullPointerException if its argument is null!");
        } catch (NullPointerException expected) {
        }
    }

    @Test
    public void complexPricingTestAddNullArgument() {
        try {
            new ComplexPricingImpl(new SalePricingMock()).add(null);
            Assertions.fail("ComplexPricing.add() should throw a NullPointerException if the argument is null!");
        } catch (NullPointerException e) {
        }
    }

    @Test
    public void complexPricingTestGetPricings() {
        ISalePricing p1 = new PercentageDiscountPricing(10.0); // 9000
        ISalePricing p2 = new PercentageDiscountPricing(5.0); // 9500
        ISalePricing p3 = new AbsoluteDiscountPricing(500, 5000); // 9500
        ISalePricing p4 = new AbsoluteDiscountPricing(3000, 9000); // 9000
        ISalePricing p5 = new AbsoluteDiscountPricing(1900, 8000); // 8100

        ComplexPricing complexPricing = new ComplexPricingImpl(p1);
        complexPricing.add(p2);
        complexPricing.add(p3);
        complexPricing.add(p4);
        complexPricing.add(p5);

        Assertions.assertEquals(complexPricing.getPricings(), Arrays.asList(p1, p2, p3, p4, p5),
                "ComplexPricing.getTotal() should return the correct total price!");
    }

    private static class ComplexPricingImpl extends ComplexPricing {
        public ComplexPricingImpl(ISalePricing pricing) {
            super(pricing);
        }

        @Override
        public long getTotal(Sale sale) {
            return 0;
        }
    }

    private static class SalePricingMock implements ISalePricing {
        @Override
        public long getTotal(Sale sale) {
            return 0;
        }
    }
}
