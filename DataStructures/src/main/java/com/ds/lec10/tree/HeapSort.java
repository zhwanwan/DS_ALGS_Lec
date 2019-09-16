package com.ds.lec10.tree;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 堆排序
 * 基本介绍
 * 1.堆排序是利用堆这种数据结构而设计的一种排序算法，堆排序是一种选择排序，
 * 它的最坏/最好/平均时间复杂度均为O(nlogn)，它也是不稳定排序。
 * 2.堆是具有以下性质的完全二叉树：
 * 每个结点的值都大于或等于其左右孩子结点的值，称为大顶堆,
 * 注意 : 没有要求结点的左孩子的值和右孩子的值的大小关系。
 * 3.每个结点的值都小于或等于其左右孩子结点的值，称为小顶堆。
 * 4.大顶堆特点：arr[i] >= arr[2*i+1] && arr[i] >= arr[2*i+2] //i对应第几个节点，i从0开始编号
 * 5.小顶堆特点：arr[i] <= arr[2*i+1] && arr[i] <= arr[2*i+2] //i对应第几个节点，i从0开始编号
 * 6.一般升序采用大顶堆，降序采用小顶堆。
 * <p>
 * <p>
 * 基本思想
 * 1.将待排序序列构造成一个大顶堆
 * 2.此时，整个序列的最大值就是堆顶的根节点。
 * 3.将其与末尾元素进行交换，此时末尾就为最大值。
 * 4.将剩余n-1个元素重新构造成一个堆，这样会得到n个元素的次小值。如此反复执行，便能得到一个有序序列了。
 * 可以看到在构建大顶堆的过程中，元素的个数逐渐减少，最后就得到一个有序序列了。
 *
 * @author zhwanwan
 * @create 2019-09-15 4:54 PM
 */
public class HeapSort {
    public static void main(String[] args) {

        /*int[] arr = {4, 6, 8, 5, 9, -1, 4, -2, 7, -99};
        sort(arr);
        System.out.println("堆排序后：" + Arrays.toString(arr));*/

        int[] arr = new int[8000000];
        ThreadLocalRandom random = ThreadLocalRandom.current();
        for (int i = 0; i < 8000000; i++) {
            //arr[i] = (int)(Math.random() * 80000);
            arr[i] = random.nextInt(800000);
        }
        long start = System.currentTimeMillis();
        sort(arr); //八百万个数耗时 2839 ms
        System.out.printf("耗时： %d\n", System.currentTimeMillis() - start);
    }

    /**
     * 堆排序算法
     * 1).将无序序列构建成一个堆，根据升序降序需求选择大顶堆或小顶堆;
     * 2).将堆顶元素与末尾元素交换，将最大元素"沉"到数组末端;
     * 3).重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素，
     * 反复执行调整+交换步骤，直到整个序列有序。
     *
     * @param arr
     */
    public static void sort(int[] arr) {
        int length = arr.length;
        //将无序序列构建成一个堆，根据升序降序需求选择大顶堆或小顶堆
        for (int i = length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, length);
        }
        //2.将堆顶元素与末尾元素交换，将最大元素"沉"到数组末端;
        //3.重新调整结构
        for (int j = length - 1; j > 0; j--) {
            //arr[0]与arr[j]交换
            arr[0] = arr[0] ^ arr[j];
            arr[j] = arr[j] ^ arr[0];
            arr[0] = arr[0] ^ arr[j];
            adjustHeap(arr, 0, j);
        }

    }

    /**
     * 将一个数组（二叉树）调整成一个最大堆
     * 功能： 完成将以i对应的非叶子结点的树调整成大顶堆
     * int arr[] = {4, 6, 8, 5, 9}; => i = 1 => adjustHeap => 得到 {4, 9, 8, 5, 6}
     * 如果我们再次调用  adjustHeap 传入的是 i = 0 => 得到 {4, 9, 8, 5, 6} => {9,6,8,5, 4}
     *
     * @param arr    待调整的数组
     * @param i      非叶子结点在数组中索引
     * @param length 对多少个元素继续调整
     */
    public static void adjustHeap(int[] arr, int i, int length) {
        int temp = arr[i];
        //k = i * 2 + 1 , k是i结点的左子结点
        for (int k = 2 * i + 1; k < length; k = k * 2 + 1) {
            if (k + 1 < length && arr[k] < arr[k + 1]) //说明左子结点的值小于右子结点的值
                k++;
            if (arr[k] > temp) { //如果子结点大于父结点
                arr[i] = arr[k]; //把较大的值赋给当前结点
                i = k; //!!! i指向k,继续循环比较
            } else
                break;
        }
        //当for循环结束后将以i为父结点的树的最大值,放在最顶(局部)
        arr[i] = temp; //将temp值放到调整后的位置
    }

    /**
     * 堆排序-升序
     * 1.Building heap using bottom-up method
     * 2.
     * --Remove the maximum, one at a time;
     * --Leave in array,instead of nulling out
     *
     * @param arr
     */
    public static void heapSort(int arr[]) {
        int len = arr.length;
        //1.Heap construction:构造"最大堆"--从下往上
        for (int k = len / 2; k >= 1; k--)
            sink(arr, k, len);

        //sort down
        while (len > 1) {
            exch(arr, 1, len--);
            sink(arr, 1, len);
        }
    }

    /**
     * @param arr    数组
     * @param i      第几个元素，从1开始
     * @param length 数组长度
     */
    private static void sink(int arr[], int i, int length) {
        while (2 * i <= length) {
            int j = 2 * i;
            if (j < length && arr[j - 1] < arr[j])
                j++;
            if (arr[i - 1] >= arr[j - 1]) {
                /**
                 * 没有元素需要移动,停止处理
                 * (只有在当前元素需要交换后,才需要检查后面的是否需要移动)
                 */
                break;
            }
            exch(arr, i, j);
            i = j;
        }
    }

    /**
     * 交换数组元素
     *
     * @param arr 数组
     * @param i   数组中第i个元素
     * @param j   数组中第j个元素
     */
    private static void exch(int arr[], int i, int j) {
        if (i >= j)
            return;
        arr[i - 1] = arr[i - 1] ^ arr[j - 1];
        arr[j - 1] = arr[j - 1] ^ arr[i - 1];
        arr[i - 1] = arr[i - 1] ^ arr[j - 1];
    }

}
