package zhwanwan.algs;

import edu.princeton.cs.algs4.StdOut;

/**
 * SparseVector class
 * 稀疏向量 类
 *
 * @author zhwanwan
 * @create 2019-06-17 10:35 AM
 */
public class SpVector {

    private int d;
    private ST<Integer, Double> st;

    public SpVector(int d) {
        this.d = d;
        this.st = new ST<>();
    }

    public void put(int i, double value) {
        if (i < 0 || i >= d) throw new IllegalArgumentException("Illegal index");
        if (value == 0.0) st.delete(i);
        else st.put(i, value);
    }

    public double get(int i) {
        if (i < 0 || i >= d) throw new IllegalArgumentException("Illegal index");
        if (st.contains(i)) return st.get(i);
        else return 0.0;
    }

    /**
     * number of nonzero entries
     *
     * @return
     */
    public int nnz() {
        return st.size();
    }


    public int dimension() {
        return d;
    }

    /**
     * 点乘
     *
     * @param that
     * @return
     */
    public double dot(double[] that) {
        double sum = 0.0;
        for (int i : st.keys())
            sum += that[i] * this.get(i);
        return sum;
    }

    public double dot(SpVector that) {
        if (this.d != that.d) throw new IllegalArgumentException("Vector lengths disagree");
        double sum = 0.0;
        // iterate over the vector with the fewest nonzeros
        if (this.st.size() <= that.st.size()) {
            for (int i : this.st.keys()) {
                if (that.st.contains(i)) sum += this.get(i) * that.get(i);
            }
        } else {
            for (int i : that.st.keys()) {
                if (this.st.contains(i)) sum += this.get(i) * that.get(i);
            }
        }
        return sum;
    }

    public double magnitude() {
        return Math.sqrt(this.dot(this));
    }

    public SpVector scale(double alpha) {
        SpVector c = new SpVector(d);
        for (int i : this.st.keys())
            c.put(i, alpha * this.get(i));
        return c;
    }

    public SpVector plus(SpVector that) {
        if (this.d != that.d) throw new IllegalArgumentException("Vector lengths disagree");
        SpVector c = new SpVector(d);
        for (int i : this.st.keys()) c.put(i, this.get(i));
        for (int i : that.st.keys()) c.put(i, that.get(i));
        return c;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i : st.keys())
            s.append("(" + i + ", " + st.get(i) + ")");
        return s.toString();
    }

    public static void main(String[] args) {
        SpVector a = new SpVector(10);
        SpVector b = new SpVector(10);

        a.put(3, 0.50);
        a.put(9, 0.75);
        a.put(6, 0.11);
        a.put(6, 0.00);
        b.put(3, 0.60);
        b.put(4, 0.90);
        StdOut.println("a = " + a);
        StdOut.println("b = " + b);
        StdOut.println("a dot b = " + a.dot(b));
        StdOut.println("a + b   = " + a.plus(b));

    }

}
