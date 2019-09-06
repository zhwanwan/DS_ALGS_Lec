package com.ds.lec04.linkedlist;

/**
 * @author zhwanwan
 * @create 2019-08-16 8:16
 */
public class SingleLinkedListDemo {

    public static void main(String[] args) {

        //进行测试
        //先创建节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        //创建要给链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();

        //加入
        /*singleLinkedList.add(hero1);
        singleLinkedList.add(hero4);
        singleLinkedList.add(hero2);
        singleLinkedList.add(hero3);

        System.out.println("原来链表的情况~~");
        singleLinkedList.list();*/

        //加入按照编号的顺序
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero3);

        //显示一把
        singleLinkedList.list();

        //测试修改节点的代码
        /*HeroNode newHeroNode = new HeroNode(2, "小卢", "玉麒麟~~");
        singleLinkedList.update(newHeroNode);*/

        /*System.out.println("修改后的链表情况~~");
        singleLinkedList.list();
        singleLinkedList.delete(4);
        singleLinkedList.delete(2);
        singleLinkedList.delete(5);
        System.out.println("删除后的链表情况~~");
        singleLinkedList.list();*/

        //测试一下看看是否得到了倒数第K个节点
        /*HeroNode res = SingleLinkedListExe.findLastIndexNode(singleLinkedList.getHead(), 2);
        System.out.println("res=" + res);*/

        System.out.println("链表反转");
        SingleLinkedListExe.reverse(singleLinkedList.getHead());
        singleLinkedList.list();

        System.out.println("逆序打印链表：");
        SingleLinkedListExe.reversePrint(singleLinkedList.getHead());
    }


}

class SingleLinkedList {

    //定义头结点，不存放任何数据
    private HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }

    //添加节点到单向链表
    public void add(HeroNode node) {
        HeroNode p = head;
        while (p.next != null)
            p = p.next;
        p.next = node;
    }

    //显示链表[遍历]
    public void list() {
        if (head.next == null) {
            System.out.println("链表为空！");
            return;
        }
        HeroNode p = head.next;
        while (p != null) {
            System.out.println(p);
            p = p.next;
        }
    }

    //第二种方式在添加英雄时，根据排名将英雄插入到指定位置
    //(如果有这个排名，则添加失败，并给出提示)
    @Deprecated
    public void addByOrder1(HeroNode heroNode) {
        HeroNode p = head;
        if (p.next == null) {
            p.next = heroNode;
            return;
        }
        while (true) {
            p = p.next;
            if (p.next == null) {
                if (p.no < heroNode.no) {
                    p.next = heroNode;
                    break;
                } else if (p.no > heroNode.no) {
                    heroNode.next = p;
                    p = heroNode;
                    break;
                } else {
                    System.out.printf("结点[%d]已存在！", heroNode.no);
                    break;
                }
            } else {
                if (heroNode.no > p.no && heroNode.no < p.next.no) {
                    heroNode.next = p.next;
                    p.next = heroNode;
                    break;
                } else if (heroNode.no < p.no) {
                    heroNode.next = p;
                    p = heroNode;
                    break;
                } else if (heroNode.no == p.no) {
                    System.out.printf("结点[%d]已存在！\n", heroNode.no);
                    break;
                }
            }
        }
    }

    /**
     * 需要按照编号的顺序添加--优化addByOrder1方法
     * 1. 首先找到新添加的节点的位置, 通过辅助变量(指针)p找到添加位置的前一个结点
     * 2. 新的节点.next = temp.next
     * 3. 将temp.next = 新的节点
     *
     * @param hn
     */
    public void addByOrder(HeroNode hn) {
        //p是添加位置的前一个结点
        HeroNode p = head;
        boolean found = false; //found标志添加的编号是否存在，默认false
        while (true) {
            if (p.next == null) //说明p已经在链表的最后
                break;
            if (p.next.no == hn.no) { //要插入的结点编号已存在
                found = true;
                break;
            } else if (p.next.no > hn.no)  //位置找到，就在p后面插入
                break;
            p = p.next; //后移，遍历当前链表
        }
        if (found) //编号已存在，不能添加
            System.out.printf("准备添加的英雄编号[%d]已经存在,无法添加!\n", hn.no);
        else {
            //插入到p结点后面
            hn.next = p.next;
            p.next = hn;
        }
    }

    //修改节点的信息, 根据no编号来修改，即no编号不能改.
    //1. 根据 newHeroNode 的 no 来修改即可
    public void update(HeroNode newHeroNode) {
        if (head.next == null) {
            System.out.println("链表为空！");
            return;
        }
        HeroNode p = head.next;
        boolean found = false;
        while (true) {
            if (p == null)
                break;
            if (p.no == newHeroNode.no) {
                //找到
                found = true;
                break;
            }
            p = p.next;
        }
        if (found) {
            p.name = newHeroNode.name;
            p.nickname = newHeroNode.nickname;
        } else
            System.out.printf("没有找到编号[%d]的节点,不能修改\n", newHeroNode.no);
    }

    //删除节点
    //思路
    //1. head 不能动，因此我们需要一个temp辅助节点找到待删除节点的前一个节点
    //2. 说明我们在比较时，是p.next.no 和  需要删除的节点的no比较
    public void delete(int no) {
        HeroNode p = head;
        boolean found = false;

        while (true) {
            if (p.next == null) //已经到链表的最后
                break;
            if (p.next.no == no) {
                //找到待删除结点的前一个结点p
                found = true;
                break;
            }
            p = p.next;
        }

        if (found) {
            p.next = p.next.next;
            System.out.printf("编号[%d]的节点已删除\n", no);
        } else
            System.out.printf("没有找到编号[%d]的节点,不能删除\n", no);

    }
}

class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;

    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
