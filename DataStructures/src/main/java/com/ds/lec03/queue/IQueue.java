package com.ds.lec03.queue;

/**
 * @author zhwanwan
 * @create 2019-08-15 22:23
 */
public interface IQueue {

    boolean isFull();

    boolean isEmpty();

    void addQueue(int n);

    int getQueue();

    void showQueue();

    int headQueue();

}
