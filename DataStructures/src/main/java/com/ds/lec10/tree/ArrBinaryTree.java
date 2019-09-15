package com.ds.lec10.tree;

/**
 * 顺序存储二叉树遍历
 * <p>
 * 顺序存储二叉树的特点:
 * <p>
 * 顺序二叉树通常只考虑完全二叉树
 * 第n个元素的左子节点为  2 * n + 1
 * 第n个元素的右子节点为  2 * n + 2
 * 第n个元素的父节点为  (n-1) / 2
 * n : 表示二叉树中的第几个元素(按0开始编号)
 *
 * @author zhwanwan
 * @create 2019-09-12 8:18 PM
 */
public class ArrBinaryTree {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        System.out.println("先序遍历：");
        arrBinaryTree.preOrder(); // 1,2,4,5,3,6,7
        System.out.println();
        System.out.println("中序遍历：");
        arrBinaryTree.infixOrder();
        System.out.println();
        System.out.println("后续遍历：");
        arrBinaryTree.postOrder();
    }

    private int[] arr;

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    public void preOrder() {
        this.preOrder(0);
    }

    public void infixOrder() {
        this.infixOrder(0);
    }

    public void postOrder() {
        this.postOrder(0);
    }

    private void preOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能按照二叉树的前序遍历");
            return;
        }
        System.out.print(arr[index] + " ");
        //左递归
        if (2 * index + 1 < arr.length)
            preOrder(2 * index + 1);
        //右递归
        if (2 * index + 2 < arr.length)
            preOrder(2 * index + 2);
    }

    private void infixOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能按照二叉树的前序遍历");
            return;
        }
        if (2 * index + 1 < arr.length)
            infixOrder(2 * index + 1);
        System.out.print(arr[index] + " ");
        if (2 * index + 2 < arr.length)
            infixOrder(2 * index + 2);
    }

    private void postOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能按照二叉树的前序遍历");
            return;
        }
        if (2 * index + 1 < arr.length)
            postOrder(2 * index + 1);
        if (2 * index + 2 < arr.length)
            postOrder(2 * index + 2);
        System.out.print(arr[index] + " ");
    }
}
