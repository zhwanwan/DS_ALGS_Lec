package com.ds.lec10.tree;

/**
 * @author zhwanwan
 * @create 2019-09-11 14:03
 */
public class BinaryTree {

    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    public void preOrder() {
        if (this.root != null)
            this.root.preOrder();
        else
            System.out.println("树为空！");
    }

    public void infixOrder() {
        if (this.root != null)
            this.root.infixOrder();
        else
            System.out.println("树为空！");
    }

    public void postOrder() {
        if (this.root != null)
            this.root.postOrder();
        else
            System.out.println("树为空！");
    }

    public HeroNode preOrderSearch(int no) {
        if (root != null)
            return root.preOrderSearch(no);
        else
            return null;
    }

    public HeroNode infixOrderSearch(int no) {
        if (root != null)
            return root.infixOrderSearch(no);
        else
            return null;
    }

    public HeroNode postOrderSearch(int no) {
        if (root != null)
            return root.postOrderSearch(no);
        else
            return null;
    }

    public void deleteNode(int no) {
        if (root != null) {
            if (root.getNo() == no)
                root = null;
            else
                root.deleteNode(no);
        } else
            System.out.println("树为空，不能删除！");
    }


}
