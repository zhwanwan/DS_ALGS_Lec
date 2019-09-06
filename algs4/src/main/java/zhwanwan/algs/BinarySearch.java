package zhwanwan.algs;

import java.util.Arrays;

/**
 * @author wangzhen
 * @create 2019-04-28 10:24 AM
 */
public class BinarySearch {

    /**
     * 查找key在数组a的位置
     *
     * @param a
     * @param key
     * @return
     */
    public static int binarySearch(int[] a, int key) {
        return binarySearchWile(a, key, 0, a.length - 1);
    }

    /**
     * rank方法-loop
     *
     * @param a
     * @param key
     * @return
     */
    public static int rank(int[] a, int key) {
        int low = 0;
        int high = a.length - 1;
        //当low <= high--继续查找
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (key > a[mid]) {
                low = mid + 1;
            } else if (key < a[mid]) {
                high = mid - 1;
            } else {
                return mid;
            }
        }
        //low > high--key不存在,key应该在low当前位置
        return low;
    }

    /**
     * 递归实现
     *
     * @param a    数组
     * @param key  被查找元素值
     * @param low  开始位置
     * @param high 结束位置
     * @return 查找元素的下标
     */
    private static int binarySearchRecursion(int[] a, int key, int low, int high) {
        if (low > high)
            return -1;
        int mid = low + (high - low) / 2;
        if (key < a[mid])
            return binarySearchRecursion(a, key, low, mid - 1);
        else if (key > a[mid])
            return binarySearchRecursion(a, key, mid + 1, high);
        else
            return mid;

    }

    /**
     * 非递归实现
     *
     * @param a
     * @param key
     * @param fromIndex
     * @param toIndex
     * @return
     */
    private static int binarySearchWile(int[] a, int key, int fromIndex, int toIndex) {
        int low = fromIndex;
        int high = toIndex - 1;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            int midVal = a[mid];
            if (midVal < key)
                low = mid + 1;
            else if (midVal > key)
                high = mid - 1;
            else
                return mid;
        }
        return -(low + 1);
    }


    /**
     * rank方法--recursive
     *
     * @param a
     * @param key
     * @param low
     * @param high
     * @return
     */
    public static int rank(int[] a, int key, int low, int high) {
        if (low > high)
            return low;
        int mid = low + (high - low) / 2;
        if (key < a[mid])
            return rank(a, key, low, mid - 1);
        else if (key > a[mid])
            return rank(a, key, mid + 1, high);
        else
            return mid;
    }

    public static void main(String[] args) {
        int[] a = {9, 33, 4, 2, 56, 24, 22};
        Arrays.sort(a);
//        System.out.println(Arrays.toString(a));
//        System.out.println(rank(a, 111));
        System.out.println(binarySearchWile(a, 22, 0, a.length - 1));
        int i = 0x7fffffff;
        System.out.println(Integer.toBinaryString(i));
    }
}
