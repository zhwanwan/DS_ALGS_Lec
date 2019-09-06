package zhwanwan.algs;

import edu.princeton.cs.algs4.StdIn;

import java.util.Arrays;

/**
 * @author wangzhen
 * @create 2019-04-28 3:22 PM
 */
public class Chapter1Test {
    public static void main(String[] args) {
        System.out.println(2.0e-6 * 100000000.1);
        System.out.println(true && false || true && true); //&&比||优先
        System.out.println(1 + 2 + "3");
        int a[][] = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        revertArray(a);
        System.out.println(lg(513));
        int a115[] = {1, 2, 2, 3, 3, 4, 5, 6, 6};
        int res[] = histogram(a115, 5);
        System.out.println(Arrays.toString(res));
        System.out.println(F(100));
        System.out.println(lnFn(1));

        /*while (StdIn.hasNextLine()) {
            String line = StdIn.readLine();
            String[] arr = line.split(" ");
            StdOut.printf("%s\t", arr[0]);
            StdOut.printf("%d\t", Long.valueOf(arr[1]));
            StdOut.printf("%d\t", Long.valueOf(arr[2]));
            StdOut.printf("%.3f\t\n", Double.valueOf(arr[2]) / Double.valueOf(arr[1]));
        }*/

        long p = StdIn.readLong();
        long q = StdIn.readLong();
        System.out.printf("%d, %d 的最大公约数是:", p, q);
        System.out.println(getMaxDivisor(p,q));


    }

    public static void revertArray(int a[][]) {
        System.out.println("Original:");
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("Reverted:");
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                System.out.print(a[j][i] + " ");
            }
            System.out.println();
        }
    }

    public static int lg(int n) {
        int sum = 1;
        int result = 1;
        while (sum * 2 <= n) {
            sum *= 2;
            result++;
        }
        return result;
    }

    public static int[] histogram(int a[], int m) {
        int res[] = new int[m];
        for (int i = 1; i <= m; i++) {
            int n = 0;
            for (int j = 0; j < a.length; j++) {
                if (i == a[j])
                    n++;
            }
            res[i - 1] = n;
        }
        return res;
    }

    static long[] arr = new long[1000];

    public static long F(int N) {
        if (N == 0)
            return 0;
        if (N == 1)
            return 1;
        if (arr[N] != 0)
            return arr[N];
        else {
            arr[N] = F(N - 1) + F(N - 2);
            return arr[N];
        }
    }


    public static double lnFn(int n) {
        return Math.log(F(n));
    }

    public static long getMaxDivisor(long p, long q) {
        if (p == q)
            return p;
        if (p > q) {
            long mod = p % q;
            if (mod == 0)
                return q;
            else
                return getMaxDivisor(q, mod);
        } else {
            long mod = q % p;
            if (mod == 0)
                return p;
            else
                return getMaxDivisor(p, mod);
        }
    }


}
