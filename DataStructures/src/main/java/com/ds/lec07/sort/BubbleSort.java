package com.ds.lec07.sort;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 冒泡排序
 * <p>
 * 冒泡排序（Bubble Sorting）的基本思想是：
 * 通过对待排序序列从前向后（从下标较小的元素开始）,依次比较相邻元素的值，
 * 若发现逆序则交换，使值较大的元素逐渐从前移向后部，就象水底下的气泡一样逐渐向上冒。
 * 对于N个元素进行冒泡排序，最多需要排N-1趟（如果N-1个元素位置确定了，最后一个元素也就确定了）
 * <p>
 * 在排序的过程中，各元素不断接近自己的位置，如果一趟比较下来没有进行过交换，
 * 就说明序列有序，因此要在排序过程中设置一个标志flag判断元素是否进行过交换。
 * 从而减少不必要的比较。
 * <p>
 * 时间复杂度：O(n^2)
 *
 * @author zhwanwan
 * @create 2019-09-04 16:45
 */
public class BubbleSort {

    public static void main(String[] args) {
        //int[] arr = {-1, 9, 0, 5, 3, 2, 7, 4, 8, 1};
        int[] arr = new int[80000];
        ThreadLocalRandom random = ThreadLocalRandom.current();
        for (int i = 0; i < 80000; i++) {
            //arr[i] = (int)(Math.random() * 8000000);
            arr[i] = random.nextInt(80000);
        }
        long start = System.currentTimeMillis();
        sort(arr);
        System.out.printf("耗时： %d\n", System.currentTimeMillis() - start);

    }

    public static void sort(int[] arr) {
        //定义标识变量，表示是否交换
        boolean flag = false;
        for (int i = 0, cnt = arr.length - 1; i < cnt; i++) {
            for (int j = 0; j < cnt - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    arr[j] = arr[j] ^ arr[j + 1];
                    arr[j + 1] = arr[j + 1] ^ arr[j];
                    arr[j] = arr[j] ^ arr[j + 1];
                }
            }
            if (!flag)
                //本趟排序没有发生交换，则结束
                break;
            else
                //本趟排序发生交换，需要重置flag!!!，进行下趟排序
                flag = false;
        }
    }

}
