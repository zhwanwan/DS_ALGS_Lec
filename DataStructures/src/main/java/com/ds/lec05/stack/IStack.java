package com.ds.lec05.stack;

/**
 * @author zhwanwan
 * @create 2019-08-20 13:33
 */
public interface IStack {

    boolean isFull();

    boolean isEmpty();

    void push(int val);

    int pop();

    void list();
}
