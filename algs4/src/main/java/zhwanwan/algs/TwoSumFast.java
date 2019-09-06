package zhwanwan.algs;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * @author wangzhen
 * @create 2019-05-01 10:37 AM
 */
public class TwoSumFast {

    /**
     * O(N) = N log N
     *
     * @param a
     * @return
     */
    public static int count(int[] a) {
        Arrays.sort(a);
        int N = a.length;
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            if (BinarySearch.binarySearch(a, -a[i]) > i) {
                cnt++;
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        int a[] = in.readAllInts();
        StdOut.println(count(a));
    }

}
