import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

 
public class StockTest extends StockTestBase {
    @Test
    public void testGetCountNullArgument() {
        try {
            stock.getCount(null);
            Assertions.fail("Stock.get() should throw a NullPointerException if the argument is null!");
        } catch (NullPointerException e) {
        }
    }

    @Test
    public void testGetCount() {
        Assertions.assertEquals(-1, stock.getCount(new Resource("1", "resComp")),
                "Stock.getCount() should return -1 if the stock does not contain the part!");
        Assertions.assertEquals(15, stock.getCount(part),
                "Stock.getCount() should return the correct number of parts within the stock!");
    }

    @Test
    public void testInsertIllegalArgument() {
        try {
            stock.insert(null, 15);
            Assertions.fail("Stock.insert() should throw a NullPointerException if the part argument is null!");
        } catch (NullPointerException e) {
        }

        try {
            stock.insert(part, 0);
            Assertions.fail("Stock.insert() should throw an IllegalArgumentException if the count argument is less than one!");
        } catch (IllegalArgumentException e) {
        }

        try {
            stock.insert(part, -1);
            Assertions.fail("Stock.insert() should throw an IllegalArgumentException if the count argument is less than one!");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void testInsert() {
        SingleComponent sc = new SingleComponent("3", "name");
        Assertions.assertTrue(stock.insert(sc, 15),
                "Stock.insert() should return true if the part was successfully inserted!");
        Assertions.assertEquals(15, stock.getCount(sc),
                "Stock.insert() should add new parts to the stock if it did not contain these parts previously!");
        stock.insert(sc, 15);
        Assertions.assertEquals(30, stock.getCount(sc), "Stock.insert() should insert the correct number of parts into the stock!");
    }

    @Test
    public void testRemoveNullArgument() {
        try {
            stock.remove(null, 10);
            Assertions.fail("Stock.remove() should throw a NullPointerException if the part argument is null!");
        } catch (NullPointerException e) {
        }
    }

    @Test
    public void testRemoveIllegalArgument() {
        try {
            stock.remove(part, 0);
            Assertions.fail("Stock.remove() should throw an IllegalArgumentException if the count argument is less than 1!");
        } catch (IllegalArgumentException e) {
        }

        try {
            stock.remove(part, -1);
            Assertions.fail("Stock.remove() should throw an IllegalArgumentException if the count argument is less " +
                    "than 1!");
        } catch (IllegalArgumentException e) {
        }

        Assertions.assertEquals(15, stock.getCount(part), "Stock.remove() should not change part count if the count " +
                "argument is invalid!");
    }

    @Test
    public void testRemove() {
        Assertions.assertFalse(stock.remove(new Resource("1", "resComp"), 1), "Stock.remove() should return false if the part is not in the stock!");
        Assertions.assertFalse(stock.remove(part, 20), "Stock.remove() should return false if the count argument is greater than the number of parts within the stock!");
        Assertions.assertEquals(15, stock.getCount(part),
                "Stock.remove() should not remove any parts from the stock if the count argument is invalid!");
        Assertions.assertTrue(stock.remove(part, 4), "Stock.remove() should return true if parts were removed successfully!");
        Assertions.assertEquals(11, stock.getCount(part), "Stock.remove() should actually remove parts from the stock!");
    }

    @Test
    public void testAddObserverNullArgument() {
        try {
            stock.addObserver(null);
            Assertions.fail("Stock.addObserver() should throw a NullPointerException if the argument is null!");
        } catch (NullPointerException e) {
        }
    }
}
