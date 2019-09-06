package zhwanwan.algs;

import java.util.Deque;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * @author wangzhen
 * @create 2019-05-13 8:17 PM
 */
public class BST<Key extends Comparable<Key>, Value> {

    private Node root;

    private class Node {
        private Key key;
        private Value value;
        private Node left, right;
        private int size;

        public Node(Key key, Value val, int size) {
            this.key = key;
            this.value = val;
            this.size = size;
        }

    }

    public BST() {
    }

    //BST结点数
    public int size() {
        return size(root);
    }

    //是否包括指定key
    public boolean contains(Key key) {
        if (key == null)
            throw new IllegalArgumentException("argument to contains() is null");
        return get(key) == null;
    }

    private int size(Node x) {
        if (x == null) return 0;
        return x.size;
    }

    //查找指定key
    public Value get(Key key) {
        if (key == null)
            throw new IllegalArgumentException("argument to contains() is null");
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        if (key == null)
            throw new IllegalArgumentException("argument to contains() is null");
        int cmp = key.compareTo(x.key);
        if (cmp > 0)
            return get(x.right, key);
        else if (cmp < 0)
            return get(x.left, key);
        else
            return x.value;
    }

    //添加/更新元素
    public void put(Key key, Value value) {
        if (key == null)
            throw new IllegalArgumentException("key can't be null");
        put(root, key, value);
    }


    private Node put(Node x, Key key, Value value) {
        if (key == null)
            throw new IllegalArgumentException("key can't be null");
        if (x == null)
            return new Node(key, value, 1);
        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            x.left = put(x.left, key, value);
        else if (cmp > 0)
            x.right = put(x.right, key, value);
        else
            x.value = value;
        //更新size
        x.size = size(x.left) + size(x.right) + 1;
        return x;

    }

    //删除最小元素
    public void deleteMin() {
        if (isEmpty())
            throw new NoSuchElementException("Symbol table underflow");
        deleteMin(root);
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    private Node deleteMin(Node x) {
        if (x.left == null)
            return x.right;
        else
            x.left = deleteMin(x.left);
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }

    public void deleteMax() {
        if (isEmpty())
            throw new NoSuchElementException("Symbol table underflow");
        deleteMax(root);
    }

    private Node deleteMax(Node x) {
        if (x.right == null)
            return x.left;
        x.right = deleteMax(x.right);
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }

    //删除指定的key
    public void delete(Key key) {
        if (key == null)
            throw new IllegalArgumentException("calls to delete() with a null key");
        root = delete(root, key);
    }

    private Node delete(Node x, Key key) {
        if (x == null)
            return null;
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
                //左右都存在结点
                Node t = x;
                x = min(t.right);
                x.left = t.left;
                x.right = deleteMin(t.right);

            }
        }
        x.size = 1 + size(x.left) + size(x.right);
        return x;

    }

    public Node min() {
        if (isEmpty())
            throw new NoSuchElementException("Symbol table empty");
        return min(root);
    }

    private Node min(Node x) {
        if (x.left == null)
            return x;
        return min(x.left);
    }

    public Key max() {
        if (isEmpty())
            throw new NoSuchElementException("Symbol table empty");
        return max(root);
    }

    private Key max(Node x) {
        if (x.right == null)
            return x.key;
        return max(x.right);
    }

    //return number of keys less than key
    public int rank(Key key) {
        if (key == null)
            throw new IllegalArgumentException("argument to rank() is null");
        return rank(root, key);
    }

    private int rank(Node x, Key key) {
        if (x == null)
            return 0;
        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            return rank(x.left, key);
        else if (cmp > 0)
            return 1 + size(x.left) + rank(x.right, key);
        else
            return size(x.left);

    }

    //树的高度(1-node tree has height 0)
    public int height() {
        return height(root);
    }

    private int height(Node x) {
        if (x == null)
            return -1;
        return 1 + Math.max(height(x.left), height(x.right));
    }

    /**
     * @return the keys in the BST in level order traversal
     */
    public Iterable<Key> levelOrder() {
        Deque<Key> keys = new LinkedList<>();
        Deque<Node> queue = new LinkedList<>();
        queue.push(root);
        while (!queue.isEmpty()) {
            Node x = queue.poll();
            if (x == null) continue;
            keys.push(x.key);
            queue.push(x.left);
            queue.push(x.right);
        }
        return keys;
    }

    public boolean isBST() {
        return isBST(root, null, null);
    }


    // Credit: Bob Dondero's elegant solution
    private boolean isBST(Node x, Key min, Key max) {
        if (x == null)
            return true;
        if (min != null && x.key.compareTo(min) <= 0)
            return false;
        if (max != null && x.key.compareTo(max) >= 0)
            return false;
        return isBST(x.left, min, x.key) && isBST(x.right, x.key, max);
    }
}
