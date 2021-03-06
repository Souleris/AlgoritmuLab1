package sorting;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

public class ArrayDAO extends BaseDAO implements Iterable<Double> {

    public ArrayDAO(String filename) throws FileNotFoundException {
        super(filename);
    }

    public Double get(int index) throws IOException {
        seek(index);
        return randomAccessFile.readDouble();
    }

    @Override
    protected long getPos(int next) {
        return super.getPos(next) + 4;
    }

    public void set(int index, Double node) throws IOException {
        seek(index);
        randomAccessFile.writeDouble(node);
    }

    public boolean isEmpty() throws IOException {
        return size() == 0;
    }

    @Override
    public Iterator<Double> iterator() {
        try {
            return new ArrayDAOIterator();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public class ArrayDAOIterator implements Iterator<Double> {

        private int current;
        private int size;

        public ArrayDAOIterator() throws IOException {
            this.size = size();
            this.current = 0;
        }

        @Override
        public boolean hasNext() {
            return current < size;
        }

        @Override
        public Double next() {
            try {
                return get(current++);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return -1.0;
        }
    }




}
