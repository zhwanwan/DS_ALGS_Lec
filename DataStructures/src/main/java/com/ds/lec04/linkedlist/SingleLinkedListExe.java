package com.ds.lec04.linkedlist;

/**
 * 带头结点的链表练习题
 * @author zhwanwan
 * @create 2019-08-16 14:51
 */
public class SingleLinkedListExe {

    /**
     * 获取到单链表的节点的个数(如果是带头结点的链表，需求不统计头节点)
     *
     * @param head 链表的头节点
     * @return 返回的就是有效节点的个数
     */
    public static int getLength(HeroNode head) {
        if (head.next == null)
            return 0;
        int length = 0;
        HeroNode cur = head.next;
        while (cur != null) {
            cur = cur.next;
            length++;
        }
        return length;
    }


    /**
     * 查找单链表中的倒数第k个结点【新浪面试题】
     * 思路:
     * 1. 编写一个方法，接收head节点，同时接收一个index
     * 2. index 表示是倒数第index个节点
     * 3. 先把链表从头到尾遍历，得到链表的总的长度 getLength
     * 4. 得到size 后，我们从链表的第一个结点开始遍历 (size-index)个，就可以得到
     * 5. 如果找到了，则返回该节点，否则返回null
     *
     * @param head
     * @param index
     * @return
     */
    public static HeroNode findLastIndexNode(HeroNode head, int index) {
        if (head.next == null)
            return null;
        int size = getLength(head);
        if (index <= 0 || index > size)
            return null;
        HeroNode p = head.next; //第一个结点
        for (int i = 0, j = size - index; i < j; i++) {
            p = p.next;
        }
        return p;
    }

    /**
     * 单链表反转
     *
     * @param head
     * @return
     */
    public static void reverse(HeroNode head) {
        if (head.next == null || head.next.next == null)
            return;
        HeroNode cur = head.next;
        HeroNode next = null;
        HeroNode reverseHn = new HeroNode(0, "", "");
        while (cur != null) {
            next = cur.next;
            cur.next = reverseHn.next;
            reverseHn.next = cur;
            cur = next;
        }
        //原来头结点的下一个结点指向逆置头结点的下一个结点
        head.next = reverseHn.next;
    }

    /**
     * 递归实现带头结点的单链表反转
     *
     * @param head
     * @return
     */
    public static HeroNode reverseByRecursion(HeroNode head) {
        if (head.next == null)
            return null;
        HeroNode reversedFirst = reverseLinkList(head.next);
        head.next = reversedFirst;
        return head;

    }

    /**
     * 递归实现不带头结点的单链表反转
     *
     * @param first 链表第一个结点--非头结点
     * @return
     */
    private static HeroNode reverseLinkList(HeroNode first) {
        if (first == null || first.next == null)
            return first;
        HeroNode p = first.next;
        HeroNode q = reverseLinkList(p);
        p.next = first;
        first.next = null;
        return q;
    }

    /**
     * 逆序打印带头结点的单链表
     *
     * @param head
     */
    public static void reversePrint(HeroNode head) {
        if (head.next == null) //空链表，返回
            return;
        reversePrint(head.next);
        System.out.println(head.next);
    }

}
