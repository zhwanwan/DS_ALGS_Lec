package zhwanwan.algs;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.TreeSet;

/**
 * @author zhwanwan
 * @create 2019-06-14 3:46 PM
 */
public class MySET<Key extends Comparable<Key>> implements Iterable<Key> {

    private TreeSet<Key> set;

    public MySET() {
        set = new TreeSet<>();
    }

    public MySET(MySET<Key> x) {
        set = new TreeSet<>(x.set);
    }

    public void add(Key key) {
        if (key == null) throw new IllegalArgumentException("called add() with a null key");
        set.add(key);
    }

    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("called contains() with a null key");
        return set.contains(key);
    }

    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("called delete() with a null key");
        set.remove(key);
    }

    public int size() {
        return set.size();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public Key max() {
        if (isEmpty()) throw new NoSuchElementException("called max() with empty set");
        return set.last();
    }

    public Key min() {
        if (isEmpty()) throw new NoSuchElementException("called min() with empty set");
        return set.first();
    }

    /**
     * Returns the smallest key in this set greater than or equal to key.
     *
     * @param key
     * @return
     */
    public Key ceiling(Key key) {
        if (key == null) throw new IllegalArgumentException("called ceiling() with a null key");
        Key k = set.ceiling(key);
        if (k == null) throw new NoSuchElementException("all keys are less than " + key);
        return k;
    }

    /**
     * Returns the largest key in this set less than or equal to key.
     *
     * @param key
     * @return
     */
    public Key floor(Key key) {
        if (key == null) throw new IllegalArgumentException("called floor() with a null key");
        Key k = set.floor(key);
        if (k == null) throw new NoSuchElementException("all keys are greater than " + key);
        return k;
    }

    public MySET<Key> union(MySET<Key> that) {
        if (that == null) throw new IllegalArgumentException("called union() with a null argument");
        MySET<Key> c = new MySET<>();
        this.forEach(c::add);
        that.forEach(c::add);
        return c;
    }

    /**
     * Returns the intersection of this set and that set
     *
     * @param that
     * @return
     */
    public MySET<Key> intersects(MySET<Key> that) {
        if (that == null) throw new IllegalArgumentException("called intersects() with a null argument");
        MySET<Key> c = new MySET<>();
        if (this.size() < that.size()) {
            this.forEach(x -> {
                if (that.contains(x)) c.add(x);
            });
        } else {
            that.forEach(x -> {
                if (this.contains(x)) c.add(x);
            });
        }
        return c;
    }


    @Override
    public Iterator<Key> iterator() {
        return set.iterator();
    }

    /**
     * This operation is not supported because sets are mutable.
     * @return
     */
    @Override
    public int hashCode() {
        throw new UnsupportedOperationException("hashCode() is not supported because sets are mutable");
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) return true;
        if (other == null) return false;
        if (other.getClass() != this.getClass()) return false;
        MySET that = (MySET) other;
        return this.set.equals(this.set);
    }

    @Override
    public String toString() {
        String s = set.toString();
        return "{ " + s.substring(1, s.length() - 1) + " }";
    }
}
