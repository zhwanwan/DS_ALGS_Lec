package zhwanwan.algs;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.TreeMap;

/**
 * @author zhwanwan
 * @create 2019-05-27 10:54 AM
 */
public class ST<Key extends Comparable<Key>, Value> implements Iterable<Key> {

    private TreeMap<Key, Value> st;

    public ST() {
        st = new TreeMap<>();
    }

    private void checkNull(Key key) {
        if (key == null)
            throw new IllegalArgumentException("key cannot be null");
    }


    public Value get(Key key) {
        checkNull(key);
        return st.get(key);
    }

    public void put(Key key, Value val) {
        checkNull(key);
        if (val == null)
            st.remove(key);
        else
            st.put(key, val);
    }

    public void delete(Key key) {
        checkNull(key);
        st.remove(key);
    }

    public boolean contains(Key key) {
        checkNull(key);
        return st.containsKey(key);
    }

    public int size() {
        return st.size();
    }

    public boolean isEmpty() {
        return st.size() == 0;
    }

    public Iterable<Key> keys() {
        return st.keySet();
    }

    @Override
    public Iterator<Key> iterator() {
        return st.keySet().iterator();
    }

    public Key min() {
        if (isEmpty())
            throw new NoSuchElementException("calls min() with empty symbol table");
        return st.firstKey();
    }

    public Key max() {
        if (isEmpty())
            throw new NoSuchElementException("calls max(0 with empty symbol table");
        return st.lastKey();
    }

    public Key ceiling(Key key) {
        checkNull(key);
        Key k = st.ceilingKey(key);
        if (k == null)
            throw new NoSuchElementException("all keys are less than " + key);
        return k;
    }

    public Key floor(Key key) {
        checkNull(key);
        Key k = st.floorKey(key);
        if (k == null)
            throw new NoSuchElementException("all keys are greater than " + key);
        return k;
    }

    public static void main(String[] args) {
        ST<String, Integer> st = new ST<>();
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }
        for (String s : st.keys()) {
            StdOut.println(s + " " + st.get(s));
        }
        /**
         * Java
         * Hello
         * World
         * Oracle
         * MySQL
         * RabbitMQ
         * Redis
         * SpringBoot
         * SpringCloud
         * ^D---------
         * Hello 1
         * Java 0
         * MySQL 4
         * Oracle 3
         * RabbitMQ 5
         * Redis 6
         * SpringBoot 7
         * SpringCloud 8
         * World 2
         */
    }

}
