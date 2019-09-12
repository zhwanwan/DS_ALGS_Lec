package com.ds.lec10.tree;

/**
 * @author zhwanwan
 * @create 2019-09-11 13:46
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关胜");

        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        binaryTree.setRoot(root);

        /*System.out.println("前序遍历");
        binaryTree.preOrder();
        System.out.println("中序遍历");
        binaryTree.infixOrder();
        System.out.println("后序遍历");
        binaryTree.postOrder();*/

        System.out.println("删除前,前序遍历");
        binaryTree.preOrder(); // 1,2,3,5,4
        binaryTree.deleteNode(5);
        System.out.println("删除后，前序遍历");
        binaryTree.preOrder(); // 1,2,3,4

    }
}
