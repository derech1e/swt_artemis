import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

 
public class MyMatrixTest {
    MyMatrix<String> filledMatrix, emptyMatrix;
    String a, b;

    @BeforeEach
    public void setUp() {
        filledMatrix = new MyMatrix<String>();
        emptyMatrix = new MyMatrix<String>();
        a = "a";
        b = "b";

        filledMatrix.put(0, 1, a);
        filledMatrix.put(1, 3, b);
        filledMatrix.put(2, 0, a);
    }

    @Test
    public void testGetRowCount() {
        Assertions.assertEquals(3, filledMatrix.getRowCount(),
                "MyMatrix.getRowCount() should return the current number of rows of the matrix!");
        filledMatrix.put(2, 0, "c");
        Assertions.assertEquals(3, filledMatrix.getRowCount(),
                "MyMatrix.getRowCount() should return the current number of rows of the matrix!");
        filledMatrix.put(3, 0, "c");
        Assertions.assertEquals(4, filledMatrix.getRowCount(), "MyMatrix.getRowCount() should return the current number of rows of the matrix!");
        filledMatrix.put(6, 0, "c");
        Assertions.assertEquals(7, filledMatrix.getRowCount(),
                "MyMatrix.getRowCount() should return the current number of rows of the matrix!");

        Assertions.assertEquals(0, emptyMatrix.getRowCount(), "MyMatrix.getRowCount() should return the current number of rows of the matrix!");
    }

    @Test
    public void testGetColumnCount() {
        Assertions.assertEquals(4, filledMatrix.getColumnCount(),
                "MyMatrix.getColumnCount() should return the current number of columns of the matrix!");
        filledMatrix.put(0, 3, "d");
        Assertions.assertEquals(4, filledMatrix.getColumnCount(),
                "MyMatrix.getColumnCount() should return the current number of columns of the matrix!");
        filledMatrix.put(0, 4, "d");
        Assertions.assertEquals(5, filledMatrix.getColumnCount(), "MyMatrix.getColumnCount() should return the " +
                "current number of columns of the matrix!");
        filledMatrix.put(0, 7, "d");
        Assertions.assertEquals(8, filledMatrix.getColumnCount(), "MyMatrix.getColumnCount() should return the current number of columns of the matrix!");

        Assertions.assertEquals(0, emptyMatrix.getColumnCount(), "MyMatrix.getColumnCount() should return the current" +
                " number of columns of the matrix!");
    }

    @Test
    public void testGetObjectCount() {
        Assertions.assertEquals(3, filledMatrix.getObjectCount(), "MyMatrix.getObjectCount() should return the number of objects which are currently stored in the matrix!");
        Assertions.assertEquals(0, emptyMatrix.getObjectCount(),
                "MyMatrix.getObjectCount() should return the number of objects which are currently stored in the matrix!");
    }

    @Test
    public void testGetDistinctObjectCount() {
        Assertions.assertEquals(2, filledMatrix.getDistinctObjectCount(),
                "MyMatrix.getDistinctObjectCount() should return the number of objects which are currently stored in the matrix but without counting the same object more than once!");
        Assertions.assertEquals(0, emptyMatrix.getDistinctObjectCount(), "MyMatrix.getDistinctObjectCount() should return the number of objects which are currently stored in the matrix but without counting the same object more than once!");
    }

    @Test
    public void testIterator() {
        String c = "c", d = "d";
        filledMatrix.put(4, 1, b);
        filledMatrix.put(0, 2, c);
        filledMatrix.put(2, 4, d);
        filledMatrix.put(2, 1, d);
        filledMatrix.put(1, 1, c);
        filledMatrix.put(3, 3, a);

        Iterator<String> iter = filledMatrix.iterator();
        Assertions.assertEquals("MyMatrix.DepthFirstIterator", iter.getClass().getCanonicalName(),
                "MyMatrix.iterator() should return an Iterator of type DepthFirstIterator which should be an inner class of MyMatrix!");
        String[] values = {a, a, c, d, b, c, b, a, d};
        for (int i = 0; i < values.length; i++) {
            Assertions.assertTrue(iter.hasNext(), "DepthFirstIterator.hasNext() should return true if there is a next" +
                    " available element!");
            Assertions.assertEquals(values[i], iter.next(),
                    "DepthFirstIterator.next() should return the correct next element if there is one available!");
        }
        Assertions.assertFalse(iter.hasNext(), "DepthFirstIterator.hasNext() should return false if there is no next element!");
        try {
            iter.next();
            Assertions.fail("DepthFirstIterator.next() should throw a NoSuchElementException if there is no next element!");
        } catch (NoSuchElementException e) {
        }

        try {
            iter.remove();
            Assertions.fail(
                    "DepthFirstIterator.remove() should throw a UnsupportedOperationException upon being called!");
        } catch (UnsupportedOperationException e) {
        }
    }

