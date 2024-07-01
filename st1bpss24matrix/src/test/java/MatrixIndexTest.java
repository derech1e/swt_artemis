import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

 
public class MatrixIndexTest {
    @Test
    public void setterMethodsShouldNotExist() {
        try {
            MatrixIndex.class.getMethod("setRow", int.class);
            Assertions.fail("MatrixIndex.setRow() shouldn't exist!");
        } catch (NoSuchMethodException e) {
        }
        try {
            MatrixIndex.class.getMethod("setColumn", int.class);
            Assertions.fail("MatrixIndex.setColumn() shouldn't exist!");
        } catch (NoSuchMethodException e) {
        }
    }

    @Test
    public void hashCodeAndEqualsShouldBeOverriden() {
        try {
            MatrixIndex.class.getMethod("equals", Object.class);
        } catch (NoSuchMethodException e) {
            Assertions.fail("MatrixIndex.equals() should be overridden!");
        }
        try {
            MatrixIndex.class.getMethod("hashCode");
        } catch (NoSuchMethodException e) {
            Assertions.fail("MatrixIndex.hashCode() should be overridden!");
        }
    }

    @Test
    public void equalIndexesShouldHaveSameHashCode() {
        MatrixIndex index1 = new MatrixIndex(2, 4);
        MatrixIndex index2 = new MatrixIndex(2, 4);
        String message = "MatrixIndex.hashCode() of two indexes should be equal if they have "
                + "the same row and column!";
        Assertions.assertEquals(index1.hashCode(), index2.hashCode(), message);
    }

    @Test
    public void indexesWithSameColumnAndRowShouldBeEqual() {
        MatrixIndex index1 = new MatrixIndex(5, 1);
        MatrixIndex index2 = new MatrixIndex(5, 1);

        String message1 = "Identical MatrixIndex objects should be equal according to equals(…)!";
        Assertions.assertEquals(index1, index1, message1);
        Assertions.assertEquals(index2, index2, message1);

        String message2 = "MatrixIndex objects with equal column and row should be equal according to equals(…)!";
        Assertions.assertEquals(index1, index2, message2);
        Assertions.assertEquals(index2, index1, message2);
    }

    @Test
    public void getterMethodsShouldReturnCorrectValues() {
        MatrixIndex matrixIndex = new MatrixIndex(3, 4);
        Assertions.assertEquals(matrixIndex.getRow(), 3, "MatrixIndex.getRow() should return the right number!");
        Assertions.assertEquals(matrixIndex.getColumn(), 4, "MatrixIndex.getColumn() should return the right number!");
    }
}
