package zhwanwan.algs;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * SeqSearchST is short for SequentialSearchST which represents an (unordered)
 * symbol table of generic key-value pairs.
 *
 * @author zhwanwan
 * @create 2019-05-24 4:27 PM
 */
public class SeqSearchST<Key, Value> {

    private int n;       // number of key-value pairs
    private Node first;  // the linked list of key-value pairs

    // a helper linked data type
    private class Node {
        private Key key;
        private Value val;
        private Node next;

        public Node(Key key, Value val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    public SeqSearchST() {
    }

    /**
     * Returns the number of key-value pairs in this symbol table
     *
     * @return the number of key-value pairs in this symbol table
     */
    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public boolean contains(Key key) {
        if (key == null)
            throw new IllegalArgumentException("argument to contains() is null");
        return get(key) == null;
    }

    /**
     * Returns the value associated with the given key in this symbol table
     *
     * @param key the key
     * @return the value associated with the given key
     */
    public Value get(Key key) {
        if (key == null)
            throw new IllegalArgumentException("argument to get() is null");
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key))
                return x.val;
        }
        return null;
    }

    /**
     * Inserts the specified key-value pair into the symbol table, overwriting the
     * old value with the new value if the symbol table already contains the specified
     * key.
     * Delete the specified key (and its associated value) from this symbol table if
     * the specified value is null.
     *
     * @param key
     * @param val
     */
    public void put(Key key, Value val) {

        if (key == null)
            throw new IllegalArgumentException("argument to put() is null");
        if (val == null) {
            //delete this key-value pair
            delete(key);
            return;
        }
        //replace value for existing key
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.val = val;
                return;
            }
        }
        //add this key-value pair
        first = new Node(key, val, first);
        n++;
    }

    /**
     * Removes the specified key and its associated value from this symbol table
     *
     * @param key
     */
    public void delete(Key key) {
        if (key == null)
            throw new IllegalArgumentException("argument to delete() is null");
        first = delete(first, key);
    }

    /**
     * delete key in linked list beginning at Node x
     *
     * @param x
     * @param key
     * @return
     */
    private Node delete(Node x, Key key) {
        if (x == null)
            return null;
        if (key.equals(x.key)) {
            n--;
            return x.next;
        }
        //delete node -- none-recursion
        /*Node p = x;
        for (Node i = x.next; i != null; i = i.next) {
            if (key.equals(i.key)) {
                p.next = i.next;
                n--;
                break;
            }
            p = p.next;
        }*/

        //delete node -- recursion
        x.next = delete(x.next, key); // ***important point
        return x;
    }

    public Iterable<Key> keys() {
        Deque<Key> queue = new ArrayDeque<>();
        for (Node x = first; x != null; x = x.next)
            queue.offer(x.key);
        return queue;
    }

    public static void main(String[] args) {

        SeqSearchST<String, Integer> st = new SeqSearchST<>();

        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }
        for (String s : st.keys())
            StdOut.println(s + " " + st.get(s));

    }


}
