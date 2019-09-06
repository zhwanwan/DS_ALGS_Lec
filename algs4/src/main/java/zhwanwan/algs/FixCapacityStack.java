package zhwanwan.algs;

/**
 * @author wangzhen
 * @create 2019-04-30 2:44 PM
 */
public class FixCapacityStack<Item> {

    private Item[] a; //stack entries
    private int N; //size

    public FixCapacityStack(int cap) {
//        a = new Item[cap];
        a = (Item[]) new Object[cap];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void push(Item item) {
        a[N++] = item;
    }

    public Item pop() {
        return a[--N];
    }


}
