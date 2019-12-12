package com.algo.lec06.prim;

import java.util.Arrays;

/**
 * 普里姆算法--Prim
 * 应用场景：修路问题
 * ----------------------------------------------------------------------------
 * 最小生成树
 * 修路问题本质就是最小生成树问题，最小生成树(Minimum Cost Spanning Tree,MST):
 * 给定一个带权的无向连通图，如何选取一个生成树，使树上权的总和为最小。
 * 1.N个顶点，一定有N-1条边
 * 2.包含全部顶点
 * 3.N-1条边都在图中
 * ----------------------------------------------------------------------------
 * 普里姆算法介绍
 * <p>
 * 普利姆(Prim)算法求最小生成树，也就是在包含n个顶点的连通图中，找出只有(n-1)条边包含所有n个顶点的连通子图，也就是所谓的极小连通子图。
 * 普利姆的算法如下:
 * <p>
 * 1.设G=(V,E)是连通网，T=(U,D)是最小生成树，V,U是顶点集合，E,D是边的集合 
 * 2.若从顶点u开始构造最小生成树，则从集合V中取出顶点u放入集合U中，标记顶点v的visited[u]=1
 * 3.若集合U中顶点ui与集合V-U中的顶点vj之间存在边，则寻找这些边中权值最小的边，但不能构成回路，将顶点vj加入集合U中，将边（ui,vj）加入集合D中，标记visited[vj]=1
 * 4.重复步骤②，直到U与V相等，即所有顶点都被标记为访问过，此时D中有n-1条边
 */
public class PrimAlgorithm {

    public static void main(String[] args) {
        char[] data = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int verxs = data.length;
        //邻接矩阵的关系使用二维数组表示,10000这个大数，表示两个点不联通
        int[][] weight = new int[][]{
                {10000, 5, 7, 10000, 10000, 10000, 2},
                {5, 10000, 10000, 9, 10000, 10000, 3},
                {7, 10000, 10000, 10000, 8, 10000, 10000},
                {10000, 9, 10000, 10000, 10000, 4, 10000},
                {10000, 10000, 8, 10000, 10000, 5, 4},
                {10000, 10000, 10000, 4, 5, 10000, 6},
                {2, 3, 10000, 10000, 4, 6, 10000}
        };

        MGraph graph = new MGraph(verxs);
        MinTree minTree = new MinTree();
        minTree.createGraph(graph, verxs, data, weight);
        minTree.showGraph(graph);
        minTree.prim(graph, 0);

    }


}


//创建最小生成树
class MinTree {
    //创建图的邻接矩阵

    /**
     * @param graph
     * @param verxs  顶点个数
     * @param data   顶点的值
     * @param weight 图的邻接矩阵
     */
    public void createGraph(MGraph graph, int verxs, char[] data, int[][] weight) {
        int i, j;
        for (i = 0; i < verxs; i++) {
            graph.data[i] = data[i];
            for (j = 0; j < verxs; j++) {
                graph.weight[i][j] = weight[i][j];
            }
        }
    }

    //显示图的邻接矩阵
    public void showGraph(MGraph graph) {
        for (int[] link : graph.weight) {
            System.out.println(Arrays.toString(link));
        }
    }

    /**
     * @param graph
     * @param v     index of vertex
     */
    public void prim(MGraph graph, int v) {
        int[] visited = new int[graph.verxs];
        //将当前结点设为已访问
        //0--未访问，1--已访问
        visited[v] = 1;
        int h1 = -1, h2 = -1;
        int minWeight = 10000;
        for (int k = 1; k < graph.verxs; k++) { //N-1条边
            //确定每一次生成的子图，和哪个顶点的距离最近
            for (int i = 0; i < graph.verxs; i++) { //i表示已被访问的顶点
                for (int j = 0; j < graph.verxs; j++) { //j表示未被访问的顶点
                    if (visited[i] == 1 && visited[j] == 0 && graph.weight[i][j] < minWeight) {
                        minWeight = graph.weight[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }
            System.out.println("Edge<" + graph.data[h1] + "," + graph.data[h2] + "> Weight：" + minWeight);
            visited[h2] = 1;
            minWeight = 10000;
        }
    }
}


class MGraph {
    int verxs; //图的结点个数
    char[] data; //存放结点的数据
    int[][] weight; //存放边，邻接矩阵

    public MGraph(int verxs) {
        this.verxs = verxs;
        data = new char[verxs];
        weight = new int[verxs][verxs];
    }

}