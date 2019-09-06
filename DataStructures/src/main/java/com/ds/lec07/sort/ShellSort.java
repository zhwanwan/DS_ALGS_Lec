package com.ds.lec07.sort;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 希尔排序
 * 希尔排序是希尔（Donald Shell）于1959年提出的一种排序算法。
 * 希尔排序也是一种插入排序，它是简单插入排序经过改进之后的一个更高效的版本，也称为缩小增量排序。
 * <p>
 * 希尔排序法基本思想
 * 希尔排序是把记录按下标的一定增量分组，对每组使用直接插入排序算法排序；
 * 随着增量逐渐减少，每组包含的关键词越来越多，当增量减至1时，整个文件恰被分成一组，算法便终止
 * <p>
 * 程序在插入排序算法逻辑中分别采用交换法(效率慢，代码简洁)和移动法(效率高)
 *
 * @author zhwanwan
 * @create 2019-09-06 22:41
 */
public class ShellSort {

    public static void main(String[] args) {

        int[] arr = {-1, 9, 0, 5, 3, 2, 7, 4, 8, 1, -2, 6};
        System.out.println("排序前数组：" + Arrays.toString(arr));
        shellSort(arr);
        System.out.println("排序后数组：" + Arrays.toString(arr));

        /*int[] arr = new int[80000];
        ThreadLocalRandom random = ThreadLocalRandom.current();
        for (int i = 0; i < 80000; i++) {
            //arr[i] = (int)(Math.random() * 80000);
            arr[i] = random.nextInt(80000);
        }
        long start = System.currentTimeMillis();
        //sort(arr); // 6664
        //sort2(arr); // 17132
        //shellSort(arr); //20
        shellSort2(arr); //7640
        System.out.printf("耗时： %d\n", System.currentTimeMillis() - start);*/
    }

    /**
     * 12个元素
     * -1/, 9?, 0！, 5@, 3#, 2$, 7/, 4?, 8！, 1@, -2#, 6$
     * -1!, 4@, 0#, 1!, -2@, 2#, 7!, 9@, 8#, 5!, 3@, 6#
     * k           j
     * 5           7
     * -1!, -2!, 0!, 1!, 3!, 2!, 5!, 4!, 6!, 7!, 9!, 8!
     * -2, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9
     * 第一轮： 12/2 = 6
     * 第二轮： 6/2 = 3
     * 第三轮： 3/2 = 1
     *
     * @param arr
     */
    public static void sort(int[] arr) {
        int len = arr.length;
        int increment = len / 2;
        while (increment != 0) {
            for (int i = 0; i < increment; i++) {
                for (int j = i + increment; j < len; j += increment) {
                    for (int k = i; k < j; k += increment) {
                        //移动法
                        if (arr[j] < arr[k]) {
                            int val = arr[j];
                            int end = j;
                            while (k < end) {
                                arr[end] = arr[end - increment];
                                end -= increment;
                            }
                            arr[k] = val;
                            break;
                        }
                    }
                }
            }
            increment /= 2;
        }
    }

    public static void shellSort(int[] arr) {
        int len = arr.length;
        // 增量gap, 并逐步的缩小增量
        for (int gap = len / 2; gap > 0; gap /= 2) {
            // 从第gap个元素，逐个对其所在的组进行直接插入排序
            for (int i = gap; i < len; i++) {
                int j = i;
                int temp = arr[j];
                if (arr[j] < arr[j - gap]) {
                    while (j - gap >= 0 && temp < arr[j - gap]) {
                        //移动
                        arr[j] = arr[j - gap];
                        j -= gap;
                    }
                    //当退出while后，就给temp找到插入的位置
                    arr[j] = temp;
                }

            }
        }
    }

    public static void shellSort2(int a[]) {
        int len = a.length;
        int h = 1;
        // 3x+1 increment sequence:  1, 4, 13, 40, 121, 364, 1093, ...
        while (h < len / 3)
            h = 3 * h + 1;
        while (h >= 1) {
            for (int i = h; i < len; i++) {
                for (int j = i; j >= h; j -= h) {
                    if (a[j] < a[j - h]) {
                        a[j] = a[j] ^ a[j - h];
                        a[j - h] = a[j - h] ^ a[j];
                        a[j] = a[j] ^ a[j - h];
                    }
                }
            }
            h /= 3;
        }
    }

    /**
     * 12个元素
     * -1/, 9?, 0！, 5@, 3#, 2$, 7/, 4?, 8！, 1@, -2#, 6$
     * -1!, 4@, 0#, 1!, -2@, 2#, 7!, 9@, 8#, 5!, 3@, 6#
     * -1!, -2!, 0!, 1!, 3!, 2!, 5!, 4!, 6!, 7!, 9!, 8!
     * -2, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9
     * 第一轮： 12/2 = 6
     * 第二轮： 6/2 = 3
     * 第三轮： 3/2 = 1
     *
     * @param arr
     */
    public static void sort2(int[] arr) {
        int len = arr.length;
        int increment = len / 2;
        while (increment != 0) {
            for (int i = 0; i < increment; i++) {
                for (int j = i + increment; j < len; j += increment) {
                    for (int k = 1; k < j; k += increment) {
                        //交换法
                        if (arr[j] < arr[k]) {
                            arr[j] = arr[j] ^ arr[k];
                            arr[k] = arr[k] ^ arr[j];
                            arr[j] = arr[j] ^ arr[k];
                        }
                    }
                }
            }
            increment /= 2;
        }
    }


}
