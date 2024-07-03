import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

 
public class ReceivingStockTest extends StockTestBase {
    private Purchasing purch;

    @Override
    @BeforeEach
    public void setUp() {
        stock = new ReceivingStock(10, 100);
        purch = new Purchasing((ReceivingStock) stock);
        part = Factory.createPart(PartType.SINGLE_COMPONENT, "0", "A Single Component");

        stock.insert(part, 15);
    }

    @Test
    public void testConstructorIllegalArgument() {
        try {
            new ReceivingStock(-1, 1);
            Assertions.fail(
                    "ReceivingStock.ReceivingStock() should throw an IllegalArgumentException if the minStockItems argument is less than 1!");
        } catch (IllegalArgumentException e) {
        }

        try {
            new ReceivingStock(0, -1);
            Assertions.fail(
                    "ReceivingStock.ReceivingStock() should throw an IllegalArgumentException if the maxStockItems argument is less than the minStockItems argument!");
        } catch (IllegalArgumentException e) {
        }

        try {
            new ReceivingStock(5, 4);
            Assertions.fail(
                    "ReceivingStock.ReceivingStock() should throw an IllegalArgumentException if the maxStockItems argument is less than or equal to the minStockItems argument!");
        } catch (IllegalArgumentException e) {
        }

        try {
            new ReceivingStock(4, 4);
            Assertions.fail(
                    "ReceivingStock.ReceivingStock() should throw an IllegalArgumentException if the maxStockItems argument is less than or equal to the minStockItems argument!");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void testObserver() {
        stock.addObserver(purch);
        stock.remove(part, 6);
        Assertions.assertEquals(100, stock.getCount(part),
                "It seems that Stock.addObserver() or Stock.notifyPartCountChanged() are not implemented correctly! The ReceivingStock is not getting refilled correctly.");
    }

    @Test
    public void testGetMinStockItems() {
        Assertions.assertEquals(10, ((ReceivingStock) stock).getMinStockItems(),
                "ReceivingStock.getMinStockItems() should return the correct value!");
    }

    @Test
    public void testGetMaxStockItems() {
        Assertions.assertEquals(100, ((ReceivingStock) stock).getMaxStockItems(),
                "ReceivingStock.getMaxStockItems() should return the correct value!");
    }

    @Test
    public void testReceivingStockInsert() {
        Assertions.assertFalse(((ReceivingStock) stock).insert(part, 10000),
                "ReceivingStock.insert() should return false if the new amount would exceed maxStockItems!");
    }

    @Test
    public void testReceivingStockRemove() {
        Assertions.assertFalse(((ReceivingStock) stock).remove(part, 16), "ReceivingStock.remove() should return false if the new amount would fall below zero!");
    }
}
