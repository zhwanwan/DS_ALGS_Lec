package com.ds.lec05.stack;

/**
 * @author zhwanwan
 * @create 2019-08-20 13:37
 */
public class LinkedStack implements IStack {

    private StackNode top = null;
    private int maxSize;
    private int count = 0;

    public LinkedStack(int maxSize) {
        this.maxSize = maxSize;
    }

    @Override
    public boolean isFull() {
        return count == maxSize;
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public void push(int val) {
        if (isFull()) {
            System.out.println("栈已满！");
            return;
        }
        StackNode node = new StackNode(val);
        if (top == null)
            top = node;
        else {
            node.next = top;
            top = node;
        }
        count++;

    }

    @Override
    public int pop() {
        if (isEmpty())
            throw new RuntimeException("栈为空！");
        int val = top.no;
        top = top.next;
        count--;
        return val;
    }

    @Override
    public void list() {
        if (isEmpty())
            System.out.println("栈为空！");
        StackNode p = top;
        while (p != null) {
            System.out.println(p);
            p = p.next;
        }
    }
}

class StackNode {

    public int no;
    public StackNode next;

    public StackNode(int no) {
        this.no = no;
    }

    @Override
    public String toString() {
        return "StackNode{" +
                "no=" + no +
                '}';
    }
}