    @Test
    public void testGetIllegalArgument() {
        for (int i = 3; i < 5; i++) {
            try {
                filledMatrix.get(i, 0);
                Assertions.fail("MyMatrix.get() should throw an IllegalArgumentException if the given position is out of bounds!");
            } catch (IllegalArgumentException e) {
            }
        }
        for (int i = 4; i < 6; i++) {
            try {
                filledMatrix.get(0, i);
                Assertions.fail("MyMatrix.get() should throw an IllegalArgumentException if the given position is out of bounds!");
            } catch (IllegalArgumentException e) {
            }
        }
        try {
            filledMatrix.get(-1, 0);
            Assertions.fail("MyMatrix.get() should throw an IllegalArgumentException if the given row is negative!");
        } catch (IllegalArgumentException ignored) {
        }
        try {
            filledMatrix.get(0, -1);
            Assertions.fail("MyMatrix.get() should throw an IllegalArgumentException if the given column is negative!");
        } catch (IllegalArgumentException ignored) {
        }
    }

    @Test
    public void testGet() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                if ((i == 0 && j == 1) || (i == 2 && j == 0)) {
                    Assertions.assertEquals(a, filledMatrix.get(i, j), "MyMatrix.get() should return the correct object at the given position!");
                } else {
                    if (i == 1 && j == 3) {
                        Assertions.assertEquals(b, filledMatrix.get(i, j), "MyMatrix.get() should return the correct object at the given position!");
                    } else {
                        Assertions.assertNull(filledMatrix.get(i, j),
                                "MyMatrix.get() should return null of there is no object at the given position which " +
                                        "is within the bounds!");
                    }
                }
            }
        }
    }

    @Test
    public void testPut() {
        Assertions.assertEquals(a, filledMatrix.put(2, 0, b), "MyMatrix.put() should return the old object if it is replaced by a new one!");
        Assertions.assertEquals(b, filledMatrix.put(1, 3, a), "MyMatrix.put() should return the old object if it is replaced by a new one!");
        Assertions.assertNull(emptyMatrix.put(0, 0, a), "MyMatrix.put() should return null if no object has to be replaced!");
        Assertions.assertNull(emptyMatrix.put(5, 5, null), "MyMatrix.put() should return null if no object has to be replaced!");
    }

    @Test
    public void testPutIllegalArgument() {
        try {
            filledMatrix.put(-1, 0, "negative row");
            Assertions.fail("MyMatrix.put() should throw an IllegalArgumentException if the given row is negative!");
        } catch (IllegalArgumentException ignored) {
        }
        try {
            filledMatrix.put(0, -1, "negative column");
            Assertions.fail("MyMatrix.put() should throw an IllegalArgumentException if the given column is negative!");
        } catch (IllegalArgumentException ignored) {
        }
    }

    @Test
    public void testContains() {
        Assertions.assertTrue(filledMatrix.contains(a),
                "MyMatrix.contains() should return true if the matrix contains the given object!");
        Assertions.assertTrue(filledMatrix.contains(b), "MyMatrix.contains() should return true if the matrix contains the given object!");
        Assertions.assertFalse(filledMatrix.contains("c"), "MyMatrix.contains() should return false if the matrix does not contain the given object!");
        Assertions.assertFalse(emptyMatrix.contains(a), "MyMatrix.contains() should return false if the matrix does not contain the given object!");
    }
}
