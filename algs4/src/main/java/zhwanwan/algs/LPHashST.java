package zhwanwan.algs;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LinearProbingHashST
 *
 * @author zhwanwan
 * @create 2019-06-14 11:07 AM
 */
public class LPHashST<Key, Value> {

    private static final int INIT_CAPACITY = 4;

    private int n; //number of key-value pairs in the symbol table
    private int m; // size of liner probing table
    private Key[] keys;
    private Value[] vals;

    public LPHashST() {
        this(INIT_CAPACITY);
    }

    /**
     * Initializes am empty symbol table with the specified initial capacity.
     *
     * @param capacity
     */
    public LPHashST(int capacity) {
        m = capacity;
        n = 0;
        keys = (Key[]) new Object[m];
        vals = (Value[]) new Object[m];
    }

    /**
     * Returns the number of key-value pairs in this symbol table.
     *
     * @return
     */
    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }

    /**
     * Returns the value associated with the specified key
     *
     * @param key
     * @return
     */
    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        for (int i = hash(key); keys[i] != null; i = (i + 1) % m)
            if (keys[i].equals(key))
                return vals[i];
        return null;
    }

    // hash function for keys - -returns value between 0 and M-1
    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % m;
    }

    // resizes the hash table to the given capacity by re-hashing all the keys
    private void resize(int capacity) {
        LPHashST<Key, Value> temp = new LPHashST<>(capacity);
        for (int i = 0; i < m; i++) {
            if (keys[i] != null) {
                temp.put(keys[i], vals[i]);
            }
        }
        keys = temp.keys;
        vals = temp.vals;
        m = temp.m;

    }

    /**
     * @param key
     * @param val
     */
    public void put(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException("first argument to put() is null");

        if (val == null) {
            delete(key);
            return;
        }

        // double table size if 50% full
        if (n >= m / 2) resize(2 * m);

        int i;

        for (i = hash(key); keys[i] != null; i = (i + 1) % m) {
            if (keys[i].equals(key)) {
                //found --> update
                vals[i] = val;
                return;
            }
        }

        keys[i] = key;
        vals[i] = val;
        n++;

    }

    /**
     * Removes the specified key and its associated value from this symbol table
     *
     * @param key
     */
    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");
        if (!contains(key)) return;
        // find position of key
        int i = hash(key);
        while (!key.equals(keys[i])) {
            i = (i + 1) % m;
        }
        // delete key and associated value
        keys[i] = null;
        vals[i] = null;

        // rehash all keys in the same cluster
        i = (i + 1) % m;
        while (keys[i] != null) {
            // delete keys[i] and vals[i] and reinsert
            Key keyToRehash = keys[i];
            Value valToRehash = vals[i];
            keys[i] = null;
            vals[i] = null;
            n--;
            put(keyToRehash, valToRehash);
            i = (i + 1) % m;
        }
        n--;

        // halves size of array if it's 12.5 full or less
        if (n > 0 && n <= m / 8) resize(m / 2);
    }

    /**
     * Returns all keys in this symbol table as an Iterable.
     *
     * @return
     */
    public Iterable<Key> keys() {
        Deque<Key> queue = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            if (keys[i] != null)
                queue.offer(keys[i]);
        }
        return queue;
    }

    // integrity check -- don't check after each put()
    // integrity not maintained during a delete()
    private boolean check() {
        // check that table is at most 50% full
        if (m < 2 * n) {
            System.err.println("Hash table size m = " + m + "; array size n = " + n);
            return false;
        }

        // check that each key in the table can be found by get()
        for (int i = 0; i < m; i++) {
            if (keys[i] == null) continue;
            else if (get(keys[i]) != vals[i]) {
                System.err.println("get[" + keys[i] + "] = " + get(keys[i]) + "; vals[i] = " + vals[i]);
                return false;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        LPHashST<String, Integer> st = new LPHashST<>();

        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }

        // print keys
        for (String s : st.keys()) {
            StdOut.println(s + " " + st.get(s));
        }
        /*int i = "AAA".hashCode() & 0xffffffff;
        int a = 0x7fffffff;
        System.out.println("AAA".hashCode());
        System.out.println(Integer.toBinaryString(64545));
        System.out.println(Integer.toBinaryString(a));
        System.out.println(i % 4);*/

    }


}
