package com.ds.lec08.search;

/**
 * 插值查找算法
 * <p>
 * 插值查找算法类似于二分查找，不同的是插值查找每次从自适应mid处开始查找。
 * 将折半查找中的求mid索引的公式 , low表示左边索引left, high表示右边索引right.  key就是前面我们讲的val。
 * <p>
 * 插值索引：
 * int mid = low + (high - low) * (key - arr[low]) / (arr[high] - arr[low])  ;
 * 即：
 * int mid = left + (right – left) * (val – arr[left]) / (arr[right] – arr[left])
 * <p>
 * 插值查找注意事项：
 * 对于数据量较大，关键字分布比较均匀的查找表来说，采用插值查找, 速度较快.
 * 关键字分布不均匀的情况下，该方法不一定比折半查找要好.
 *
 * @author zhwanwan
 * @create 2019-09-09 23:37
 */
public class InsertValueSearch {

    public static void main(String[] args) {
        int arr[] = {1, 8, 10, 89, 1000, 1000, 1234};

        //int index = search(arr, 0, arr.length - 1, 1234);
        int index = BinarySearch.search(arr, 0, arr.length, 1234);
        System.out.println("index = " + index);
    }

    public static int search(int[] arr, int left, int right, int val) {
        System.out.println("插值查找次数~~");
        if (left > right || val < arr[0] || val > arr[arr.length - 1])
            return -1;
        //求出mid, 自适应
        int mid = left + (right - left) * (val - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];
        if (val > midVal)        //向右递归查找
            return search(arr, mid + 1, right, val);
        else if (val < midVal)   //向左递归查找
            return search(arr, left, mid - 1, val);
        else
            return mid;
    }

}
