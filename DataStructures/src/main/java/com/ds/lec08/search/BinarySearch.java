package com.ds.lec08.search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 二分查找
 * <p>
 * 注意：使用二分查找的前提是该数组是有序的.
 *
 * @author zhwanwan
 * @create 2019-09-09 23:18
 */
public class BinarySearch {

    public static void main(String[] args) {

        int arr[] = {1, 8, 10, 89, 1000, 1000, 1234};
        int resIndex = search(arr, 0, arr.length - 1, 10001);
        System.out.println("resIndex=" + resIndex);

        List<Integer> resIndexList = searchAll(arr, 0, arr.length - 1, 10001);
        System.out.println("resIndexList=" + resIndexList);
    }

    /**
     * 二分查找算法
     *
     * @param arr
     * @param left
     * @param right
     * @param val
     * @return 如果找到就返回下标，否则返回-1
     */
    public static int search(int[] arr, int left, int right, int val) {
        //System.out.println("二分查找被调用~");
        //递归结束条件：当 left > right 时，说明递归整个数组，但是没有找到
        if (left > right)
            return -1;
        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if (val > midVal)        //向右递归查找
            return search(arr, mid + 1, right, val);
        else if (val < midVal)   //向左递归查找
            return search(arr, left, mid - 1, val);
        else
            return mid;
    }

    /**
     * 查找所有相同值的下标
     * <p>
     * 思路分析
     * 1. 在找到mid 索引值，不要马上返回
     * 2. 向mid 索引值的左边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
     * 3. 向mid 索引值的右边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
     * 4. 返回List
     *
     * @param arr
     * @param left
     * @param right
     * @param val
     * @return
     */
    public static List<Integer> searchAll(int arr[], int left, int right, int val) {
        if (left > right)
            return Collections.singletonList(-1);
        int mid = (left + right) / 2;
        int midVal = arr[mid];
        if (val > midVal)        //向右递归查找
            return searchAll(arr, mid + 1, right, val);
        else if (val < midVal)   //向左递归查找
            return searchAll(arr, left, mid - 1, val);
        else {
            List<Integer> resIndexList = new ArrayList<>();
            //向左扫描重复值
            int temp = mid - 1;
            while (true) {
                if (temp < 0 || arr[temp] != val)
                    break;
                resIndexList.add(temp--);
            }
            resIndexList.add(mid);
            //向右扫描重复值
            temp = mid + 1;
            while (true) {
                if (temp > arr.length - 1 || arr[temp] != val)
                    break;
                resIndexList.add(temp--);
            }
            return resIndexList;
        }

    }

}
