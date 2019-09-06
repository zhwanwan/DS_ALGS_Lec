package com.ds.lec04.linkedlist;

/**
 * Josephu(约瑟夫、约瑟夫环)  问题
 * Josephu问题为：设编号为1，2，… n的n个人围坐一圈，约定编号为k（1<=k<=n）的人从1开始报数，
 * 数到m 的那个人出列，它的下一位又从1开始报数，数到m的那个人又出列，
 * 依次类推，直到所有人出列为止，由此产生一个出队编号的序列。
 *
 * @author zhwanwan
 * @create 2019-08-19 18:29
 */
public class Josepfu {

    public static void main(String[] args) {
        // 测试一把看看构建环形链表，和遍历是否ok
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoys(125);// 加入5个小孩节点
        circleSingleLinkedList.showBoys();
        //测试一把小孩出圈是否正确
        circleSingleLinkedList.countBoy(10, 20, 125); // 2->4->1->5->3
    }


}


/**
 * 构建一个单向的环形链表思路
 * 1. 先创建第一个节点, 让 first 指向该节点，并形成环形
 * 2. 后面当我们每创建一个新的节点，就把该节点，加入到已有的环形链表中即可.
 * <p>
 * 遍历环形链表
 * 1. 先让一个辅助指针(变量) curBoy，指向first节点
 * 2. 然后通过一个while循环遍历 该环形链表即可 curBoy.next  == first 结束
 */
class CircleSingleLinkedList {

    private Boy first = null;

    public void addBoys(int nums) {
        if (nums < 1) {
            System.out.println("nums的值不正确");
            return;
        }
        Boy curBoy = null;
        for (int i = 1; i <= nums; i++) {
            Boy boy = new Boy(i);
            if (i == 1) {
                first = boy;
                first.setNext(first);
            } else {
                curBoy.setNext(boy);
                boy.setNext(first);
            }
            curBoy = boy;
        }
    }

    /**
     * 显示列表
     */
    public void showBoys() {
        if (first == null) {
            System.out.println("没有任何小孩");
            return;
        }
        Boy curBoy = first;
        while (true) {
            System.out.printf("小孩的编号%d \n", curBoy.getNo());
            if (curBoy.getNext() == first)
                break;
            curBoy = curBoy.getNext();
        }
    }

    /**
     * 根据用户的输入，计算出小孩出圈的顺序
     * <p>
     * 根据用户的输入，生成一个小孩出圈的顺序
     * n = 5 , 即有5个人
     * k = 1, 从第一个人开始报数
     * m = 2, 数2下
     * <p>
     * 1.  需求创建一个辅助指针(变量) helper , 事先应该指向环形链表的最后这个节点.
     * 补充： 小孩报数前，先让 first 和  helper 移动 k - 1次
     * 2.  当小孩报数时，让first 和 helper 指针同时移动 m  - 1 次
     * 3.  这时就可以将first 指向的小孩节点 出圈
     *      first = first.next
     *      helper.next = first
     * 原来first 指向的节点就没有任何引用，就会被回收
     *
     * @param startNo  表示从第几个小孩开始数数
     * @param countNum 表示数几下
     * @param numbs    表示最初有多少小孩在圈中
     */
    public void countBoy(int startNo, int countNum, int numbs) {
        if (first == null || startNo < 1 || startNo > numbs) {
            System.out.println("参数输入有误， 请重新输入");
            return;
        }
        //辅助指针helper,帮助完成小孩出圈
        Boy helper = first;
        //需求创建一个辅助指针(变量) helper , 事先应该指向环形链表的最后这个节点
        while (true) {
            if (helper.getNext() == first) //helper指向最后小孩节点
                break;
            helper = helper.getNext();
        }

        //小孩报数前，先让 first 和  helper 移动 k - 1次
        for (int i = 0; i < startNo - 1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        //当小孩报数时，让first 和 helper 指针同时移动m - 1 次, 然后出圈
        //这里是一个循环操作，知道圈中只有一个节点
        while (true) {
            if (helper == first) //说明圈中只有一个节点
                break;
            //让 first 和 helper 指针同时 的移动 countNum - 1
            for (int j = 0; j < countNum - 1; j++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            //这时first指向的节点，就是要出圈的小孩节点
            System.out.printf("小孩%d出圈\n", first.getNo());
            //这时将first指向的小孩节点出圈
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后留在圈中的小孩编号%d \n", first.getNo());
    }

}

class Boy {

    private int no;
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }

}