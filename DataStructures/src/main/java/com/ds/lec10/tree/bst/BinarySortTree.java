package com.ds.lec10.tree.bst;

/**
 * @author zhwanwan
 * @create 2019-09-18 12:21 AM
 */
public class BinarySortTree {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        BinarySortTree bst = new BinarySortTree();
        for (int i = 0; i < arr.length; i++) {
            bst.add(new Node(arr[i]));
        }
        System.out.println("先序遍历：");
        bst.preOrder();
        System.out.println("中序遍历二叉排序树~");
        bst.infixOrder();
        System.out.println("后续遍历：");
        bst.postOrder();
        //测试删除叶子节点
        bst.delete(10);
        bst.delete(3);
        bst.delete(9);
        bst.delete(1);
        System.out.println("删除后：");
        bst.infixOrder();

    }

    private Node root;

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public void add(Node node) {
        if (root == null) //如果root为空则直接让root指向node
            root = node;
        else
            root.add(node);
    }

    public void infixOrder() {
        if (root != null)
            root.infixOrderByStack();
        else
            System.out.println("二叉树为空！");
    }

    public void preOrder() {
        if (root != null)
            root.preOrderByStack();
        else
            System.out.println("二叉树为空！");
    }

    public void postOrder() {
        if (root != null)
            root.postOrderByStack();
        System.out.println("二叉树为空！");
    }

    public Node search(int value) {
        if (root == null)
            return null;
        return root.search(value);
    }

    public Node searchParent(int value) {
        if (root == null)
            return null;
        return root.searchParent(value);
    }

    /**
     * 删除结点
     * 考虑三种情况：
     * 1.删除叶子节点
     * 2.删除只有一颗子树的节点
     * 3.删除有两颗子树的节点
     *
     * @param value
     */
    public void deleteNode(int value) {
        if (root == null)
            return;
        Node target = search(value);
        if (target == null) //没找到指定结点
            return;
        //找到指定结点就是root结点
        if (root.getLeft() == null && root.getRight() == null) {
            root = null;
            return;
        }
        Node parent = searchParent(value);
        //1.如果要删除的是叶子结点
        if (target.getLeft() == null && target.getRight() == null) {
            //判断targetNode是父节点的左子节点还是右子节点
            if (parent.getLeft() != null && parent.getLeft().getValue() == value) //左子节点
                parent.setLeft(null);
            else if (parent.getRight() != null && parent.getRight().getValue() == value) //右子节点
                parent.setRight(null);
        } else if (target.getLeft() != null && target.getRight() != null) { //3.删除只有两颗子树的结点
            int min = delRightTreeMin(target.getRight());
            target.setValue(min);
        } else { //2.删除只有一颗子树的结点
            if (target.getLeft() != null) { //要删除的结点有左子结点
                if (parent != null) {
                    if (parent.getLeft().getValue() == value) //target是parent的左子节点
                        parent.setLeft(target.getLeft());
                    else  //target是parent的右子结点
                        parent.setRight(target.getLeft());
                } else
                    root = target.getLeft();
            } else { //要删除的结点只有右子结点
                if (parent != null) {
                    if (parent.getLeft().getValue() == value) //target是parent的左子节点
                        parent.setLeft(target.getRight());
                    else //target是parent的右子结点
                        parent.setRight(target.getRight());
                } else
                    root = target.getRight();
            }
        }
    }

    /**
     * 删除以node为根节点的二叉排序树的最小节点，并返回其值
     *
     * @param node
     * @return
     */
    public int delRightTreeMin(Node node) {
        Node target = node;
        while (target.getLeft() != null)
            target = target.getLeft();
        deleteNode(target.getValue());
        return target.getValue();
    }

    /**
     * 从以x节点为根节点的二叉排序树种删除指定值的节点
     *
     * @param x
     * @param value
     * @return
     */
    private Node delete(Node x, int value) {
        if (x == null)
            return null;
        if (value < x.getValue())
            x.setLeft(delete(x.getLeft(), value));
        else if (value > x.getValue())
            x.setRight(delete(x.getRight(), value));
        else {
            //要删除的节点就是x
            if (x.getLeft() == null) //左子树为空
                return x.getRight();
            else if (x.getRight() == null) //右子树为空
                return x.getLeft();
            else { //存在左右子树
                Node t = x;
                x = min(t.getRight()); //右子树中找最小节点
                x.setRight(deleteMin(t.getRight()));
                x.setLeft(t.getLeft());
            }
        }
        return x;
    }

    private Node min(Node x) {
        if (x.getLeft() == null)
            return x;
        return min(x.getLeft());
    }

    private Node deleteMin(Node x) {
        if (x.getLeft() == null)
            return x.getRight();
        else
            x.setLeft(deleteMin(x.getLeft()));
        return x;
    }

    /**
     * 删除指定值的节点
     *
     * @param value
     */
    public void delete(int value) {
        if (root == null)
            return;
        root = delete(root, value);
    }
}
