import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PurchasingTest {
    private Purchasing purch;
    private Part part;
    private ReceivingStock rStock;

    @BeforeEach
    public void setUp() {
        rStock = new ReceivingStock(10, 100);
        purch = new Purchasing(rStock);
        part = new Components("id", "TestComponent");
    }

    @Test
    public void purchasingTestConstructorNullArgument() {
        try {
            new Purchasing(null);
            Assertions.fail("Purchasing.Purchasing() should throw a NullPointerException if the argument is null!");
        } catch (NullPointerException e) {
        }
    }

    @Test
    public void purchasingTestBuyIllegalArgument() {
        try {
            purch.buy(null, 10);
            Assertions.fail("Purchasing.buy() should throw a NullPointerException if the part argument is null!");
        } catch (NullPointerException e) {
        }
        try {
            purch.buy(part, 0);
            Assertions.fail(
                    "Purchasing.buy() should throw an IllegalArgumentException if the count argument less than one!");
        } catch (IllegalArgumentException e) {
        }
        try {
            purch.buy(part, -1);
            Assertions.fail("Purchasing.buy() should throw an IllegalArgumentException if the count argument is less than one!");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void purchasingTestBuy() {
        purch.buy(part, 25);
        Assertions.assertEquals(25, purch.getStock().getCount(part),
                "Purchasing.buy() should add the specified number of parts to the stock even if the stock did not contain the part!");
        part = new SingleComponent("S5", "a single component");
        rStock.insert(part, 10);
        purch.buy(part, 13);
        Assertions.assertEquals(23, purch.getStock()
                .getCount(part), "Purchasing.buy() should add the specified number of parts to the stock!");
    }

    @Test
    public void purchasingTestGetStock() {
        Assertions.assertEquals(rStock, purch.getStock(), "Purchasing.getStock() should return the correct object!");
    }

    @Test
    public void onPartCountChangedTest() {
        rStock.addObserver(purch);
        rStock.insert(part, 25);
        rStock.remove(part, 16);
        Assertions.assertEquals(100, rStock.getCount(part), "Purchasing.onPartCountChanged() should restock if the amount is lower than minStockitems!");
    }
}
