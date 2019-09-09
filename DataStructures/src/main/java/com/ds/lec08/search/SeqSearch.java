package com.ds.lec08.search;

/**
 * 线性查找
 *
 * @author zhwanwan
 * @create 2019-09-09 23:15
 */
public class SeqSearch {

    public static void main(String[] args) {
        int arr[] = {1, 9, 11, -1, 34, 89};// 没有顺序的数组
        int index = search(arr, -1);
        if (index == -1) {
            System.out.println("没有找到到");
        } else {
            System.out.println("找到，下标为=" + index);
        }
    }

    public static int search(int[] arr, int value) {
        for (int i = 0, len = arr.length; i < len; i++) {
            if (arr[i] == value)
                return i;
        }
        return -1;
    }
}
