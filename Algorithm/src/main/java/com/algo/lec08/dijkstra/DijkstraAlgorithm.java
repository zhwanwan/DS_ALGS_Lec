package com.algo.lec08.dijkstra;

import java.util.Arrays;

/**
 * 迪杰斯特拉(Dijkstra)算法
 * 迪杰斯特拉(Dijkstra)算法是典型最短路径算法，用于计算一个结点到其他结点的最短路径; 
 * 它的主要特点是以起始点为中心向外层层扩展(广度优先搜索思想)，直到扩展到终点为止。
 * ----------------------------------------------------------------------------
 * 迪杰斯特拉(Dijkstra)算法过程:
 * 设置出发顶点为v，顶点集合V{v1,v2,vi...}，v到V中各顶点的距离构成距离集合Dis，Dis{d1,d2,di...}，
 * Dis集合记录着v到图中各顶点的距离(到自身可以看作0，v到vi距离对应为di)。
 * 1.从Dis中选择值最小的di并移出Dis集合，同时移出V集合中对应的顶点vi，此时的v到vi即为最短路径
 * 2.更新Dis集合，更新规则为：比较v到V集合中顶点的距离值，与v通过vi到V集合中顶点的距离值，
 * 保留值较小的一个(同时也应该更新顶点的前驱节点为vi，表明是通过vi到达的)
 * 3.重复执行两步骤，直到最短路径顶点为目标顶点即可结束
 */
public class DijkstraAlgorithm {

    public static void main(String[] args) {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        //邻接矩阵
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;// 表示不可以连接
        matrix[0] = new int[]{N, 5, 7, N, N, N, 2};
        matrix[1] = new int[]{5, N, N, 9, N, N, 3};
        matrix[2] = new int[]{7, N, N, N, 8, N, N};
        matrix[3] = new int[]{N, 9, N, N, N, 4, N};
        matrix[4] = new int[]{N, N, 8, N, N, 5, 4};
        matrix[5] = new int[]{N, N, N, 4, 5, N, 6};
        matrix[6] = new int[]{2, 3, N, N, 4, 6, N};
        Graph graph = new Graph(vertex, matrix);
        graph.showGraph();
        graph.dsj(2);
        graph.showDijkstra();

    }

}

class Graph {
    private char[] vertex;
    private int[][] matrix;
    private VisitedVertex vv; //已经访问的顶点集合

    public Graph(char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.matrix = matrix;
    }

    //显示图
    public void showGraph() {
        for (int[] link : matrix) {
            System.out.println(Arrays.toString(link));
        }
    }

    //Dijkstra算法
    public void dsj(int index) {
        vv = new VisitedVertex(vertex.length, index);
        update(index); //更新index顶点到周围顶点的距离和前驱顶点
        for (int i = 1; i < vertex.length; i++) {
            index = vv.updateArr(); //选择并返回新的访问顶点
            update(index); //更新index顶点到周围顶点的距离和前驱顶点
        }
    }

    //更新index下标顶点到周围顶点的距离和周围顶点的前驱顶点
    private void update(int index) {
        int len = 0;
        //根据遍历邻接矩阵的matrix[index]行
        for (int j = 0; j < matrix[index].length; j++) {
            //len:出发顶点到index顶点的距离+从index顶点到j顶点的距离之和
            len = vv.getDis(index) + matrix[index][j];
            if (!vv.in(j) && len < vv.getDis(j)) {
                vv.updatePre(j, index); //更新j顶点的前驱为index顶点
                vv.updateDis(j, len); //更新出发顶点到j顶点的距离
            }
        }
    }

    //显示结果
    public void showDijkstra() {
        vv.show();
    }
}

class VisitedVertex {
    //记录各个顶点是否访问过 1--已访问 0--未访问
    private int[] already_arr;
    //每个下标对应的值为前一个顶点下标，动态更新
    private int[] pre_visited;
    //记录出发顶点到其他所有顶点的距离，比如G为出发点，就会记录G到其他顶点的距离，会动态更新，求的最短距离会存放到dis
    public int[] dis;

    /**
     * @param length 顶点个数
     * @param index  出发顶点下标，从0开始，比如G的下标为6
     */
    public VisitedVertex(int length, int index) {
        this.already_arr = new int[length];
        this.pre_visited = new int[length];
        this.dis = new int[length];
        //初始化dis
        Arrays.fill(dis, 65535);
        this.already_arr[index] = 1; //设置出发顶点已被访问
        this.dis[index] = 0; //设置出发顶点的访问距离为0
    }

    public boolean in(int index) {
        return already_arr[index] == 1;
    }

    /**
     * 更新出发顶点到index顶点的距离
     *
     * @param index
     * @param len
     */
    public void updateDis(int index, int len) {
        dis[index] = len;
    }

    /**
     * 更新pre顶点的前驱结点为index结点
     *
     * @param pre
     * @param index
     */
    public void updatePre(int pre, int index) {
        pre_visited[pre] = index;
    }

    /**
     * 返回出发顶点到index顶点的距离
     *
     * @param index
     * @return
     */
    public int getDis(int index) {
        return dis[index];
    }

    //继续选择并返回新的访问结点，比如G访问后，就是A顶点作为新的访问顶点(注意不是出发顶点)
    public int updateArr() {
        int min = 65535, index = 0;
        for (int i = 0; i < already_arr.length; i++) {
            if (already_arr[i] == 0 && dis[i] < min) { //i未访问
                min = dis[i];
                index = i;
            }
        }
        //更新 index顶点被访问过
        already_arr[index] = 1;
        return index;
    }

    //将三个数组的情况输出
    public void show() {
        System.out.println("\nalready_arr:");
        //输出already_arr
        for (int i : already_arr) {
            System.out.print(i + " ");
        }
        System.out.println("\npre_visited:");
        //输出pre_visited
        for (int i : pre_visited) {
            System.out.print(i + " ");
        }
        System.out.println("\ndis:");
        //输出dis
        for (int i : dis) {
            System.out.print(i + " ");
        }
        System.out.println();
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int count = 0;
        for (int i : dis) {
            if (i != 65535) {
                System.out.print(vertex[count] + "(" + i + ") ");
            } else {
                System.out.println("N ");
            }
            count++;
        }
        System.out.println();
    }

}