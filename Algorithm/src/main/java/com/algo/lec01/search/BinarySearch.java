package com.algo.lec01.search;

import java.util.Arrays;

/**
 * @author zhwanwan
 * @create 2019-09-26 2:17 PM
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] arr = {1, 3, 8, 10, 11, 67, 100};
        System.out.printf("index of %d in %s is %d\n", 67, Arrays.toString(arr), searchNoRecursion(arr, 67));
    }

    public static int search(int[] arr, int target) {
        return search(arr, 0, arr.length - 1, target);
    }

    private static int search(int[] arr, int low, int high, int target) {
        if (low > high)
            return -1;
        int mid = (low + high) / 2;
        if (target < arr[mid])
            return search(arr, low, mid - 1, target);
        else if (target > arr[mid])
            return search(arr, mid + 1, high, target);
        else
            return mid;
    }

    public static int searchNoRecursion(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        while (low < high) {
            int mid = (low + high) / 2;
            if (target < arr[mid])
                high = mid - 1;
            else if (target > arr[mid])
                low = mid + 1;
            else
                return mid;
        }
        return -1;
    }
}
