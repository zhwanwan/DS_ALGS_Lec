package zhwanwan.algs;

/**
 * @author zhwanwan
 * @create 2019-08-14 15:07
 */
public class SingleLinkedList {

    private static class Node {
        final int data;
        Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    /**
     * 逆序打印单链表--递归实现
     * 1->2->3
     * 3->2->1
     * 递归实现
     *
     * @param h
     */
    public static void reversePrint(Node h) {
        if (h == null)
            return;
        reversePrint(h.next);
        System.out.println(h.data);
    }

    /**
     * 逆序打印单链表--迭代实现
     *
     * @param h
     */
    public static void reversePrint2(Node h) {
        Node cur = h;
        Node last = null;
        while (cur != last) {
            while (cur.next != last) {
                cur = cur.next;
            }
            System.out.println(cur.data); //打印当前最后一个结点
            last = cur; //设置当前结点为最后一个结点
            cur = h;
        }
    }

    /**
     * 打印单链表--递归实现
     *
     * @param h
     */
    public static void printNode(Node h) {
        if (h == null)
            return;
        System.out.println(h.data);
        printNode(h.next);
    }

    /**
     * 打印单链表--迭代实现
     *
     * @param h
     */
    public static void printNode2(Node h) {
        while (h != null) {
            System.out.println(h.data);
            h = h.next;
        }
    }

    /**
     * 逆置单链表
     *
     * @param h
     * @return
     */
    public static Node reverse(Node h) {
        if (h == null || h.next == null)
            return h;
        Node p = h.next;
        Node q = reverse(p);
        p.next = h;
        h.next = null;
        return q;
    }

    public static void main(String[] args) {

        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
//        reversePrint2(n1);
        System.out.println("--------------------");
        printNode2(n1);
        System.out.println("--------------------");
        printNode(reverse(n1));

    }

}
