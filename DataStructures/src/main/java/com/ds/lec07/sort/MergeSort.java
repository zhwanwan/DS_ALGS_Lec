package com.ds.lec07.sort;

import java.util.Arrays;

/**
 * 归并排序
 * <p>
 * 归并排序（MERGE-SORT）是利用归并的思想实现的排序方法，
 * 该算法采用经典的分治（divide-and-conquer）策略.
 * 分治法将问题分(divide)成一些小的问题然后递归求解，
 * 而治(conquer)的阶段则将分的阶段得到的各答案"修补"在一起，即分而治之。
 *
 * @author zhwanwan
 * @create 2019-09-08 12:39
 */
public class MergeSort {

    public static void main(String[] args) {

        int[] arr = {-1, 9, 0, 5, 3, 2, 7, 4, 8, 1, -2, 6};
        System.out.println("排序前数组：" + Arrays.toString(arr));
        sort(arr);
        System.out.println("排序后数组：" + Arrays.toString(arr));

        /*int[] arr = new int[800000];
        ThreadLocalRandom random = ThreadLocalRandom.current();
        for (int i = 0; i < 800000; i++) {
            //arr[i] = (int)(Math.random() * 80000);
            arr[i] = random.nextInt(800000);
        }
        long start = System.currentTimeMillis();
        sort(arr); //195
        System.out.printf("耗时： %d\n", System.currentTimeMillis() - start);*/
    }

    public static void sort(int[] arr) {
        int[] aux = new int[arr.length];
        mergeSort(arr, 0, arr.length - 1, aux);
    }

    private static void mergeSort(int[] arr, int left, int right, int[] tempArr) {
        if (left < right) {
            int mid = (left + right) / 2;
            //向左递归进行分解
            mergeSort(arr, left, mid, tempArr);
            //向右递归进行分解
            mergeSort(arr, mid + 1, right, tempArr);
            //合并
            merge(arr, left, mid, right, tempArr);
        }
    }

    /**
     * 合并的方法
     *
     * @param arr     待排序数组
     * @param left    待排序数组左边有序序列的初始索引
     * @param mid     待排序数组中间索引
     * @param right   待排序数组右端索引
     * @param tempArr 临时数组
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] tempArr) {

        int i = left;    //初始化i,左边有序序列的初始索引
        int j = mid + 1; //初始化j,右边有序序列的初始索引
        int t = 0;       //临时数组当前索引

        //1.把左右两边序列填充按大小顺序填充到临时数组中
        //直到左右序列有一段处理完
        while (i <= mid && j <= right) {
            if (arr[i] < arr[j]) {
                tempArr[t++] = arr[i++];
            } else {
                tempArr[t++] = arr[j++];
            }
        }

        //2.把剩余一端序列剩下的数组依次填充到临时数组
        //处理左边剩下的元素
        while (i <= mid)
            tempArr[t++] = arr[i++];

        //处理右边剩下的元素
        while (j <= right)
            tempArr[t++] = arr[j++];

        //3.将临时数组中数组拷贝至原始数组
        //注意：并不是每次拷贝所有
        t = 0;
        int tl = left;
        while (tl <= right) {
            arr[tl++] = tempArr[t++];
        }

    }
}
