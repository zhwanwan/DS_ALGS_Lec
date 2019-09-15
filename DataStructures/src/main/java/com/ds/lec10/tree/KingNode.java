package com.ds.lec10.tree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author zhwanwan
 * @create 2019-09-13 3:27 PM
 */
public class KingNode {
    private int no;
    private String name;
    private KingNode left;
    private KingNode right;

    //leftType=0表示指向左子树，
    //leftType=1表示指向前驱结点
    private int leftType;
    //rightType=0表示指向右子树
    //rightType=1表示指向后继结点
    private int rightType;

    public KingNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public KingNode getLeft() {
        return left;
    }

    public void setLeft(KingNode left) {
        this.left = left;
    }

    public KingNode getRight() {
        return right;
    }

    public void setRight(KingNode right) {
        this.right = right;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    @Override
    public String toString() {
        return "KingNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    //1.如果删除的节点是叶子节点，则删除该节点
    //2.如果删除的节点是非叶子节点，则删除该子树
    public void deleteNode(int no) {
        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }
        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }
        if (this.left != null)
            this.left.deleteNode(no);
        if (this.right != null)
            this.right.deleteNode(no);
    }

    //前序遍历--递归
    public void preOrder() {
        System.out.println(this);
        if (this.left != null)
            this.left.preOrder();
        if (this.right != null)
            this.right.preOrder();
    }

    //非递归前序遍历
    //1.对于任意节点current,若该节点不为空则访问该节点后再将节点压栈,并将左子树节点置为current,重复此操作
    //2.若左子树为空,栈顶节点出栈,将该节点的右子树置为current
    //3.重复1,2操作,直到current为空且栈内节点为空
    public void preOrderByStack() {
        Deque<KingNode> stack = new LinkedList<>();
        KingNode current = this;
        while (current != null || !stack.isEmpty()) {
            if (current != null) {
                //当前结点不为空则访问它，并将其压入栈
                System.out.println(current);
                stack.push(current);
                //访问左结点
                current = current.left;
            } else {
                //当前结点为空则出栈
                current = stack.pop();
                //访问右结点
                current = current.right;
            }
        }
    }

    //中序遍历--递归
    public void infixOrder() {
        if (this.left != null)
            this.left.infixOrder();
        System.out.println(this);
        if (this.right != null)
            this.right.infixOrder();
    }

    //非递归中序遍历
    //1.遇到一个结点,就把它压栈,并去遍历它的左子树;
    //2.当左子树遍历结束后,从栈顶弹出这个结点并访问它;
    //3.然后按其右指针再去中序遍历该结点的右子树.
    public void infixOrderByStack() {
        Deque<KingNode> stack = new LinkedList<>();
        KingNode current = this;
        while (current != null || !stack.isEmpty()) {
            while (current != null) { //一直向左并将沿途结点压入栈
                stack.push(current);
                current = current.left;
            }
            if (!stack.isEmpty()) {
                //栈中弹出结点并访问它
                current = stack.pop();
                System.out.println(current);
                //转向右子树
                current = current.right;
            }
        }
    }

    public void preOrderByStackBasedInfix() {
        Deque<KingNode> stack = new LinkedList<>();
        KingNode current = this;
        while (current != null || !stack.isEmpty()) {
            while (current != null) { //一直向左访问并将沿途结点压入栈
                System.out.println(current); //先访问当前结点
                stack.push(current);
                current = current.left;
            }
            if (!stack.isEmpty()) {
                //弹出栈顶结点并转向右结点
                current = stack.pop();
                current = current.right;
            }
        }
    }

    //后序遍历--递归
    public void postOrder() {
        if (this.left != null)
            this.left.postOrder();
        if (this.right != null)
            this.right.postOrder();
        System.out.println(this);
    }

    //非递归后序遍历
    //要保证根结点在左孩子和右孩子访问之后才能访问，因此对于任一结点P，先将其入栈。
    //如果P不存在左孩子和右孩子，则可以直接访问它;
    //或者P存在左孩子或者右孩子，但是其左孩子和右孩子都已被访问过了，则同样可以直接访问该结点。
    //若非上述两种情况，则将P的右孩子和左孩子依次入栈,
    //这样就保证了每次取栈顶元素的时候，左孩子在右孩子前面被访问，左孩子和右孩子都在根结点前面被访问。
    public void postOrderByStack() {
        Deque<KingNode> stack = new LinkedList<>();
        KingNode current = null; //当前结点
        KingNode pre = null; //已访问过的结点
        stack.push(this);
        while (!stack.isEmpty()) {
            current = stack.peek(); //当前元素指向栈顶元素
            if ((current.left == null && current.right == null) || (pre != null) &&
                    (pre == current.left || pre == current.right)) {
                System.out.println(current);
                pre = current;
                stack.pop();
            } else {
                //先右后左，这样取出时才能保证先左后右
                if (current.right != null)
                    stack.push(current.right);
                if (current.left != null)
                    stack.push(current.left);
            }
        }
    }

    /**
     * 层次遍历
     * 借助于对列实现:
     * 遍历当前结点开始,首先将其入队,然后开始执行循环:结点出队,访问该结点,其左右儿子入队
     */
    public void levelOrder() {
        Deque<KingNode> queue = new LinkedList<>();
        queue.offer(this); //当前结点入队
        while (!queue.isEmpty()) {
            //结点出列并将其左右子结点入队
            KingNode current = queue.poll();
            System.out.println(current);
            if (current.left != null)
                queue.offer(current.left);
            if (current.right != null)
                queue.offer(current.right);
        }
    }


}
