package com.ds.lec07.sort;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 选择排序
 * 选择式排序也属于内部排序法，是从欲排序的数据中，按指定的规则选出某一元素，再依规定交换位置后达到排序的目的。
 * <p>
 * 选择排序思想:
 * <p>
 * 选择排序（select sorting）也是一种简单的排序方法。
 * 它的基本思想是：
 * 第一次从arr[0]~arr[n-1]中选取最小值，与arr[0]交换，
 * 第二次从arr[1]~arr[n-1]中选取最小值，与arr[1]交换，
 * 第三次从arr[2]~arr[n-1]中选取最小值，与arr[2]交换，…，
 * 第i次从arr[i-1]~arr[n-1]中选取最小值，与arr[i-1]交换，…,
 * 第n-1次从arr[n-2]~arr[n-1]中选取最小值，与arr[n-2]交换，
 * 总共通过n-1次，得到一个按排序从小到大排列的有序序列。
 * <p>
 * 选择排序只要交换n-1次即可！
 * <p>
 * 说明：
 * 1. 选择排序一共有 数组大小 - 1轮排序
 * 2. 每1轮排序，又是一个循环, 循环的规则(代码)
 * 2.1先假定当前这个数是最小数
 * 2.2 然后和后面的每个数进行比较，如果发现有比当前数更小的数，就重新确定最小数，并得到下标
 * 2.3 当遍历到数组的最后时，就得到本轮最小数和下标
 * 2.4 交换
 *
 * @author zhwanwan
 * @create 2019-09-04 19:18
 */
public class SelectSort {

    public static void main(String[] args) {
        /*int[] arr = {-1, 9, 0, 5, 3, 2, 7, 4, 8, 1, -2, 6};
        System.out.println(Arrays.toString(arr));
        sort2(arr);
        System.out.println(Arrays.toString(arr));*/
        int[] arr = new int[80000];
        ThreadLocalRandom random = ThreadLocalRandom.current();
        for (int i = 0; i < 80000; i++) {
            //arr[i] = (int)(Math.random() * 8000000);
            arr[i] = random.nextInt(80000);
        }
        long start = System.currentTimeMillis();
        //sort(arr); //16688
        sort2(arr); //5569
        System.out.printf("耗时： %d\n", System.currentTimeMillis() - start);

    }

    public static void sort(int[] arr) {
        for (int i = 0, cnt = arr.length - 1; i < cnt; i++) {
            for (int j = i + 1; j <= cnt; j++) {
                if (arr[i] > arr[j]) {
                    arr[i] = arr[i] ^ arr[j];
                    arr[j] = arr[j] ^ arr[i];
                    arr[i] = arr[i] ^ arr[j];
                }
            }
        }
    }

    /**
     * 选择排序思路：
     * 1. 选择排序一共有 数组大小 - 1 轮排序
     * 2. 每1轮排序，又是一个循环, 循环的规则(代码)
     * 2.1先假定当前这个数是最小数
     * 2.2 然后和后面的每个数进行比较，如果发现有比当前数更小的数，就重新确定最小数，并得到下标
     * 2.3 当遍历到数组的最后时，就得到本轮最小数和下标
     * 2.4 交换
     *
     * @param arr
     */
    public static void sort2(int[] arr) {
        for (int i = 0, cnt = arr.length - 1; i < cnt; i++) {
            int minIndex = i;
            int min = arr[i];
            for (int j = i + 1; j <= cnt; j++) {
                if (min > arr[j]) {
                    min = arr[j];
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }
    }
}
