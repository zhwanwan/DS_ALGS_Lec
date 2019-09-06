package com.ds.lec03.queue;

import java.util.Scanner;

/**
 * 数组模拟环形队列~~~
 *1.  front 变量的含义做一个调整： front 就指向队列的第一个元素, 也就是说 arr[front] 就是队列的第一个元素
 * front 的初始值 = 0
 * 2.  rear 变量的含义做一个调整：rear 指向队列的最后一个元素的后一个位置. 因为希望空出一个空间做为约定.
 * rear 的初始值 = 0
 * 3. 当队列满时，条件是  (rear  + 1) % maxSize == front 【满】
 * 4. 对队列为空的条件， rear == front 空
 * 5. 当我们这样分析， 队列中有效的数据的个数   (rear + maxSize - front) % maxSize   // rear = 1 front = 0
 * 6. 我们就可以在原来的队列上修改得到，一个环形队列
 * @author zhwanwan
 * @create 2019-08-15 22:19
 */
public class CircleArrayQueueDemo {


    public static void main(String[] args) {
        System.out.println("测试数组模拟环形队列的案例~~~");

        // 创建一个环形队列
        CircleArrayQueue queue = new CircleArrayQueue(4); //说明设置4, 其队列的有效数据最大是3
        char key = ' '; // 接收用户输入
        Scanner scanner = new Scanner(System.in);//
        boolean loop = true;
        // 输出一个菜单
        while (loop) {
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 从队列取出数据");
            System.out.println("h(head): 查看队列头的数据");
            key = scanner.next().charAt(0);// 接收一个字符
            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("输出一个数:");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g': // 取出数据
                    try {
                        int res = queue.getQueue();
                        System.out.printf("取出的数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h': // 查看队列头的数据
                    try {
                        int res = queue.headQueue();
                        System.out.printf("队列头的数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e': // 退出
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出~~");
    }
}

/**
 * 数组模拟环形队列:
 * 1.  front 变量的含义做一个调整： front 就指向队列的第一个元素, 也就是说 arr[front] 就是队列的第一个元素
 * front 的初始值 = 0
 * 2.  rear 变量的含义做一个调整：rear 指向队列的最后一个元素的后一个位置. 因为希望空出一个空间做为约定.
 * rear 的初始值 = 0
 * 3. 当队列满时，条件是  (rear  + 1) % maxSize == front 【满】
 * 4. 对队列为空的条件， rear == front 空
 * 5. 当我们这样分析， 队列中有效的数据的个数   (rear + maxSize - front) % maxSize   // rear = 1 front = 0
 * 6. 我们就可以在原来的队列上修改得到，一个环形队列
 */
class CircleArrayQueue implements IQueue {
    private int maxSize; // 表示数组的最大容量
    //front 变量的含义做一个调整： front 就指向队列的第一个元素, 也就是说 arr[front] 就是队列的第一个元素
    //front 的初始值 = 0
    private int front;//对列头
    //rear 变量的含义做一个调整：rear 指向队列的最后一个元素的后一个位置. 因为希望空出一个空间做为约定.
    //rear 的初始值 = 0
    private int rear; //对列尾
    private int[] arr; //存放数据，模拟对列

    public CircleArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        front = 0;
        rear = 0;
        arr = new int[maxSize];
    }

    @Override
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    @Override
    public boolean isEmpty() {
        return front == rear;
    }

    @Override
    public void addQueue(int n) {
        if (isFull()) {
            System.out.println("对列已满！");
            return;
        }
        //直接将数据加入
        arr[rear] = n;
        //将rear后移，考虑取模
        rear = (rear + 1) % maxSize;
    }

    @Override
    public int getQueue() {
        if (isEmpty())
            throw new RuntimeException("对列为空！");
        // 这里需要分析出 front是指向队列的第一个元素
        // 1. 先把 front 对应的值保留到一个临时变量
        // 2. 将 front 后移, 考虑取模
        // 3. 将临时保存的变量返回
        int val = arr[front];
        front = (front + 1) % maxSize;
        return val;
    }

    @Override
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("对列为空！");
            return;
        }
        // 思路：从front开始遍历，遍历多少个元素?
        for (int i = front, len = size(); i < front + size(); i++) {
            int index = i % maxSize;
            System.out.printf("arr[%d]=%d\n", index, arr[index]);
        }

    }

    /**
     * 队列中元素个数
     * @return
     */
    public int size() {
        return (rear + maxSize - front) % maxSize;
    }

    @Override
    public int headQueue() {
        return 0;
    }
}