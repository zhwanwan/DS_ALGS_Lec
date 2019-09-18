package com.ds.lec10.tree.bst;

/**
 * @author zhwanwan
 * @create 2019-09-18 5:09 PM
 */
public class MyBST<Key extends Comparable<Key>, Value> {

    private BSTNode root;

    private class BSTNode {

        private Key key;
        private Value value;
        private int size;
        private BSTNode left, right;

        public BSTNode(Key key, Value value, int size) {
            this.key = key;
            this.value = value;
            this.size = size;
        }

        @Override
        public String toString() {
            return "BSTNode{" +
                    "key=" + key +
                    ", value=" + value +
                    ", size=" + size +
                    '}';
        }
    }

    public int size() {
        return size(root);
    }

    private int size(BSTNode x) {
        if (x == null)
            return 0;
        return x.size;
    }

    private Value get(BSTNode x, Key key) {
        if (key == null)
            throw new IllegalArgumentException("key cannot be null");
        int cmp = key.compareTo(x.key);
        if (cmp > 0)
            return get(x.right, key);
        else if (cmp < 0)
            return get(x.left, key);
        else
            return x.value;
    }

    public Value get(Key key) {
        return get(root, key);
    }

    private BSTNode put(BSTNode x, Key key, Value value) {
        if (key == null)
            throw new IllegalArgumentException("key cannot be null");
        if (x == null)
            return new BSTNode(key, value, 1);
        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            x.left = put(x.left, key, value);
        else if (cmp > 0)
            x.right = put(x.right, key, value);
        else
            x.value = value;
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void put(Key key, Value value) {
        if (key == null)
            throw new IllegalArgumentException("key cannot be null");
        root = put(root, key, value);
    }

    public boolean contains(Key key) {
        return get(key) == null;
    }

    private BSTNode min(BSTNode x) {
        if (x.left == null)
            return x;
        return min(x.left);
    }

    /**
     * 删除指定结点为根的最小子结点
     * 思路：
     * 最小结点在左子树，递归处理
     * 递归退出条件：当前结点没有左子树，则返回其右节点
     *
     * @param x
     * @return
     */
    private BSTNode deleteMin(BSTNode x) {
        if (x.left == null)
            return x.right;
        else
            x.left = deleteMin(x.left);
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }

    private BSTNode deleteMax(BSTNode x) {
        if (x.right == null)
            return x.left;
        else
            x.right = deleteMax(x.right);
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }

    private BSTNode delete(BSTNode x, Key key) {
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
                BSTNode t = x;
                x = min(t.right);
                x.right = deleteMin(t.right);
                x.left = t.left;
            }
        }
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }

    public void delete(Key key) {
        if (key == null)
            throw new IllegalArgumentException("key cannot be null");
        root = delete(root, key);
    }

    private void infixOrder(BSTNode x) {
        if (x == null)
            return;
        if (x.left != null)
            infixOrder(x.left);
        System.out.println(x);
        if (x.right != null)
            infixOrder(x.right);
    }

    public void infixOrder() {
        infixOrder(root);
    }

}
