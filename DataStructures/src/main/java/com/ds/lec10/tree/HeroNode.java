package com.ds.lec10.tree;

/**
 * @author zhwanwan
 * @create 2019-09-11 13:47
 */
public class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    public HeroNode() {
    }

    public HeroNode(int no, String name) {
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

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        System.out.println(this);
        if (this.left != null)
            this.left.preOrder();
        if (this.right != null)
            this.right.preOrder();
    }

    /**
     * 中序遍历
     */
    public void infixOrder() {
        if (this.left != null)
            this.left.infixOrder();
        System.out.println(this);
        if (this.right != null)
            this.right.infixOrder();
    }

    /**
     * 后序遍历
     */
    public void postOrder() {
        if (this.left != null)
            this.left.postOrder();
        if (this.right != null)
            this.right.postOrder();
        System.out.println(this);
    }

    /**
     * 前序遍历查找
     *
     * @param no
     * @return
     */
    public HeroNode preOrderSearch(int no) {
        if (this.no == no)
            return this;
        HeroNode resNode = null;
        if (this.left != null)
            resNode = this.left.preOrderSearch(no);
        if (resNode != null)
            return resNode;
        if (this.right != null)
            resNode = this.right.preOrderSearch(no);
        return resNode;
    }

    /**
     * 中序遍历查找
     *
     * @param no
     * @return
     */
    public HeroNode infixOrderSearch(int no) {
        HeroNode resNode = null;
        if (this.left != null)
            resNode = this.left.infixOrderSearch(no);
        if (resNode != null)
            return resNode;
        if (this.no == no)
            return this;
        if (this.right != null)
            resNode = this.right.infixOrderSearch(no);
        return resNode;
    }

    /**
     * 后序遍历查找
     *
     * @param no
     * @return
     */
    public HeroNode postOrderSearch(int no) {
        HeroNode resNode = null;
        if (this.left != null)
            resNode = this.left.postOrderSearch(no);
        if (resNode != null)
            return resNode;
        if (this.right != null)
            resNode = this.right.postOrderSearch(no);
        if (resNode != null)
            return resNode;
        if (this.no == no)
            return this;
        return resNode;
    }

    /**
     * 删除指定结点
     * 采用递归删除
     * 1.如果删除的结点是叶子结点，则删除该结点
     * 2.如果删除的结点是非叶子结点，则删除该子树
     *
     * @param no
     */
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

}
