package com.ds.lec11.graph;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 深度优先搜索查找图中的路径
 * <p>
 * edgeTo[]数组是一颗用父链接表示的以s为根且含有所以与连通的顶点的树；
 * edgeTo[w]=v表示v-w是第一次访问w时经过的边。
 */
public class DFSPaths {
    private boolean[] marked;
    private int[] edgeTo;
    private final int s; //source vertex

    //在G中找出所有起点为s的路径
    public DFSPaths(Graph G, int s) {
        int len = G.V();
        marked = new boolean[len];
        edgeTo = new int[len];
        validateVertex(s);
        this.s = s;
        dfs(G, s);
    }

    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            }
        }
    }

    /**
     * 是否存在从s到v的路径
     *
     * @param v
     * @return
     */
    public boolean hasPathTo(int v) {
        validateVertex(v);
        return marked[v];
    }

    /**
     * s到v的路径，如果不存在则返回null
     *
     * @param v
     * @return
     */
    public Iterable<Integer> pathTo(int v) {
        validateVertex(v);
        if (!hasPathTo(v))
            return null;
        Deque<Integer> path = new LinkedList<>();
        for (int x = v; x != s; x = edgeTo[x])
            path.push(x);
        path.push(s);
        return path;
    }

    public static void main(String[] args) {

        Graph G = new Graph(5);
        G.addEdge(0, 1);
        G.addEdge(0, 2);
        G.addEdge(1, 2);
        G.addEdge(2, 3);
        G.addEdge(3, 4);
        G.addEdge(2, 4);
        int s = 2;
        DFSPaths dfs = new DFSPaths(G, s);
        System.out.println(G);
        for (int v = 0; v < G.V(); v++) {
            if (dfs.hasPathTo(v)) {
                System.out.printf("%d to %d ", s, v);
                for (int x : dfs.pathTo(v)) {
                    if (x == s)
                        System.out.print(x);
                    else
                        System.out.print("-" + x);
                }
                System.out.println();
            } else
                System.out.printf("%d to %d: not connected\n", s, v);
        }
    }
}
