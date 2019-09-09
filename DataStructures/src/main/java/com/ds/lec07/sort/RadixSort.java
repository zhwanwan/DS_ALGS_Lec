package com.ds.lec07.sort;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 基数排序
 * 基数排序（radix sort）属于“分配式排序”（distribution sort），
 * 又称“桶子法”（bucket sort）或bin sort，顾名思义，它是通过键值的各个位的值，
 * 将要排序的元素分配至某些“桶”中，达到排序的作用。
 * <p>
 * 基数排序法是属于稳定性的排序，基数排序法的是效率高的稳定性排序法。
 * <p>
 * 基数排序(Radix Sort)是桶排序的扩展。
 * <p>
 * 基数排序是1887年赫尔曼·何乐礼发明的。它是这样实现的：将整数按位数切割成不同的数字，然后按每个位数分别比较。
 * <p>
 * 算法思想：
 * 将所有待比较数值统一为同样的数位长度，数位较短的数前面补零。
 * 然后，从最低位开始，依次进行一次排序。这样从最低位排序一直到最高位排序完成以后, 数列就变成一个有序序列。
 * <p>
 * <p>
 * 例子：将数组 {53, 3, 542, 748, 14, 214} 使用基数排序, 进行升序排序
 * <p>
 * 第1轮排序 [按照个位排序]:
 * 说明： 事先准备10个数组(10个桶)， 0-9 分别对应 位数的 0-9
 * (1) 将各个数按照个位大小放入到 对应的各个数组中
 * (2) 然后从 0-9 个数组/桶，依次按照加入元素的先后顺序取出
 * <p>
 * 第2轮排序 [按照十位排序]
 * (1) 将各个数按照十位大小放入到对应的各个数组中
 * (2) 然后从 0-9 个数组/桶，依次按照加入元素的先后顺序取出
 * <p>
 * 第3轮排序[按照百位排序]
 * (1) 将各个数按照百位大小放入到对应的 各个数组中
 * (2) 然后从 0-9 个数组/桶，依次按照加入元素的先后顺序取出
 * <p>
 * <p>
 * 基数排序的说明:
 * 基数排序是对传统桶排序的扩展，速度很快.
 * 基数排序是经典的空间换时间的方式，占用内存很大, 当对海量数据排序时，容易造成 OutOfMemoryError 。
 * 基数排序时稳定的。
 * [注:假定在待排序的记录序列中，存在多个具有相同的关键字的记录，
 * 若经过排序，这些记录的相对次序保持不变，即在原序列中，r[i]=r[j]，且r[i]在r[j]之前，
 * 而在排序后的序列中，r[i]仍在r[j]之前，则称这种排序算法是稳定的；否则称为不稳定的]
 * 有负数的数组，我们不用基数排序来进行排序,
 * 如果要支持负数，参考: https://code.i-harness.com/zh-CN/q/e98fa9
 *
 * @author zhwanwan
 * @create 2019-09-09 21:51
 */
public class RadixSort {

    public static void main(String[] args) {
        /*int arr[] = {53, 3, 542, 748, 14, 214};
        System.out.println("排序前数组：" + Arrays.toString(arr));
        sort(arr);
        System.out.println("排序后数组：" + Arrays.toString(arr));*/

        int[] arr = new int[800000];
        ThreadLocalRandom random = ThreadLocalRandom.current();
        for (int i = 0; i < 800000; i++) {
            //arr[i] = (int)(Math.random() * 80000);
            arr[i] = random.nextInt(800000);
        }
        long start = System.currentTimeMillis();
        sort(arr); //107
        System.out.printf("耗时： %d\n", System.currentTimeMillis() - start);
    }

    public static void sort(int[] arr) {

        //定义一个二维数组，表示10个桶, 每个桶就是一个一维数组
        //说明
        //1. 二维数组包含10个一维数组
        //2. 为了防止在放入数的时候，数据溢出，则每个一维数组(桶)，大小定为arr.length
        //3. 名明确，基数排序是使用空间换时间的经典算法
        int[][] buckets = new int[10][arr.length];

        //为了记录每个桶中，实际存放了多少个数据,我们定义一个一维数组来记录各个桶的每次放入的数据个数
        //比如：bucketElementCounts[0] , 记录的就是 bucket[0] 桶的放入数据个数
        int[] bucketElementCounts = new int[10];

        //或者数组中最大的数
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max)
                max = arr[i];
        }
        //得到最大数是几位数
        int maxLength = (max + "").length();

        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            //(针对每个元素的对应位进行排序处理)， 第一次是个位，第二次是十位，第三次是百位..
            for (int j = 0; j < arr.length; j++) {
                int digitOfElement = arr[j] / n % 10;
                //放入对应的桶中
                buckets[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                bucketElementCounts[digitOfElement]++;
            }
            //按照这个桶的顺序(一维数组的下标依次取出数据，放入原来数组)
            int index = 0;
            //遍历每一桶，并将桶中是数据，放入到原数组
            for (int k = 0; k < bucketElementCounts.length; k++) {
                //如果桶中，有数据，我们才放入到原数组
                if (bucketElementCounts[k] != 0) {
                    //循环该桶即第k个桶(即第k个一维数组), 放入
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        arr[index++] = buckets[k][l];
                    }
                }
                bucketElementCounts[k] = 0;
            }
            //System.out.println("第" + (i + 1) + "轮排序处理 arr =" + Arrays.toString(arr));
        }

    }
}
