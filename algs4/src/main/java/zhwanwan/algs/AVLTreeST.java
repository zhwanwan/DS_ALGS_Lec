package zhwanwan.algs;

import edu.princeton.cs.algs4.StdIn;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * @author zhwanwan
 * @create 2019-05-29 10:03 PM
 */
public class AVLTreeST<Key extends Comparable<Key>, Value> {

    private class Node {
        private final Key key;
        private Value val;
        private int height;
        private int size;
        private Node left, right;

        public Node(Key key, Value val, int height, int size) {
            this.key = key;
            this.val = val;
            this.height = height;
            this.size = size;
        }
    }

    private Node root;

    public AVLTreeST() {
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null)
            return 0;
        return x.size;
    }

    public int height() {
        return height(root);
    }

    private int height(Node x) {
        if (x == null)
            return -1;
        return x.height;
    }

    public Value get(Key key) {
        if (key == null)
            throw new IllegalArgumentException("argument to get() is null");
        Node x = get(root, key);
        if (x == null)
            return null;
        return x.val;
    }

    /**
     * Returns value associated with the given key in the subtree of
     * null if no such key
     *
     * @param x   the subtree
     * @param key the key
     * @return value associated with the given key in the subtree
     */
    private Node get(Node x, Key key) {
        if (x == null)
            return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            return get(x.left, key);
        else if (cmp > 0)
            return get(x.right, key);
        else
            return x;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public void put(Key key, Value val) {
        if (key == null)
            throw new IllegalArgumentException("first argument to put() is null");
        if (val == null) {
            //delete(key);
            return;
        }
        root = put(root, key, val);

    }

    /**
     * Inserts the key-value pair in the subtree. It overrides the old value with
     * the new value if the symbol table already contains the specified key and
     * delete the specified key and its associated value from this symbo table if
     * the specified value is null.
     *
     * @param x
     * @param key
     * @param val
     * @return
     */
    private Node put(Node x, Key key, Value val) {
        if (x == null)
            return new Node(key, val, 0, 1);
        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            x.left = put(x.left, key, val);
        else if (cmp > 0)
            x.right = put(x.right, key, val);
        else {
            x.val = val;
            return x;
        }
        x.size = 1 + size(x.left) + size(x.right);
        x.height = 1 + Math.max(height(x.left), height(x.right));
        return balance(x);
    }

    /**
     * Restores the AVL tree property of the subtree.
     *
     * @param x
     * @return
     */
    private Node balance(Node x) {
        if (balanceFactor(x) < -1) {
            if (balanceFactor(x.right) > 0) {
                x.right = rotateRight(x.right);
            }
            x = rotateLeft(x);
        } else if (balanceFactor(x) > 1) {
            if (balanceFactor(x.left) < 0) {
                x.left = rotateLeft(x.left);
            }
            x = rotateRight(x);

        }
        return x;
    }

    /**
     * Rotates the given subtree to the right
     *
     * @param x
     * @return
     */
    private Node rotateRight(Node x) {
        Node y = x.left;
        x.left = y.right;
        y.right = x;
        y.size = x.size;
        x.size = 1 + size(x.left) + size(x.right);
        x.height = 1 + Math.max(height(x.left), height(x.right));
        y.height = 1 + Math.max(height(y.left), height(y.right));
        return y;
    }

    private Node rotateLeft(Node x) {
        Node y = x.right;
        x.right = y.left;
        y.left = x;
        y.size = x.size;
        x.size = 1 + size(x.left) + size(x.right);
        x.height = 1 + Math.max(height(x.left), height(x.right));
        y.height = 1 + Math.max(height(y.left), height(y.right));
        return y;
    }

    private int balanceFactor(Node x) {
        return height(x.left) - height(x.right);
    }

    public void delete(Key key) {
        if (key == null)
            throw new IllegalArgumentException("argument to delete() is null");
        if (!contains(key))
            return;
        root = delete(root, key);
    }

    private Node delete(Node x, Key key) {
        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            x.left = delete(x.left, key);
        else if (cmp > 0)
            x.right = delete(x.right, key);
        else {
            if (x.left == null)
                return x.right;
            else if (x.right == null)
                return x.left;
            else {
                Node y = x;
                x = min(y.right);
                x.right = deleteMin(y.right);
                x.left = y.left;
            }

        }
        x.size = 1 + size(x.left) + size(x.right);
        x.height = 1 + Math.max(height(x.left), height(x.right));
        return balance(x);
    }

    public Node min(Node x) {
        if (x.left == null) return x;
        return min(x.left);
    }

    public void deleteMin() {
        if (isEmpty()) throw new NoSuchElementException("called deleteMin() with empty symbol table");
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.size = 1 + size(x.left) + size(x.right);
        x.height = 1 + Math.max(height(x.left), height(x.right));
        return balance(x);
    }

    public void deleteMax() {
        if (isEmpty()) throw new NoSuchElementException("called deleteMax() with empty symbol table");
        root = deleteMax(root);
    }

    private Node deleteMax(Node x) {
        if (x.right == null) return x;
        x.right = deleteMax(x.right);
        x.size = 1 + size(x.left) + size(x.right);
        x.height = 1 + Math.max(height(x.left), height(x.right));
        return balance(x);
    }

    public Node max(Node x) {
        if (x.right == null) return x;
        return max(x.right);
    }

    public Key max() {
        if (isEmpty()) throw new NoSuchElementException("called max() with empty symbol table");
        return max(root).key;
    }

    public Key min() {
        if (isEmpty()) throw new NoSuchElementException("called min() with empty symbol table");
        return min(root).key;
    }

    public Key floor(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to floor() is null");
        if (isEmpty()) throw new NoSuchElementException("called floor() with empty symbol table");
        Node x = floor(root, key);
        if (x == null) return null;
        else return x.key;
    }


    /**
     * Returns the node in the subtree with the largest key less then or equal
     * to the given key
     *
     * @param x
     * @param key
     * @return
     */
    private Node floor(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp < 0) return floor(x.left, key);
        //right subtree
        Node y = floor(x.right, key);
        if (y != null) return y;
        else return x;
    }

    public Key ceiling(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to ceiling() is null");
        if (isEmpty()) throw new NoSuchElementException("called ceiling() with empty symbol table");
        Node x = ceiling(root, key);
        if (x == null) return null;
        else return x.key;
    }

    /**
     * Returns the node in the subtree with the smallest key greater than
     * or equal to the given key
     *
     * @param x
     * @param key
     * @return
     */
    private Node ceiling(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp > 0) return ceiling(x.right, key);
        //left subtree
        Node y = ceiling(x.left, key);
        if (y != null) return y;
        else return x;
    }

    public Key select(int k) {
        int len = size();
        if (k < 0 || k >= len) throw new IllegalArgumentException("k is not in rage 0-" + (len - 1));
        Node x = select(root, k);
        return x.key;
    }


    /**
     * Returns the nodes with key the kth smallest key int the subtree.
     *
     * @param x
     * @param k
     * @return
     */
    private Node select(Node x, int k) {
        if (x == null) return null;
        int t = size(x.left);
        if (t > k) return select(x.left, k);
        else if (t < k) return select(x.right, k);
        else return x;
    }

    public int rank(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to rank() to null");
        return rank(key, root);
    }

    /**
     * Returns the number of keys in the subtree less than key.
     *
     * @param key
     * @param x
     * @return
     */
    private int rank(Key key, Node x) {
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return rank(key, x.left);
        else if (cmp > 0) return 1 + size(x.left) + rank(key, x.right);
        else return size(x.left);
    }

    /**
     * Returns all the keys in the symbol table following an in-order traversal.
     *
     * @return
     */
    public Iterable<Key> keysInOrder() {
        Queue<Key> queue = new ArrayDeque<>(size());
        keysInOrder(root, queue);
        return queue;
    }

    private void keysInOrder(Node x, Queue<Key> queue) {
        if (x == null) return;
        keysInOrder(x.left, queue);
        queue.offer(x.key);
        keysInOrder(x.right, queue);
    }

    /**
     * Returns all keys in the symbol table.
     *
     * @return
     */
    public Iterable<Key> keys() {
        return keysInOrder();
    }

    public Iterable<Key> keysLevelOrder() {
        //ArrayDeque底层是数组,随机访问性能较好
        //LinkedList底层是链表,插入/删除性能较好
        Queue<Key> queue = new ArrayDeque<>(size());
        if (!isEmpty()) {
            Queue<Node> q = new ArrayDeque<>();
            q.offer(root);
            while (!q.isEmpty()) {
                Node x = q.poll();
                queue.offer(x.key);
                if (x.left != null) {
                    q.offer(x.left);
                }
                if (x.right != null) {
                    q.offer(x.right);
                }
            }
        }
        return queue;
    }

    /**
     * Returns all keys in the symbol table in the given range.
     *
     * @param lo the lowest key
     * @param hi the highest key
     * @return [lo, hi)
     */
    public Iterable<Key> keys(Key lo, Key hi) {
        if (lo == null) throw new IllegalArgumentException("first argument to keys() is null");
        if (hi == null) throw new IllegalArgumentException("second argument to keys() is null");
        Queue<Key> queue = new LinkedList<>();
        keys(root, queue, lo, hi);
        return queue;
    }

    private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
        if (x == null) return;
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if (cmplo < 0) keys(x.left, queue, lo, hi);
        if (cmplo <= 0 && cmphi >= 0) queue.offer(x.key);
        if (cmphi > 0) keys(x.right, queue, lo, hi);
    }

    /**
     * Returns the number of keys in  the symbol table in the given range.
     *
     * @param lo minimun endpoint
     * @param hi maximun endpoint
     * @return the number of keys[lo,hi)
     */
    public int size(Key lo, Key hi) {
        if (lo == null) throw new IllegalArgumentException("first argument to size() is null");
        if (hi == null) throw new IllegalArgumentException("second argument to size() is null");
        if (lo.compareTo(hi) > 0) return 0;
        if (contains(hi)) return rank(hi) - rank(lo) + 1;
        else return rank(hi) - rank(lo);
    }

    private boolean isAVL(Node x) {
        if (x == null) return true;
        int bf = balanceFactor(x);
        if (Math.abs(bf) > 1) return false;
        return isAVL(x.left) && isAVL(x.right);
    }

    private boolean isAVL() {
        return isAVL(root);
    }

    /**
     * Checks if the tree rooted at x is a BST with all keys strictly between
     * min and max (if min o max is null, treat as empty constraint)
     * Credit:Bob Dondero's elegant solution
     *
     * @param x
     * @param min
     * @param max
     * @return
     */
    private boolean isBST(Node x, Key min, Key max) {
        if (x == null) return true;
        if (min != null && x.key.compareTo(min) <= 0) return false;
        if (max != null && x.key.compareTo(max) >= 0) return false;
        return isBST(x.left, min, x.key) && isBST(x.right, x.key, max);
    }

    private boolean isBST() {
        return isBST(root, null, null);
    }

    public static void main(String[] args) {
        AVLTreeST<String, Integer> st = new AVLTreeST<>();
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }
        for (String s : st.keys()) {
            System.out.println(s + " " + st.get(s));
        }
    }



}
