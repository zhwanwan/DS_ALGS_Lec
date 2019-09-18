package com.ds.lec10.tree.bst;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 二叉排序树结点
 * 二叉排序树：BST: (Binary Sort(Search) Tree), 对于二叉排序树的任何一个非叶子节点，
 * 要求左子节点的值比当前节点的值小，右子节点的值比当前节点的值大。
 * 特别说明：如果有相同的值，可以将该节点放在左子节点或右子节点。
 *
 * @author zhwanwan
 * @create 2019-09-17 2:44 PM
 */
public class Node {
    private int value;
    private Node left;
    private Node right;

    public Node(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    /**
     * 向以当前结点为根结点的二叉排序树添加指定结点
     * 递归添加
     *
     * @param node
     */
    public void add(Node node) {
        if (node == null)
            return;
        if (this.value > node.value) { //比当前结点小则放入左子树
            if (this.left == null)
                this.left = node;
            else
                this.left.add(node); //向左子树递归添加
        } else { //不小于当前结点则放入右子树
            if (this.right == null)
                this.right = node;
            else
                this.right.add(node); //向右子树递归添加
        }
    }

    public void infixOrder() {
        if (this.left != null)
            this.left.infixOrder();
        System.out.println(this);
        if (this.right != null)
            this.right.infixOrder();
    }

    //中序非递归排序--借助栈，左中右
    public void infixOrderByStack() {
        Deque<Node> stack = new LinkedList<>();
        Node current = this;
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            if (!stack.isEmpty()) {
                current = stack.pop();
                System.out.println(current);
                current = current.right;
            }
        }
    }


    /**
     * 查找指定结点
     *
     * @param value
     * @return
     */
    public Node search(int value) {
        if (this.value == value)
            return this;
        else if (value < this.value) { //向左子树找
            if (this.left == null)
                return null;
            return this.left.search(value);
        } else {
            if (this.right == null)
                return null;
            return this.right.search(value);
        }
    }

    /**
     * 查找要删除结点的父结点
     *
     * @param value
     * @return
     */
    public Node searchParent(int value) {
        if (this.left != null && this.left.value == value || this.right != null && this.right.value == value)
            return this;
        else {
            if (value < this.value && this.left != null)
                return this.left.searchParent(value);
            else if (value >= this.value && this.right != null)
                return this.right.searchParent(value);
            else
                return null;
        }
    }

}
