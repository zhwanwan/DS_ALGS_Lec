package com.ds.lec03.sparsearray;

import java.util.Arrays;

/**
 * 二维数组转稀疏数组：
 * 1.遍历原始的二维数组，得到有效数据的个数sum
 * 2.根据sum创建稀疏数组sparseArr int[sum+1][3]
 * 3.将二维数组的有效数据存入到稀疏数组
 * <p>
 * 稀疏数组转二维数组：
 * 1.先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组
 * 2.读取稀疏数组后几行数据，并赋值给原始二维数据组
 *
 * @author zhwanwan
 * @create 2019-08-14 22:22
 */
public class SparseArray {

    /**
     * 二维数组转稀疏数组
     *
     * @param arrs
     * @return
     */
    public static int[][] convertToSparseArray(int[][] arrs) {

        if (arrs == null)
            return arrs;
        ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
        threadLocal.set(0);
        Arrays.stream(arrs).forEach(
                arr -> {
                    Arrays.stream(arr).forEach(
                            i -> {
                                if (i != 0)
                                    threadLocal.set(threadLocal.get() + 1);
                            }
                    );
                }
        );

        int sum = threadLocal.get();
        int sparseArrs[][] = new int[sum + 1][3];
        //稀疏数组首行赋值[rowNum][colNum][sum]
        sparseArrs[0][0] = 11;
        sparseArrs[0][1] = 11;
        sparseArrs[0][2] = sum;
        int count = 0;
        for (int i = 0, len1 = arrs.length; i < len1; i++) {
            for (int j = 0, len2 = arrs[i].length; j < len2; j++) {
                if (arrs[i][j] != 0) {
                    count++;
                    sparseArrs[count][0] = i;
                    sparseArrs[count][1] = j;
                    sparseArrs[count][2] = arrs[i][j];
                }
            }
        }
        return sparseArrs;
    }

    /**
     * 稀疏数组转二维数组
     *
     * @param sparseArray
     * @return
     */
    public static int[][] convertToArrays(int[][] sparseArray) {
        if (sparseArray == null)
            return sparseArray;

        int[][] arrs = new int[sparseArray[0][0]][sparseArray[0][1]];
        for (int i = 1, len = sparseArray.length; i < len; i++) {
            arrs[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
        }
        return arrs;
    }

    public static void printArrays(int[][] arrs) {
        if (arrs == null)
            return;

        Arrays.stream(arrs).forEach(
                arr -> {
                    Arrays.stream(arr).forEach(
                            i -> System.out.printf("%d\t", i)

                    );
                    System.out.println();
                }
        );
    }

    public static void main(String[] args) {

        // 创建一个原始的二维数组 11 * 11
        // 0: 表示没有棋子， 1 表示 黑子 2 表蓝子
        int chessArray[][] = new int[11][11];
        chessArray[1][2] = 1;
        chessArray[2][3] = 2;
        chessArray[4][5] = 2;
        System.out.println("原始二维数组~~");
        printArrays(chessArray);
        int[][] sparseArray = convertToSparseArray(chessArray);
        System.out.println("得到稀疏数组为~~~~");
        printArrays(sparseArray);
        int[][] arrays = convertToArrays(sparseArray);
        System.out.println("稀疏数组恢复到原始二维数组~~~~");
        printArrays(arrays);

    }

}
