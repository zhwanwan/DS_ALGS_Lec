package com.ds.lec10.tree;

/**
 * @author zhwanwan
 * @create 2019-09-14 5:25 PM
 */
public class ThreadedBinaryTree {

    public static void main(String[] args) {

        KingNode root = new KingNode(1, "tom");
        KingNode node2 = new KingNode(2, "jack");
        KingNode node3 = new KingNode(3, "smith");
        KingNode node4 = new KingNode(4, "mary");
        KingNode node5 = new KingNode(5, "king");
        KingNode node6 = new KingNode(6, "dim");

        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        ThreadedBinaryTree binaryTree = new ThreadedBinaryTree();
        binaryTree.setRoot(root);
        System.out.println("先序递归遍历");
        binaryTree.preOrder();
        System.out.println("先序非递归遍历1");
        binaryTree.preOrderByStack();
        System.out.println("先序非递归遍历2");
        binaryTree.preOrderByStackBasedInfix();
        System.out.println("中序递归遍历");
        binaryTree.infixOrder();
        System.out.println("中序非递归遍历");
        binaryTree.infixOrderByStack();
        System.out.println("后序递归遍历");
        binaryTree.postOrder();
        System.out.println("后序非递归遍历");
        binaryTree.postOrderByStack();
        System.out.println("层次遍历");
        binaryTree.levelOrder();

    }

    private KingNode root;

    public void setRoot(KingNode root) {
        this.root = root;
    }

    public void deleteNode(int no) {
        if (root != null) {
            if (root.getNo() == no)
                root = null;
            else
                root.deleteNode(no);
        } else
            System.out.println("空树，不能删除!");
    }

    public void preOrder() {
        if (root != null)
            this.root.preOrder();
        else
            System.out.println("二叉树为空!");
    }

    public void preOrderByStack() {
        if (root != null)
            this.root.preOrderByStack();
        else
            System.out.println("二叉树为空!");
    }

    public void preOrderByStackBasedInfix() {
        if (root != null)
            this.root.preOrderByStackBasedInfix();
        else
            System.out.println("二叉树为空！");
    }

    public void infixOrder() {
        if (root != null)
            this.root.infixOrder();
        else
            System.out.println("二叉树为空!");
    }

    public void infixOrderByStack() {
        if (root != null)
            this.root.infixOrderByStack();
        else
            System.out.println("二叉树为空！");
    }

    public void postOrder() {
        if (root != null)
            this.root.postOrder();
        else
            System.out.println("二叉树为空！");
    }

    public void postOrderByStack() {
        if (root != null)
            this.root.postOrderByStack();
        else
            System.out.println("二叉树为空！");
    }

    public void levelOrder() {
        if (root != null)
            this.root.levelOrder();
        else
            System.out.println("二叉树为空！");
    }

}
