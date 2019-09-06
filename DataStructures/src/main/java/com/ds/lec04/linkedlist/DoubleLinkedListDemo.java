package com.ds.lec04.linkedlist;

/**
 * @author zhwanwan
 * @create 2019-08-16 22:33
 */
public class DoubleLinkedListDemo {

    public static void main(String[] args) {
        // 测试
        System.out.println("双向链表的测试");
        // 先创建节点
        DHeroNode hero1 = new DHeroNode(1, "宋江", "及时雨");
        DHeroNode hero2 = new DHeroNode(2, "卢俊义", "玉麒麟");
        DHeroNode hero3 = new DHeroNode(3, "吴用", "智多星");
        DHeroNode hero4 = new DHeroNode(4, "林冲", "豹子头");
        // 创建一个双向链表
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.addByOrder(hero2);
        doubleLinkedList.addByOrder(hero1);
        doubleLinkedList.addByOrder(hero4);
        doubleLinkedList.addByOrder(hero3);
        doubleLinkedList.addByOrder(hero3);

        doubleLinkedList.list();

        // 修改
        DHeroNode newHeroNode = new DHeroNode(4, "公孙胜", "入云龙");
        doubleLinkedList.update(newHeroNode);
        System.out.println("修改后的链表情况");
        doubleLinkedList.list();

        // 删除
        doubleLinkedList.delete(1);
        System.out.println("删除后的链表情况~~");
        doubleLinkedList.list();
    }


}

// 创建一个双向链表的类
class DoubleLinkedList {

    //初始化一个头结点，不存放具体数据
    private DHeroNode head = new DHeroNode(0, "", "");

    public DHeroNode getHead() {
        return head;
    }

    /**
     * 遍历链表
     */
    public void list() {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        DHeroNode p = head.next;
        while (true) {
            if (p == null)
                break;
            System.out.println(p);
            p = p.next;
        }
    }

    /**
     * 添加一个结点到双向链表的最后
     *
     * @param dhn
     */
    public void add(DHeroNode dhn) {
        DHeroNode p = head;
        while (true) {
            if (p.next == null) //找到链表最后一个结点
                break;
            p = p.next;
        }
        //形成一个双向链表
        p.next = dhn;
        dhn.pre = p;
    }

    /**
     * 按照序号添加--带头结点双向链表
     *
     * @param dhn
     */
    public void addByOrder(DHeroNode dhn) {
        DHeroNode p = head;
        boolean found = false;

        while (true) {
            if (p.next == null) //链表最后
                break;
            if (p.next.no == dhn.no) {
                found = true;
                break;
            } else if (p.next.no > dhn.no)
                break;
            p = p.next;
        }

        if (found)
            System.out.printf("准备添加的英雄编号[%d]已经存在,无法添加!\n", dhn.no);
        else {
            if (p.next != null) {
                dhn.next = p.next;
                p.next.pre = dhn;
            }
            p.next = dhn;
            dhn.pre = p;
        }
    }

    /**
     * 修改一个结点的内容
     *
     * @param dhn
     */
    public void update(DHeroNode dhn) {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        DHeroNode p = head.next;
        boolean found = false;
        while (true) {
            if (p == null)
                break;
            if (p.no == dhn.no) {
                found = true;
                break;
            }
            p = p.next;
        }

        if (found) {
            p.name = dhn.name;
            p.nickname = dhn.nickname;
        } else
            System.out.printf("编号[%d]的结点不存在，无法修改！\n", dhn.no);
    }

    /**
     * 从双向链表删除指定编号的结点
     *
     * @param no
     */
    public void delete(int no) {
        if (head.next == null) {
            System.out.println("链表为空，无法删除！");
            return;
        }
        DHeroNode p = head.next;
        boolean found = false;
        while (true) {
            if (p == null) //到达链表最后
                break;
            if (p.no == no) {
                found = true;
                break;
            }
            p = p.next;
        }
        if (found) {
            //删除p结点
            p.pre.next = p.next;
            if (p.next != null) //如果p不是最后一个结点，此条件不能少，否则NPE
                p.next.pre = p.pre;
        } else
            System.out.printf("编号[%d]的结点不存在，无法修改！\n", no);
    }


}

class DHeroNode {
    public int no;
    public String name;
    public String nickname;
    public DHeroNode next; // 指向下一个节点, 默认为null
    public DHeroNode pre; // 指向前一个节点, 默认为null

    public DHeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "DHeroNode [no=" + no + ", name=" + name + ", nickname=" + nickname + "]";
    }
}