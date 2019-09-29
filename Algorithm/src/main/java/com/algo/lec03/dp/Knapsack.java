package com.algo.lec03.dp;

import java.util.Arrays;

/**
 * 动态规划--Dynamic Programming(DP)
 * <p>
 * 动态规划算法介绍
 * 1.动态规划算法的核心思想是：将大问题划分为小问题进行解决，从而一步步获取最优解的处理算法;
 * 2.动态规划算法与分治算法类似，其基本思想也是将待求解问题分解成若干个子问题，
 * 先求解子问题，然后从这些子问题的解得到原问题的解。
 * 3.与分治法不同的是，适合于用动态规划求解的问题，经分解得到子问题往往不是互相独立的。
 * (即下一个子阶段的求解是建立在上一个子阶段的解的基础上，进行进一步的求解 );
 * 4.动态规划可以通过填表的方式来逐步推进，得到最优解。
 * <p>
 * 动态规划算法最佳实践-背包问题(knapsack-problem)
 * <p>
 * 背包问题主要是指一个给定容量的背包、若干具有一定价值v和重量w的物品，如何选择物品放入背包使物品的价值最大。
 * 其中又分0/1背包和完全背包(完全背包指的是：每种物品都有无限件可用)。
 * <p>
 * 0/1背包算法思路：
 * 1.填表
 * 物品	    0磅   1磅	 2磅	 3磅	4磅
 * 0	  0	     0	     0	    0
 * 吉他(G)	0	1500(G)	1500(G)	1500(G)	1500(G)
 * 音响(S)	0	1500(G)	1500(G)	1500(G)	3000(S)
 * 电脑(L)	0	1500(G)	1500(G)	2000(L)	2000(L)+1500(G)
 * <p>
 * 对于给定的n个物品，设v[i]，w[i]分别为第i个物品的价值和重量，C为背包的容量；
 * 令v[i][j]表示前i个物品中能够装入容量为j的背包中的最大价值；
 * 每次遍历到第i个物品，根据w[i]和v[i]来确定是否将该物品放入背包；
 * 1.v[i][0]=v[0][j]=0; //表示 填入表 第一行和第一列是0;
 * 2.当w[i]>j时：v[i][j]=v[i-1][j] //当准备加入新增的商品的容量大于当前背包的容量时，就直接使用上一个单元格的装入策略
 * 3.当j>=w[i]时： v[i][j]=max{v[i-1][j], v[i]+v[i-1][j-w[i]]}  
 * //当准备加入的新增的商品的容量小于等于当前背包的容量,
 * //装入的方式 :
 * v[i-1][j]： 就是上一个单元格的装入的最大值
 * v[i] : 表示当前商品的价值
 * v[i-1][j-w[i]] ： 装入i-1商品，到剩余空间j-w[i]的最大值
 * 当j>=w[i]时： v[i][j]=max{v[i-1][j], v[i]+v[i-1][j-w[i]]}
 */
public class Knapsack {

    public static void main(String[] args) {
        int[] w = {1, 4, 3, 2};
        int[] val = {1500, 3000, 2000, 1000};
        int c = 3; //背包容量
        int n = val.length; //物品个数
        //创建二维数组，
        //v[i][j] 表示在前i个物品中能够装入容量为j的背包中的最大价值
        int[][] v = new int[n + 1][c + 1];
        //为了记录放入商品的情况，我们定一个二维数组
        int[][] path = new int[n + 1][c + 1];
        for (int i = 0; i < v.length; i++) { //将第一列设置为0
            v[i][0] = 0;
        }
        for (int i = 0; i < v[0].length; i++) { //将第一行设置0
            v[0][i] = 0;
        }

        for (int i = 1; i < v.length; i++) { //不处理第一行
            for (int j = 1; j < v[0].length; j++) { //不处理第一列
                //公式：这里i从1开始，公式中w[i]修改成w[i-1]
                if (w[i - 1] > j)
                    v[i][j] = v[i - 1][j];
                else {
                    //v[i][j] = Math.max(v[i - 1][j], val[i - 1] + v[i - 1][j - w[i - 1]]);
                    if (v[i - 1][j] < val[i - 1] + v[i - 1][j - w[i - 1]]) {
                        v[i][j] = val[i - 1] + v[i - 1][j - w[i - 1]];
                        path[i][j] = 1;
                    } else
                        v[i][j] = v[i - 1][j];
                }
            }
        }
        System.out.println("打印填表数据：");
        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[i].length; j++) {
                System.out.printf("%5d", v[i][j]);
            }
            System.out.println();
        }

        System.out.println("打印放入情况：");
        for (int i = 0; i < path.length; i++) {
            System.out.println(Arrays.toString(path[i]));
        }

        int i = path.length - 1; //path最后一行
        int j = path[0].length - 1; //path最后一列
        while (i > 0 && j > 0) { //从path的最后开始找
            if (path[i][j] == 1) {
                System.out.printf("第%d个物品放入背包\n", i);
                j -= w[i - 1];
            }
            i--;
        }
    }

}
