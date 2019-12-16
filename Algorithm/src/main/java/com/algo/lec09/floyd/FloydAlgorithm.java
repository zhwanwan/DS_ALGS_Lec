package com.algo.lec09.floyd;

import java.util.Arrays;

/**
 * 弗洛伊德(Floyd)算法介绍：
 * 1.和Dijkstra算法一样，弗洛伊德(Floyd)算法也是一种用于寻找给定的加权图中顶点间最短路径的算法。
 * 该算法名称以创始人之一、1978年图灵奖获得者、斯坦福大学计算机科学系教授罗伯特·弗洛伊德命名
 * 2.弗洛伊德算法(Floyd)计算图中各个顶点之间的最短路径
 * 3.迪杰斯特拉算法用于计算图中某一个顶点到其他顶点的最短路径。
 * 4.弗洛伊德算法 VS 迪杰斯特拉算法：迪杰斯特拉算法通过选定的被访问顶点，求出从出发访问顶点到其他顶点的最短路径；
 * 弗洛伊德算法中每一个顶点都是出发访问点，所以需要将每一个顶点看做被访问顶点，求出从每一个顶点到其他顶点的最短路径。
 * -----------------------------------------------------------------------------------------------------------
 * 弗洛伊德(Floyd)算法图解分析：
 * 1.设置顶点vi到顶点vk的最短路径已知为Lik，顶点vk到vj的最短路径已知为Lkj，顶点vi到vj的路径为Lij，
 * 则vi到vj的最短路径为：min((Lik+Lkj),Lij)，vk的取值为图中所有顶点，则可获得vi到vj的最短路径；
 * 2.至于vi到vk的最短路径Lik或者vk到vj的最短路径Lkj，是以同样的方式获得；
 * -----------------------------------------------------------------------------------------------------------
 * 弗洛伊德(Floyd)算法最佳应用-最短路径
 * 有7个村庄(A, B, C, D, E, F, G)
 * 各个村庄的距离用边线表示(权) ，比如 A – B 距离 5公里
 * 问：如何计算出各村庄到其它各村庄的最短距离?
 */
public class FloydAlgorithm {

    public static void main(String[] args) {
        // 测试看看图是否创建成功
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        //创建邻接矩阵
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;
        matrix[0] = new int[]{0, 5, 7, N, N, N, 2};
        matrix[1] = new int[]{5, 0, N, 9, N, N, 3};
        matrix[2] = new int[]{7, N, 0, N, 8, N, N};
        matrix[3] = new int[]{N, 9, N, 0, N, 4, N};
        matrix[4] = new int[]{N, N, 8, N, 0, 5, 4};
        matrix[5] = new int[]{N, N, N, 4, 5, 0, 6};
        matrix[6] = new int[]{2, 3, N, N, 4, 6, 0};

        //创建 Graph 对象
        Graph graph = new Graph(vertex.length, matrix, vertex);
        //调用弗洛伊德算法
        graph.floyd();
        graph.show();
    }

}

class Graph {
    private char[] vertex; //顶点数组
    private int[][] dis; //从各个顶点出发到其他顶点的距离，最后的结果就是保留在该数组
    private int[][] pre; //到达目标顶点的前驱顶点

    /**
     * @param length 大小
     * @param matrix 初始邻接矩阵
     * @param vertex 顶点
     */
    public Graph(int length, int[][] matrix, char[] vertex) {
        this.vertex = vertex;
        this.dis = matrix;
        this.pre = new int[length][length];
        // 对pre数组初始化, 注意存放的是前驱顶点的下标
        for (int i = 0; i < length; i++) {
            Arrays.fill(pre[i], i);
        }
    }

    public void show() {
        for (int i = 0; i < dis.length; i++) {
            for (int j = 0; j < dis.length; j++) {
                System.out.print(vertex[pre[i][j]] + " ");
            }
            System.out.println();
            for (int k = 0; k < dis.length; k++) {
                System.out.print("(" + vertex[i] + "到" + vertex[k] + "的最短路径是" + dis[i][k] + ") ");
            }
            System.out.println();
            System.out.println();
        }
    }

    public void floyd() {
        int len;
        //i--起点，k--中间结点, j--终点
        for (int k = 0; k < dis.length; k++) {
            for (int i = 0; i < dis.length; i++) {
                for (int j = 0; j < dis.length; j++) {
                    len = dis[i][k] + dis[k][j]; //求出从i顶点出发，经过k中间顶点，到达j顶点距离
                    if (len < dis[i][j]) {
                        dis[i][j] = len; //更新距离
                        pre[i][j] = pre[k][j]; //更新前驱结点
                    }
                }
            }
        }
    }
}
