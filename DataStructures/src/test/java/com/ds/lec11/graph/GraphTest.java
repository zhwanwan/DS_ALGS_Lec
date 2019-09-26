package com.ds.lec11.graph;

/**
 * @author zhwanwan
 * @create 2019-09-25 4:40 PM
 */
public class GraphTest {
    public static void main(String[] args) {
        String[] vertexes = {"1", "2", "3", "4", "5", "6", "7", "8"};
        MatrixGraph graph = new MatrixGraph(vertexes.length);
        for (String vertex : vertexes) {
            graph.insertVertex(vertex);
        }
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);
        graph.insertEdge(3, 7, 1);
        graph.insertEdge(4, 7, 1);
        graph.insertEdge(2, 5, 1);
        graph.insertEdge(2, 6, 1);
        graph.insertEdge(5, 6, 1);
        System.out.println("Show Graph");
        graph.showGraph();
        System.out.println("DFS~~");
        graph.dfs();
        System.out.println("\nBFS~~");
        graph.bfs();

    }
}
