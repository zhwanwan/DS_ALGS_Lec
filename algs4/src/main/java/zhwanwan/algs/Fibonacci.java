package zhwanwan.algs;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * @author zhwanwan
 * @create 2019-05-27 2:41 PM
 */
public class Fibonacci {

    private static Map<Integer, Long> map = new HashMap<>();

    /**
     * 0 1 1 2 3 5 8 ...
     *
     * @param n
     * @return
     */
    public static long getFibNum(int n) {
        if (n < 0)
            throw new IllegalArgumentException("calls to getFibNum cannot be negative number");
        if (n == 0 || n == 1) {
            if (!map.containsKey(n))
                map.put(n, (long) n);
            return n;
        }
        if (map.containsKey(n))
            return map.get(n);
        long num = getFibNum(n - 1) + getFibNum(n - 2);
        map.put(n, num);
        return num;
    }

    public static void main(String[] args) {
        IntStream.range(0, 11).forEach(i -> System.out.println(i + ": " + getFibNum(i)));
    }
}
