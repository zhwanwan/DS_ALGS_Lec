package com.ds.lec11.graph;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * 图的深度优先遍历(DFS)
 */
public class DepthFirstSearch {

    private boolean[] marked; // marked[v] = is there an s-v path?
    private int count; // number of vertices connected to s

    /**
     * Computes the vertices in graph {@code G} that are
     * connected to the source vertex {@code s}.
     *
     * @param G the graph
     * @param s the source vertex
     */
    public DepthFirstSearch(Graph G, int s) {
        marked = new boolean[G.V()];
        validateVertex(s);
        dfs(G, s);
    }

    //递归DFS--depth first search from v
    private void dfs(Graph G, int v) {
        count++;
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w])
                dfs(G, w);
        }
    }

    public boolean marked(int v) {
        validateVertex(v);
        return marked[v];
    }

    public int count() {
        return count;
    }

    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
    }

    //非递归DFS
    public DepthFirstSearch(int s, Graph G) {
        marked = new boolean[G.V()];
        validateVertex(s);
        Iterator<Integer>[] adj = new Iterator[G.V()];
        for (int v = 0; v < G.V(); v++) {
            adj[v] = G.adj(v).iterator();
        }
        // depth-first search using stack
        Deque<Integer> stack = new LinkedList<>();
        marked[s] = true;
        count++;
        stack.push(s);
        while (!stack.isEmpty()) {
            int v = stack.peek();
            if (adj[v].hasNext()) {
                int w = adj[v].next();
                if (!marked[w]) {
                    marked[w] = true;
                    count++;
                    stack.push(w);
                }
            } else {
                stack.pop();
            }
        }
    }

    public static void main(String[] args) {
        Graph G = new Graph(5);
        G.addEdge(0, 1);
        G.addEdge(0, 2);
        G.addEdge(1, 2);
        G.addEdge(2, 3);
        G.addEdge(3, 4);
        G.addEdge(2, 4);
        System.out.println(G);
        DepthFirstSearch search = new DepthFirstSearch(0, G);
        for (int v = 0; v < G.V(); v++) {
            if (search.marked(v))
                System.out.print(v + " ");
        }
        System.out.println();
        System.out.println("count = " + search.count());
    }
}
