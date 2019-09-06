package com.ds.lec03.queue;

import java.util.Scanner;

/**
 * @author zhwanwan
 * @create 2019-08-15 21:25
 * <p>
 * 存在问题：数组使用一次不能复用
 */
public class ArrayQueueDemo {

    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(3);
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;

        while (loop) {
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 从队列取出数据");
            System.out.println("h(head): 查看队列头的数据");
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("输入一个数：");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g':
                    try {
                        int res = queue.getQueue();
                        System.out.printf("取出的数据是：%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h': //查看队列头的数据
                    try {
                        int res = queue.headQueue();
                        System.out.printf("队列头的数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出！");
    }

}

class ArrayQueue implements IQueue {

    private int maxSize;
    private int front;//对列头
    private int rear; //对列尾
    private int[] arr; //存放数据，模拟对列

    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
        front = -1;
        rear = -1;
    }

    @Override
    public boolean isFull() {
        return rear == maxSize - 1;
    }

    @Override
    public boolean isEmpty() {
        return rear == front;
    }

    @Override
    public void addQueue(int n) {
        if (isFull()) {
            System.out.println("对列已满，不能添加数据");
            return;
        }
        arr[++rear] = n;
    }

    @Override
    public int getQueue() {
        if (isEmpty())
            throw new RuntimeException("对列为空，不能取数据！");
        return arr[++front];
    }

    @Override
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("对列为空，没有数据！");
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d\n", i, arr[i]);
        }
    }

    // 显示队列的头数据， 注意不是取出数据
    @Override
    public int headQueue() {
        if (isEmpty())
            throw new RuntimeException("对列为空，没有数据！");
        return arr[front + 1];
    }


}