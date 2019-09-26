package com.ds.lec10.tree.avl;

import java.util.Deque;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * 二叉平衡搜索树（AVL树）
 */
public class MyAVLTreeST<Key extends Comparable<Key>, Value> {

    private AVLSTNode root;

    private class AVLSTNode {
        private final Key key;
        private Value val;
        private int height;
        private int size;
        private AVLSTNode left, right;

        public AVLSTNode(Key key, Value val, int height, int size) {
            this.key = key;
            this.val = val;
            this.height = height;
            this.size = size;
        }
    }

    public MyAVLTreeST() {
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        return size(root);
    }

    private int size(AVLSTNode node) {
        if (node == null)
            return 0;
        return node.size;
    }

    public int height() {
        return height(root);
    }

    private int height(AVLSTNode node) {
        if (node == null)
            return -1;
        return node.height;
    }

    public Value get(Key key) {
        if (key == null)
            throw new IllegalArgumentException("key cannot be null");
        AVLSTNode x = get(root, key);
        if (x == null)
            return null;
        return x.val;
    }

    private AVLSTNode get(AVLSTNode node, Key key) {
        if (node == null)
            return null;
        int cmp = key.compareTo(node.key);
        if (cmp < 0)
            return get(node.left, key);
        else if (cmp > 0)
            return get(node.right, key);
        else
            return node;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public void put(Key key, Value val) {
        if (key == null)
            throw new IllegalArgumentException("key cannot be null");
        if (val == null) {
            delete(key);
            return;
        }
        root = put(root, key, val);

    }

    private AVLSTNode put(AVLSTNode node, Key key, Value val) {
        if (node == null)
            return new AVLSTNode(key, val, 0, 1);
        int cmp = key.compareTo(node.key);
        if (cmp < 0)
            node.left = put(node.left, key, val);
        else if (cmp > 0)
            node.right = put(node.right, key, val);
        else {
            node.val = val;
            return node;
        }
        node.size = 1 + size(node.left) + size(node.right);
        node.height = 1 + Math.max(height(node.left), height(node.right));
        return balance(node);
    }

    private AVLSTNode balance(AVLSTNode node) {
        if (balanceFactor(node) < -1) {
            if (balanceFactor(node.right) > 0)
                node.right = rotateRight(node.right);
            node = rotateLeft(node);
        } else if (balanceFactor(node) > 1) {
            if (balanceFactor(node.left) > 0)
                node.left = rotateLeft(node.left);
            node = rotateRight(node);
        }
        return node;
    }

    private AVLSTNode rotateLeft(AVLSTNode x) {
        AVLSTNode y = x.right;
        x.right = y.left;
        y.left = x;
        y.size = x.size;
        x.size = 1 + size(x.left) + size(x.right);
        x.height = 1 + height(x.left) + height(x.right);
        y.height = 1 + height(y.left) + height(y.right);
        return y;
    }

    private AVLSTNode rotateRight(AVLSTNode x) {
        AVLSTNode y = x.left;
        x.left = y.right;
        y.right = x;
        y.size = x.size;
        x.size = 1 + size(x.left) + size(x.right);
        x.height = 1 + Math.max(height(x.left), height(x.right));
        y.height = 1 + Math.max(height(y.left), height(y.right));
        return y;
    }

    private int balanceFactor(AVLSTNode node) {
        return height(node.left) - height(node.right);

    }

    public void delete(Key key) {
        if (key == null)
            throw new IllegalArgumentException("key cannot be null");
        if (!contains(key))
            return;
        root = delete(root, key);
    }

    private AVLSTNode delete(AVLSTNode node, Key key) {
        int cmp = key.compareTo(node.key);
        if (cmp < 0)
            node.left = delete(node.left, key);
        else if (cmp > 0)
            node.right = delete(node.right, key);
        else {
            if (node.left == null)
                return node.right;
            else if (node.right == null)
                return node.left;
            else {
                AVLSTNode y = node;
                node = min(y.right);
                node.right = deleteMin(y.right);
                node.left = y.left;
            }
        }
        node.size = 1 + size(node.left) + size(node.right);
        node.height = 1 + Math.max(height(node.left), height(node.right));
        return balance(node);
    }

    private AVLSTNode deleteMin(AVLSTNode node) {
        if (node == null)
            return null;
        if (node.left == null)
            return node.right;
        node.left = deleteMin(node.left);
        node.size = 1 + size(node.left) + size(node.right);
        node.height = 1 + Math.max(height(node.left), height(node.right));
        return balance(node);
    }

    private AVLSTNode min(AVLSTNode node) {
        if (node == null)
            return null;
        if (node.left == null)
            return node;
        else
            return min(node.left);
    }

    public void deleteMin() {
        if (isEmpty())
            throw new NoSuchElementException("no node found!");
        root = deleteMin(root);
    }

    public Key min() {
        if (isEmpty())
            throw new NoSuchElementException("no data found!");
        return min(root).key;
    }

    public Key max() {
        if (isEmpty())
            throw new NoSuchElementException("no data found!");
        return max(root).key;
    }

    private AVLSTNode max(AVLSTNode node) {
        if (node == null)
            return null;
        if (node.right == null)
            return node;
        return max(node.right);
    }

    public void deleteMax() {
        if (isEmpty())
            throw new NoSuchElementException("no data found!");
        root = deleteMax(root);
    }

    private AVLSTNode deleteMax(AVLSTNode node) {
        if (node.right == null)
            return node.left;
        node.right = deleteMax(node.right);
        node.size = 1 + size(node.left) + size(node.right);
        node.height = 1 + Math.max(height(node.left), height(node.right));
        return balance(node);
    }

    /**
     * rex max(x) <= key
     *
     * @param key
     * @return
     */
    public Key floor(Key key) {
        if (key == null)
            throw new IllegalArgumentException("key cannot be null");
        if (isEmpty())
            throw new NoSuchElementException("no data found");
        AVLSTNode node = floor(root, key);
        if (node == null)
            return null;
        else
            return node.key;
    }

    /**
     * Returns the node with the largest key less than or equal to key.
     *
     * @param node
     * @param key
     * @return
     */
    private AVLSTNode floor(AVLSTNode node, Key key) {
        if (node == null)
            return null;
        int cmp = key.compareTo(node.key);
        if (cmp == 0) //相等，找到返回
            return node;
        if (cmp < 0) //继续在左子树查找并返回
            return floor(node.left, key);
        //
        AVLSTNode y = floor(node.right, key);
        if (y != null)
            return y;
        else return node;
    }

    /**
     * min(node) >= key
     *
     * @param key
     * @return
     */
    public Key ceiling(Key key) {
        if (key == null)
            throw new IllegalArgumentException("key cannot be null");
        if (isEmpty())
            throw new NoSuchElementException("no data found");
        AVLSTNode node = ceiling(root, key);
        if (node == null)
            return null;
        else return node.key;
    }

    /**
     * Returns the node with the smallest key greater than or equal to key.
     *
     * @param node
     * @param key
     * @return
     */
    private AVLSTNode ceiling(AVLSTNode node, Key key) {
        if (node == null)
            return null;
        int cmp = key.compareTo(node.key);
        if (cmp == 0)
            return node;
        if (cmp > 0)
            return ceiling(node.right, key);
        AVLSTNode y = ceiling(node.left, key);
        if (y != null)
            return y;
        else return node;
    }

    public Key select(int k) {
        if (k < 0 || k >= size())
            throw new IllegalArgumentException("k is not in range 0-" + (size() - 1));
        AVLSTNode node = select(root, k);
        return node.key;
    }

    /**
     * Returns the node with key the kth smallest key in the subtree.
     *
     * @param node
     * @param k
     * @return
     */
    private AVLSTNode select(AVLSTNode node, int k) {
        if (node == null)
            return null;
        int t = size(node.left);
        if (t > k)
            return select(node.left, k);
        else if (t < k)
            return select(node.right, k - t - 1);
        else return node;
    }

    public int rank(Key key) {
        if (key == null)
            throw new IllegalArgumentException("key cannot be null");
        return rank(root, key);
    }

    /**
     * Returns the number of keys in the subtree less than key.
     *
     * @param node
     * @param key
     * @return
     */
    private int rank(AVLSTNode node, Key key) {
        if (node == null)
            return 0;
        int cmp = key.compareTo(node.key);
        if (cmp < 0)
            return rank(node.left, key);
        else if (cmp > 0)
            return 1 + size(node.left) + rank(node.right, key);
        else
            return size(node.left);
    }

    public Iterable<Key> keys() {
        return keysInOrder();
    }

    private Iterable<Key> keysInOrder() {
        Deque<Key> queue = new LinkedList<>();
        keysInOrder(root, queue);
        return queue;
    }

    private void keysInOrder(AVLSTNode node, Deque<Key> queue) {
        if (node == null)
            return;
        keysInOrder(node.left, queue);
        queue.offer(node.key);
        keysInOrder(node.right, queue);
    }

    public Iterable<Key> keysLevelOrder() {
        Deque<Key> queue = new LinkedList<>();
        if (!isEmpty()) {
            Deque<AVLSTNode> deque = new LinkedList<>();
            deque.offer(root);
            while (!deque.isEmpty()) {
                AVLSTNode x = deque.poll();
                queue.offer(x.key);
                if (x.left != null)
                    deque.offer(x.left);
                if (x.right != null)
                    deque.offer(x.right);
            }
        }
        return queue;
    }

    public boolean isAVL() {
        return isAVL(root);
    }

    private boolean isAVL(AVLSTNode node) {
        if (node == null)
            return true;
        int bf = balanceFactor(node);
        if (Math.abs(bf) > 1)
            return false;
        return isAVL(node.left) && isAVL(node.right);
    }

    public boolean isBST() {
        return isBST(root, null, null);
    }

    /**
     * Checks if the tree rooted at node is a BST with all keys strictly
     * between min and max(if min or max is null,treat as empty constraint).
     *
     * @param node the subtree
     * @param min  the minimum key in subtree
     * @param max  the maximum key in subtree
     * @return
     */
    private boolean isBST(AVLSTNode node, Key min, Key max) {
        if (node == null)
            return true;
        if (min != null && node.key.compareTo(min) <= 0)
            return false;
        if (max != null && node.key.compareTo(max) >= 0)
            return false;
        return isBST(node.left, min, node.key) && isBST(node.right, node.key, max);
    }

    private boolean isSizeConsistent() {
        return isSizeConsistent(root);
    }

    private boolean isSizeConsistent(AVLSTNode node) {
        if (node == null)
            return true;
        if (node.size != size(node.left) + size(node.right) + 1)
            return false;
        return isSizeConsistent(node.left) && isSizeConsistent(node.right);
    }

    private boolean isRankConsistent() {
        for (int i = 0; i < size(); i++) {
            if (i != rank(select(i)))
                return false;
        }
        for (Key key : keys()) {
            if (key.compareTo(select(rank(key))) != 0)
                return false;
        }
        return true;
    }

}
