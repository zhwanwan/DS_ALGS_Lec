package com.ds.lec10.tree;

import com.ds.lec10.tree.avl.MyAVLTreeST;

/**
 * @author zhwanwan
 * @create 2019-09-24 10:33 AM
 */
public class AVLTreeTest {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        MyAVLTreeST<Integer, Integer> avlTree = new MyAVLTreeST();
        for (int i = 0; i < arr.length; i++) {
            avlTree.put(arr[i], i);
        }
        avlTree.keysLevelOrder().forEach(System.out::println);
        System.out.println();
        avlTree.keys().forEach(System.out::println);

    }
}
