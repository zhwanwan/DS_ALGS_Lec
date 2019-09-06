package zhwanwan.algs;

import edu.princeton.cs.algs4.StdOut;

/**
 * @author zhwanwan
 * @create 2019-05-27 3:02 PM
 */
public class SpareVector {
    private int d;
    private ST<Integer, Double> st;

    public SpareVector(int d) {
        this.d = d;
        this.st = new ST<>();
    }

    private void checkIndex(int i) {
        if (i < 0 || i >= d)
            throw new IllegalArgumentException("Illegal index");
    }

    public void put(int i, double value) {
        checkIndex(i);
        if (value == .0)
            st.delete(i);
        else
            st.put(i, value);
    }

    public double get(int i) {
        checkIndex(i);
        if (st.contains(i))
            return st.get(i);
        else return .0;
    }

    /**
     * Returns the number of nonzero entries in this vector.
     *
     * @return
     */
    public int nnz() {
        return st.size();
    }

    public int size() {
        return d;
    }

    public int dimension() {
        return d;
    }

    /**
     * Returns the inner product of this vector with the specified vector.
     *
     * @param that
     * @return
     */
    public double dot(SpareVector that) {
        if (this.d != that.d)
            throw new IllegalArgumentException("Vector lengths disagree");
        double sum = .0;
        if (this.st.size() <= that.st.size()) {
            for (int i : this.st.keys())
                if (that.st.contains(i))
                    sum += this.get(i) * that.st.get(i);
        } else {
            for (int i : that.st.keys())
                if (this.st.contains(i))
                    sum += this.get(i) * that.get(i);
        }

        return sum;
    }

    public double dot(double[] that) {
        double sum = .0;
        for (int i : st.keys())
            sum += that[i] * this.get(i);
        return sum;
    }

    public double magnitude() {
        return Math.sqrt(this.dot(this));
    }

    public double norm() {
        return Math.sqrt(this.dot(this));
    }

    public SpareVector scale(double alpha) {
        SpareVector c = new SpareVector(d);
        for (int i : this.st.keys())
            c.put(i, alpha * this.get(i));
        return c;
    }

    public SpareVector plus(SpareVector that) {
        if (this.d != that.d)
            throw new IllegalArgumentException("Vector lengths disagree");
        SpareVector c = new SpareVector(d);
        for (int i : this.st.keys())
            c.put(i, this.get(i));
        for (int i : that.st.keys())
            c.put(i, that.get(i) + c.get(i));
        return c;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i : st.keys())
            s.append("(" + i + ", " + st.get(i) + ") ");
        return s.toString();
    }

    public static void main(String[] args) {

        SpareVector a = new SpareVector(10);
        SpareVector b = new SpareVector(10);

        a.put(3, 0.55);
        a.put(9, 0.70);
        a.put(6, 0.15);
        a.put(6, 0.00);
        b.put(3, 0.65);
        b.put(4, 0.95);

        StdOut.println("a = " + a);
        StdOut.println("b = " + b);
        StdOut.println("a dot b = " + a.dot(b));
        StdOut.println("a + b   = " + a.plus(b));
    }


}
