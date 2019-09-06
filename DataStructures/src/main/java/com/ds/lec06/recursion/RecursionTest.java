package com.ds.lec06.recursion;

/**
 * @author zhwanwan
 * @create 2019-09-02 13:09
 */
public class RecursionTest {
    public static void main(String[] args) {
        test(4);
        int n = 6;
        System.out.printf("%d! = %d\n", n, factorial(n));
        System.out.printf("fibonacci(%d) = %d\n", n, fibonacci(n));
    }

    /**
     * 打印问题
     *
     * @param n
     */
    public static void test(int n) {
        if (n > 2) {
            test(n - 1);
        }
        System.out.println("n=" + n);
    }

    /**
     * 阶乘问题
     *
     * @param n
     * @return
     */
    public static int factorial(int n) {
        if (n == 1)
            return 1;
        else
            return factorial(n - 1) * n;
    }

    /**
     * 斐波那契数列
     * 优化点：存在重复计算
     *
     * @param n
     * @return
     */
    public static int fibonacci(int n) {
        if (n == 0 || n == 1)
            return n;
        else
            return fibonacci(n - 1) + fibonacci(n - 2);
    }

}
