package com.ds.lec07.sort;

import java.util.Arrays;

/**
 * 插入排序
 * 插入式排序属于内部排序法，是对于欲排序的元素以插入的方式找寻该元素的适当位置，以达到排序的目的。
 * <p>
 * 插入排序法思想:
 * <p>
 * 插入排序（Insertion Sorting）的基本思想是：
 * 把n个待排序的元素看成为一个有序表和一个无序表，开始时有序表中只包含一个元素，无序表中包含有n-1个元素，
 * 排序过程中每次从无序表中取出第一个元素，把它的排序码依次与有序表元素的排序码进行比较，
 * 将它插入到有序表中的适当位置，使之成为新的有序表。
 * <p>
 * 本例中插入排序算法逻辑中分别采用交换法(效率慢，代码简洁)和移动法(效率高)
 *
 * @author zhwanwan
 * @create 2019-09-05 13:53
 */
public class InsertSort {

    public static void main(String[] args) {
        int[] arr = {-1, 9, 0, 5, 3, 2, 7, 4, 8, 1, -2, 6, 100};
        System.out.println("排序前数组：" + Arrays.toString(arr));
        sort(arr);
        System.out.println("排序后数组：" + Arrays.toString(arr));

        /*int[] arr = new int[80000];
        ThreadLocalRandom random = ThreadLocalRandom.current();
        for (int i = 0; i < 80000; i++) {
            //arr[i] = (int)(Math.random() * 80000);
            arr[i] = random.nextInt(80000);
        }
        long start = System.currentTimeMillis();
        sort(arr); // 1737
        //sort2(arr); //12980
        System.out.printf("耗时： %d\n", System.currentTimeMillis() - start);*/
    }

    public static void sort(int[] arr) {
        for (int i = 1, cnt = arr.length - 1; i <= cnt; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] < arr[j]) {
                    //移动法
                    int val = arr[i];
                    //将位置j到i-1的元素后移
                    int end = i;
                    while (j < end) {
                        arr[end] = arr[end - 1];
                        end--;
                    }
                    //将i位置元素插入到j位置
                    arr[j] = val;
                    break;
                }
            }
            //System.out.println("第" + i + "轮排序 ：" + Arrays.toString(arr));
        }
    }

    //效率低下
    public static void sort2(int a[]) {
        int len = a.length;
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (a[i] < a[j]) {
                    //交换法
                    a[i] = a[i] ^ a[j];
                    a[j] = a[j] ^ a[i];
                    a[i] = a[i] ^ a[j];
                }
            }
        }
    }

}
