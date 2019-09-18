package com.ds.lec10.tree;

import com.ds.lec10.tree.bst.MyBST;

/**
 * @author zhwanwan
 * @create 2019-09-18 9:49 PM
 */
public class BinarySortTreeTest {

    public static void main(String[] args) {

        MyBST<Integer, String> bst = new MyBST<>();
        bst.put(7, "Jack");
        bst.put(3, "Tony");
        bst.put(10, "Peter");
        bst.put(12, "Lucy");
        bst.put(5, "Tom");
        bst.put(1, "Jay");
        bst.put(9, "Zach");
        bst.put(4, "Ram");

        bst.infixOrder();
        System.out.println("树高:" + bst.height());
        Iterable<Integer> keys = bst.getKeysByLevelOrder();
        keys.forEach(k -> System.out.print(k + " "));
        System.out.println();
        bst.delete(1);
        bst.delete(7);
        System.out.println("删除后");
        bst.infixOrder();
    }
}
