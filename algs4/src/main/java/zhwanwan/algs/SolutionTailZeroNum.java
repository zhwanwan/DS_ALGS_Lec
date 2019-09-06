package zhwanwan.algs;

import java.util.Scanner;

/**
 * @author wangzhen
 * @create 2019-05-06 3:12 PM
 */
public class SolutionTailZeroNum {

    private static long[] arr = new long[1000];

    public static long factorialResult(int n) {
        if (n <= 2) {
            return n;
        } else {
            if (arr[n] != 0)
                return arr[n];
            else {
                arr[n] = n * factorialResult(n - 1);
                return arr[n];
            }
        }
    }

    public static int getTailZeroNum(long target) {
        int num = 0;
        while (target % Math.pow(10, num + 1) == 0)
            num++;
        return num;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            int nextInt = scanner.nextInt();
            long result = factorialResult(nextInt);
            int num = getTailZeroNum(result);
            System.out.println(nextInt + "! = " + result + ", 尾数有" + num + "个0");
        }
    }
}
