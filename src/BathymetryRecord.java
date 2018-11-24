package w2parser;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class BathymetryRecord<T> implements Collection<T> {
    private List<T> Data;
    private String identifier;

    public BathymetryRecord(String identifier) {
        Data = new ArrayList<>();
        this.identifier = identifier;
    }

    BathymetryRecord(List<T> Data, String identifier) {
        this.Data = Data;
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public List<T> getData() {
        return Data;
    }

    public void setData(List<T> data) {
        Data = data;
    }

    /**
     * Determine the number of significant digits
     * (to the left of the decimal point, if it is a
     * floating point number).
     */
    int getCharacteristic() {
        int characteristic = 0;
        for (T value : Data) {
            int c = (int) (Math.floor(Math.log10((Double) value)) + 1);
            if (c > characteristic)
                characteristic = c;
        }
        return characteristic;
    }

    @Override
    public int size() {
        return Data.size();
    }

    @Override
    public boolean isEmpty() {
        return Data.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return Data.contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        return Data.iterator();
    }

    @Override
    public Object[] toArray() {
        return Data.toArray();
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return Data.toArray(a);
    }

    @Override
    public boolean add(T t) {
        return Data.add(t);
    }

    @Override
    public boolean remove(Object o) {
        return Data.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return Data.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return Data.addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return Data.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return Data.retainAll(c);
    }

    @Override
    public void clear() {
        Data.clear();
    }

    public void set(int index, T value) {
        Data.set(index, value);
    }

    public T get(int index) {
        return Data.get(index);
    }
}
