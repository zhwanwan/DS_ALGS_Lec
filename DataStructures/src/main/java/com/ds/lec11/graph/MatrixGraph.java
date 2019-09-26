package com.ds.lec11.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 图的表示方式有两种：二维数组表示（邻接矩阵）；链表表示（邻接表）。
 * <p>
 * 邻接矩阵
 * 邻接矩阵是表示图形中顶点之间相邻关系的矩阵，对于n个顶点的图而言，矩阵是的row和col表示的是1....n个点。
 * <p>
 * 邻接表
 * 邻接矩阵需要为每个顶点都分配n个边的空间，其实有很多边都是不存在,会造成空间的一定损失.
 * 邻接表的实现只关心存在的边，不关心不存在的边。因此没有空间浪费，邻接表由数组+链表组成.
 * <p>
 * 图遍历介绍
 * 所谓图的遍历，即是对结点的访问。
 * 一个图有那么多个结点，如何遍历这些结点，需要特定策略，一般有两种访问策略: (1)深度优先遍历 (2)广度优先遍历.
 * <p>
 * 图的深度优先搜索DFS(Depth First Search) :
 * 深度优先遍历，从初始访问结点出发，初始访问结点可能有多个邻接结点，
 * 深度优先遍历的策略就是首先访问第一个邻接结点，然后再以这个被访问的邻接结点作为初始结点，
 * 访问它的第一个邻接结点， 可以这样理解：每次都在访问完当前结点后首先访问当前结点的第一个邻接结点。
 * 我们可以看到，这样的访问策略是优先往纵向挖掘深入，而不是对一个结点的所有邻接结点进行横向访问。
 * 显然，深度优先搜索是一个递归的过程。
 * <p>
 * 图的广度优先搜索BST(Broad First Search)：
 * 类似于一个分层搜索的过程，广度优先遍历需要使用一个队列以保持访问过的结点的顺序，
 * 以便按这个顺序来访问这些结点的邻接结点。
 *
 * @author zhwanwan
 * @create 2019-09-25 8:48 AM
 */
public class MatrixGraph {

    public static void main(String[] args) {
        String[] vertexes = {"A", "B", "C", "D", "E"};
        MatrixGraph graph = new MatrixGraph(vertexes.length);
        for (String vertex : vertexes) {
            graph.insertVertex(vertex);
        }
        graph.insertEdge(0, 1, 1); //
        graph.insertEdge(0, 2, 1); //
        graph.insertEdge(1, 2, 1); //
        graph.insertEdge(1, 3, 1); //
        graph.insertEdge(1, 4, 1); //

        graph.showGraph();
        graph.dfs();
    }

    private List<String> vertexList;
    private int[][] edges;
    private int numOfEdges;
    private boolean[] isVisited;

    public MatrixGraph(int n) {
        edges = new int[n][n];
        vertexList = new ArrayList<>(n);
        numOfEdges = 0;
    }

    public int getNumOfVertex() {
        return vertexList.size();
    }

    public int getNumOfEdges() {
        return numOfEdges;
    }

    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }

    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }

    public void showGraph() {
        for (int[] link : edges)
            System.out.println(Arrays.toString(link));
    }

    public int getFirstNeighbor(int index) {
        for (int i = 0; i < vertexList.size(); i++) {
            if (edges[index][i] > 0)
                return i;
        }
        return -1;
    }

    public int getNextNeighbor(int v1, int v2) {
        for (int i = v2 + 1; i < vertexList.size(); i++) {
            if (edges[v1][i] > 0)
                return i;
        }
        return -1;
    }


    /**
     * 深度优先遍历算法(Depth First Search)
     * 1.访问初始结点v,标记结点v并标记已访问；
     * 2.查找结点v的第一个邻接结点w;
     * 3.若w存在，则继续执行4，如果w不存在，则回退到1，从v的下一个结点继续；
     * 4.若w未被访问，对w进行DFS（递归）
     * 5.查找结点v的w邻接结点的下一个邻接结点，抓到步骤3.
     *
     * @param isVisited 访问数组
     * @param i         顶点坐标
     */
    private void dfs(boolean[] isVisited, int i) {
        System.out.print(getValueByIndex(i) + "->");
        isVisited[i] = true;
        int w = getFirstNeighbor(i);
        while (w != -1) { //找到邻结点
            if (!isVisited[w]) //如果未访问
                dfs(isVisited, w); //继续访问这个结点
            w = getNextNeighbor(i, w); //访问下一个结点
        }
    }

    public void dfs() {
        isVisited = new boolean[vertexList.size()];
        for (int i = 0; i < vertexList.size(); i++) {
            if (!isVisited[i])
                dfs(isVisited, i);
        }
    }

    /**
     * 广度优先遍历算法
     * 1.访问初始结点v并标记结点v为已访问。
     * 2.结点v入队列
     * 3.当队列非空时，继续执行，否则算法结束。
     * 4.出队列，取得队头结点u。
     * 5.查找结点u的第一个邻接结点w。
     * 6.若结点u的邻接结点w不存在，则转到步骤3；否则循环执行以下三个步骤：
     * 6.1 若结点w尚未被访问，则访问结点w并标记为已访问。
     * 6.2 结点w入队列
     * 6.3 查找结点u的继w邻接结点后的下一个邻接结点w，转到步骤6。
     *
     * @param isVisited
     * @param i
     */
    private void bfs(boolean[] isVisited, int i) {
        //访问结点
        System.out.print(getValueByIndex(i) + "->");
        //标记已访问
        isVisited[i] = true;
        Deque<Integer> queue = new LinkedList<>();
        //将结点加入队列
        queue.offer(i);
        int u; //对列的头结点u
        int w; //邻接结点w
        while (!queue.isEmpty()) {
            //取出队列的头结点下标
            u = queue.poll();
            //得到第一个邻接结点下标
            w = getFirstNeighbor(u);
            while (w != -1) { //存在w
                if (!isVisited[w]) { //w未访问
                    System.out.print(getValueByIndex(w) + "->");
                    isVisited[w] = true;
                    queue.offer(w);
                }
                //以u为前驱点，找到w后面的下一个邻接结点
                w = getNextNeighbor(u, w);
            }
        }
    }

    public void bfs() {
        isVisited = new boolean[vertexList.size()];
        for (int i = 0; i < vertexList.size(); i++) {
            if (!isVisited[i])
                bfs(isVisited, i);
        }
    }

}
