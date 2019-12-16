package com.algo.lec10.house;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 马踏棋盘游戏代码实现
 * 1.马踏棋盘问题(骑士周游问题)实际上是图的深度优先搜索(DFS)的应用。
 * 2.如果使用回溯（就是深度优先搜索）来解决，假如马儿踏了53个点，如图：走到了第53个，坐标（1,0），
 * 发现已经走到尽头，没办法，那就只能回退了，查看其他的路径，就在棋盘上不停的回溯…… ，思路分析+代码实现
 * 3.分析第一种方式的问题，并使用贪心算法（greedyalgorithm）进行优化。解决马踏棋盘问题.
 * 4.使用前面的游戏来验证算法是否正确
 */
public class HouseChessboard {

    private static int X; //列
    private static int Y; //行
    //标记棋盘各个位置是否访问
    private static boolean[] visited;
    private static boolean finished;

    /**
     * 完成骑士周游问题的算法
     *
     * @param chessBoard 棋盘
     * @param row        马儿当前的位置的行 从0开始
     * @param column     马儿当前的位置的列  从0开始
     * @param step       是第几步 ,初始位置就是第1步
     */
    public static void traversalChessBoard(int[][] chessBoard, int row, int column, int step) {
        chessBoard[row][column] = step;
        visited[row * X + column] = true;
        //获取当前位置可以走的下一个位置集合
        List<Point> ps = next(new Point(column, row));
        //优化：对ps进行排序,排序的规则就是对ps的所有的Point对象的下一步的位置的数目，进行非递减排序--可走步数多的先走，减少回溯
        sort(ps);
        while (!ps.isEmpty()) {
            Point p = ps.remove(0); //取出一个位置
            //判断该点是否已经访问
            if (!visited[p.y * X + p.x]) {
                traversalChessBoard(chessBoard, p.y, p.x, step + 1);
            }
        }
        //判断是否走完
        if (step < X * Y && !finished) {
            chessBoard[row][column] = 0;
            visited[row * X + column] = false;
        } else {
            finished = true;
        }

    }


    public static List<Point> next(Point curPoint) {
        List<Point> ps = new ArrayList<>();
        Point p1 = new Point();
        //马走日判断8个位置是否能走
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y - 1) >= 0) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y - 2) >= 0) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y - 1) >= 0) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y - 2) >= 0) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }
        return ps;
    }

    //根据当前这个一步的所有的下一步的选择位置，进行非递减排序, 减少回溯的次数
    public static void sort(List<Point> ps) {
        ps.sort((p1, p2) -> {
            int s1 = next(p1).size();
            int s2 = next(p2).size();
            return s1 - s2;
        });
    }


    public static void main(String[] args) {
        X = Y = 10;
        int row = 1;
        int column = 1;
        //创建棋盘
        int[][] chessBoard = new int[X][Y];
        visited = new boolean[X * Y];
        long start = System.currentTimeMillis();
        traversalChessBoard(chessBoard, row - 1, column - 1, 1);
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start) + "毫秒");
        for (int[] r : chessBoard) {
            System.out.println(Arrays.toString(r));
        }
    }
}
