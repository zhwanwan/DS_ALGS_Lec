package com.algo.lec02.dac;

/**
 * 分治算法 Divide-and-Conquer(P)
 *
 * 分治法是一种很重要的算法。字面上的解释是“分而治之”，
 * 就是把一个复杂的问题分成两个或更多的相同或相似的子问题，
 * 再把子问题分成更小的子问题……直到最后子问题可以简单的直接求解，原问题的解即子问题的解的合并。
 * 这个技巧是很多高效算法的基础，如排序算法(快速排序，归并排序)，傅立叶变换(快速傅立叶变换)。
 * <p>
 * 分治算法可以求解的一些经典问题
 * 二分搜索
 * 大整数乘法
 * 棋盘覆盖
 * 合并排序
 * 快速排序
 * 线性时间选择
 * 最接近点对问题
 * 循环赛日程表
 * 汉诺塔
 * <p>
 * 分治法在每一层递归上都有三个步骤：
 * 分解：将原问题分解为若干个规模较小，相互独立，与原问题形式相同的子问题；
 * 解决：若子问题规模较小而容易被解决则直接解，否则递归地解各个子问题；
 * 合并：将各个子问题的解合并为原问题的解。
 * <p>
 * 分治(Divide-and-Conquer(P))算法设计模式如下：
 * <p>
 * if |P|≤n0
 * then return(ADHOC(P))
 * //将P分解为较小的子问题 P1 ,P2 ,…,Pk
 * for i←1 to k
 * do yi ← Divide-and-Conquer(Pi)   递归解决Pi
 * T ← MERGE(y1,y2,…,yk)   合并子问题
 * return(T)
 * <p>
 * 其中|P|表示问题P的规模；n0为一阈值，表示当问题P的规模不超过n0时，问题已容易直接解出，不必再继续分解。
 * ADHOC(P)是该分治法中的基本子算法，用于直接解小规模的问题P。因此，当P的规模不超过n0时直接用算法ADHOC(P)求解。
 * 算法MERGE(y1,y2,…,yk)是该分治法中的合并子算法，用于将P的子问题P1 ,P2 ,…,Pk的相应的解y1,y2,…,yk合并为P的解。
 * <p>
 * --------------------------------------------------------------------------------------------------
 * 分治算法最佳实践-汉诺塔
 * 汉诺塔思路分析：
 * 1.如果是有一个盘， A->C
 * 2.如果有 n >= 2 情况，我们总是可以看做是两个盘 1.最下边的盘 2.上面的盘
 * a.先把 最上面的盘 A->B
 * b.把最下边的盘 A->C
 * c.把B塔的所有盘 从 B->C
 */
public class HanoiTower {

    public static void main(String[] args) {
        playHanoiTower(4, 'A', 'B', 'C');
    }


    /**
     * @param num 盘子个数
     * @param a   塔a,盘子起始所在塔
     * @param b   塔b
     * @param c   塔c，盘子要移动到的塔
     */
    public static void playHanoiTower(int num, char a, char b, char c) {
        if (num == 1) //只有一个盘时
            System.out.println("第" + num + "个盘从 " + a + "->" + c);
        else {
            //如果我们有 n >= 2 情况，我们总是可以看做是两个盘 1.最下边的一个盘 2. 上面的所有盘
            //1. 先把 最上面的所有盘 A->B，移动过程会使用到 c
            playHanoiTower(num - 1, a, c, b);
            //2. 把最下边的盘 A->C
            System.out.println("第" + num + "个盘从 " + a + "->" + c);
            //3. 把B塔的所有盘从B->C , 移动过程使用到 a塔
            playHanoiTower(num - 1, b, a, c);
        }
    }

}
