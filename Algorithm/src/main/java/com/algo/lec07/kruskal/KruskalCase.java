package com.algo.lec07.kruskal;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

/**
 * 克鲁斯卡尔算法介绍
 * 1.克鲁斯卡尔(Kruskal)算法，是用来求加权连通图的最小生成树的算法;
 * 2.基本思想：按照权值从小到大的顺序选择n-1条边，并保证这n-1条边不构成回路;
 * 3.具体做法：首先构造一个只含n个顶点的森林，然后依权值从小到大从连通网中选择边加入到森林中，并使森林中不产生回路，直至森林变成一棵树为止;
 */
public class KruskalCase {

    private int edgeNum;
    private char[] vertexs;
    private int[][] matrix; //邻接矩阵
    //INF表示两条边不能连通
    private static final int INF = Integer.MAX_VALUE;

    public KruskalCase(char[] vertexs, int[][] matrix) {
        int vlen = vertexs.length;
        //初始化顶点，复制拷贝的方式
        this.vertexs = new char[vlen];
        for (int i = 0; i < vlen; i++) {
            this.vertexs[i] = vertexs[i];
        }
        //初始化边
        this.matrix = new int[vlen][vlen];
        for (int i = 0; i < vlen; i++) {
            for (int j = 0; j < vlen; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }
        //统计边的条数
        for (int i = 0; i < vlen; i++) {
            for (int j = i + 1; j < vlen; j++) { //j=i+1而不是0，排除自己跟自己相连
                if (this.matrix[i][j] != INF) {
                    edgeNum++;
                }
            }
        }
    }

    public void kruskal() {
        int index = 0; //表示最后结果数组的索引
        int[] ends = new int[edgeNum]; //用于保存‘已有最小生成树’中每个顶点在最小生成树中的终点
        //创建结果数组，保存最后的最小生成树
        EData[] rets = new EData[edgeNum];

        //获取图中所有边的结合，一共12条边
        EData[] edges = getEdges();
        Arrays.sort(edges, Comparator.comparingInt(e -> e.weight));
        //遍历edges数组，将边添加到最小生成树中，判断准备加入的边是否形成回路，如果没有就加入rets,否则不能加入
        for (int i = 0; i < edgeNum; i++) {
            //获取到第i条边的第一个顶点（起点）
            int p1 = getPosition(edges[i].start);
            int p2 = getPosition(edges[i].end);
            //获取p1顶点在已有的最小生成树中的终点
            int m = getEnd(ends, p1);
            //获取p2顶点在已有的最小生成树中的终点
            int n = getEnd(ends, p2);
            //判断是否构成回路
            if (m != n) { //没有构成回路
                ends[m] = n; //设置m在“已有最小生成树”中的终点
                //加入rets
                rets[index++] = edges[i];
            }
        }
        System.out.println("最小生成树：");
        Arrays.stream(rets).filter(Objects::nonNull).forEach(System.out::println);
    }

    //打印邻接矩阵
    public void print() {
        System.out.println("邻接矩阵:");
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = 0; j < vertexs.length; j++) {
                if (INF == matrix[i][j])
                    System.out.printf("%s\t", "INF");
                else
                    System.out.printf("%d\t", matrix[i][j]);
            }
            System.out.println();
        }
    }


    /**
     * 对边进行排序--冒泡
     * 替代：Arrays.sort(edges, Comparator.comparingInt(e -> e.weight));
     *
     * @param edges
     */
    private void sortEdges(EData[] edges) {
        for (int i = 0; i < edges.length; i++) {
            for (int j = 0; j < edges.length - 1 - i; j++) {
                if (edges[j].weight > edges[j + 1].weight) {
                    EData temp = edges[j];
                    edges[j] = edges[j + 1];
                    edges[j + 1] = temp;
                }
            }
        }
    }

    /**
     * @param ch 顶点的值，如‘A’
     * @return
     */
    private int getPosition(char ch) {
        for (int i = 0; i < vertexs.length; i++) {
            if (vertexs[i] == ch)
                return i;
        }
        return -1;
    }

    /**
     * 获取图中的边，放到EData[]数组中，便于后面遍历数组
     * EData[]形式：[['A','B',12],[]]
     *
     * @return
     */
    private EData[] getEdges() {
        int index = 0;
        EData[] edges = new EData[edgeNum];
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = i + 1; j < vertexs.length; j++) {
                if (matrix[i][j] != INF) {
                    edges[index++] = new EData(vertexs[i], vertexs[j], matrix[i][j]);
                }
            }
        }
        return edges;
    }

    /**
     * 获取下标为i的顶点的终点,用于判断两个顶点的终点是否相同
     *
     * @param ends 数组就是记录了各个顶点对应的终点是哪个
     * @param i    顶点的下标
     * @return 返回下标为i的顶点对应的终点下标
     */
    private int getEnd(int[] ends, int i) {
        while (ends[i] != 0) {
            i = ends[i];
        }
        return i;
    }

    public static void main(String[] args) {
        char[] vertexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        //克鲁斯卡尔算法的邻接矩阵
        int matrix[][] = {
                /*A*//*B*//*C*//*D*//*E*//*F*//*G*/
                /*A*/ {0, 12, INF, INF, INF, 16, 14},
                /*B*/ {12, 0, 10, INF, INF, 7, INF},
                /*C*/ {INF, 10, 0, 3, 5, 6, INF},
                /*D*/ {INF, INF, 3, 0, 4, INF, INF},
                /*E*/ {INF, INF, 5, 4, 0, 2, 8},
                /*F*/ {16, 7, 6, INF, 2, 0, 9},
                /*G*/ {14, INF, INF, INF, 8, 9, 0}};

        KruskalCase kruskalCase = new KruskalCase(vertexs, matrix);
        kruskalCase.print();
        EData[] edges = kruskalCase.getEdges();
        Arrays.sort(edges, Comparator.comparingInt(e -> e.weight));
        System.out.println("初始邻接矩阵：");
        Arrays.stream(edges).forEach(System.out::println);
        kruskalCase.kruskal();

    }

}

class EData {
    char start;
    char end;
    int weight;

    public EData(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "EData[" + "<" + start + ", " + end + ">=" + weight + ']';
    }
}