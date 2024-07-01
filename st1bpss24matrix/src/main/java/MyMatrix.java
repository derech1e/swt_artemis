import java.util.*;

public class MyMatrix<T> implements Matrix<T> {

    private Map<MatrixIndex, T> matrixEntries = new HashMap<>();

    public int getRowCount() {
        int highestRow = 0;
        for (Map.Entry<MatrixIndex, T> entries : matrixEntries.entrySet()) {
            int currentRow = entries.getKey().getRow() + 1;
            if (currentRow >= highestRow) {
                highestRow = currentRow;
            }
        }
        return highestRow;
    }

    public int getColumnCount() {
        int highestColumn = 0;
        for (Map.Entry<MatrixIndex, T> entries : matrixEntries.entrySet()) {
            int currentColumn = entries.getKey().getColumn() + 1;
            if (currentColumn >= highestColumn) {
                highestColumn = currentColumn;
            }
        }
        return highestColumn;
    }

    public int getObjectCount() {
        int objectCount = 0;
        for (Map.Entry<MatrixIndex, T> entries : matrixEntries.entrySet()) {
            if (entries.getValue() != null) {
                objectCount += 1;
            }
        }
        return objectCount;
    }

    public int getDistinctObjectCount() {
        List<T> objects = new ArrayList<>();
        for (Map.Entry<MatrixIndex, T> entries : matrixEntries.entrySet()) {
            if (entries.getValue() != null && !objects.contains(entries.getValue())) {
                objects.add(entries.getValue());
            }
        }
        return objects.size();
    }

    public Iterator<T> iterator() {
        return new DepthFirstIterator();
    }

    public T get(int row, int column) {
        if (row < 0 || column < 0 || row >= getRowCount() || column >= getColumnCount())
            throw new IllegalArgumentException();
        return matrixEntries.get(new MatrixIndex(row, column));
    }

    public T put(int row, int column, T value) {
        if (row < 0 || column < 0) throw new IllegalArgumentException();
        T previousEntry = matrixEntries.get(new MatrixIndex(row, column));
        matrixEntries.put(new MatrixIndex(row, column), value);
        return previousEntry;
    }

    public boolean contains(T value) {
        return matrixEntries.containsValue(value);
    }

    class DepthFirstIterator implements Iterator<T> {
        private int currentRow = 0;
        private int currentColumn = 0;

        public DepthFirstIterator() {
        }

        public T next() {
            if (!hasNext()) throw new NoSuchElementException();

            while (currentColumn < getColumnCount()) {
                T result = get(currentRow, currentColumn);

                if (currentRow + 1 < getColumnCount()) {
                    currentRow++;
                } else {
                    currentRow = 0;
                    currentColumn++;
                }
                if (result != null) return result;
            }
            return null;
        }

        public boolean hasNext() {
            int tempRow = currentRow;
            int tempColumn = currentColumn;

            while (tempColumn < getColumnCount()) {
                while (tempRow < getRowCount()) {
                    if (get(tempRow, tempColumn) != null) {
                        return true;
                    }
                    tempRow++;
                }
                tempRow = 0;
                tempColumn++;
            }
            return false;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

}