package zhwanwan.algs;

import java.util.Arrays;

/**
 * @author wangzhen
 * @create 2019-04-29 11:00 PM
 */
public class SortUtils {

    /**
     * 选择排序
     *
     * @param a
     */
    public static void selectSort(int a[]) {
        int len = a.length;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (a[i] > a[j]) {
                    a[i] = a[i] ^ a[j];
                    a[j] = a[j] ^ a[i];
                    a[i] = a[i] ^ a[j];
                }
            }
        }
    }

    /**
     * 插入排序
     *
     * @param a
     */
    public static void insertSort(int a[]) {
        int len = a.length;
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (a[i] < a[j]) {
                    a[i] = a[i] ^ a[j];
                    a[j] = a[j] ^ a[i];
                    a[i] = a[i] ^ a[j];
                }
            }
        }
    }

    /**
     * Shell Sort
     *
     * @param a
     */
    public static void shellSort(int a[]) {
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
     * Merge Sort
     * 1.Divide array into two halves
     * 2.Recursively sort each half
     * 3.Merge two halves.
     *
     * @param arr
     */
    public static void mergeSort(int arr[]) {
        int aux[] = new int[arr.length];
        mSort(arr, aux, 0, arr.length - 1);
        assert isSorted(arr);
    }

    private static void mSort(int arr[], int aux[], int lo, int hi) {
        if (lo >= hi)
            return;
        int mid = lo + (hi - lo) / 2;
        mSort(arr, aux, lo, mid);
        mSort(arr, aux, mid + 1, hi);
        merge(arr, aux, lo, mid, hi);
    }

    private static void merge(int arr[], int aux[], int lo, int mid, int hi) {
        assert isSorted(arr, lo, mid);
        assert isSorted(arr, mid + 1, hi);
        //copy arr to aux
        for (int k = lo; k <= hi; k++)
            aux[k] = arr[k];

        //merge back to arr
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid)
                arr[k] = aux[j++];
            else if (j > hi)
                arr[k] = aux[i++];
            else if (aux[i] > aux[j])
                arr[k] = aux[j++];
            else
                arr[k] = aux[i++];
        }
        assert isSorted(arr, lo, hi);
    }

    private static boolean isSorted(int arr[]) {
        return isSorted(arr, 0, arr.length - 1);
    }

    private static boolean isSorted(int arr[], int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (arr[i] < arr[i - 1])
                return false;
        return true;
    }

    /**
     * 快速排序
     * 1.找到分区点位置
     * 2.对分区左侧快速排序
     * 3.对分区右侧快速排序
     *
     * @param arr
     */
    public static void quickSort(int arr[]) {
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int arr[], int from, int to) {
        if (from < to) { //递归结束的条件
            //1.分区
            int base = partition2(arr, from, to);
            //2.对左边进行排序
            quickSort(arr, from, base - 1);
            //3.对右边进行排序
            quickSort(arr, base + 1, to);
        }
    }

    private static int partition(int a[], int i, int j) {
        int key = a[i];
        while (i < j) {
            while (a[j] >= key && i < j) {
                j--;
            }
            if (i < j) { //***这个条件一定不能少
                a[i] = a[j];
                i++;
            }
            while (a[i] < key && i < j) {
                i++;
            }
            if (i < j) { ////***这个条件一定不能少
                a[j] = a[i];
                j--;
            }
        }
        a[i] = key;
        return i;
    }

    private static int partition2(int a[], int lo, int hi) {
        int i = lo, j = hi + 1, v = a[lo];
        while (i < j) { //pointers not cross
            //find item on lo to swap
            while (a[++i] < v) {
                if (i == hi)
                    break;
            }
            //find item on hi to swap
            while (v < a[--j]) {
                if (j == lo)
                    break;
            }
            swap(a, i, j);
        }
        swap(a, lo, j);
        return j;
    }

    public static int quickSelect(int a[], int k) {
        int lo = 0, hi = a.length - 1;
        while (hi > lo) {
            int j = partition2(a, lo, hi);
            if (j > k)
                lo = j + 1;
            else if (j < k)
                hi = j - 1;
            else
                return a[k];
        }
        return a[k];
    }

    private static void swap(int a[], int i, int j) {
        if (i >= j)
            return;
        a[i] = a[i] ^ a[j];
        a[j] = a[j] ^ a[i];
        a[i] = a[i] ^ a[j];
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
        int n = arr.length;
        //1.Heap construction:构造"最大堆"--从下往上
        for (int k = n / 2; k >= 1; k--)
            sink(arr, k, n);

        //sort down
        while (n > 1) {
            exch(arr, 1, n--);
            sink(arr, 1, n);
        }
    }

    private static void sink(int a[], int k, int n) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && a[j - 1] < a[j])
                j++;
            if (a[k - 1] >= a[j - 1]) {
                /**
                 * 没有元素需要移动,停止处理
                 * (只有在当前元素需要交换后,才需要检查后面的是否需要移动)
                 */
                break;
            }
            exch(a, k, j);
            k = j;
        }
    }

    private static void exch(int a[], int i, int j) {
        if (i >= j)
            return;
        a[i - 1] = a[i - 1] ^ a[j - 1];
        a[j - 1] = a[j - 1] ^ a[i - 1];
        a[i - 1] = a[i - 1] ^ a[j - 1];
    }

    public static void main(String args[]) {
        int arr[] = {72, 6, 57, 88, 60, 42, 83, 43, 48, 85, 3, 5, 45, 32, 11, 2, 1, 99, 71};
        System.out.println(Arrays.toString(arr));
//        quickSort(arr);
//        selectSort(arr);
        insertSort(arr);
//        shellSort(arr);
//        swap(arr, 0, 1);
//        System.out.println(quickSelect(arr, 3));
//        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }

}
