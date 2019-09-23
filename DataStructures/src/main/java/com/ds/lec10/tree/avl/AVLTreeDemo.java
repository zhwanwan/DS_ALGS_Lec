package com.ds.lec10.tree.avl;

/**
 * 平衡二叉树（AVL树）
 * 平衡二叉树也叫平衡二叉搜索树（Self-balancing binary search tree）又被称为AVL树，可以保证查询效率较高。
 * 具有以下特点：它是一 棵空树或它的左右两个子树的高度差的绝对值不超过1，并且左右两个子树都是一棵平衡二叉树。
 * 平衡二叉树的常用实现方法有红黑树、AVL、Treap、伸展树等。
 */
public class AVLTreeDemo {
    public static void main(String[] args) {
        int[] arr = {4, 3, 6, 5, 7, 8};
//        int[] arr = { 10, 12, 8, 9, 7, 6 };
//        int[] arr = {10, 11, 7, 6, 8, 9};
        //创建一个 AVLTree对象
        AVLTree avlTree = new AVLTree();
        //添加结点
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new Node(arr[i]));
        }

        //遍历
        System.out.println("中序遍历");
        avlTree.infixOrder();

        System.out.println("树的高度=" + avlTree.getRoot().height()); //
        System.out.println("树的左子树高度=" + avlTree.getRoot().leftHeight()); //
        System.out.println("树的右子树高度=" + avlTree.getRoot().rightHeight()); //
        System.out.println("当前的根结点=" + avlTree.getRoot());//
    }
}

class AVLTree {
    private Node root;

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public void add(Node node) {
        if (root == null)
            root = node;
        else
            root.add(node);
    }

    public void infixOrder() {
        if (root == null)
            System.out.println("二叉树为空！");
        else
            root.infixOrder();
    }
}

class Node {
    int value;
    Node left, right;

    public Node(int value) {
        this.value = value;
    }

    //返回以当前结点为根结点的树的高度
    public int height() {
        return 1 + Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height());
    }

    //左子树的高度
    public int leftHeight() {
        if (left == null)
            return 0;
        return left.height();
    }

    //右子树的高度
    public int rightHeight() {
        if (right == null)
            return 0;
        return right.height();
    }

    //左旋转操作
    private void leftRotate() {
        //创建新结点，值为当前根结点的值
        Node newNode = new Node(value);
        //把新结点的左子树设置成（指向）当前节点的左子树
        newNode.left = left;
        //3.把新结点的右子树设置成（指向）当前结点的右子树的左子树
        newNode.right = right.left;
        //4.把当前结点的值替换成（指向）当前结点右子结点的值
        value = right.value;
        //5.把当前结点右子结点设置成（指向）当前结点右子树的右子树
        right = right.right;
        //6.把当前节点的左子树设置成（指向）新结点
        left = newNode;
    }

    //右旋转操作
    private void rightRotate() {
        Node newNode = new Node(value);
        newNode.right = right;
        newNode.left = left.right;
        value = left.value;
        left = left.left;
        right = newNode;
    }

    public void add(Node node) {
        if (node == null)
            return;
        if (node.value < this.value) {
            if (this.left == null)
                this.left = node;
            else
                this.left.add(node);
        } else {
            if (this.right == null)
                this.right = node;
            else
                this.right.add(node);
        }

        //判断是否平衡
        // (右子树的高度-左子树的高度) > 1, 左旋转
        // (左子树的高度-右子树的高度) > 1, 右旋转
        if (rightHeight() - leftHeight() > 1) { //左旋
            if (right != null && right.leftHeight() > right.rightHeight()) {
                //先对右子树进行右旋
                right.rightRotate();
                //然后对当前结点进行左旋
                leftRotate();
            } else
                leftRotate();
            return;
        }
        if (leftHeight() - rightHeight() > 1) { //右旋
            if (left != null && left.rightHeight() > left.leftHeight()) {
                left.leftRotate();
                rightRotate();
            } else
                rightRotate();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    public void infixOrder() {
        if (left != null)
            left.infixOrder();
        System.out.println(this);
        if (right != null)
            right.infixOrder();
    }
}