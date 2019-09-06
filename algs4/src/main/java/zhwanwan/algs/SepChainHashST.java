package zhwanwan.algs;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * SepChainHashST is short for SeparateChainingHashST which represents a symbol table of
 * generic key-value pairs.
 *
 * @author zhwanwan
 * @create 2019-05-24 11:26 PM
 */
public class SepChainHashST<Key, Value> {

    private static final int INIT_CAPACITY = 4;

    private int n;   // number of key-value pairs
    private int m;   // hash table size
    private SeqSearchST<Key, Value>[] st; // array of linked-list symbol tables

    public SepChainHashST(int m) {
        this.m = m;
        st = new SeqSearchST[m];
        for (int i = 0; i < m; i++) {
            st[i] = new SeqSearchST<Key, Value>();
        }
    }

    public SepChainHashST() {
        this(INIT_CAPACITY);
    }

    // resize the hash table
    private void resize(int chains) {
        SepChainHashST<Key, Value> temp = new SepChainHashST<>(chains);
        for (int i = 0; i < m; i++) {
            for (Key key : st[i].keys()) {
                temp.put(key, st[i].get(key));
            }
        }
        this.m = temp.m;
        this.n = temp.n;
        this.st = temp.st;
    }

    //hash value between 0 and m-1
    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % m;
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public boolean contains(Key key) {
        if (key == null)
            throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }

    public Value get(Key key) {
        if (key == null)
            throw new IllegalArgumentException("argument to get() is null");
        int i = hash(key);
        return st[i].get(key);
    }

    public void put(Key key, Value val) {

        if (key == null)
            throw new IllegalArgumentException("argument to put() is null");
        if (val == null) {
            delete(key);
            return;
        }
        if (n >= 10 * m)
            resize(2 * m);

        int i = hash(key);
        if (!st[i].contains(key))
            n++;
        st[i].put(key, val);
    }

    public void delete(Key key) {
        if (key == null)
            throw new IllegalArgumentException("argument to delete() is null");
        int i = hash(key);
        if (st[i].contains(key))
            n--;
        st[i].delete(key);

        // halve table size if average length of list <= 2
        if (m > INIT_CAPACITY && n <= 2 * m)
            resize(m / 2);
    }

    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<>();
        for (int i = 0; i < m; i++) {
            for (Key key : st[i].keys())
                queue.enqueue(key);
        }
        return queue;
    }

    public static void main(String[] args) {

        SepChainHashST<String, Integer> st = new SepChainHashST<>();
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }

        // print keys
        for (String s : st.keys())
            StdOut.println(s + " " + st.get(s));
    }

}


