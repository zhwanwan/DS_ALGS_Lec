package com.ds.lec06.recursion;

/**
 * 八皇后问题
 * 八皇后问题，是一个古老而著名的问题，是回溯算法的典型案例。
 * 该问题是国际西洋棋棋手马克斯·贝瑟尔于1848年提出：在8×8格的国际象棋上摆放八个皇后，使其不能互相攻击，
 * 即：任意两个皇后都不能处于同一行、同一列或同一斜线上，问有多少种摆法。
 * <p>
 * 思路分析：
 * 1.第一个皇后先放第一行第一列
 * 2.第二个皇后放在第二行第一列、然后判断是否OK， 如果不OK，继续放在第二列、第三列、依次把所有列都放完，找到一个合适
 * 3.继续第三个皇后，还是第一列、第二列……直到第8个皇后也能放在一个不冲突的位置，算是找到了一个正确解
 * 4.当得到一个正确解时，在栈回退到上一个栈时，就会开始回溯，即将第一个皇后，放到第一列的所有正确解，全部得到.
 * 5.回头继续第一个皇后放第二列，后面继续循环执行 1,2,3,4的步骤
 *
 * @author zhwanwan
 * @create 2019-09-03 10:47
 */
public class Queue8 {

    //定义一个有多少个皇后
    private int max = 8;
    //定义一个数组，保存皇后放置位置的结果
    //数组下标表示第几行，数组值表示第几列
    private int[] array = new int[max];

    private int count;
    private int judgeCount;

    //放置第n个皇后，检测是否和前面已经摆放好的冲突
    private boolean judge(int n) {
        judgeCount++;
        for (int i = 0; i < n; i++) {
            //说明：
            //1. array[i] == array[n]  表示判断 第n个皇后是否和前面的n-1个皇后在同一列
            //2. Math.abs(n-i) == Math.abs(array[n] - array[i]) 表示判断第n个皇后是否和第i皇后是否在同一斜线
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i]))
                return false;
        }
        return true;
    }

    //输出皇后摆放的位置
    private void print() {
        count++;
        for (int i = 0, len = array.length; i < len; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    /**
     * 放置第n个皇后
     *
     * @param n
     */
    public void check(int n) {
        if (n == max) {
            print();
            return;
        }
        //将当前皇后依次从第0列开始放入放入，并判断是否冲突
        for (int i = 0; i < max; i++) {
            //先把当前这个皇后n放到该行的第1列
            array[n] = i;
            //判断是否冲突
            if (judge(n)) //不冲突
                //接着放n+1个皇后，开始递归
                check(n + 1);
            //如果冲突，就继续执行array[n]=i;即将第n个皇后放在本行的后移一个位置
        }

    }

    public static void main(String[] args) {
        Queue8 queue8 = new Queue8();
        queue8.check(0);
        System.out.printf("一共有%d种解法\n", queue8.count);
        System.out.printf("一共判断冲突的次数%d次\n", queue8.judgeCount); // 1.5w
    }


}
