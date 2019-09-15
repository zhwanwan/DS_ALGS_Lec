package com.ds.lec10.tree;

/**
 * 线索化二叉树
 * <p>
 * n个结点的二叉链表中含有n+1【公式 2n-(n-1)=n+1】 个空指针域。
 * 利用二叉链表中的空指针域，存放指向该结点在某种遍历次序下的前驱和后继结点的指针（这种附加的指针称为"线索"）
 * <p>
 * 这种加上了线索的二叉链表称为线索链表，相应的二叉树称为线索二叉树(Threaded BinaryTree)。
 * 根据线索性质的不同，线索二叉树可分为前序线索二叉树、中序线索二叉树和后序线索二叉树三种。
 * <p>
 * 一个结点的前一个结点，称为前驱结点
 * 一个结点的后一个结点，称为后继结点
 *
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
        /*System.out.println("先序递归遍历");
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
        binaryTree.levelOrder();*/

        //测试线索二叉树
        binaryTree.inOrderThreadedNodes();
        KingNode leftNode = node5.getLeft();
        KingNode rightNode = node5.getRight();
        System.out.println("5号结点的前驱结点是 =" + leftNode); //2
        System.out.println("5号结点的后继结点是=" + rightNode); //1
        binaryTree.threadedList();

    }

    private KingNode root;
    //为了实现线索化，需要创建要给指向当前结点的前驱结点的指针
    //在递归进行线索化时，pre总是保留前一个结点
    private KingNode pre;

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

    /**
     * 中序线索化结点
     *
     * @param node
     */
    public void inOrderThreadedNodes(KingNode node) {
        if (node == null)
            return;
        //线索化左子树
        inOrderThreadedNodes(node.getLeft());
        if (node.getLeft() == null) {
            //让当前结点的左指针指向前驱结点
            node.setLeft(pre);
            //修改当前结点的左指针的类型,指向前驱结点
            node.setLeftType(1);
        }
        //处理后继结点
        if (pre != null && pre.getRight() == null) {
            //让前驱结点的右指针指向当前结点
            pre.setRight(node);
            //修改前驱结点的右指针类型
            pre.setRightType(1);
        }
        //!!! 每处理一个结点后，让当前结点是下一个结点的前驱结点
        pre = node;
        //线索化右子树
        inOrderThreadedNodes(node.getRight());
    }

    public void inOrderThreadedNodes() {
        this.inOrderThreadedNodes(root);
    }

    /**
     * 遍历线索化二叉树（中序）
     */
    public void threadedList() {
        KingNode node = root;
        while (node != null) {
            //循环的找到leftType == 1的结点
            //后面随着遍历而变化,因为当leftType==1时，说明该结点是按照线索化处理后的有效结点
            while (node.getLeftType() == 0)
                node = node.getLeft();
            System.out.println(node);
            //如果当前结点的右指针指向的是后继结点,就一直输出
            while (node.getRightType() == 1) {
                node = node.getRight();
                System.out.println(node);
            }
            //替换这个遍历的结点
            node = node.getRight();
        }
    }

}
