package com.ds.lec10.tree;

/**
 * @author zhwanwan
 * @create 2019-09-16 12:47 PM
 */
public class Node implements Comparable<Node> {

    private Byte data; //存放字符本身，如'a'
    private int weight; //权值
    private Node left;
    private Node right;

    public Node(int value) {
        this.weight = value;
    }

    public Node(int weight, Byte data) {
        this.weight = weight;
        this.data = data;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Byte getData() {
        return data;
    }

    public void setData(Byte data) {
        this.data = data;
    }

    @Override
    public int compareTo(Node o) {
        //从小到大
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }
}
