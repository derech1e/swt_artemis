import java.util.*;

public class MatrixIndex {
    private int row;
    private int column;

    public MatrixIndex(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return this.row;
    }

    public int getColumn() {
        return this.column;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof MatrixIndex))
            throw new ClassCastException();

        MatrixIndex compareObj = (MatrixIndex) obj;
        return compareObj.getRow() == this.getRow() && compareObj.getColumn() == this.getColumn();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getRow(), this.getColumn());
    }
}