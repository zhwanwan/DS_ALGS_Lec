package com.ds.lec05.stack;

/**
 * @author zhwanwan
 * @create 2019-08-20 13:36
 */
public class ArrayStack implements IStack {

    private int maxSize;
    private int[] stack;

    private int top = -1; //top表示栈顶，初始化为-1

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    /**
     * 判断栈满
     *
     * @return
     */
    public boolean isFull() {
        return top == maxSize - 1;
    }

    /**
     * 判断栈空
     *
     * @return
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * 入栈
     *
     * @param val
     */
    public void push(int val) {
        if (isFull()) {
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = val;
    }

    /**
     * 出栈
     *
     * @return
     */
    public int pop() {
        if (isEmpty())
            throw new RuntimeException("栈空，无数据");
        return stack[top--];
    }

    /**
     * 遍历栈，从栈顶开始
     *
     * @return
     */
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空，无数据");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }
}
