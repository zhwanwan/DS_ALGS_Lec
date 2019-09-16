package com.ds.lec10.tree;

import java.util.*;

/**
 * HuffmanTree(哈夫曼树)
 * 基本介绍
 * 1.给定n个权值作为n个叶子结点，构造一棵二叉树，若该树的带权路径长度(wpl)达到最小，
 * 称这样的二叉树为最优二叉树，也称为哈夫曼树(Huffman Tree)。
 * 2.赫夫曼树是带权路径长度最短的树，权值较大的结点离根较近。
 * <p>
 * 相关概念
 * 1.路径和路径长度：在一棵树中，从一个结点往下可以达到的孩子或孙子结点之间的通路，称为路径。
 * 通路中分支的数目称为路径长度。若规定根结点的层数为1，则从根结点到第L层结点的路径长度为L-1。
 * 2.结点的权及带权路径长度：若将树中结点赋给一个有着某种含义的数值，则这个数值称为该结点的权。
 * 结点的带权路径长度为：从根结点到该结点之间的路径长度与该结点的权的乘积。
 * 3.树的带权路径长度：树的带权路径长度规定为所有叶子结点的带权路径长度之和，
 * 记为WPL(Weighted Path Length),权值越大的结点离根结点越近的二叉树才是最优二叉树。
 * 4.WPL最小的就是赫夫曼树。
 * <p>
 * 构成赫夫曼树的步骤：
 * 1) 从小到大进行排序, 将每一个数据，每个数据都是一个节点，每个节点可以看成是一颗最简单的二叉树；
 * 2) 取出根节点权值最小的两颗二叉树；
 * 3) 组成一颗新的二叉树, 该新的二叉树的根节点的权值是前面两颗二叉树根节点权值的和；
 * 4) 再将这颗新的二叉树，以根节点的权值大小再次排序，不断重复1-2-3-4的步骤，直到数列中，所有的数据都被处理，就得到一颗赫夫曼树。
 *
 * @author zhwanwan
 * @create 2019-09-16 12:51 PM
 */
public class HuffmanTree {
    public static void main(String[] args) {
        int arr[] = {13, 7, 8, 3, 29, 6, 1};
        Node huffmanTreeRoot = createHuffmanTree(arr);
        listHuffmanTreeNodes(huffmanTreeRoot);

    }

    /**
     * 构造一棵哈夫曼树
     *
     * @param arr
     * @return
     */
    public static Node createHuffmanTree(int[] arr) {
        // 第一步为了操作方便
        // 1. 遍历 arr 数组
        // 2. 将arr的每个元素构成成一个Node
        // 3. 将Node 放入到ArrayList中
        List<Node> nodes = new ArrayList<>();
        for (int val : arr) {
            nodes.add(new Node(val));
        }
        while (nodes.size() > 1) {
            Collections.sort(nodes);
            //取出结点权值最小的两个结点
            Node left = nodes.get(0);
            Node right = nodes.get(1);
            Node parent = new Node(left.getWeight() + right.getWeight());
            parent.setLeft(left);
            parent.setRight(right);
            //移除已处理两个最小结点
            nodes.remove(left);
            nodes.remove(right);
            //将parent加入nodes
            nodes.add(parent);
        }
        //返回nodes剩下的最后一个结点
        return nodes.get(0);
    }

    /**
     * 输出Huffman Tree的结点
     *
     * @param node
     */
    public static void listHuffmanTreeNodes(Node node) {
        Deque<Node> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            if (cur.getLeft() == null && cur.getRight() == null)
                System.out.println(cur);
            if (cur.getLeft() != null)
                queue.offer(cur.getLeft());
            if (cur.getRight() != null)
                queue.offer(cur.getRight());
        }
    }
}
