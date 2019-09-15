package com.ds.lec08.search;

import java.util.Arrays;

/**
 * 斐波那契(黄金分割法)查找算法
 * <p>
 * 黄金分割点是指把一条线段分割为两部分，使其中一部分与全长之比等于另一部分与这部分之比。
 * 取其前三位数字的近似值是0.618。由于按此比例设计的造型十分美丽，因此称为黄金分割，也称为中外比。
 * 这是一个神奇的数字，会带来意向不大的效果。
 * 斐波那契数列 {1, 1, 2, 3, 5, 8, 13, 21, 34, 55 } 发现斐波那契数列的两个相邻数的比例，无限接近黄金分割值0.618。
 * <p>
 * 斐波那契(黄金分割法)原理:
 * 斐波那契查找原理与前两种相似，仅仅改变了中间结点（mid）的位置，mid不再是中间或插值得到，
 * 而是位于黄金分割点附近，即mid=low+F(k-1)-1 （F代表斐波那契数列）.
 * <p>
 * 对F(k-1)-1的理解：
 * 由斐波那契数列 F[k]=F[k-1]+F[k-2] 的性质，可以得到 （F[k]-1）=（F[k-1]-1）+（F[k-2]-1）+1 。
 * 该式说明：只要顺序表的长度为F[k]-1，则可以将该表分成长度为F[k-1]-1和F[k-2]-1的两段，从而中间位置为mid=low+F(k-1)-1
 * <p>
 * 类似的，每一子段也可以用相同的方式分割
 * 但顺序表长度n不一定刚好等于F[k]-1，所以需要将原来的顺序表长度n增加至F[k]-1。
 * 这里的k值只要能使得F[k]-1恰好大于或等于n即可，由以下代码得到,顺序表长度增加后，新增的位置（从n+1到F[k]-1位置），都赋为n位置的值即可。
 *
 * @author zhwanwan
 * @create 2019-09-09 23:44
 */
public class FibonacciSearch {

    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234};
        System.out.println("index=" + search(arr, 89));// 0
    }

    private static int maxSize = 20;

    private static int[] fib() {
        int[] f = new int[maxSize];
        f[0] = 0;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    public static int search(int[] arr, int val) {
        int low = 0;
        int high = arr.length - 1;
        int k = 0; //斐波那契分割数值的下标
        int mid = 0;
        int[] f = fib();
        while (high > f[k] - 1)
            k++;
        int[] tempArr = Arrays.copyOf(arr, f[k]);
        //用arr最后的元素填充
        for (int i = high + 1; i < tempArr.length; i++) {
            tempArr[i] = arr[high];
        }
        while (low <= high) {
            mid = low + f[k - 1] - 1;
            if (val < tempArr[mid]) {
                high = mid - 1;
                //说明
                //1. 全部元素 = 前面的元素 + 后边元素
                //2. f[k] = f[k-1] + f[k-2]
                //因为 前面有 f[k-1]个元素,所以可以继续拆分 f[k-1] = f[k-2] + f[k-3]
                //即 在 f[k-1] 的前面继续查找 k--
                //即下次循环 mid = f[k-1-1]-1
                k--;
            } else if (val > tempArr[mid]) {
                low = mid + 1;
                //1. 全部元素 = 前面的元素 + 后边元素
                //2. f[k] = f[k-1] + f[k-2]
                //3. 因为后面我们有f[k-2] 所以可以继续拆分 f[k-1] = f[k-3] + f[k-4]
                //4. 即在f[k-2] 的前面进行查找 k -=2
                //5. 即下次循环 mid = f[k - 1 - 2] - 1
                k -= 2;
            } else {
                return Math.min(mid, high);
            }
        }

        return -1;
    }


}
